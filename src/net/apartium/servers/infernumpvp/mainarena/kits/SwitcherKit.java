package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;

public class SwitcherKit extends Kit {
	public SwitcherKit() {
		super("Switcher", 225);
	}

	@Override
	public ItemStack helmet() {
		ItemStack helmet = new ItemStack(Material.LEATHER_LEGGINGS);
		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return helmet;
	}

	@Override
	public ItemStack chestplate() {
		ItemStack chest = new ItemStack(Material.LEATHER_LEGGINGS);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return chest;
	}

	@Override
	public ItemStack leggings() {
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return leg;
	}

	@Override
	public ItemStack boots() {
		ItemStack boot = new ItemStack(Material.LEATHER_LEGGINGS);
		boot.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return boot;
	}

	@Override
	public void onFill(Player p) {
		p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
		p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 16));
	}

	@Override
	public Material icon() {
		return Material.SNOW_BALL;
	}
}
