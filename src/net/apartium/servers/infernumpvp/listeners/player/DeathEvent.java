package net.apartium.servers.infernumpvp.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;

public class DeathEvent implements Listener{
	@SuppressWarnings("unused")
	private static InfernumPvP m = InfernumPvP.getInstance();
	
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		PlayerData pd=new PlayerData(e.getEntity());
		pd.spawn();
	}
}
