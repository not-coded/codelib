package net.notcoded.codelib.common.mixinhelper;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.fabricmc.loader.impl.util.version.VersionPredicateParser;
import net.notcoded.codelib.common.mixinhelper.annotation.MinecraftVersion;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    @SuppressWarnings("IdentityBinaryExpression")
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        Class<?> clazz;
        try {
            clazz = ClassLoader.getSystemClassLoader().loadClass(mixinClassName);
        } catch (ClassNotFoundException e) {
            return true; // no idea what happened
        }

        List<String> minecraftVersions = new ArrayList<>();

        for(Annotation annotation : clazz.getAnnotations()) { // this is cursed but it somehow works
            if(annotation.annotationType().toGenericString().equals(MinecraftVersion.class.toGenericString())) {
                try {
                    minecraftVersions = Arrays.asList((String[]) annotation.annotationType().getMethod("minecraft").invoke(annotation));
                    break;
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("Failed to get the value of MinecraftVersion annotation!");
                }
            }
        }

        if(minecraftVersions.isEmpty()) return true;

        Version gameVersion = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion(); // 1.21
        Set<VersionPredicate> versions;

        try {
            versions = VersionPredicateParser.parse(minecraftVersions);
        } catch (VersionParsingException e) {
            throw new RuntimeException(e);
        }

        if(minecraftVersions.size() == 2) {
            String mcVer1 = minecraftVersions.get(0);
            String mcVer2 = minecraftVersions.get(1);

            Iterator<VersionPredicate> iterator = versions.iterator();
            // NOTE: "@MinecraftVersion(minecraft = {">=1.21", "<=1.21.5"})" would be correct syntax for this (example).
            if(mcVer1.contains(">") && mcVer2.contains("<")) {
                return iterator.next().test(gameVersion) && iterator.next().test(gameVersion);
            }
        }

        for(VersionPredicate minecraftVersion : versions) {
            if (minecraftVersion.test(gameVersion)) return true;
        }

        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}