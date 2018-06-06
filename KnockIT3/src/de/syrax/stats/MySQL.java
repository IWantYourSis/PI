package de.syrax.stats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.syrax.main.Main;

public class MySQL {

	public static String host = Config.cfg.getString("MySQL.Host");
	public static String port = Config.cfg.getString("MySQL.Port");
	public static String database = Config.cfg.getString("MySQL.Database");
	public static String username = Config.cfg.getString("MySQL.Username");
	public static String password = Config.cfg.getString("MySQL.Password");
	public static Connection con;
	
	public static void connect(){
		if(Config.file.exists()){
			if(!isConnected()){
				try {
					con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" +database, username, password);
					System.out.println("[MySQL] Verbingung aufgebaut!");
					PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Stats (Spielername VARCHAR(100),Kills INT(100),Deaths INT(100))");
					ps.executeUpdate();
					Reconnect();
				} catch (SQLException e) {
					System.out.println("---------- MySQL Fehler ---------");
					System.out.println("[MySQL] Die Daten sind falsch MySQL konnte sich nicht Verbinden!");
					System.out.println("---------- MySQL Fehler ---------");
				}
			}
		}else{
			System.out.println("---------- MySQL Fehler ---------");
			System.out.println("[MySQL] Die Config muss noch bearbeitet werden!");
			System.out.println("---------- MySQL Fehler ---------");
		}
	}
	public static void disconnect(){
		if(isConnected()){
			try {
				con.close();
				System.out.println("[MySQL] Verbingung unterbrochen!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static boolean isConnected(){
		return (con == null ? false : true);
	}
	public static Connection getConnection(){
		return con;
	}
	private static void Reconnect(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable(){

			@Override
			public void run() {
				onReconnect();
			}
			
		}, 20*60*10, 20*60*10);
	}
	private static void onReconnect()
    {
        if(con != null)
        {
            try
            {
                con.close();
                System.out.println("[MySQL] MySQL-Verbindung beendet!");
                }
       
            catch (SQLException e)
            {
                System.err.println("[MySQL] MySQL-Verbindung konnte nicht getrennt werden!");
                e.printStackTrace();
            }
        }
   
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
        {
            public void run()
            {
                try
                {
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username ,password);
                    System.out.println("[MySQL] MySQL-Verbindung hergestellt!");
                }
           
                catch (SQLException e)
                {
                    System.err.println("[MySQL] MySQL-Verbindung konnte nicht hergestellt werden!");
                    e.printStackTrace();
                }
            }
        }, 20*3);
    }
}
