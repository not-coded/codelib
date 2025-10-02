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
            clazz = Thread.currentThread().getContextClassLoader().loadClass(mixinClassName);
        } catch (ClassNotFoundException e) {
            return true; // no idea what happened
        }

        List<String> minecraftVersions = new ArrayList<>();
        boolean enforceAllPredicates = true;

        MinecraftVersion minecraftVersion = clazz.getAnnotation(MinecraftVersion.class);
        if (minecraftVersion != null) {
            minecraftVersions = Arrays.asList(minecraftVersion.minecraft());
            enforceAllPredicates = minecraftVersion.enforceAll();
        }

        if (minecraftVersions.isEmpty()) return true;

        Version gameVersion = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion(); // 1.21
        Set<VersionPredicate> versions;

        try {
            versions = VersionPredicateParser.parse(minecraftVersions);
        } catch (VersionParsingException e) {
            throw new RuntimeException(e);
        }

        return testVersions(versions, gameVersion, enforceAllPredicates);
    }

    public boolean testVersions(Set<VersionPredicate> versions, Version gameVersion, boolean forceAll) {
        for(VersionPredicate minecraftVersion : versions) {
            boolean matches = minecraftVersion.test(gameVersion);
            if (matches != forceAll) return matches;
        }

        return forceAll;
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
