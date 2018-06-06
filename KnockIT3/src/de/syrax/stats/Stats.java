package de.syrax.stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import de.syrax.main.Main;
import de.syrax.sk.Itemmanager;

public class Stats {

	private static HashMap<Integer, String> rang = new HashMap<>();
	
	private static boolean isPlayerExists(String user){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM Stats WHERE Spielername = ?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static int getKills(String player){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Kills FROM Stats WHERE Spielername = ?");
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt("Kills");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static void addKills(String player, int kills){
		int kill = getKills(player)+kills;
		if(isPlayerExists(player)){
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Stats SET Kills = ? WHERE Spielername = ?");
				ps.setInt(1, kill);
				ps.setString(2, player);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Stats (Spielername,Kills,Deaths) VALUES (?,?,?)");
				ps.setString(1, player);
				ps.setInt(2, 0);
				ps.setInt(3, 0);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void setKills(String player, int kills){
		if(isPlayerExists(player)){
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Stats SET Kills = ? WHERE Spielername = ?");
				ps.setInt(1, kills);
				ps.setString(2, player);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Stats (Spielername,Kills,Deaths) VALUES (?,?,?)");
				ps.setString(1, player);
				ps.setInt(2, 0);
				ps.setInt(3, 0);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static int getDeaths(String player){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Deaths FROM Stats WHERE Spielername = ?");
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt("Deaths");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static void addDeaths(String player, int deaths){
		int death = getDeaths(player)+deaths;
		if(isPlayerExists(player)){
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Stats SET Deaths = ? WHERE Spielername = ?");
				ps.setInt(1, death);
				ps.setString(2, player);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Stats (Spielername,Kills,Deaths) VALUES (?,?,?)");
				ps.setString(1, player);
				ps.setInt(2, 0);
				ps.setInt(3, 0);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void setDeaths(String player, int deaths){
		if(isPlayerExists(player)){
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Stats SET Deaths = ? WHERE Spielername = ?");
				ps.setInt(1, deaths);
				ps.setString(2, player);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Stats (Spielername,Kills,Deaths) VALUES (?,?,?)");
				ps.setString(1, player);
				ps.setInt(2, 0);
				ps.setInt(3, 0);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	  public static double getKD(String Player)
	  {
	    double kills = getKills(Player);
	    double deaths = getDeaths(Player) == 0 ? 1 : getDeaths(Player);
	    return kills / deaths;
	  }
	@SuppressWarnings("deprecation")
	public static void update(){
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Spielername FROM Stats ORDER BY Kills DESC LIMIT 3");
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				i++;
				rang.put(i, rs.getString("Spielername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Location l = Main.getMap().getTop("1", Main.getMap().playMap);
		for(Entity e : l.getWorld().getEntities()){
			if(e.getType() == EntityType.ARMOR_STAND){
				e.remove();
			}
		}
		for(int i = 1; i < 4; i++){
			Location loc = Main.getMap().getTop(String.valueOf(i), Main.getMap().playMap);
			ArmorStand am = (ArmorStand) Bukkit.getWorld(loc.getWorld().getName()).spawnCreature(loc, EntityType.ARMOR_STAND);
			am.setHelmet(Itemmanager.createSkull(rang.get(i)));
			am.setCustomNameVisible(true);
			am.setArms(true);
			if(i == 1){
				am.setCustomName("§bRang #" + i + " §8» §b" + rang.get(i));
				am.setChestplate(Itemmanager.createItem(Material.DIAMOND_CHESTPLATE, 1, 0, " "));
				am.setLeggings(Itemmanager.createItem(Material.DIAMOND_LEGGINGS, 1, 0, " "));
				am.setBoots(Itemmanager.createItem(Material.DIAMOND_BOOTS, 1, 0, " "));
				am.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
			}else if(i == 2){
				am.setCustomName("§6Rang #" + i + " §8» §6" + rang.get(i));
				am.setChestplate(Itemmanager.createItem(Material.GOLD_CHESTPLATE, 1, 0, " "));
				am.setLeggings(Itemmanager.createItem(Material.GOLD_LEGGINGS, 1, 0, " "));
				am.setBoots(Itemmanager.createItem(Material.GOLD_BOOTS, 1, 0, " "));
				am.setItemInHand(new ItemStack(Material.GOLD_SWORD));
			}else if(i == 3){
				am.setCustomName("§7Rang #" + i + " §8» §7" + rang.get(i));
				am.setChestplate(Itemmanager.createItem(Material.IRON_CHESTPLATE, 1, 0, " "));
				am.setLeggings(Itemmanager.createItem(Material.IRON_LEGGINGS, 1, 0, " "));
				am.setBoots(Itemmanager.createItem(Material.IRON_BOOTS, 1, 0, " "));
				am.setItemInHand(new ItemStack(Material.IRON_SWORD));
			}
		}
		
	}
}
