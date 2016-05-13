package net.apartium.servers.infernumpvp.mainarena.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.apartium.servers.infernumpvp.mainarena.Kit;

public class HunterKit extends Kit {
	public HunterKit() {
		super("Hunter",0, "Lets hunt some kiddos");
	}

	@Override
	public ItemStack helmet() {
		return new ItemStack(Material.DIAMOND_HELMET);
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
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta swordm = sword.getItemMeta();
		swordm.setDisplayName("§cHunter's Sword");
		sword.setItemMeta(swordm);
		p.getInventory().addItem(sword);
	}

	@Override
	public ItemStack icon() {
		return new ItemStack(Material.IRON_SWORD);
	}
}
