package net.apartium.servers.infernumpvp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtil {

	public static ItemStack easy(Material m, String name) {
		ItemStack is = new ItemStack(m);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(name);
		is.setItemMeta(im);

		return is;
	}

	public static ItemStack skull(String name, String player) {
		ItemStack ret = new ItemStack(Material.SKULL_ITEM, 1);
		SkullMeta sm = (SkullMeta) ret.getItemMeta();
		sm.setOwner(player);
		sm.setDisplayName(name);

		ret.setItemMeta(sm);
		return ret;
	}
}
