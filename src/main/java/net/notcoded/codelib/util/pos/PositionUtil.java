package net.notcoded.codelib.util.pos;

import net.minecraft.core.BlockPos;
public class PositionUtil {

    /**
     * Checks if the 2 block positions are in between one block position.
     * @param corner1 Corner 1.
     * @param corner2 Corner 2.
     * @param between Position that in between.
     * @return If the block position is in between those 2.
     */
    public static boolean isBetween(BlockPos corner1, BlockPos corner2, BlockPos between) {
        return corner1.getX() <= between.getX() && corner2.getX() >= between.getX() &&
                corner1.getY() <= between.getY() && corner2.getY() >= between.getY() &&
                corner1.getZ() <= between.getZ() && corner2.getZ() >= between.getZ();
    }
}
