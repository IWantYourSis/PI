package de.syrax.sk;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import de.syrax.main.Main;

public class ActionBar {

	public static void sendActionBar(Player player, String message)
	  {
	    try
	    {
	      Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + Main.nmsver + ".entity.CraftPlayer");
	      Object p = c1.cast(player);
	      
	      Class<?> c4 = Class.forName("net.minecraft.server." + Main.nmsver + ".PacketPlayOutChat");
	      Class<?> c5 = Class.forName("net.minecraft.server." + Main.nmsver + ".Packet");
	      Object ppoc;
	      if (((Main.nmsver.equalsIgnoreCase("v1_8_R1")) || (!Main.nmsver.startsWith("v1_8_"))) && (!Main.nmsver.startsWith("v1_9_")))
	      {
	        Class<?> c2 = Class.forName("net.minecraft.server." + Main.nmsver + ".ChatSerializer");
	        Class<?> c3 = Class.forName("net.minecraft.server." + Main.nmsver + ".IChatBaseComponent");
	        Method m3 = c2.getDeclaredMethod("a", new Class[] { String.class });
	        Object cbc = c3.cast(m3.invoke(c2, new Object[] { "{\"text\": \"" + message + "\"}" }));
	        ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { cbc, Byte.valueOf((byte) 2) });
	      }
	      else
	      {
	        Class<?> c2 = Class.forName("net.minecraft.server." + Main.nmsver + ".ChatComponentText");
	        Class<?> c3 = Class.forName("net.minecraft.server." + Main.nmsver + ".IChatBaseComponent");
	        Object o = c2.getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });
	        ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { o, Byte.valueOf((byte) 2) });
	      }
	      Method m1 = c1.getDeclaredMethod("getHandle", new Class[0]);
	      Object h = m1.invoke(p, new Object[0]);
	      Field f1 = h.getClass().getDeclaredField("playerConnection");
	      Object pc = f1.get(h);
	      Method m5 = pc.getClass().getDeclaredMethod("sendPacket", new Class[] { c5 });
	      m5.invoke(pc, new Object[] { ppoc });
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	  }
}
