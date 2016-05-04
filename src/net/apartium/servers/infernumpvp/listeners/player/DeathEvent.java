package net.apartium.servers.infernumpvp.listeners.player;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.PlayerData;
import net.apartium.servers.infernumpvp._1v1.OvOGUI;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class DeathEvent implements Listener {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		PlayerData pd = new PlayerData(e.getEntity());
		pd.spawn();
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() instanceof Player) {
				PlayerData pp = new PlayerData(e.getEntity().getKiller());
				Random r = new Random();
				int i = r.nextInt(3) + 3;
				pp.giveCoins(i);
				pp.getPlayer().sendMessage(m.ECONOMY + ChatBuilder.build("You got " + i + " coins for kill."));
			}
			if (OvOGUI.ingame.containsKey(e.getEntity().getKiller().getUniqueId())) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.showPlayer(e.getEntity());
					p.showPlayer(e.getEntity().getKiller());
					e.getEntity().showPlayer(p);
					e.getEntity().getKiller().showPlayer(p);
				}
				OvOGUI.ingame.remove(e.getEntity().getKiller());
				OvOGUI.ingame.remove(e.getEntity().getUniqueId());
				Bukkit.dispatchCommand(e.getEntity().getKiller(), "spawn");
			}
		}
	}
}
