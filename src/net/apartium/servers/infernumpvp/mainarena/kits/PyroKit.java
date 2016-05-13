package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.apartium.servers.infernumpvp.mainarena.Kit;

public class PyroKit extends Kit {
	public PyroKit() {
		super("Pyro", 250, "Burn bitch BURN!");
	}

	@Override
	public ItemStack helmet() {
		ItemStack helmet = new ItemStack(Material.GOLD_HELMET);
		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return helmet;
	}

	@Override
	public ItemStack chestplate() {
		ItemStack chest = new ItemStack(Material.GOLD_CHESTPLATE);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return chest;
	}

	@Override
	public ItemStack leggings() {
		ItemStack leg = new ItemStack(Material.GOLD_LEGGINGS);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return leg;
	}

	@Override
	public ItemStack boots() {
		ItemStack boot = new ItemStack(Material.GOLD_BOOTS);
		boot.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 20);
		return boot;
	}

	@Override
	public void onFill(Player p) {
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		ItemMeta swordm = sword.getItemMeta();
		swordm.setDisplayName("§cPyro's Sword");
		sword.setItemMeta(swordm);
		p.getInventory().addItem(sword);
	}

	@Override
	public ItemStack icon() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}
}
