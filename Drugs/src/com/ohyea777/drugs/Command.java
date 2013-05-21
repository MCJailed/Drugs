package com.ohyea777.drugs;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command implements ICommand{

	public Drug drug;
	public Player player;
	
	public Command(Player player, Drug drug){
		this.drug = drug;
		this.player = player;
	}
	
	@Override
	public void doCommands() {
		if (this.hasCommands()){
			for (String s : this.getCommands()){
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", player.getName()));
			}
		}
	}

	@Override
	public List<String> getCommands() {
		return this.drug.getConfigSection().getStringList("Commands");
	}
	
	public Boolean hasCommands(){
		if (this.getCommands() != null){
			return true;
		}
		return false;
	}

}
