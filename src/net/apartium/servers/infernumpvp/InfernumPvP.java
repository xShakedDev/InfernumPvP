package net.apartium.servers.infernumpvp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.apartium.servers.infernumpvp._1v1.ArenasManager;
import net.apartium.servers.infernumpvp._1v1.Command1v1;
import net.apartium.servers.infernumpvp._1v1.DuelCustomizer;
import net.apartium.servers.infernumpvp._1v1.OvOListener;
import net.apartium.servers.infernumpvp.commands.Break_Command;
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
import net.apartium.servers.infernumpvp.listeners.kits.FisherEvents;
import net.apartium.servers.infernumpvp.listeners.kits.SwitcherEvents;
import net.apartium.servers.infernumpvp.listeners.player.ChatEvent;
import net.apartium.servers.infernumpvp.listeners.player.CommandEvent;
import net.apartium.servers.infernumpvp.listeners.player.DeathEvent;
import net.apartium.servers.infernumpvp.listeners.player.JoinEvent;
import net.apartium.servers.infernumpvp.listeners.player.LeaveEvent;
import net.apartium.servers.infernumpvp.listeners.player.PingEvent;
import net.apartium.servers.infernumpvp.regions.InventoryFlags;
import net.apartium.servers.infernumpvp.regions.Region;
import net.apartium.servers.infernumpvp.regions.RegionListener;
import net.apartium.servers.infernumpvp.utils.Cooldown;
import net.apartium.servers.infernumpvp.utils.IOUtil;

public class InfernumPvP extends JavaPlugin {
	private static InfernumPvP plugin = null;
	static File pd;
	public FileConfiguration pdc;
	static File k;
	public FileConfiguration kc;
	public File a;
	public FileConfiguration ac;
	public World spawnWorld;
	public String PREFIX = "§b§l", SUFFIX = "  §7> ", ARENA = PREFIX + "Infernum" + SUFFIX,
			KIT_SHOP = PREFIX + "Kits" + SUFFIX, ECONOMY = PREFIX + "Coins" + SUFFIX,
			SERVER = PREFIX + "Server" + SUFFIX, ADMINISTRATION = PREFIX + "Administration" + SUFFIX,
			USAGE = PREFIX + "Usage" + SUFFIX, REGIONS = PREFIX + "Regions" + SUFFIX, OVO = PREFIX + "1VS1" + SUFFIX,
			OFFLINE_PLAYER = SERVER + "Offline player";

	public File rgFolder = IOUtil.handleDir(getDataFolder(), "folder"),
			rgGlobal = IOUtil.handleYML(rgFolder, "global.yml").getKey(),
			multiWorld = IOUtil.handleYML(getDataFolder(), "multiworld.yml").getKey();

	public Map<String, File> rgFiles = new HashMap<>();
	public ArrayList<UUID> breaks = new ArrayList<>();

	public void onEnable() {
		plugin = this;

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.showPlayer(p);
		}
		spawnWorld = Bukkit.getWorld("spawn1");

		spawnWorld.setSpawnLocation(-485, 123, -98);
		Entry<File, FileConfiguration> ent = IOUtil.handleYML(getDataFolder(), "/playerdata.yml");
		pd = ent.getKey();
		pdc = ent.getValue();

		Entry<File, FileConfiguration> ent1 = IOUtil.handleYML(getDataFolder(), "/kits.yml");
		k = ent1.getKey();
		kc = ent1.getValue();

		Entry<File, FileConfiguration> ent2 = IOUtil.handleYML(getDataFolder(), "/arenas.yml");
		a = ent2.getKey();
		ac = ent2.getValue();
		ArenasManager.getArenas().reloadArenas();
		getServer().getPluginManager().registerEvents(new OvOListener(), this);
		getServer().getPluginManager().registerEvents(new MainEvents(), this);
		getServer().getPluginManager().registerEvents(new EntityShotEvent(), this);
		getServer().getPluginManager().registerEvents(new AntiTnT(), this);
		getServer().getPluginManager().registerEvents(KitGUI.events, this);
		getServer().getPluginManager().registerEvents(ShopGUI.events, this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		getServer().getPluginManager().registerEvents(new PingEvent(), this);
		getServer().getPluginManager().registerEvents(new RegionListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryFlags(), this);
		getServer().getPluginManager().registerEvents(new FisherEvents(), this);
		getServer().getPluginManager().registerEvents(new SwitcherEvents(), this);
		getServer().getPluginManager().registerEvents(new CharacterGUI(), this);
		getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(), this);
		getServer().getPluginManager().registerEvents(new DuelCustomizer(), this);
		getServer().getPluginManager().registerEvents(new CommandEvent(), this);
		getServer().getPluginManager().registerEvents(new LeaveEvent(), this);

		getServer().getPluginCommand("coins").setExecutor(new Coins_Command());
		getServer().getPluginCommand("infernum").setExecutor(new Infernum_Command());
		getServer().getPluginCommand("spawn").setExecutor(new Spawn_Command());
		getServer().getPluginCommand("test").setExecutor(new Test_Command());
		getServer().getPluginCommand("mw").setExecutor(new MultiWorld_Command());
		getServer().getPluginCommand("report").setExecutor(new Report_Command());
		getServer().getPluginCommand("ovo").setExecutor(new Command1v1());
		getServer().getPluginCommand("rg").setExecutor(new Region_Command());
		getServer().getPluginCommand("region").setExecutor(new Region_Command());
		getServer().getPluginCommand("regions").setExecutor(new Region_Command());
		getServer().getPluginCommand("break").setExecutor(new Break_Command());
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				Cooldown.handleCooldowns();
			}
		}, 1L, 1L);
		for (File f : rgFolder.listFiles()) {
			rgFiles.put(f.getName().replace(".yml", ""), f);
			Region.createNew(f.getName().replace(".yml", ""));
		}
	}

	@Override
	public void onDisable() {
		ArenasManager.getArenas().saveArenas();
		ArenasManager.getArenas().getList().clear();
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
