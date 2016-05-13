package net.apartium.servers.infernumpvp;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import net.apartium.servers.infernumpvp.mainarena.Kit;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;
import net.apartium.servers.infernumpvp.utils.ItemUtil;

public class PlayerData {

	private static InfernumPvP m = InfernumPvP.getInstance();

	public Player bukkitPlayer;
	public String name;
	public String uuid;

	public PlayerData(Player p) {
		this.bukkitPlayer = Bukkit.getPlayer(p.getName());
		this.name = p.getName();
		this.uuid = p.getUniqueId().toString();
	}

	public PlayerData(UUID id) {
		this.bukkitPlayer = Bukkit.getPlayer(id);
		this.name = bukkitPlayer.getName();
		this.uuid = bukkitPlayer.getUniqueId().toString();
	}

	public int getHighestKS() {
		if (m.pdc.get("PlayerData." + uuid + ".stats.highks") != null) {
			if (m.pdc.getInt("PlayerData." + uuid + ".stats.highks") < 0) {
				setKills(0);
				return 0;
			} else {
				return m.pdc.getInt("PlayerData." + uuid + ".stats.highks");
			}
		} else {
			return 0;
		}
	}

	public void setHighKS(int amount) {
		m.pdc.set("PlayerData." + uuid + ".stats.highks", amount);
		savePData();
	}

	public double getKills() {
		if (m.pdc.get("PlayerData." + uuid + ".stats.kills") != null) {
			if (m.pdc.getInt("PlayerData." + uuid + ".stats.kills") < 0) {
				setKills(0.0);
				return 0.0;
			} else {
				return m.pdc.getDouble("PlayerData." + uuid + ".stats.kills");
			}
		} else {
			return 0.0;
		}
	}

	public void setKills(double amount) {
		m.pdc.set("PlayerData." + uuid + ".stats.kills", amount);
		savePData();
	}

	public double getDeaths() {
		if (m.pdc.get("PlayerData." + uuid + ".stats.deaths") != null) {
			if (m.pdc.getInt("PlayerData." + uuid + ".stats.deaths") < 0.0) {
				setDeaths(0.0);
				return 0.0;
			} else {
				return m.pdc.getDouble("PlayerData." + uuid + ".stats.deaths");
			}
		} else {
			return 0.0;
		}
	}

	public void setDeaths(double d) {
		m.pdc.set("PlayerData." + uuid + ".stats.deaths", d);
		savePData();
	}

	public int getCoins() {
		if (m.pdc.get("PlayerData." + uuid + ".stats.coins") != null) {
			if (m.pdc.getInt("PlayerData." + uuid + ".stats.coins") < 0) {
				setCoins(0);
				return 0;
			} else {
				return m.pdc.getInt("PlayerData." + uuid + ".stats.coins");
			}
		} else {
			return 0;
		}
	}

	public void setCoins(int amount) {
		m.pdc.set("PlayerData." + uuid + ".stats.coins", amount);
		savePData();
	}

	public void giveCoins(int amount) {
		setCoins(getCoins() + amount);
		savePData();
	}

	public boolean hasCoins(int amount) {
		if (((m.pdc.getInt("PlayerData." + uuid + ".stats.coins")) - amount) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean take(int amount, boolean msg) {
		if (hasCoins(amount)) {
			setCoins(getCoins() - amount);
			return true;
		} else {
			if (msg) {
				getPlayer().sendMessage(ChatBuilder.build("Not enough money."));
			}
			return false;
		}
	}

	public boolean hasKit(Kit k) {
		if (k.cost() == 0)
			return true;
		if (m.kc.getStringList(uuid + ".kits").contains(k.name())) {
			return true;
		} else {
			return false;
		}
	}

	public void buyKit(Kit kit) {
		if (hasKit(kit)) {
			bukkitPlayer.sendMessage(m.KIT_SHOP + ChatBuilder.build("You already got this kit."));
		} else {
			if (hasCoins(kit.cost())) {
				List<String> list = m.kc.getStringList(uuid + ".kits");
				list.add(kit.name());
				m.kc.set(uuid + ".kits", list);
				giveCoins(-kit.cost());
				bukkitPlayer.sendMessage(m.KIT_SHOP + ChatBuilder.build("You bought kit", kit.name()));
				saveKits();
			} else {
				bukkitPlayer.sendMessage(m.ECONOMY + ChatBuilder.build("You don't have enough money"));
			}
		}
	}

	public void savePData() {
		try {
			m.pdc.save(InfernumPvP.pd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveKits() {
		try {
			m.kc.save(InfernumPvP.k);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Player getPlayer() {
		return bukkitPlayer;
	}

	public boolean hasKit(String kit) {
		if (m.kc.getStringList(uuid + ".kits").contains(kit)) {
			return true;
		} else {
			return false;
		}
	}

	public void giveKit(String kit) {
		List<String> list = m.kc.getStringList(uuid + ".kits");
		list.add(kit);
		m.kc.set(uuid, list);
		saveKits();
	}

	public String getNick() {
		return getPlayer().getDisplayName();
	}

	public void sendMessage(String str) {
		getPlayer().sendMessage(str);
	}

	public boolean hasPermission(String str, boolean msg) {
		if (getPlayer().hasPermission(str))
			return true;
		else {
			if (msg) {
				getPlayer().sendMessage(m.ADMINISTRATION + ChatBuilder.build("You don't have permission", str));
			}
			return false;
		}
	}

	public void spawn() {
		bukkitPlayer.teleport(new Location(m.spawnWorld, -485.500, 123.5, -98.5));

		ItemStack select = ItemUtil.easy(Material.CHEST, ChatColor.GRAY + "Kits");
		ItemStack shop = ItemUtil.easy(Material.ENDER_CHEST, ChatColor.GRAY + "Kit Shop");

		bukkitPlayer.getInventory().clear();
		for (PotionEffect pe : bukkitPlayer.getActivePotionEffects())
			bukkitPlayer.removePotionEffect(pe.getType());
		bukkitPlayer.setHealth(20);
		bukkitPlayer.setFoodLevel(20);
		bukkitPlayer.setGameMode(GameMode.SURVIVAL);

		bukkitPlayer.getInventory().setItem(0, select);
		bukkitPlayer.getInventory().setItem(2, shop);
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setOwner(bukkitPlayer.getName());
		im.setDisplayName("§c" + bukkitPlayer.getName() + " Stats");
		is.setItemMeta(im);
		bukkitPlayer.getInventory().addItem(ItemUtil.easy(Material.BLAZE_ROD, "§61V1 Stick"));
		bukkitPlayer.getInventory().setItem(8, is);
	}

	public double getKDR() {
		if (getDeaths() != 0) {
			return (getKills() / getDeaths());
		} else {
			return getKills();
		}
	}
}