package net.apartium.servers.infernumpvp.mainarena;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.mainarena.kits.FisherManKit;
import net.apartium.servers.infernumpvp.mainarena.kits.HunterKit;
import net.apartium.servers.infernumpvp.mainarena.kits.MarksmanKit;
import net.apartium.servers.infernumpvp.mainarena.kits.PyroKit;
import net.apartium.servers.infernumpvp.mainarena.kits.SwitcherKit;
import net.apartium.servers.infernumpvp.mainarena.kits.TerroristKit;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public abstract class Kit {

	public static final Kit HUNTER = new HunterKit(), FISHER = new FisherManKit(), SWITCHER = new SwitcherKit(),
			MARKSMAN = new MarksmanKit(), TERRORIST = new TerroristKit(), PYRO = new PyroKit();
	public static List<Kit> kits = Arrays.asList(HUNTER, MARKSMAN, TERRORIST, FISHER, SWITCHER, PYRO);
	private static final InfernumPvP m = InfernumPvP.getInstance();

	private String name;
	private int price;

	public Kit(String name, int price) {
		this.name = name;
		this.price = price;

	}

	public abstract ItemStack helmet();

	public abstract ItemStack chestplate();

	public abstract ItemStack leggings();

	public abstract ItemStack boots();

	public abstract Material icon();

	public void onFill(Player p) {
	}

	public final String name() {
		return name;
	}

	public final int cost() {

		return price;
	}

	public static void give(Kit k, Player p) {
		PlayerData pp = new PlayerData(p);
		if (!pp.hasKit(k))
			return;
		p.getInventory().clear();
		p.getInventory().getItemInHand().setType(Material.AIR);
		p.getActivePotionEffects().clear();
		p.sendMessage(m.ARENA + ChatBuilder.build("You got kit", k.name()));
		p.getInventory().setArmorContents(new ItemStack[] { k.boots(), k.leggings(), k.chestplate(), k.helmet() });
		k.onFill(p);
		InfernumPvP.getInstance().giveSoup(p);
	}

	public static String getKits() {
		StringBuilder sb = new StringBuilder();
		for (Kit k : kits)
			sb.append(k.name);
		return sb.toString();
	}

	public static Kit byName(String name) {
		for (Kit k : kits) {
			if (k.name().equalsIgnoreCase(name))
				return k;
		}
		return null;
	}

	public static Kit byItem(Material m) {
		for (Kit k : kits)
			if (k.icon() == m)
				return k;
		return null;
	}

	public static boolean isKit(String string) {
		if (byName(string) == null) {
			return false;
		} else {
			return true;
		}
	}

}
