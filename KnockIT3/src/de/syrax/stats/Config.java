package de.syrax.stats;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	public static File file = new File("plugins/Stats/mysql.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void createFile(){
		if(!file.exists()){
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				cfg.set("MySQL.Host", "host");
				cfg.set("MySQL.Port", "3306");
				cfg.set("MySQL.Database", "Database");
				cfg.set("MySQL.Username", "Username");
				cfg.set("MySQL.Password", "Password");
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
