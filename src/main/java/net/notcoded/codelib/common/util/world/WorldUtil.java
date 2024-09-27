package net.notcoded.codelib.common.util.world;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WorldUtil {
    /**
     * Gets the world name of a level.
     * @param level The level.
     * @return The name of the world, e.g. <STRONG>minecraft</STRONG> (namespace) and <STRONG>overworld</STRONG> (path)
     */
    public static ResourceLocation getWorldName(@NotNull Level level) {
        return level.dimension().location();
    }

    /**
     * Gets the world name of a level as a string.
     * @param level The level.
     * @return The name of the world, e.g. minecraft:overworld
     */
    public static String getStringWorldName(@NotNull Level level) {
        return level.dimension().location().toString();
    }
}