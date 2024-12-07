package net.notcoded.codelib.common.util.item;

//? if <=1.18.2 {
/*import net.minecraft.util.registry.Registry;
*///?} else if >=1.19.4 {
import net.minecraft.registry.Registries;
//?}

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

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
    @SuppressWarnings("StringSplitter")
    public static String blockToText(Block block) {
        //? if <=1.18.2 {
        /*String[] splitRegistryKey = Registry.BLOCK.getKey(block).toString().split(":");
        *///?} else if >=1.19.4 {
        String[] splitRegistryKey = Registries.BLOCK.getKey(block).toString().split(":");
        //?}

        if (splitRegistryKey.length < 2) return splitRegistryKey[0];
        return splitRegistryKey[1];
    }

}
