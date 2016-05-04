package net.apartium.servers.infernumpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

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

		p.getInventory().clear();
		for (PotionEffect pe : p.getActivePotionEffects())
			p.removePotionEffect(pe.getType());
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setGameMode(GameMode.SURVIVAL);

		p.getInventory().setItem(0, select);
		p.getInventory().setItem(2, shop);
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setOwner(p.getName());
		is.setItemMeta(im);
		p.getInventory().addItem(ItemUtil.easy(Material.BLAZE_ROD, "§61V1 Stick"));
		p.getInventory().setItem(8, is);

		return false;
	}

}
