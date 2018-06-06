package de.syrax.sk;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.syrax.main.Main;

public class MapCounter {

	public static int cd = 600;
	
	public static void start(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable(){

			@Override
			public void run() {
				if(cd >= 0){
					if(cd == 600){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b10 §7minuten!");
						
					}else if(cd == 300){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b5 §7minuten!");
						
					}else if(cd == 180){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b3 §7minuten!");
						
					}else if(cd == 60){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b1 §7minute!");
						
					}else if(cd == 30){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b30 §7sekunden!");
						
					}else if(cd == 20){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b20 §7sekunden!");
						
					}else if(cd == 10){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b10 §7sekunden!");
						
					}else if(cd == 5){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b5 §7sekunden!");
						
					}else if(cd == 4){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b4 §7sekunden!");
						
					}else if(cd == 3){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b3 §7sekunden!");
						
					}else if(cd == 2){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b2 §7sekunden!");
						
					}else if(cd == 1){
						Bukkit.broadcastMessage(Main.pre + "Map wechsel in §b1 §7sekunden!");
					}else if(cd == 0){
						cd = 600;
							Main.getMap().randomMap();
							for(Player all : Bukkit.getOnlinePlayers()){
								all.teleport(Main.getMap().getMapSpawn(Main.getMap().getPlayMap()));
								Main.getScoreboardManager().sendPacketsScoreboard(all);
								all.sendMessage(Main.pre+"Gespielte Map : §e"+Main.getMap().playMap);
						}
						}
						
							
					}
				cd--;
				}
				
		}, 20, 20);
	}
}
