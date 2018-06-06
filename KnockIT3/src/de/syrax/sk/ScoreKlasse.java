package de.syrax.sk;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.syrax.main.Kit;
import de.syrax.main.Main;
import de.syrax.stats.Stats;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class ScoreKlasse
{
@SuppressWarnings("unused")
private Main main;
  
  public ScoreKlasse(Main main)
  {
    this.main = main;
  }
  
  public void sendPacketsScoreboard(Player p)
  {
	  
    Scoreboard sb = new Scoreboard();
    ScoreboardObjective obj = sb.registerObjective("§8» §6KnockIT", IScoreboardCriteria.b);
    
    PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
    PacketPlayOutScoreboardObjective deletePacket = new PacketPlayOutScoreboardObjective(obj, 1);
    
    PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
    
	obj.setDisplayName("§6KnockIT");

	ScoreboardScore a16 = new ScoreboardScore(sb, obj, "§b");
	ScoreboardScore a15 = new ScoreboardScore(sb, obj, "§7Kit");
	ScoreboardScore a14 = null;
	if(Main.kit == Kit.Normal){
		a14 = new ScoreboardScore(sb, obj, "§8» §c§lEnterhacken");
	} else if(Main.kit == Kit.Ender){
		a14 = new ScoreboardScore(sb, obj, "§8» §5§lEnderman");
	} else if(Main.kit == Kit.Bogen){
		a14 = new ScoreboardScore(sb, obj, "§8» §e§lStandart");
	} else if(Main.kit == Kit.Schnee){
		a14 = new ScoreboardScore(sb, obj, "§8» §f§lSchnee");
	}
	ScoreboardScore a13 = new ScoreboardScore(sb, obj, "§9");
	ScoreboardScore a12 = new ScoreboardScore(sb, obj, "§7Map");
	ScoreboardScore a11 = new ScoreboardScore(sb, obj, "§8» §e" + Main.getMap().getPlayMap());
	ScoreboardScore a10 = new ScoreboardScore(sb, obj, "§e");
	ScoreboardScore a9 = new ScoreboardScore(sb, obj, "§7Kills/Tode");
	ScoreboardScore a8 = new ScoreboardScore(sb, obj, "§r§8» §e"+ Stats.getKills(p.getName()) + "§7/§e" + Stats.getDeaths(p.getName()));
	ScoreboardScore a7 = new ScoreboardScore(sb, obj, "§d");
	ScoreboardScore a6 = new ScoreboardScore(sb, obj, "§8» §e--");

	a16.setScore(16);
	a15.setScore(15);
	a14.setScore(14);
	a13.setScore(13);
	a12.setScore(12);
	a11.setScore(11);
	a10.setScore(10);
	a9.setScore(9);
	a8.setScore(8);
	a7.setScore(7);
	a6.setScore(6);


	PacketPlayOutScoreboardScore pa6 = new PacketPlayOutScoreboardScore(a6);
	PacketPlayOutScoreboardScore pa7 = new PacketPlayOutScoreboardScore(a7);
	PacketPlayOutScoreboardScore pa8 = new PacketPlayOutScoreboardScore(a8);
	PacketPlayOutScoreboardScore pa9 = new PacketPlayOutScoreboardScore(a9);
	PacketPlayOutScoreboardScore pa10 = new PacketPlayOutScoreboardScore(a10);
	PacketPlayOutScoreboardScore pa11 = new PacketPlayOutScoreboardScore(a11);
	PacketPlayOutScoreboardScore pa12 = new PacketPlayOutScoreboardScore(a12);
	PacketPlayOutScoreboardScore pa13 = new PacketPlayOutScoreboardScore(a13);
	PacketPlayOutScoreboardScore pa14 = new PacketPlayOutScoreboardScore(a14);
	PacketPlayOutScoreboardScore pa15 = new PacketPlayOutScoreboardScore(a15);
	PacketPlayOutScoreboardScore pa16 = new PacketPlayOutScoreboardScore(a16);
	
	sendPacket(p, deletePacket);
	sendPacket(p, createPacket);
	sendPacket(p, display);
	
	sendPacket(p, pa6);
	sendPacket(p, pa7);
	sendPacket(p, pa8);
	sendPacket(p, pa9);
	sendPacket(p, pa10);
	sendPacket(p, pa11);
	sendPacket(p, pa12);
	sendPacket(p, pa13);
	sendPacket(p, pa14);
	sendPacket(p, pa15);
	sendPacket(p, pa16);
  }
  
  @SuppressWarnings("rawtypes")
private static void sendPacket(Player p, Packet packet)
  {
    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
  }
}
