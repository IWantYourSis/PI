package de.syrax.main;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.syrax.commands.cmd_build;
import de.syrax.commands.cmd_forcekit;
import de.syrax.commands.cmd_info;
import de.syrax.commands.cmd_sethigh;
import de.syrax.commands.cmd_setspawn;
import de.syrax.commands.cmd_settop;
import de.syrax.commands.cmd_stats;
import de.syrax.commands.cmd_statsreset;
import de.syrax.listener.listener_Break;
import de.syrax.listener.listener_Chat;
import de.syrax.listener.listener_Interact;
import de.syrax.listener.listener_Join;
import de.syrax.sk.KitCounter;
import de.syrax.sk.Map;
import de.syrax.sk.MapCounter;
import de.syrax.sk.ScoreKlasse;
import de.syrax.stats.Config;
import de.syrax.stats.MySQL;
import de.syrax.stats.Stats;

public class Main extends JavaPlugin{

  public static String pre = "§6§lKnockIT §8» §7";
  public static String nmsver;
  private static ScoreKlasse sbk;
  public static Main plugin;
  public static Kit kit;
  public static Stats api;
  public static Main m;
  public static Main pl;
  public KitCounter Counter = new KitCounter();
  public static ArrayList<Player> build = new ArrayList<>();
  public static Map map = new Map();

  public static File file = new File("plugins/KnockIT", "config.yml");
  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
  @Override
public void onEnable(){
	
	
	System.out.println("§6§lKnockIT §8» §7Du hast das Plugin erfolgreich aktiviert!");
    plugin = this;
    kit = Kit.Normal;
    m = this;
    pl = this;
    nmsver = Bukkit.getServer().getClass().getPackage().getName();
    nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
    Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    map.registerMaps();
    
    PluginManager pm = Bukkit.getPluginManager();
  	pm.registerEvents( new listener_Join(), this);
  	pm.registerEvents( new listener_Interact(), this);
  	pm.registerEvents( new listener_Chat(), this);
  	pm.registerEvents( new listener_Break(), this);

    getCommand("setspawn").setExecutor(new cmd_setspawn());
    getCommand("sethigh").setExecutor(new cmd_sethigh());
    getCommand("KnockITinfo").setExecutor(new cmd_info());
    getCommand("build").setExecutor(new cmd_build());
    getCommand("settop").setExecutor(new cmd_settop());
    getCommand("statsreset").setExecutor(new cmd_statsreset());
    getCommand("stats").setExecutor(new cmd_stats());
    getCommand("forcekit").setExecutor(new cmd_forcekit());
    
    reloadConfig();
    fetchClasses();
    
    MySQL.connect();
    Config.createFile();
    
    KitCounter.start();
    MapCounter.start();
    Stats.update();
    
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

		@Override
		public void run() {
			Stats.update();
			Bukkit.broadcastMessage(pre+"Die §eTop 3 §7wurden geupdatet");
		}
    	
    }, 20*60*3, 20*60*3);
  }
   @Override
	public void onDisable() {
	MySQL.disconnect();
	}

	public void fetchClasses(){
		sbk = new ScoreKlasse(this);
		api = new Stats();
	}
	public static ScoreKlasse getScoreboardManager(){
		return sbk;
	}

public Stats getAPI(){
    return api;
}
public static Map getMap() {
	return map;
}
}

