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
import net.apartium.servers.infernumpvp.gui.icons.CloseIcon;
import net.apartium.servers.infernumpvp.gui.icons.CoinsIcon;
import net.apartium.servers.infernumpvp.utils.InvUtil;

public class CharacterGUI implements Listener{
	
	public static final Inventory of(Player p) {
		PlayerData pd = new PlayerData(p);
		Inventory ret = Bukkit.createInventory(null, 9*3, pd.getNick()+"§6 - Character");
		
		ret.setItem(11, new CoinsIcon(pd).item());
		ret.setItem(17, new CloseIcon().item());

		
		return InvUtil.fillInventory(ret, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14));
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory().getName().endsWith("§6 - Character")) {
			if(e.getCursor().getType()==new CloseIcon().material())
				e.getWhoClicked().closeInventory();
			else
				e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getPlayer().getItemOnCursor().getType()==Material.SKULL||
				e.getPlayer().getItemOnCursor().getType()==Material.SKULL_ITEM)
			e.getPlayer().openInventory(of(e.getPlayer()));
	}
}
