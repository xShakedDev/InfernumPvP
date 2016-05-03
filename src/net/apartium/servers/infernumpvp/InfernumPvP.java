package net.apartium.servers.infernumpvp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.apartium.servers.infernumpvp.commands.Coins_Command;
import net.apartium.servers.infernumpvp.commands.Infernum_Command;
import net.apartium.servers.infernumpvp.commands.MultiWorld_Command;
import net.apartium.servers.infernumpvp.commands.Region_Command;
import net.apartium.servers.infernumpvp.commands.Report_Command;
import net.apartium.servers.infernumpvp.commands.Spawn_Command;
import net.apartium.servers.infernumpvp.commands.Test_Command;
import net.apartium.servers.infernumpvp.gui.CharacterGUI;
import net.apartium.servers.infernumpvp.gui.KitGUI;
import net.apartium.servers.infernumpvp.gui.ShopGUI;
import net.apartium.servers.infernumpvp.listeners.arena.AntiTnT;
import net.apartium.servers.infernumpvp.listeners.arena.EntityShotEvent;
import net.apartium.servers.infernumpvp.listeners.arena.MainEvents;
import net.apartium.servers.infernumpvp.listeners.arena.PlayerDeathEvent;
import net.apartium.servers.infernumpvp.listeners.kits.FisherEvents;
import net.apartium.servers.infernumpvp.listeners.kits.PyroEvents;
import net.apartium.servers.infernumpvp.listeners.kits.SwitcherEvents;
import net.apartium.servers.infernumpvp.listeners.player.ChatEvent;
import net.apartium.servers.infernumpvp.listeners.player.DeathEvent;
import net.apartium.servers.infernumpvp.listeners.player.JoinEvent;
import net.apartium.servers.infernumpvp.listeners.player.PingEvent;
import net.apartium.servers.infernumpvp.mainarena.kits.CheaterMan;
import net.apartium.servers.infernumpvp.regions.InventoryFlags;
import net.apartium.servers.infernumpvp.regions.RegionListener;
import net.apartium.servers.infernumpvp.utils.IOUtil;
import net.milkbowl.vault.permission.Permission;

public class InfernumPvP extends JavaPlugin {
	private static InfernumPvP plugin = null;
	static File pd;
	public FileConfiguration pdc;
	static File k;
	public FileConfiguration kc;
	static File a;
	public FileConfiguration ac;
	public World spawnWorld;

	public String 
			PREFIX = "§b§l",
			SUFFIX = "  §7> ",
			ARENA = PREFIX + "Infernum" + SUFFIX,
			KIT_SHOP = PREFIX + "Kits" + SUFFIX,
			ECONOMY = PREFIX + "Coins" + SUFFIX,
			SERVER = PREFIX + "Server" + SUFFIX,
			ADMINISTRATION = PREFIX + "Administration" + SUFFIX,
			USAGE = PREFIX + "Usage" + SUFFIX,
			REGIONS = PREFIX + "Regions" + SUFFIX,
			OVO = PREFIX + "1VS1" + SUFFIX,
			OFFLINE_PLAYER = SERVER + "Offline player";

	public File rgFolder = IOUtil.handleDir(getDataFolder(), "folder"),
			rgGlobal = IOUtil.handleYML(rgFolder, "*:global.yml").getKey(),
			multiWorld = IOUtil.handleYML(getDataFolder(), "multiworld.yml").getKey();

	public Permission permVault;

	public Map<String, File> rgFiles = new HashMap<>();

	public void onEnable() {
		plugin = this;

		permVault = Bukkit.getServicesManager().getRegistration(Permission.class).getProvider();

		spawnWorld = Bukkit.getWorld("spawn1");

		Entry<File, FileConfiguration> ent = IOUtil.handleYML(getDataFolder(), "/playerdata.yml");
		pd = ent.getKey();
		pdc = ent.getValue();
		try {
			pdc.save(pd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Entry<File, FileConfiguration> ent1 = IOUtil.handleYML(getDataFolder(), "/kits.yml");
		k = ent1.getKey();
		kc = ent1.getValue();
		try {
			kc.save(k);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Entry<File, FileConfiguration> ent2 = IOUtil.handleYML(getDataFolder(), "/arenas.yml");
		a = ent2.getKey();
		ac = ent2.getValue();
		try {
			ac.save(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (File f : rgFolder.listFiles()) 
			rgFiles.put(f.getName().replace(".yml", ""), f);
		
		getServer().getPluginManager().registerEvents(new MainEvents(), this);
		getServer().getPluginManager().registerEvents(new EntityShotEvent(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), this);
		getServer().getPluginManager().registerEvents(new AntiTnT(), this);
		getServer().getPluginManager().registerEvents(KitGUI.events, this);
		getServer().getPluginManager().registerEvents(ShopGUI.events, this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		getServer().getPluginManager().registerEvents(new PingEvent(), this);
		getServer().getPluginManager().registerEvents(new RegionListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryFlags(), this);
		getServer().getPluginManager().registerEvents(new FisherEvents(), this);
		getServer().getPluginManager().registerEvents(new SwitcherEvents(), this);
		getServer().getPluginManager().registerEvents(new PyroEvents(), this);
		getServer().getPluginManager().registerEvents(new CharacterGUI(), this);
		getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(), this);
		getServer().getPluginManager().registerEvents(new CheaterMan(), this);

		getServer().getPluginCommand("coins").setExecutor(new Coins_Command());
		getServer().getPluginCommand("infernum").setExecutor(new Infernum_Command());
		getServer().getPluginCommand("spawn").setExecutor(new Spawn_Command());
		getServer().getPluginCommand("test").setExecutor(new Test_Command());
		getServer().getPluginCommand("mw").setExecutor(new MultiWorld_Command());
		getServer().getPluginCommand("report").setExecutor(new Report_Command());

		getServer().getPluginCommand("rg").setExecutor(new Region_Command());
		getServer().getPluginCommand("region").setExecutor(new Region_Command());
		getServer().getPluginCommand("regions").setExecutor(new Region_Command());
		
	}

	@Override
	public void onDisable() {

	}

	public static InfernumPvP getInstance() {
		return plugin;
	}

	public void clearKits(Player p) {

	}

	public void setArmor(Material helmet, Material chest, Material leg, Material boot, Player p) {
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chest));
		p.getInventory().setLeggings(new ItemStack(leg));
		p.getInventory().setBoots(new ItemStack(boot));
	}

	public void giveSoup(Player p) {
		for (int i = 1; i < 35; i++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
			p.getInventory().setItem(35, new ItemStack(Material.BOWL));
		}
	}

}
