package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;

public class PyroKit extends Kit {
	public PyroKit() {
		super("Pyro", 250);
	}

	@Override
	public ItemStack helmet() {
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return helmet;
	}

	@Override
	public ItemStack chestplate() {
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
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
		ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
		boot.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return boot;
	}

	@Override
	public void onFill(Player p) {
		p.getInventory().addItem(new ItemStack(Material.GOLD_SWORD));
		p.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
	}

	@Override
	public ItemStack icon() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}
}
