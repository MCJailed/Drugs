package com.mcjailed.drugs;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DrugEvent implements Listener{

	Drugs plugin;

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event){

		if (Drugs.perms.has(event.getPlayer(), "drugs.use")){
			if (event.getAction() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
				if (event.getPlayer().isSneaking() && getDrugIds(event.getItem())){
					Set<String> e = null;
					Integer i = null;
					Short data = null;
					data = event.getItem().getDurability();
					i = event.getItem().getTypeId();
					e = ConfigUtils.getDrugEffects(i, data);
					Material m = Material.getMaterial(i);
					event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s %s", Bukkit.getPluginManager().getPlugin("Drugs").getConfig().get("Options.Prefix"), Bukkit.getPluginManager().getPlugin("Drugs").getConfig().get("Message.Drug_Use").toString().replace("{drug}", m.name()))));
					for (String s : e){
						Player p = event.getPlayer();
						int duration = ConfigUtils.getDrugEffectDuration(i.toString(), data, s);
						int strength = ConfigUtils.getDrugEffectStrength(i.toString(), data, s);
						p.addPotionEffect(new PotionEffect(toPotion(s), duration*20, strength));
					}
					if (event.getItem().getAmount() != 1 && !(event.getPlayer().getGameMode().equals(GameMode.CREATIVE))){
						event.getItem().setAmount(event.getItem().getAmount() - 1);
					}
					else if (!(event.getPlayer().getGameMode().equals(GameMode.CREATIVE))){
						event.getPlayer().getInventory().remove(event.getItem());
					}
					event.setCancelled(true);
				}
			}
		}
		else{
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("%s %s", Bukkit.getPluginManager().getPlugin("Drugs").getConfig().get("Options.Prefix"), Bukkit.getPluginManager().getPlugin("Drugs").getConfig().get("Message.No_Perm").toString().replace("{drug}", Material.getMaterial(event.getItem().getTypeId()).name()))));
		}

	}

	public Boolean getDrugIds(ItemStack item){
		for (ItemStack s : ConfigUtils.getDrugIds().keySet()){
			if (s.getTypeId() == item.getTypeId() && s.getDurability() == item.getDurability()){
				return true;
			}
		}
		return false;
	}

	public PotionEffectType toPotion(String s){

		switch(s.toLowerCase()){
		case "blind":
			return PotionEffectType.BLINDNESS;
		case "blindness":
			return PotionEffectType.BLINDNESS;
		case "confusion":
			return PotionEffectType.CONFUSION;
		case "resistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "damage resistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "damageresistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "damage_resistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "fastdig":
			return PotionEffectType.FAST_DIGGING;
		case "fastdigging":
			return PotionEffectType.FAST_DIGGING;
		case "fast_digging":
			return PotionEffectType.FAST_DIGGING;
		case "speeddigging":
			return PotionEffectType.FAST_DIGGING;
		case "speeddig":
			return PotionEffectType.FAST_DIGGING;
		case "speed_digging":
			return PotionEffectType.FAST_DIGGING;
		case "haste":
			return PotionEffectType.FAST_DIGGING;
		case "fire":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fireres":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fire_res":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fireresistance":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fire_resistance":
			return PotionEffectType.FIRE_RESISTANCE;
		case "harm":
			return PotionEffectType.HARM;
		case "heal":
			return PotionEffectType.HEAL;
		case "health":
			return PotionEffectType.HEAL;
		case "hunger":
			return PotionEffectType.HUNGER;
		case "highdmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high_dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "highdamage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high_damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increasedmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase_dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increasedamage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase_damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "strength":
			return PotionEffectType.INCREASE_DAMAGE;
		case "invisible":
			return PotionEffectType.INVISIBILITY;
		case "invisibility":
			return PotionEffectType.INVISIBILITY;
		case "jump":
			return PotionEffectType.JUMP;
		case "jumpboost":
			return PotionEffectType.JUMP;
		case "jump boost":
			return PotionEffectType.JUMP;
		case "jump_boost":
			return PotionEffectType.JUMP;
		case "nightvision":
			return PotionEffectType.NIGHT_VISION;
		case "night vision":
			return PotionEffectType.NIGHT_VISION;
		case "night_vision":
			return PotionEffectType.NIGHT_VISION;
		case "poison":
			return PotionEffectType.POISON;
		case "regeneration":
			return PotionEffectType.REGENERATION;
		case "slow":
			return PotionEffectType.SLOW;
		case "slowdigging":
			return PotionEffectType.SLOW_DIGGING;
		case "slow digging":
			return PotionEffectType.SLOW_DIGGING;
		case "slow_digging":
			return PotionEffectType.SLOW_DIGGING;
		case "speed":
			return PotionEffectType.SPEED;
		case "waterbreathing":
			return PotionEffectType.WATER_BREATHING;
		case "water breathing":
			return PotionEffectType.WATER_BREATHING;
		case "water_breathing":
			return PotionEffectType.WATER_BREATHING;
		case "underwaterbreathing":
			return PotionEffectType.WATER_BREATHING;
		case "underwater breathing":
			return PotionEffectType.WATER_BREATHING;
		case "underwater_breathing":
			return PotionEffectType.WATER_BREATHING;
		case "weak":
			return PotionEffectType.WEAKNESS;
		case "weakness":
			return PotionEffectType.WEAKNESS;
		case "wither":
			return PotionEffectType.WITHER;
		case "withereffect":
			return PotionEffectType.WITHER;
		case "wither effect":
			return PotionEffectType.WITHER;
		case "wither_effect":
			return PotionEffectType.WITHER;
		}
		return null;
	}

}