package com.ohyea777.drugs;

import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Drug implements IDrug{

	public Integer id;
	public short damage;
	public String drug;
	public DrugsPlus plugin = DrugsPlus.instance;
	
	public Drug(Integer id, short s){
		this.id = id;
		this.damage = s;
		
		if (s == 0){
			this.drug = this.id.toString();
		}
		else{
			this.drug = this.id + ":" + this.damage;
		}
	}
	
	public Drug(Integer id){
		this.id = id;
		this.damage = 0;
		
		this.drug = this.id.toString();
	}

	@Override
	public void setNick(String effects) {
		if (this.isDrug()){
			this.getConfigSection().set("Options.Nickname", effects);
		}
	}

	@Override
	public void setUsage(String effects) {
		if (this.isDrug()){
			this.getConfigSection().set("Options.Usage_Message", effects);
		}
	}

	@Override
	public Set<String> getEffects() {
		if (this.isDrug() || this.getConfigSection().getConfigurationSection("Effects").getKeys(false) != null){
			return this.getConfigSection().getConfigurationSection("Effects").getKeys(false);
		}
		return null;
	}

	@Override
	public Set<String> getRandEffects() {
		if (this.isDrug() || this.getConfigSection().getConfigurationSection("Random_Effects").getKeys(false) != null){
			return this.getConfigSection().getConfigurationSection("Random_Effects").getKeys(false);
		}
		return null;
	}

	@Override
	public List<String> getCommands() {
		if (this.isDrug() || this.getConfigSection().getStringList("Commands") != null){
			return this.getConfigSection().getStringList("Commands");
		}
		return null;
	}

	@Override
	public String getNick() {
		if (this.isDrug()){
			if (this.getConfigSection().getString("Options.Nickname") == null){
				return plugin.getConfig().getString("Message.Drug_Use");
			}
			else{
				return this.getConfigSection().getString("Options.Nickname");
			}
		}
		return null;
	}

	@Override
	public String getUsage() {
		if (this.isDrug()){
			if (this.getConfigSection().getString("Options.Usage_Message") == null){
				ItemStack item = new ItemStack(this.id, 1);
				return item.getType().toString();
			}
			else{
				return this.getConfigSection().getString("Options.Usage_Message");
			}
		}
		return null;
	}

	@Override
	public Boolean isDrug() {
		if (this.getConfigSection() != null){
			return true;
		}
		return false;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public short getDamage(){
		return this.damage;
	}

	@Override
	public ConfigurationSection getConfigSection() {
		return plugin.getConfig().getConfigurationSection("Drugs." + this.drug);
	}

	@Override
	public Boolean hasPermission(Player player) {
		if (plugin.getConfig().getBoolean("Options.UseCustomPerms")){
			if (this.getConfigSection().getString("Options.Permission") != null){
				if (player.hasPermission(this.getConfigSection().getString("Options.Permission"))){
					return true;
				}
			}
			if (player.hasPermission("drugs.use.*") || player.hasPermission("drugs.*")){
				return true;
			}
		}
		else{
			if (player.hasPermission("drugs.use")){
				return true;
			}
		}
		
		if (player.isOp()){
			return true;
		}
		return false;
	}

}
