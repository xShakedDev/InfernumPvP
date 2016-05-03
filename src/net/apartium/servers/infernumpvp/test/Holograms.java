package net.apartium.servers.infernumpvp.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;


public class Holograms {
   
    private List<String> lines;
    private Location loc;
    private static final double ABS = 0.23D;
    private static String path;
    private static String version;
   
   
    static {
        path = Bukkit.getServer().getClass().getPackage().getName();
        version = path.substring(path.lastIndexOf(".")+1, path.length());
    }

    public Holograms(Location loc, List<String> lines) {
        this.lines = lines;
        this.loc = loc;
    }
   
    // Boolean successfully
    public boolean display(Player p) {
        Location displayLoc = loc.clone().add(0, (ABS * lines.size()) - 1.97D, 0);
        for (int i = 0; i < lines.size(); i++) {
            Object packet = this.getPacket(this.loc.getWorld(), displayLoc.getX(), displayLoc.getY(), displayLoc.getZ(), this.lines.get(i));
            if (packet == null) return false;
            this.sendPacket(p, packet);
            displayLoc.add(0, -ABS, 0);
        }
       
        return true;
    }
   
    public Object getPacket(World w, double x, double y, double z, String text) {
        try {
            Class<?> armorStand = Class.forName("net.minecraft.server." + version + ".EntityArmorStand");
            Class<?> worldClass = Class.forName("net.minecraft.server." + version + ".World");
            Class<?> nmsEntity = Class.forName("net.minecraft.server." + version + ".Entity");
            Class<?> craftWorld = Class.forName("org.bukkit.craftbukkit." + version + ".CraftWorld");
            Class<?> packetClass = Class.forName("net.minecraft.server." + version + ".PacketPlayOutSpawnEntityLiving");
            Class<?> entityLivingClass = Class.forName("net.minecraft.server." + version + ".EntityLiving");
            Constructor<?> cww = armorStand.getConstructor(new Class<?>[] { worldClass });
            Object craftWorldObj = craftWorld.cast(w);
            Method getHandleMethod = craftWorldObj.getClass().getMethod("getHandle", new Class<?>[0]);
            Object entityObject = cww.newInstance(new Object[] { getHandleMethod.invoke(craftWorldObj, new Object[0]) });
            Method setCustomName = entityObject.getClass().getMethod("setCustomName", new Class<?>[] { String.class });
            setCustomName.invoke(entityObject, new Object[] { text });
            Method setCustomNameVisible = nmsEntity.getMethod("setCustomNameVisible", new Class[] { boolean.class });
            setCustomNameVisible.invoke(entityObject, new Object[] { true });
            Method setGravity = entityObject.getClass().getMethod("setGravity", new Class<?>[] { boolean.class });
            setGravity.invoke(entityObject, new Object[] { false });
            Method setLocation = entityObject.getClass().getMethod("setLocation", new Class<?>[] { double.class, double.class, double.class, float.class, float.class });
            setLocation.invoke(entityObject, new Object[] { x, y, z, 0.0F, 0.0F });
            Method setInvisible = entityObject.getClass().getMethod("setInvisible", new Class<?>[] { boolean.class });
            setInvisible.invoke(entityObject, new Object[] { true });
            Constructor<?> cw = packetClass.getConstructor(new Class<?>[] { entityLivingClass });
            Object packetObject = cw.newInstance(new Object[] { entityObject });
            return packetObject;
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    private void sendPacket(Player p, Object packet) {
        String path = Bukkit.getServer().getClass().getPackage().getName();
        String version = path.substring(path.lastIndexOf(".") + 1, path.length());
        try {
           Method getHandle = p.getClass().getMethod("getHandle");
           Object entityPlayer = getHandle.invoke(p);
           Object pConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
           Class<?> packetClass = Class.forName("net.minecraft.server." + version + ".Packet");
           Method sendMethod = pConnection.getClass().getMethod("sendPacket", new Class[] { packetClass });
           sendMethod.invoke(pConnection, new Object[] { packet });
        } catch (Exception e) {
           e.printStackTrace();
        }
     }

}