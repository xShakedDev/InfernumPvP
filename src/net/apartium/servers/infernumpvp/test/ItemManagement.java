package net.apartium.servers.infernumpvp.test;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.NBTTagList;

@SuppressWarnings("deprecation")
public class ItemManagement {
	//Reminder: This Version of ItemManagment is OfirTIM Changes of Item Manage.... DONT SHARE IT PLEASE!
	
	public static ItemStack createItem(Material material, int amount, short shrt, String displayname, String... lore) {
	    ItemStack item = new ItemStack(material, amount, shrt);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(displayname);
	    meta.setLore(Arrays.asList(lore));
	    item.setItemMeta(meta);
	    return item;
	}
	public static ItemStack createItem(Material material, int amount, short shrt, String displayname) {
		ItemStack item = new ItemStack(material, amount, shrt);
		ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(displayname);
	    item.setItemMeta(meta);
	    return item;
	}
	public static ItemStack createItem(Material material, int amount, short shrt) {
	    ItemStack item = new ItemStack(material, amount, shrt);
	    ItemMeta meta = item.getItemMeta();
	    item.setItemMeta(meta);
	    return item;
	}
	public static ItemStack createItem(Material material, int amount, String displayname) {
	    ItemStack item = new ItemStack(material, amount);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(displayname);
	    item.setItemMeta(meta);
	    return item;
	}
	
	public static ItemStack createItem(int type, int amount, short shrt, String displayname, String... lore) {
		ItemStack item = new ItemStack(type, amount, shrt);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(displayname);
	    meta.setLore(Arrays.asList(lore));
	    item.setItemMeta(meta);
	    return item;
	}
	
	public static ItemStack createItem(Material material, int amount, String displayname, String... lore) {
	    ItemStack item = new ItemStack(material, amount);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(displayname);
	    meta.setLore(Arrays.asList(lore));
	    item.setItemMeta(meta);
	    return item;
	}
	
    public static ItemStack addGlow(ItemStack item) {
        net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
}
