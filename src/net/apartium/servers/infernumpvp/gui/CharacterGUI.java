package net.apartium.servers.infernumpvp.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.gui.icons.CoinsIcon;
import net.apartium.servers.infernumpvp.gui.icons.StatsIcons;
import net.apartium.servers.infernumpvp.utils.InvUtil;

public class CharacterGUI implements Listener {

	public static final Inventory of(Player p) {
		PlayerData pd = new PlayerData(p);
		Inventory ret = Bukkit.createInventory(null, 9 * 3, pd.getNick() + "§6 - Character");

		ret.setItem(11, new CoinsIcon(pd).item());
		ret.setItem(12, new StatsIcons.Kills(p).item());
		ret.setItem(13, new StatsIcons.Deaths(p).item());
		ret.setItem(14, new StatsIcons.KD(p).item());
		ret.setItem(15, new StatsIcons.KS(p).item());

		return InvUtil.fillInventory(ret, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("§6 - Character")) {
				e.setCancelled(true);
				e.getWhoClicked().sendMessage(e.getCurrentItem().getItemMeta().getDisplayName().replace(" §c", ": §c"));
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getItemInHand().getType() == Material.SKULL
				|| e.getPlayer().getItemInHand().getType() == Material.SKULL_ITEM)
			e.getPlayer().openInventory(of(e.getPlayer()));
	}
}
