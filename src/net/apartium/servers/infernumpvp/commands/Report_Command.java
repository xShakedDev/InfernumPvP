package net.apartium.servers.infernumpvp.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.AnvilGUI;
import net.apartium.servers.infernumpvp.utils.AnvilGUI.AnvilClickEvent;
import net.apartium.servers.infernumpvp.utils.AnvilGUI.AnvilClickEventHandler;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class Report_Command implements CommandExecutor {
	private static InfernumPvP m = InfernumPvP.getInstance();
	private Map<String, String> map = new HashMap<>();
	
	public void sendReport(String msg) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission("infernumpvp.admin.reports"))
				p.sendMessage(msg);

		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (label.equalsIgnoreCase("report")) {
				AnvilGUI report = new AnvilGUI(p, new AnvilClickEventHandler() {

					@Override
					public void onAnvilClick(AnvilClickEvent event) {
						sendReport(m.ADMINISTRATION
								+ ChatBuilder.build("The player", p.getName(), "reported the player",
										map.get(p.getName()), "the report was:", event.getName()));
						
					}
					
				});
				AnvilGUI name = new AnvilGUI(p, new AnvilClickEventHandler() {

					@Override
					public void onAnvilClick(AnvilClickEvent event) {
						map.put(p.getName(), event.getName());
						event.setWillClose(true);
						event.setWillDestroy(true);
						p.closeInventory();
						ItemStack tag = ItemUtil.easy(Material.NAME_TAG, "The report");
						report.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, tag);
						new BukkitRunnable() {

							@Override
							public void run() {
								report.open();
							}
						}.runTaskLater(m, 1);
					}
				});
				ItemStack tag = ItemUtil.easy(Material.NAME_TAG, "Player name");
				name.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, tag);
				name.open();
			}
		}
		return false;
	}
}
