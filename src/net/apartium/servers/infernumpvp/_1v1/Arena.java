package net.apartium.servers.infernumpvp._1v1;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena {
	private final String name;
	private final Location pos1;
	private final Location pos2;
	
	private Location spawn1;
	private Location spawn2;
	
	public Arena(String name, Location pos1, Location pos2) {
		this.name = name;
		this.pos1=pos1;
		this.pos2=pos2;
		ArenasManager.getArenas().addArena(this);
	}
	
	public void setSpawn1(Location val) {spawn1=val;}
	public void setSpawn2(Location val) {spawn2=val;}

	public String getName() {return name;}
	public Location getPos1() {return pos1;}
	public Location getPos2() {return pos2;}
	
	
	public void spawn(Player p1, Player p2) {
		p1.teleport(spawn1);
		p2.teleport(spawn2);
	}
}
