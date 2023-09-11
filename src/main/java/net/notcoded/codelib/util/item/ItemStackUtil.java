package net.notcoded.codelib.util.item;

import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.ArrayList;
import java.util.Map;

public class ItemStackUtil {

    /**
     * Checks if the inventory has enough room to store an item.
     * @return If the inventory has room for the item.
     */
    public static boolean hasRoomFor(Inventory inventory, ItemStack itemStack) {
        int count = itemStack.getCount();

        for (ItemStack slot : inventory.items) {
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
            if (inventory.getItem(i).getItem() == item) return true;
        }
        return false;
    }


    /**
     * Checks if the inventory has the same item.
     * @return If the inventory has the same item.
     */
    public static boolean isSameItem(ItemStack itemStack1, ItemStack itemStack2) {
        if (itemStack1.getItem() != itemStack2.getItem()) return false;

        if (itemStack1.getTag() == null) {
            return itemStack2.getTag() == null;
        } else {
            return itemStack1.getTag().equals(itemStack2.getTag());
        }
    }

    /**
     * Turns a string into an ItemStack, e.g. diamond_sword.
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
     * @return ItemStack name.
     */
    public static String itemStackToString(ItemStack itemStack, boolean reversible) {
        if(reversible){
            return itemStack.getCount() + " " + itemStack.getDescriptionId().split("\\.")[2];
        }
        return itemStack.getDescriptionId();
    }

    /**
     * Gets all the ItemStacks in the player's inventory.
     * @return ItemStacks in ArrayList.
     */
    public static ArrayList<ItemStack> getInvItems(ServerPlayer player) {
        ArrayList<ItemStack> fullInv = new ArrayList<>();
        fullInv.addAll(player.inventory.items);
        fullInv.addAll(player.inventory.armor);
        fullInv.addAll(player.inventory.offhand);
        return fullInv;
    }

    /**
     * Makes the player's inventory refresh.
     */
    public static void sendInventoryRefreshPacket(ServerPlayer player) {
        NonNullList<ItemStack> i = NonNullList.create();
        for (int j = 0; j < player.containerMenu.slots.size(); ++j) {
            ItemStack itemStack = player.containerMenu.slots.get(j).getItem();
            i.add(itemStack.isEmpty() ? ItemStack.EMPTY : itemStack);
        }
        player.refreshContainer(player.containerMenu, i);
    }

    /**
     * Gets the item name, e.g. diamond_sword.
     * @return Item name.
     */
    public static Item itemFromString(String name) {
        name = name.toLowerCase();
        return Registry.ITEM.get(new ResourceLocation(name));
    }

    /**
     * Enchants an ItemStack with an enchantment and level.
     */
    public static void enchant(ItemStack itemStack, Enchantment enchantment, int level) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
        if (level > 0) enchantments.put(enchantment, level);
        else enchantments.remove(enchantment);
        EnchantmentHelper.setEnchantments(enchantments, itemStack);
    }

    /**
     * Gets the ItemStack in the player's container click
     * @return ItemStack.
     */
    public static ItemStack getContainerClickItem(ServerPlayer player, ServerboundContainerClickPacket packet) {
        int slot = packet.getSlotNum();

        if (slot >= 0 && slot < player.containerMenu.slots.size()) {
            return player.containerMenu.getSlot(slot).getItem();
        } else if (slot == -999) {
            return player.inventory.getCarried();
        } else {
            return null;
        }
    }
}
