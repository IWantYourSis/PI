package de.syrax.sk;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

public class Itemmanager {

	public static ItemStack createItem(Material mat, int amount, int id, String name){
		ItemStack it = new ItemStack(mat,amount,(short)id);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(name);
		it.setItemMeta(meta);
		return it;
	}
	public static ItemStack createSkull(String name){
		ItemStack it = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta meta = (SkullMeta) it.getItemMeta();
		meta.setDisplayName(" ");
		meta.setOwner(name);
		it.setItemMeta(meta);
		return it;
	}
	public static ItemStack createUnbreakItem(Material mat, int amount, int id, String name){
		ItemStack it = new ItemStack(mat,amount,(short)id);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(name);
		meta.spigot().setUnbreakable(true);
		it.setItemMeta(meta);
		return it;
	}
	public static ItemStack createEnchantItem(Material mat, int amount, int id, String name, Enchantment ent){
		ItemStack it = new ItemStack(mat,amount,(short)id);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(name);
		meta.addEnchant(ent, 2, true);
		it.setItemMeta(meta);
		return it;
	}
	public static ItemStack createEnchantItem1(Material mat, int amount, int id, String name, Enchantment ent){
		ItemStack it = new ItemStack(mat,amount,(short)id);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(name);
		meta.addEnchant(ent, 2, true);
		it.setItemMeta(meta);
		return it;
	}
	public static ItemStack createEnchantItem2(Material mat, int amount, int id, String name, Enchantment ent){
		ItemStack it = new ItemStack(mat,amount,(short)id);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(name);
		meta.addEnchant(ent, 10, true);
		it.setItemMeta(meta);
		return it;
	}
	public static ItemStack createColoredArmor(Material mat, String name, Color color){
		ItemStack it = new ItemStack(mat, 1);
		LeatherArmorMeta meta = (LeatherArmorMeta) it.getItemMeta();
		meta.setColor(color);
		meta.setDisplayName(name);
		it.setItemMeta(meta);
		return it;
	}
	public static void clearPlayer(Player p){
		p.setHealth(20.0);
		p.setFoodLevel(20);
		p.setLevel(0);
		p.setExp(0);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		for(PotionEffect effect : p.getActivePotionEffects()){
			p.removePotionEffect(effect.getType());
		}
	}
	public static int randomInt(int min, int max){
		Random r = new Random();
		int i = r.nextInt(max-min);
		return i;
	}
	
}

