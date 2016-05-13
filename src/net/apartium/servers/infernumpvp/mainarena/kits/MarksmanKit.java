package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class MarksmanKit extends Kit {

	public MarksmanKit() {
		super("Marksman", 0, "Run away or you will be sniped");

	}

	@Override
	public ItemStack helmet() {
		return new ItemStack(Material.IRON_HELMET);
	}

	@Override
	public ItemStack chestplate() {
		return new ItemStack(Material.IRON_CHESTPLATE);
	}

	@Override
	public ItemStack leggings() {
		return new ItemStack(Material.IRON_LEGGINGS);
	}

	@Override
	public ItemStack boots() {
		return new ItemStack(Material.IRON_BOOTS);
	}

	@Override
	public void onFill(Player p) {
		p.getInventory().addItem(ItemUtil.easy(Material.STONE_SWORD, "§cMarksman's Sword"));
		ItemStack bow = new ItemStack(Material.BOW);
		bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		p.getInventory().addItem(bow);
		p.getInventory().setItem(34, new ItemStack(Material.ARROW));
	}

	@Override
	public ItemStack icon() {
		return new ItemStack(Material.BOW);
	}
}
