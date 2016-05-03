package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;

public class FisherManKit extends Kit {
	public FisherManKit() {
		super("FisherMan",175);
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
		p.getInventory().addItem(new ItemStack(Material.GOLD_SWORD));
		p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
	}

	@Override
	public Material icon() {
		return Material.FISHING_ROD;
	}
}
