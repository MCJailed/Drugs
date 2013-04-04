package com.mcjailed.drugs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ConfigUtils {

	public static Plugin plugin(){
		return Bukkit.getPluginManager().getPlugin("Drugs");
	}
	
	public List<String> getCommand(String drug){
		
		if (plugin().getConfig().getStringList("Drugs." + drug + "Commands") != null){
			List<String> list = plugin().getConfig().getStringList("Drugs." + drug + "Commands");
			return list;
		}
		
		return null;
	}
	
	public static Map<ItemStack, Byte> getDrugIds(){
		Set<String> list = plugin().getConfig().getConfigurationSection("Drugs").getKeys(false);
		Map<ItemStack, Byte> map = new HashMap<ItemStack, Byte>();
		for (String s : list){
			s.replaceAll("'", "");
			if (s.contains(":")){
				String[] val = s.split(":");
				Integer id = Integer.valueOf(val[0]);
				short data = Short.valueOf(val[1]);
				ItemStack stack = new ItemStack(id);
				stack.setDurability(data);
				map.put(stack, null);
			}
			else{
				ItemStack stack = new ItemStack(Integer.valueOf(s));
				map.put(stack, null);
			}
		}
		return map;
	}
	
	public static Set<String> getDrugEffects(Integer i, Short data){
		Set<String> list = null;
		String s = i + ":" + data;
		if (data != 0){
			list = plugin().getConfig().getConfigurationSection("Drugs." + s + ".Effects").getKeys(false);
		}
		else{
			list = plugin().getConfig().getConfigurationSection("Drugs." + i + ".Effects").getKeys(false);
		}
		return list;
	}
	
	public static int getDrugEffectStrength(String d, Short data, String e){
		int list;
		if (data == 0){
		list = plugin().getConfig().getInt("Drugs." + d + ".Effects." + e + ".Strength");
		}
		else{
			list = plugin().getConfig().getInt("Drugs." + d + ":" + data + ".Effects." + e + ".Strength");
		}
		return list;
	}
	
	public static int getDrugEffectDuration(String d, Short data, String e){
		int list;
		if (data == 0){
		list = plugin().getConfig().getInt("Drugs." + d + ".Effects." + e + ".Duration");
		}
		else{
			list = plugin().getConfig().getInt("Drugs." + d + ":" + data + ".Effects." + e + ".Duration");
		}
		return list;
	}
}
