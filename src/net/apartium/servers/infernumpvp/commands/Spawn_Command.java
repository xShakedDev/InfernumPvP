package net.apartium.servers.infernumpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class Spawn_Command implements CommandExecutor {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player p = (Player) arg0;
		p.teleport(new Location(m.spawnWorld, -485.500, 123.5, -98.5));

		ItemStack select = ItemUtil.easy(Material.CHEST, ChatColor.GRAY + "Kits");
		ItemStack shop = ItemUtil.easy(Material.ENDER_CHEST, ChatColor.GRAY + "Kit Shop");
		ItemStack character = ItemUtil.skull(ChatColor.RESET + p.getName(), ChatColor.RESET + p.getName());

		p.getInventory().clear();
		p.getActivePotionEffects().clear();

		p.setHealth(20);
		p.setFoodLevel(20);
		p.setGameMode(GameMode.SURVIVAL);

		p.getInventory().setItem(0, select);
		p.getInventory().setItem(2, shop);
		p.getInventory().setItem(8, character);

		return false;
	}

}
