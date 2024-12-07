package net.notcoded.codelib.common.util.world;

import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class WorldUtil {
    /**
     * Gets the world name of a level.
     * @param world The world.
     * @return The name of the world, e.g. <STRONG>minecraft</STRONG> (namespace) and <STRONG>overworld</STRONG> (path)
     */
    public static Identifier getWorldName(@NotNull World world) {
        return world.getRegistryKey().getValue();
    }

    /**
     * Gets the world name of a level as a string.
     * @param world The world.
     * @return The name of the world, e.g. minecraft:overworld
     */
    public static String getStringWorldName(@NotNull World world) {
        return getWorldName(world).toString();
    }
}