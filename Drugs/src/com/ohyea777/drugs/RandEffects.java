package com.ohyea777.drugs;

import java.util.Random;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RandEffects implements IEffects{

	public Set<String> effects;
	public Player player;
	public Drug drug;
	
	public RandEffects(Player player, Drug drug){
		this.effects = drug.getRandEffects();
		this.player = player;
		this.drug = drug; 
	}
	
	@Override
	public void doEffects() {
		if (this.hasEffects()){
			for (String s : this.effects){
				Integer strength = this.getStrength(s);
				Integer duration = this.getDuration(s);
				Integer chance = this.getChance(s);
				
				if (this.getRandPercent(chance)){
					player.addPotionEffect(new PotionEffect(toPotion(s), duration*20, strength));
				}
			}
		}
	}

	@Override
	public Integer getStrength(String effect){
		if (this.hasEffects()){
			return this.getConfigSection().getInt(effect + ".Strength");
		}
		return null;
	}

	@Override
	public Integer getDuration(String effect){
		if (this.hasEffects()){
			return this.getConfigSection().getInt(effect + ".Duration");
		}
		return null;
	}

	@Override
	public ConfigurationSection getConfigSection() {
		return this.drug.getConfigSection().getConfigurationSection("Random_Effects");
	}


	public boolean hasEffects() {
		if (this.getConfigSection() != null){
			return true;
		}
		return false;
	}
	
	public Integer getChance(String effect){
		if (this.hasEffects()){
			return this.getConfigSection().getInt(effect + ".Chance");
		}
		return null;
	}
	
	public boolean getRandPercent(int percent) {
	    Random rand = new Random();
	    return rand.nextInt(100) <= percent;
	}
	
	public PotionEffectType toPotion(String s){

		switch(s.toLowerCase()){
		case "blind":
			return PotionEffectType.BLINDNESS;
		case "blindness":
			return PotionEffectType.BLINDNESS;
		case "confusion":
			return PotionEffectType.CONFUSION;
		case "nausea":
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
