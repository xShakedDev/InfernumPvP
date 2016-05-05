package net.apartium.servers.infernumpvp._1v1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena {
	private final String name;
	private final Location pos1;
	private final Location pos2;
	private boolean inuse;
	private Player playerinarena;
	private Player tempplayer;
	private Player Opponentinarena;

	public Arena(String name, Location pos1, Location pos2) {
		this.name = name;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.setInuse(false);
		ArenasManager.getArenas().addArena(this);
	}

	public String getName() {
		return name;
	}

	public Location getPos1() {
		return pos1;
	}

	public Location getPos2() {
		return pos2;
	}

	public void endDuel() {
		Bukkit.dispatchCommand(getPlayerinarena(), "spawn");
		Bukkit.dispatchCommand(Bukkit.getPlayer(OvOListener.ingame.get(getPlayerinarena().getUniqueId())), "spawn");
		OvOListener.ingame.remove(OvOListener.ingame.get(getPlayerinarena().getUniqueId()));
		OvOListener.ingame.remove(getPlayerinarena().getUniqueId());
		setInuse(false);
		setPlayerinarena(null);
	}

	public boolean isInuse() {
		if (getPlayerinarena() != null)
			return inuse;
		else
			return false;
	}

	public void setInuse(boolean inuse) {
		this.inuse = inuse;
	}

	public Player getPlayerinarena() {
		return playerinarena;
	}

	public void setPlayerinarena(Player playerinarena) {
		this.playerinarena = playerinarena;
	}

	public Player getTempplayer() {
		return tempplayer;
	}

	public void setTempplayer(Player tempplayer) {
		this.tempplayer = tempplayer;
	}

	public Player getOpponentinarena() {
		return Opponentinarena;
	}

	public void setOpponentinarena(Player Opponentinarena) {
		this.Opponentinarena = Opponentinarena;
	}

}
