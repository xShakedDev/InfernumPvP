	package net.apartium.servers.infernumpvp.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.regions.InventoryFlags;
import net.apartium.servers.infernumpvp.regions.Region;
import net.apartium.servers.infernumpvp.regions.Region.Flag_List;
import net.apartium.servers.infernumpvp.regions.RegionListener;

public class Region_Command implements CommandExecutor {
	private InfernumPvP plugin=InfernumPvP.getInstance();

	public Region_Command() {}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		PlayerData pd = new PlayerData(p);
		
		if ((label.equalsIgnoreCase("rg") || label.equalsIgnoreCase("region"))
				&&pd.hasPermission("infernumpvp.regions", true)) {
			
			if (args.length == 0) {
				p.sendMessage(plugin.REGIONS + ChatColor.RED + "Usage: /rg *[Action] -[Args]");
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					p.sendMessage(plugin.REGIONS+plugin.rgFiles.keySet().toString().
							replace(plugin.rgFiles.keySet().toString(), "§c" + 
					plugin.rgFiles.keySet().toString() + "§7, "));
				}
			} else if (args.length == 2) {

				if (args[0].equalsIgnoreCase("create")) {

					WorldEditPlugin worldedit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

					Selection selection = worldedit.getSelection(p);

					if (selection != null) 
						Region.createNew(p,args[1],selection);
					else 
						p.sendMessage(plugin.REGIONS + ChatColor.RED + "You first had to select a area with worldedit");
					
				} else if(args[0].equalsIgnoreCase("check")) {
					if(RegionListener.playerInRegion(p.getLocation())) {
						p.sendMessage(RegionListener.getLocationRegion(p.getLocation()).getName());
					} else {
						p.sendMessage("none");
					}
				}
				if (args[0].equalsIgnoreCase("remove")) {
					Region rgm = Region.byName(args[1]);
					rgm.remove(p);
				}
				if (args[0].equalsIgnoreCase("flag")) {
					Region rgm = Region.byName(args[1]);
					if (rgm!=null) {
						InventoryFlags.setupInventory(args[1]);
						p.openInventory(InventoryFlags.inv);
					} else {
						p.sendMessage(plugin.REGIONS + "Region doesn't Exists");
					}
				}
				if (args[0].equalsIgnoreCase("info")) {
					Region rgm = Region.byName(args[1]);
					if (rgm.exists()) {
						File rg_file = plugin.rgFiles.get(args[1]);
						FileConfiguration rg_cfg = YamlConfiguration.loadConfiguration(rg_file);
						if (rg_cfg.contains("location")) {
							p.sendMessage(plugin.REGIONS + "§e" + args[1] + "info" + ": §3MaxBlockX: §8" + rg_cfg.getInt("location.toX")+
									"§3MinBlockX: §8" + rg_cfg.getInt("location.fromX" + 
									"§3MinBlockZ: §8" + rg_cfg.getInt("location.fromZ") +
									"§3MaxBlockZ: §8" + rg_cfg.getInt("location.toZ")+
									"§3MaxBlockZ: §8" + rg_cfg.getInt("location.toZ")+
									"§3World: §8" + rg_cfg.getString("location.world")+
									"§3BlockedCMDs: §8" + rgm.getFlagsList(Flag_List.BLOCKED_CMD)+
									"§3Creator: §8" + rg_cfg.getString("creator")));

						} else {
							p.sendMessage(plugin.REGIONS + "§4This is global region for undefined lands");
						}
					} else {
						p.sendMessage(plugin.REGIONS + "Region doesn't Exists");
					}
				}
			}
		}
		return false;
	}
}
