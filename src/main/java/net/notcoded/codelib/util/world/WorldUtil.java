package net.notcoded.codelib.util.world;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WorldUtil {
    /**
     * Gets the world name of a level.
     * @param level The level.
     * @return The name of the world, e.g. minecraft:overworld
     */
    public static String getWorldName(@NotNull Level level) {
        return level.dimension().toString().replaceAll("dimension / ", "").replaceAll("]", "").replaceAll("ResourceKey\\[minecraft:", "");
    }

    /**
     * Splits the world name.
     * @param name The name
     * @return ResourceLocation, e.g. minecraft (namespace) and overworld (path)
     */
    public static ResourceLocation getWorldName(String name) {
        String[] splitName = name.split(":");
        return new ResourceLocation(splitName[0], splitName[1]);
    }

}