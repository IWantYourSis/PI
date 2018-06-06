package de.syrax.listener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;

import de.syrax.main.Kit;
import de.syrax.main.Main;
import de.syrax.sk.Itemmanager;
import de.syrax.stats.Stats;

public class listener_Join implements Listener{
	
	private HashMap<Player, Player> hit = new HashMap<>();
	
	@EventHandler 
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		p.getInventory().clear();
		p.setMaxHealth(6);
		p.setHealth(6);
		p.setLevel(0);
		p.setFoodLevel(20);
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		if(Main.kit == Kit.Ender){
			p.getInventory().clear();
			p.getInventory().setItem(0, Itemmanager.createEnchantItem1(Material.BLAZE_ROD, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
			p.getInventory().setItem(1, Itemmanager.createItem(Material.ENDER_PEARL, 3, 0, "§8» §cEnderPearl"));
		}else if (Main.kit == Kit.Bogen){
			p.getInventory().clear();
			p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
		}else if (Main.kit == Kit.Schnee){
			p.getInventory().clear();
			p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
			p.getInventory().setItem(1, Itemmanager.createItem(Material.SNOW_BALL, 4, 0, "§8» §fSchnee Ball"));
		}else if (Main.kit == Kit.Normal){
			p.getInventory().clear();
			p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
			p.getInventory().setItem(1, Itemmanager.createUnbreakItem(Material.FISHING_ROD, 1, 0, "§8» §cEnterhaken")); 
		}
	    Main.getScoreboardManager().sendPacketsScoreboard(p);
	    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.m, new Runnable(){
	    	@Override
	    	public void run() {
	    		p.teleport(Main.getMap().getMapSpawn(Main.getMap().playMap));
	    	}
	    }, 20);
		
		
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e){
			if(e.getCause().equals(DamageCause.FALL)){
				e.setCancelled(true);
			}
			if(e.getCause().equals(DamageCause.SUFFOCATION)) {
				e.setCancelled(true);
			}
	}
			@EventHandler
			public void onDamageEvent(EntityDamageByEntityEvent e){
				if(e.getEntity() instanceof Player && e.getDamager ()instanceof Player){
					Player p = (Player) e.getEntity();
					Player pl = (Player) e.getDamager();
					if(p.getLocation().getY() > Main.getMap().getMapHigh(Main.getMap().playMap)){
						e.setCancelled(true);
						}else{
							hit.put(p, pl);
							e.setDamage(0);
						}
					}else if(e.getEntity() instanceof Player && e.getDamager() instanceof FishHook){
						Player p = (Player) e.getEntity();
						if(p.getLocation().getY() > Main.getMap().getMapHigh(Main.getMap().playMap)){
							e.setCancelled(true);
						}
					}else if(e.getEntity() instanceof Player && e.getDamager() instanceof EnderPearl){
						Player p = (Player) e.getEntity();
						if(p.getLocation().getY() > Main.getMap().getMapHigh(Main.getMap().playMap)){
							e.setCancelled(true);
						}
					}else if(e.getEntity() instanceof ArmorStand && e.getDamager() instanceof Player){
						e.setCancelled(true);
					}else if(e.getEntity() instanceof Player && e.getDamager() instanceof Snowball){
						Player p = (Player) e.getEntity();
						if(p.getLocation().getY() > Main.getMap().getMapHigh(Main.getMap().playMap)){
				       e.setCancelled(true);
						}
	}
			}
				@EventHandler
				public void onDamageEvent1(PlayerMoveEvent e){
						Player p = e.getPlayer();
						if(p.getLocation().getY() < 0 ){
							p.damage(20);
				}
				
					}
			@EventHandler
			public void onEntityDamage(EntityDamageByBlockEvent e){
				e.setCancelled(true);
			}
			@EventHandler
			public void onDeathDamage(PlayerDeathEvent e){
				Player p = e.getEntity();
				p.spigot().respawn();
				e.setKeepInventory(true);
				if(hit.containsKey(p)){
					Player pl = hit.get(p);
					p.sendMessage(Main.pre+ "§8» §7Du wurdest von §4" + pl.getName() + " §7getötet§c!");
					p.setLevel(0);
					pl.sendMessage(Main.pre+ "§8» §7Du hast §c" + p.getName() + " §7getötet§c!");
					pl.setLevel(pl.getLevel() + 1);
					Stats.addKills(pl.getName(), 1);
					Stats.addDeaths(p.getName(), 1);
					hit.remove(p);
					hit.remove(pl);
					Main.getScoreboardManager().sendPacketsScoreboard(pl);
					if(pl.getLevel() == 5){
						pl.sendMessage(Main.pre+ "Du hast eine Killstreak von §c5 §7erreicht!");
						pl.sendMessage(Main.pre+ "§a+ §315");
						for(Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(Main.pre+ "§c" + pl.getName() + "§7Hat eine §c5er Killstreak§4!");
						}
						pl.playSound(pl.getLocation(), Sound.ENDERMAN_SCREAM, 1, 1);
					}else if(pl.getLevel() == 10){
						pl.sendMessage(Main.pre+ "Du hast eine Killstreak von §c10 §7erreicht!");
						pl.sendMessage(Main.pre+ "§a+ §320");
						pl.sendMessage(Main.pre+" Du hast nun durch deine Killstreak ein neues Item bekommen!");
						pl.getInventory().setItem(7, Itemmanager.createEnchantItem(Material.GOLD_BLOCK, 1, 0, Main.pre+ "§c10 Killstreak!", Enchantment.KNOCKBACK));
						for(Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(Main.pre+ "§c" + pl.getName() + "§7Hat eine §c10er Killstreak§4!");
						}
						pl.playSound(pl.getLocation(), Sound.ENDERMAN_SCREAM, 1, 1);
					}else if(pl.getLevel() == 15){
						pl.sendMessage(Main.pre+ "Du hast eine Killstreak von §c15 §7erreicht!");
						pl.sendMessage(Main.pre+ "§a+ §325");
						pl.sendMessage(Main.pre+" Du hast nun durch deine Killstreak ein neues Item bekommen!");
						pl.getInventory().setItem(7, Itemmanager.createEnchantItem1(Material.GOLD_BLOCK, 1, 0, Main.pre+ "§c15 Killstreak!", Enchantment.KNOCKBACK));
						for(Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(Main.pre+ "§c" + pl.getName() + "§7Hat eine §c15er Killstreak§4!");
						}
						pl.playSound(pl.getLocation(), Sound.ENDERMAN_SCREAM, 1, 1);
					}else if(pl.getLevel() == 20){
						pl.sendMessage(Main.pre+ "Du hast eine Killstreak von §c20 §7erreicht!");
						pl.sendMessage(Main.pre+ "§a+ §30");
						pl.sendMessage(Main.pre+" Du hast nun durch deine Killstreak ein neues Item bekommen!");
						pl.getInventory().setItem(7, Itemmanager.createEnchantItem1(Material.GOLD_BLOCK, 1, 0, Main.pre+ "§c20 Killstreak!", Enchantment.KNOCKBACK));
						for(Player all : Bukkit.getOnlinePlayers()){
							all.sendMessage(Main.pre+ "§c" + pl.getName() + "§7Hat eine §c20er Killstreak§4!");
						}
						pl.playSound(pl.getLocation(), Sound.ENDERMAN_SCREAM, 1, 1);
					}else if(pl.getLevel() == 25){
						pl.sendMessage(Main.pre+ "Du hast eine Killstreak von §c25 §7erreicht!");
						pl.sendMessage(Main.pre+ "§a+ §340");
						pl.sendMessage(Main.pre+" Du hast nun durch deine Killstreak ein neues Item bekommen!");
						pl.getInventory().setItem(7, Itemmanager.createEnchantItem1(Material.GOLD_BLOCK, 1, 0, Main.pre+ "§c25 Killstreak!", Enchantment.KNOCKBACK));
						for(Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(Main.pre+ "§c" + pl.getName() + "§7Hat eine §c25er Killstreak§4!");
						}
						pl.playSound(pl.getLocation(), Sound.ENDERMAN_SCREAM, 1, 1);
					}else if(pl.getLevel() == 50){
						pl.sendMessage(Main.pre+ "Du hast eine Killstreak von §c50 §7erreicht!");
						pl.sendMessage(Main.pre+ "§a+ §350");
						pl.sendMessage(Main.pre+" Du hast nun durch deine Killstreak ein neues Item bekommen!");
						pl.getInventory().setItem(7, Itemmanager.createEnchantItem1(Material.GOLD_BLOCK, 1, 0, Main.pre+ "§c50 Killstreak!", Enchantment.KNOCKBACK));
						for(Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(Main.pre+ "§c" + pl.getName() + "§7Hat eine §c50er Killstreak§4!");
						}
						pl.playSound(pl.getLocation(), Sound.ENDERMAN_SCREAM, 1, 1);
					}else if(pl.getLevel() == 100){
						pl.sendMessage(Main.pre+ "Du hast eine Killstreak von §c100 §7erreicht!");
						pl.sendMessage(Main.pre+ "§a+ §3100");
						pl.sendMessage(Main.pre+" Du hast nun durch deine Killstreak ein neues Item bekommen!");
						pl.getInventory().setItem(7, Itemmanager.createEnchantItem2(Material.GOLD_BLOCK, 1, 0, Main.pre+ "§c100 Killstreak!", Enchantment.KNOCKBACK));
						for(Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(Main.pre+ "§c" + pl.getName() + "§7Hat eine §c100er Killstreak§4!");
						}
						pl.playSound(pl.getLocation(), Sound.ENDERMAN_SCREAM, 1, 1);
					
					}
					
				}else{
					p.sendMessage(Main.pre+ "§8»§cDu bist gestorben");
					Stats.addDeaths(p.getName(), 1);
					Main.getScoreboardManager().sendPacketsScoreboard(p);
				}
			    Main.getScoreboardManager().sendPacketsScoreboard(p);
				e.setDeathMessage(null);
			}
				@EventHandler
				public void onRespawn(PlayerRespawnEvent e){
					Player p = e.getPlayer();
			        p.teleport(Main.getMap().getMapSpawn(Main.getMap().playMap));
			        e.setRespawnLocation(Main.getMap().getMapSpawn(Main.getMap().playMap));
					if(Main.kit == Kit.Ender){
						p.getInventory().clear();
						p.getInventory().setItem(0, Itemmanager.createEnchantItem1(Material.BLAZE_ROD, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
						p.getInventory().setItem(1, Itemmanager.createItem(Material.ENDER_PEARL, 3, 0, "§8» §cEnderPearl"));
					}else if (Main.kit == Kit.Bogen){
						p.getInventory().clear();
						p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
					}else if (Main.kit == Kit.Schnee){
						p.getInventory().clear();
						p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
						p.getInventory().setItem(1, Itemmanager.createItem(Material.SNOW_BALL, 4, 0, "§8» §fSchnee Ball"));
					}else if (Main.kit == Kit.Normal){
						p.getInventory().clear();
						p.getInventory().setItem(0, Itemmanager.createEnchantItem(Material.STICK, 1, 0, "§8» §6Knockback-Stick", Enchantment.KNOCKBACK));
						p.getInventory().setItem(1, Itemmanager.createUnbreakItem(Material.FISHING_ROD, 1, 0, "§8» §cEnterhaken")); 
					}
			}
			@EventHandler
			public void Food(FoodLevelChangeEvent e){
				e.setCancelled(true);
			}
            @EventHandler
            public void onWeather(WeatherChangeEvent e){
            e.setCancelled(true);
            }
			@EventHandler
            public void onPick(PlayerPickupItemEvent e){
           e.setCancelled(true);
            }
           @EventHandler
	       public void onExplode(EntityExplodeEvent e){
		   e.setCancelled(true);
	       }
           @EventHandler
	       public void onEntity(PlayerInteractAtEntityEvent e){
		   e.setCancelled(true);
	       }
           @EventHandler
           public void onQuit(PlayerQuitEvent e){
        	   e.setQuitMessage(null);
           }
           @EventHandler
           public void on1(CraftItemEvent e){
        	   e.setCancelled(true);
           }
           
		@EventHandler
           public void onDrop(PlayerDropItemEvent e){
        	   e.setCancelled(true);
           }
        		@EventHandler
        		public void onCommand(PlayerCommandPreprocessEvent e) {
        			if(!e.isCancelled()) {
        				Player p = e.getPlayer();
        				String msg = e.getMessage().split(" ")[0];
        				
        				HelpTopic help = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
        				
        				if(help == null) {
        					e.setCancelled(true);
        					p.sendMessage(Main.pre + "§7Der Befehl §e" + e.getMessage() + " §7wurde nicht gefunden.");
        				}

        			}
           }
}