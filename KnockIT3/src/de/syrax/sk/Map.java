package de.syrax.sk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Map {

	public String playMap = "";
	public ArrayList<String> maps = new ArrayList<>();
	
	public void registerMaps(){
		File maps = new File("plugins/KnockIT/Maps");
		if(!maps.exists()){
			try {
				maps.getParentFile().mkdirs();
				maps.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(maps.listFiles() != null){
			for(File map : maps.listFiles()){
				this.maps.add(map.getName());
				System.out.println(map.getName());
			}
			Random r = new Random();
			int i = r.nextInt(this.maps.size());
			playMap = this.maps.get(i);
		}
	}
	public String getPlayMap() {
		return playMap;
	}
	public void randomMap(){
		Random r = new Random();
		int i = r.nextInt(this.maps.size());
		playMap = this.maps.get(i);
	}
	public void setMapSpawn(String map, Location loc){
		File file = new File("plugins/KnockIT/Maps/"+map+"/Spawn.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()){
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cfg.set("Spawn.world", loc.getWorld().getName());
		cfg.set("Spawn.x", loc.getX());
		cfg.set("Spawn.y", loc.getY());
		cfg.set("Spawn.z", loc.getZ());
		cfg.set("Spawn.yaw", loc.getYaw());
		cfg.set("Spawn.pitch", loc.getPitch());
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Location getMapSpawn(String map){
		File file = new File("plugins/KnockIT/Maps/"+map+"/Spawn.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		World world = Bukkit.getWorld(cfg.getString("Spawn.world"));
		double x = cfg.getDouble("Spawn.x");
		double y = cfg.getDouble("Spawn.y");
		double z = cfg.getDouble("Spawn.z");
		Location loc = new Location(world, x, y, z);
		loc.setYaw(cfg.getInt("Spawn.yaw"));
		loc.setPitch(cfg.getInt("Spawn.pitch"));
		return loc;
	}
	
	public void setMapHigh(String map, Location loc){
		File file = new File("plugins/KnockIT/Maps/"+map+"/High.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()){
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cfg.set("High.y", loc.getY());
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getMapHigh(String map){
		File file = new File("plugins/KnockIT/Maps/"+map+"/High.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int y = (int) cfg.getDouble("High.y");
		return y;
	}
	public void setTop(String nummer, Location loc, String map) {
		File file = new File("plugins/KnockIT/Maps/"+map+"/Tops.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()){
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cfg.set("Top."+nummer+".world", loc.getWorld().getName());
		cfg.set("Top."+nummer+".x", loc.getX());
		cfg.set("Top."+nummer+".y", loc.getY());
		cfg.set("Top."+nummer+".z", loc.getZ());
		cfg.set("Top."+nummer+".yaw", loc.getYaw());
		cfg.set("Top."+nummer+".pitch", loc.getPitch());
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Location getTop(String nummer, String map) {
		File file = new File("plugins/KnockIT/Maps/"+map+"/Tops.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		World world = Bukkit.getWorld(cfg.getString("Top."+nummer+".world"));
		double x = cfg.getDouble("Top."+nummer+".x");
		double y = cfg.getDouble("Top."+nummer+".y");
		double z = cfg.getDouble("Top."+nummer+".z");
		Location loc = new Location(world, x, y, z);
		loc.setYaw(cfg.getInt("Top."+nummer+".yaw"));
		loc.setPitch(cfg.getInt("Top."+nummer+".pitch"));
		return loc;
	}
}
