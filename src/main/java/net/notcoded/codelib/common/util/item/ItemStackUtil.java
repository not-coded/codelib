package net.notcoded.codelib.common.util.item;

//? if >=1.19.4 {
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.Registries;
//? if <=1.18.2 {
/*import net.minecraft.util.registry.Registry;
*///?}

//?if <=1.20.1 {
/*import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import java.util.Map;
*///?}

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.notcoded.codelib.common.util.version.VersionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@SuppressWarnings("StringSplitter")
public class ItemStackUtil {

    /**
     * Checks if the inventory has enough room to store an item.
     * @return If the inventory has room for the item.
     */
    public static boolean hasRoomFor(PlayerInventory inventory, ItemStack itemStack) {
        int count = itemStack.getCount();

        for (ItemStack slot : inventory.main) {
            if (slot.getItem() == Items.AIR) {
                count -= 64;
            } else if (isSameItem(slot, itemStack)) {
                count -= 64 - slot.getCount();
            }
            if (count <= 0) return true;
        }
        return false;
    }

    /**
     * Checks if the inventory has the item.
     * @return If the inventory has the item.
     */
    public static boolean hasItem(Inventory inventory, Item item) {
        for (int i = 0; i < 41; i++) {
            if (inventory.getStack(i).getItem() == item) return true;
        }
        return false;
    }


    /**
     * Checks if the inventory has the same item.
     * @param itemStack2 The original ItemStack.
     * @param itemStack1 The ItemStack that's going to get compared.
     * @return If the inventory has the same item.
     */
    public static boolean isSameItem(ItemStack itemStack1, ItemStack itemStack2) {
        if (itemStack1.getItem() != itemStack2.getItem()) return false;

        //? if <=1.18.2 {
        /*if (itemStack1.getTag() == null) {
            return itemStack2.getTag() == null;
        } else {
            return itemStack1.getTag().equals(itemStack2.getTag());
        }
        *///?} else if 1.19.4 {
        //return itemStack1.isItemEqual(itemStack2);
        //?} elif >=1.20.1 <1.21 {
        //return ItemStack.areItemsEqual(itemStack1, itemStack2);
        //?} elif >=1.21 {
        return ItemStack.areItemsAndComponentsEqual(itemStack1, itemStack2);
        //?}
    }

    /**
     * Turns a string into an ItemStack, e.g. diamond_sword.
     * @param string Name, e.g. diamond_sword.
     * @return ItemStack.
     */
    public static ItemStack stringToItemStack(String string) {
        try {
            String[] itemStack = string.split(" ");
            String item = itemStack[itemStack.length-1];
            int amount = 1;
            if (itemStack.length > 1) amount = Integer.parseInt(itemStack[0]);

            return new ItemStack(itemFromString(item), amount);
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Gets the ItemStack name, e.g. iron_block.
     * @param itemStack ItemStack.
     * @param reversible If the String can be turned back into an ItemStack with stringToItemStack.
     * @return ItemStack name.
     */
    public static String itemStackToString(ItemStack itemStack, boolean reversible) {
        if(reversible){
            return itemStack.getCount() + " " + itemStack.getTranslationKey().split("\\.")[2];
        }
        return itemStack.getTranslationKey();
    }

    /**
     * Gets all the ItemStacks in the player's inventory.
     * @param player The player.
     * @return ItemStacks in a List.
     */
    public static List<ItemStack> getInvItems(PlayerEntity player) {
        List<ItemStack> fullInv = new ArrayList<>();
        PlayerInventory inventory;
        //? if >=1.19.4 {
        inventory = player.getInventory();
        //?} else if <=1.18.2 {
        /*inventory = player.inventory;
        *///?}


        fullInv.addAll(inventory.main);
        fullInv.addAll(inventory.armor);
        fullInv.addAll(inventory.offHand);
        return fullInv;
    }

    /**
     * Gets the item name, e.g. diamond_sword.
     * @param name The name.
     * @return The Item (nullable).
     */
    public static Item itemFromString(String name) {
        name = name.toLowerCase(Locale.ROOT);
        //? if <=1.18.2 {
        /*return Registry.ITEM.get(new Identifier(name));
        *///?} elif >=1.19.4 {
        return Registries.ITEM.get(VersionUtil.identifier(name));
    }

    /**
     * Enchants an ItemStack with an enchantment and level.
     * Setting the level to 0 removes the enchantment (if on the item).
     * @param itemStack ItemStack to enchant.
     * @param enchantment The enchantment.
     * @param level The level of the enchantment.
     */

    //? if <=1.20.1 {
    /*public static void enchant(ItemStack itemStack, Enchantment enchantment, int level) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(itemStack);
        if (level > 0) enchantments.put(enchantment, level);
        else enchantments.remove(enchantment);
        EnchantmentHelper.set(enchantments, itemStack);
    }
    *///?}

    /**
     * Gets the ItemStack in the player's container click
     * @param player The player.
     * @param packet The ClickSlotC2SPacket.
     * @return ItemStack.
     */
    public static ItemStack getContainerClickItem(PlayerEntity player, ClickSlotC2SPacket packet) {
        int slot = packet.getSlot();

        if (slot >= 0 && slot < player.currentScreenHandler.slots.size()) {
            return player.currentScreenHandler.getSlot(slot).getStack();
        } else if (slot == -999) {
            //? if <=1.18.2 {
            /*return player.inventory.getCursorStack();
            *///?} elif >=1.19.4 {
            return player.currentScreenHandler.getCursorStack();
            //?}
        } else {
            return null;
        }
    }
}
