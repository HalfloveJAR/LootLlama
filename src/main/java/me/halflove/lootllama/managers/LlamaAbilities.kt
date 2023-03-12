package me.halflove.lootllama.managers

import me.halflove.lootllama.misc.DropItem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
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
                        if(player.location.distance(llama.location) < 10 && LlamaSpawn.llamaActive) {
                            player.playSound(player.location, Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 2.0F, 1.0F);
                        }
                    }
                }
            }, 100)
        }
    }

    fun warp(llama: Llama, attacker: Player) {
        val lastLoc: Location = llama.location
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

    fun grabLoot(attacker: Player) {
        val randomNumber = (1..DropItem.lootTableSize).random()
        attacker.inventory.addItem(DropItem.getSlotItemStack(randomNumber))
        attacker.playSound(attacker.location, Sound.ENTITY_COW_HURT, 2.0F, 2.0F);
        attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oYou pull some loot from Larry's fur! Ouch!"))
    }

}