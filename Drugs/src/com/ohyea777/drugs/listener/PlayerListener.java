package com.ohyea777.drugs.listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.drugs.Command;
import com.ohyea777.drugs.Drug;
import com.ohyea777.drugs.DrugsPlus;
import com.ohyea777.drugs.Effects;
import com.ohyea777.drugs.RandEffects;
import com.ohyea777.drugs.lang.Message;

public class PlayerListener implements Listener{

	DrugsPlus plugin = DrugsPlus.instance;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			
			Player p = event.getPlayer();
			
			if (!(p.getItemInHand().getType() == Material.AIR) && p.isSneaking()){
				ItemStack item = p.getItemInHand();
				Drug drug = new Drug(item.getTypeId(), item.getDurability());
				if (drug.isDrug()){

					if (drug.hasPermission(p)){
						Effects effects = null;
						RandEffects randEffects = null;
						Command cmd = null;
						
						try{
							effects = new Effects(p, drug);
						}
						catch	(NullPointerException e){
						}
						try{
							randEffects = new RandEffects(p, drug);
						}
						catch	(NullPointerException e){
						}
						try{
							cmd = new Command(p, drug);
						}
						catch	(NullPointerException e){
						}
						
						if (effects != null && effects.hasEffects()){
							effects.doEffects();
						}
						if (randEffects != null && randEffects.hasEffects()){
							randEffects.doEffects();
						}
						if (cmd != null && cmd.hasCommands()){
							cmd.doCommands();
						}
						
						if (event.getPlayer().getItemInHand().getAmount() != 1 && !(event.getPlayer().getGameMode().equals(GameMode.CREATIVE))){
							event.getItem().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
						}
						else if (!(event.getPlayer().getGameMode().equals(GameMode.CREATIVE))){
							event.getPlayer().getInventory().remove(event.getPlayer().getItemInHand());
						}

						event.setCancelled(true);
						
						String prefix = plugin.getConfig().getString("Options.Prefix");
						
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + drug.getUsage().replace("{drug}", drug.getNick())));
					}
					else{
						Message m = new Message();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', m.getPrefix() + m.getNoPerm()));
						p.sendMessage(drug.getUsage().replace("{drug}", drug.getNick()));
					}
				}
			}
			
		}
	}
	
}