package com.ohyea777.drugs;

import org.bukkit.configuration.ConfigurationSection;

public interface IEffects {

	public void doEffects();
	
	public Integer getStrength(String effect);
	
	public Integer getDuration(String effect);
	
	public ConfigurationSection getConfigSection();
}
