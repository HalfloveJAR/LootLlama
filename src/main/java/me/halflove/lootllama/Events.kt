package me.halflove.lootllama

import me.halflove.lootllama.managers.LlamaAbilities
import me.halflove.lootllama.managers.LlamaSpawn
import me.halflove.lootllama.misc.DropItem
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Llama
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.spigotmc.event.entity.EntityMountEvent

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

class Events: Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun hitLlama(event: EntityDamageByEntityEvent) {
        if (event.entityType == EntityType.LLAMA && event.damager is Player) {
            if(LlamaSpawn.customLlama.containsKey(event.entity as Llama)) {
                DropItem.itemDrop(event.entity.location)

                val randomNumber = (0..16).random()
                if(randomNumber == 0) { //7/13
                    LlamaAbilities.baby(event.entity as Llama)
                }
                if(randomNumber == 1 || randomNumber == 2) {
                    LlamaAbilities.superSpeed(event.entity as Llama)
                }
                if(randomNumber == 3) {
                    LlamaAbilities.knockback(event.entity as Llama)
                }
                if(randomNumber == 4 || randomNumber == 5) {
                    LlamaAbilities.warp(event.entity as Llama, event.damager as Player)
                }

                if(randomNumber == 6) {
                    LlamaAbilities.grabLoot(event.damager as Player)
                }

                if(randomNumber == 7) {
                    val randomNumberRareTable = (0..2).random()
                    if(randomNumberRareTable == 0) {
                        LlamaAbilities.grabKey(event.damager as Player)
                    }
                }
                event.damage = 1.0
            }
        }
    }

    @EventHandler
    fun clickLlama(event: PlayerInteractEntityEvent){
        if(event.rightClicked.type == EntityType.LLAMA && event.player.location.world?.name == "world") {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun deathLlama(event: EntityDeathEvent) {
        if(event.entityType == EntityType.LLAMA && LlamaSpawn.customLlama.containsKey(event.entity as Llama)) {
            event.drops.clear()
            LlamaSpawn.llamaActive = false
        }
    }

    @EventHandler
    fun hurtLlama(event: EntityDamageEvent) {
        if(event.entityType == EntityType.LLAMA && LlamaSpawn.customLlama.containsKey(event.entity as Llama)) {
            if (event.entity.location.y < LlamaSpawn.y - 38) {
                event.entity.teleport(LlamaSpawn.loc)
            }
            if(event.cause == EntityDamageEvent.DamageCause.FIRE) {
                event.isCancelled = true
            }
        }
    }

}