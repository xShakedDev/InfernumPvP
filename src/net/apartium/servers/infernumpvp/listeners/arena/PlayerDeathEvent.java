package net.apartium.servers.infernumpvp.listeners.arena;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class PlayerDeathEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onPlayerKill(org.bukkit.event.entity.PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() instanceof Player) {
				PlayerData pp = new PlayerData(e.getEntity().getKiller());
				Random r = new Random();
				int i = r.nextInt(3) + 3;
				pp.giveCoins(i);
				pp.getPlayer().sendMessage(m.ECONOMY + ChatBuilder.build("You got " + i + " coins for kill."));
			}
		}
	}
}
