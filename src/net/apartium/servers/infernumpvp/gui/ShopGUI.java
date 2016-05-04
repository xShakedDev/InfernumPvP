package net.apartium.servers.infernumpvp.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.gui.icons.CoinsIcon;
import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;
import net.apartium.servers.infernumpvp.utils.InvUtil;

public class ShopGUI {
	private static InfernumPvP m = InfernumPvP.getInstance();
	public ItemStack kit;
	public ItemMeta kitm;
	public Inventory i;

	public ShopGUI(Player p) {
		PlayerData pp = new PlayerData(p);
		i = Bukkit.createInventory(null, 9 * 4, "Shop Menu");
		int count = 10;
		for (Kit k : Kit.kits) {
			if (k.cost() != 0) {
				kit = new ItemStack(k.icon());
				kitm = kit.getItemMeta();
				kitm.setDisplayName(pp.hasKit(k) ? "§c" + k.name() : "§a" + k.name());
				List<String> lore = new ArrayList<>();
				lore.clear();
				lore.add(pp.hasKit(k) ? "§cUnlocked" : "§aKit price: " + k.cost());
				kitm.setLore(lore);
				kit.setItemMeta(kitm);
				i.setItem(count, kit);
				count++;
				if (((count - 1) % 9) == 0)
					count = +2;
			}
		}
		i.setItem(35, new CoinsIcon(pp).item());
		InvUtil.fillInventory(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
	}

	public Inventory getResult() {
		return i;
	}

	public static Listener events = new Listener() {

		@SuppressWarnings("deprecation")
		@EventHandler
		public void onClickShopItem(PlayerInteractEvent e) {
			if (e.getPlayer().getItemInHand() == null)
				return;
			if (e.getPlayer().getItemInHand().getType() == Material.ENDER_CHEST) {
				if (e.getPlayer().getItemInHand().hasItemMeta()) {
					e.getPlayer().openInventory(new ShopGUI(e.getPlayer()).getResult());
				}
			}
		}

		@EventHandler
		public void onClick(InventoryClickEvent e) {
			Player p = (Player) e.getWhoClicked();
			PlayerData pp = new PlayerData(p);
			if (e.getInventory().getName() == null)
				return;
			if (e.getInventory().getName().equalsIgnoreCase("Shop Menu")) {
				if (e.getClickedInventory() == null)
					return;
				if (e.getCurrentItem() == null)
					return;
				if (!e.getCurrentItem().hasItemMeta())
					return;
				e.setCancelled(true);
				p.closeInventory();
				if (Kit.isKit(Kit.byItem(e.getCurrentItem().getType()).name())) {
					if (e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE
							|| e.getCurrentItem().getType() != Material.GOLD_INGOT) {
						if (!pp.hasKit(Kit.byItem(e.getCurrentItem().getType()))) {
							pp.buyKit(Kit.byItem(e.getCurrentItem().getType()));
						} else {
							p.sendMessage(m.KIT_SHOP + ChatBuilder.build("Already got the kit",
									Kit.byItem(e.getCurrentItem().getType()).name()));
						}
					} else
						e.setCancelled(true);
				}
			}
		}
	};
}