package net.notcoded.codelib.common.util.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

public class ItemDisplayUtil {

    /**
     * Adds lore to an item.
     * @param itemStack ItemStack that should get the lore added onto.
     * @param string The lore message.
     * @param line The line.
     */
    public static void addLore(ItemStack itemStack, String string, int line) {
        //? if 1.16.5 {
        //NbtCompound compoundTag = itemStack.getOrCreateTag();
        //?} else if >=1.16.5 <=1.20.1 {
        //NbtCompound compoundTag = itemStack.getOrCreateNbt();
        //?} else if >=1.21 {
        NbtCompound compoundTag = null;

        //?}
        // TODO: pain in the ass to do

        NbtCompound display = compoundTag.getCompound("display");
        NbtList listTag = display.getList("Lore", 8);

        string = "{\"text\":\"" + string + "\"}";
        if (line < 0) {
            listTag.add(listTag.size() + line + 1, NbtString.of(string));
        } else {
            listTag.add(line, NbtString.of(string));
        }

        display.put("Lore", listTag);
        compoundTag.put("display", display);
        //? if 1.16.5 {
        //itemStack.setTag(compoundTag);
        //?} else if >=1.16.5 <=1.20.1 {
        //itemStack.setNbt(compoundTag);
        //?} else if >=1.21 {

        //?}
        // TODO: pain in the ass to do
    }

    /**
     * Removes lore from an item.
     * @param itemStack ItemStack that should get the lore removed.
     * @param line The line that should get removed.
     */

    public static void removeLore(ItemStack itemStack, int line) {
        //? if 1.16.5 {
        //NbtCompound compoundTag = itemStack.getOrCreateTag();
        //?} else if >=1.16.5 <=1.20.1 {
        //NbtCompound compoundTag = itemStack.getOrCreateNbt();
        //?} else if >=1.21 {
        NbtCompound compoundTag = null;

        //?}
        // TODO: pain in the ass to do
        NbtCompound display = compoundTag.getCompound("display");
        NbtList listTag = display.getList("Lore", 8);

        if (line < 0) {
            if (!listTag.isEmpty()) listTag.remove(listTag.size() - 1);
        } else {
            if (listTag.size() > line) {
                listTag.remove(line);
            }
        }

        if (!listTag.isEmpty()) display.put("Lore", listTag);
        else display.remove("Lore");

        if (!display.getKeys().isEmpty()) compoundTag.put("display", display);
        else compoundTag.remove("display");

        //? if 1.16.5 {
        //itemStack.setTag(compoundTag);
        //?} else if >=1.16.5 <=1.20.1 {
        //itemStack.setNbt(compoundTag);
        //?} else if >=1.21 {

        //?}
        // TODO: pain in the ass to do
    }

    /**
     * Adds enchantment glint to an item.
     * @param itemStack The ItemStack that should get the glint.
     * @return Tħe ItemStack with glint added.
     */
    public static ItemStack addGlint(ItemStack itemStack) {
        if (!itemStack.hasEnchantments()) {
            NbtList listTag = new NbtList();
            listTag.add(new NbtCompound());
            //? if 1.16.5 {
            //itemStack.getOrCreateTag().put("Enchantments", listTag);
            //?} else if >=1.16.5 <=1.20.1 {
            //itemStack.getOrCreateNbt().put("Enchantments", listTag);
            //?} else if >=1.21 {

            //?}
            // TODO: pain in the ass to do
        }
        return itemStack;
    }
}
