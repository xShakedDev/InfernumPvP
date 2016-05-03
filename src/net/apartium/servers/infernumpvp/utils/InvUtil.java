package net.apartium.servers.infernumpvp.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvUtil {

	public static Inventory fillInventory(Inventory inv, ItemStack fill) {
		for (int i = 0; i < inv.getSize(); i++)
			if (inv.getItem(i) == null)
				inv.setItem(i, fill);
		return inv;
	}
}
