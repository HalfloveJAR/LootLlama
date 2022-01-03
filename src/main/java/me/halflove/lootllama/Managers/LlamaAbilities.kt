package me.halflove.lootllama.Managers

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Llama
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

object LlamaAbilities {

    val plugin: Plugin? = Bukkit.getServer().pluginManager.getPlugin("LootLlama")

    fun baby(llama: Llama) {
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    player.playSound(player.location, Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 2.0F, 1.0F);
                }
            }
        }
        llama.setBaby()
        if (plugin != null) {
            Bukkit.getServer().scheduler.scheduleSyncDelayedTask(plugin, {
                llama.setAdult()
                for(player: Player in Bukkit.getOnlinePlayers()) {
                    if(player.location.world == llama.location.world) {
                        if(player.location.distance(llama.location) < 10) {
                            player.playSound(player.location, Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 2.0F, 1.0F);
                        }
                    }
                }
            }, 100)
        }
    }

    fun warp(llama: Llama, attacker: Player) {
        var lastLoc: Location = llama.location
        llama.teleport(attacker.location)
        attacker.teleport(lastLoc)
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    player.playSound(player.location, Sound.ENTITY_SHULKER_TELEPORT, 2.0F, 2.0F);
                }
            }
        }
    }

    fun knockback(llama: Llama) {
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    player.velocity = player.location.direction.multiply(-1.1);
                    player.playSound(player.location, Sound.ENTITY_BAT_TAKEOFF, 2.0F, 1.0F);
                }
            }
        }
    }

    fun superSpeed(llama: Llama) {
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    player.playSound(player.location, Sound.ENTITY_EVOKER_CELEBRATE, 2.0F, 2.0F);
                }
            }
        }
        llama.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 150, 3))
    }

}