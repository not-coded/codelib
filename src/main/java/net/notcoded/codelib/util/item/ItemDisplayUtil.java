package net.notcoded.codelib.util.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.item.ItemStack;

public class ItemDisplayUtil {

    /**
     * Adds lore to an item.
     * @param itemStack ItemStack that should get the lore added onto.
     * @param string The lore message.
     * @param line The line.
     */
    public static void addLore(ItemStack itemStack, String string, int line) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        CompoundTag display = compoundTag.getCompound("display");
        ListTag listTag = display.getList("Lore", 8);

        string = "{\"text\":\"" + string + "\"}";
        if (line < 0) {
            listTag.add(listTag.size() + line + 1, StringTag.valueOf(string));
        } else {
            listTag.add(line, StringTag.valueOf(string));
        }

        display.put("Lore", listTag);
        compoundTag.put("display", display);
        itemStack.setTag(compoundTag);
    }

    /**
     * Removes lore from an item.
     * @param itemStack ItemStack that should get the lore removed.
     * @param line The line that should get removed.
     */

    public static void removeLore(ItemStack itemStack, int line) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        CompoundTag display = compoundTag.getCompound("display");
        ListTag listTag = display.getList("Lore", 8);

        if (line < 0) {
            if (!listTag.isEmpty()) listTag.remove(listTag.size() - 1);
        } else {
            if (listTag.size() > line) {
                listTag.remove(line);
            }
        }

        if (!listTag.isEmpty()) display.put("Lore", listTag);
        else display.remove("Lore");

        if (!display.getAllKeys().isEmpty()) compoundTag.put("display", display);
        else compoundTag.remove("display");

        itemStack.setTag(compoundTag);
    }

    /**
     * Adds enchantment glint to an item.
     * @param itemStack The ItemStack that should get the glint.
     */
    public static void addGlint(ItemStack itemStack) {
        try {
            if (!itemStack.isEnchanted()) {
                ListTag listTag = new ListTag();
                listTag.add(new CompoundTag());
                itemStack.getOrCreateTag().put("Enchantments", listTag);
            }
        } catch (Exception ignored) {}
    }

}
