package net.apartium.servers.infernumpvp.mainarena;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.mainarena.kits.CheetahKit;
import net.apartium.servers.infernumpvp.mainarena.kits.FisherManKit;
import net.apartium.servers.infernumpvp.mainarena.kits.HunterKit;
import net.apartium.servers.infernumpvp.mainarena.kits.MarksmanKit;
import net.apartium.servers.infernumpvp.mainarena.kits.PyroKit;
import net.apartium.servers.infernumpvp.mainarena.kits.StomperKit;
import net.apartium.servers.infernumpvp.mainarena.kits.SwitcherKit;
import net.apartium.servers.infernumpvp.mainarena.kits.TerroristKit;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public abstract class Kit {

	public static final Kit HUNTER = new HunterKit(), FISHER = new FisherManKit(), SWITCHER = new SwitcherKit(),
			MARKSMAN = new MarksmanKit(), TERRORIST = new TerroristKit(), PYRO = new PyroKit(),
			STOMPER = new StomperKit(), CHEETAH = new CheetahKit();
	public static List<Kit> kits = Arrays.asList(HUNTER, MARKSMAN, TERRORIST, FISHER, SWITCHER, PYRO, CHEETAH, STOMPER);
	private static final InfernumPvP m = InfernumPvP.getInstance();

	private String name;
	private String desc;
	private int price;

	public Kit(String name, int price, String desc) {
		this.name = name;
		this.price = price;
		this.desc = desc;

	}

	public abstract ItemStack helmet();

	public abstract ItemStack chestplate();

	public abstract ItemStack leggings();

	public abstract ItemStack boots();

	public abstract ItemStack icon();

	public void onFill(Player p) {
	}

	public final String name() {
		return name;
	}

	public final String desc() {
		return desc;
	}

	public final int cost() {

		return price;
	}

	@SuppressWarnings("deprecation")
	public static void give(Kit k, Player p) {
		PlayerData pp = new PlayerData(p);
		if (!pp.hasKit(k)) {
			p.sendMessage(m.KIT_SHOP + ChatBuilder.build("You dont have the kit", k.name()));
			return;
		}
		p.getInventory().clear();
		p.getInventory().getItemInHand().setType(Material.AIR);
		for (PotionEffect pe : p.getActivePotionEffects())
			p.removePotionEffect(pe.getType());
		p.sendMessage(m.ARENA + ChatBuilder.build("You got kit", k.name()));
		p.getInventory().setArmorContents(new ItemStack[] { k.boots(), k.leggings(), k.chestplate(), k.helmet() });
		k.onFill(p);
		InfernumPvP.getInstance().giveSoup(p);

		int maxX = Math.max(-40, -68);
		int maxZ = Math.max(30, 55);
		int minX = Math.min(-40, -68);
		int minZ = Math.min(30, 55);
		float factor = (float) Math.random();
		int x = (int) (minX + (maxX - minX) * factor);
		factor = (float) Math.random();
		int z = (int) (minZ + (maxZ - minZ) * factor);
		int y = Bukkit.getWorld("Main2").getHighestBlockAt(x, z).getY();
		p.teleport(new Location(Bukkit.getWorld("Main2"), x, y, z));
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
			if (k.icon().getType() == m)
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
