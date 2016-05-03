package net.apartium.servers.infernumpvp.mainarena.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.apartium.servers.infernumpvp.mainarena.Kit;

public class TerroristKit extends Kit {

	public TerroristKit() {
		super("Terrorist", 200);

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
		p.getInventory().addItem(new ItemStack(Material.EGG, 16));
	}

	@Override
	public Material icon() {
		return Material.EGG;
	}

	@Override
	public ArrayList<PotionEffect> pots() {
		return null;
	}
}
