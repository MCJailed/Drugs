package com.ohyea777.drugs;

import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public interface IDrug {
	
	public void setNick(String effects);
	
	public void setUsage(String effects);
	
	public Set<String> getEffects();
	
	public Set<String> getRandEffects();
	
	public List<String> getCommands();
	
	public String getNick();
	
	public ConfigurationSection getConfigSection();
	
	public String getUsage();
	
	public Boolean isDrug();
	
	public Boolean hasPermission(Player player);
	
}