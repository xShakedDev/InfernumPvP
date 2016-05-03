package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;

public class MarksmanKit extends Kit {

	public MarksmanKit() {
		super("Marksman", 0);

	}

	@Override
	public ItemStack helmet() {
		return new ItemStack(Material.LEATHER_HELMET);
	}

	@Override
	public ItemStack chestplate() {
		return new ItemStack(Material.LEATHER_CHESTPLATE);
	}

	@Override
	public ItemStack leggings() {
		return new ItemStack(Material.LEATHER_LEGGINGS);
	}

	@Override
	public ItemStack boots() {
		return new ItemStack(Material.LEATHER_BOOTS);
	}

	@Override
	public void onFill(Player p) {
		p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
		ItemStack bow = new ItemStack(Material.BOW);
		bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		p.getInventory().addItem(bow);
		p.getInventory().setItem(34, new ItemStack(Material.ARROW));
	}

	@Override
	public Material icon() {
		return Material.BOW;
	}

}
