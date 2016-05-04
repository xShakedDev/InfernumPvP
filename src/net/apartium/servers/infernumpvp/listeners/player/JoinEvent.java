package net.apartium.servers.infernumpvp.listeners.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp._1v1.OvOGUI;

public class JoinEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PlayerData pp = new PlayerData(p.getUniqueId());

		p.sendMessage("§l§aWelcome, " + pp.getNick() + " to InfernumPvP!");
		e.setJoinMessage(pp.getNick() + "§e joined the game");
		Bukkit.dispatchCommand(p, "spawn");

		m.kc.set(p.getUniqueId().toString() + ".name", p.getName());
		m.pdc.set("PlayerData." + p.getUniqueId() + ".name", p.getName());
		if (m.pdc.get("PlayerData." + p.getUniqueId() + ".stats.coins") == null) {
			pp.setCoins(0);
		}
		pp.saveKits();
		pp.savePData();
		for (UUID ps : OvOGUI.ingame.keySet()) {
			p.hidePlayer(Bukkit.getPlayer(ps));
			Bukkit.getPlayer(ps).hidePlayer(p);
		}
	}
}
