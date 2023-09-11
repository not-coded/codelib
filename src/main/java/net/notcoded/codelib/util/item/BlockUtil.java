package net.notcoded.codelib.util.item;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockUtil {

    /**
     * Gets the block name, e.g. iron_block.
     * @param blockState Block State.
     * @return Block name.
     */
    public static String blockToText(BlockState blockState) {
        return blockToText(blockState.getBlock());
    }

    /**
     * Gets the block name, e.g. iron_block
     * @param block Block.
     * @return Block name.
     */
    public static String blockToText(Block block) {
        String[] splitRegistryKey = Registry.BLOCK.getKey(block).toString().split(":");
        if (splitRegistryKey.length < 2) return splitRegistryKey[0];
        return splitRegistryKey[1];
    }

}
