package net.apartium.servers.infernumpvp.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp._1v1.ArenasManager;
import net.apartium.servers.infernumpvp._1v1.OvOListener;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class LeaveEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Player t = null;
		if (OvOListener.ingame.containsKey(p.getUniqueId())) {
			t = Bukkit.getPlayer(OvOListener.ingame.get(p.getUniqueId()));
			t.sendMessage(m.OVO + ChatBuilder.build("You won the 1V1 versus", p.getName()));
			ArenasManager.getArenas().getArenaByPlayer(p.getPlayer()).endDuel();
		}
	}
}
