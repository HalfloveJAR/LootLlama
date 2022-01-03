package me.halflove.lootllama.Listeners

import me.halflove.lootllama.Managers.LlamaAbilities
import me.halflove.lootllama.Managers.LlamaSpawn
import me.halflove.lootllama.Misc.DropItem
import org.bukkit.entity.EntityType
import org.bukkit.entity.Llama
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.util.Vector

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

class DamageLlama: Listener {

    @EventHandler
    fun hitLlama(event: EntityDamageByEntityEvent) {
        if(LlamaSpawn.customLlama.containsKey(event.entity as Llama)) {
            if (event.entityType == EntityType.LLAMA && event.damager is Player) {
                event.damage = 1.0
                DropItem.itemDrop(event.entity.location)

                var randomNumber = (0..7).random()
                if(randomNumber == 0) {
                    LlamaAbilities.baby(event.entity as Llama)
                }
                if(randomNumber == 1) {
                    LlamaAbilities.superSpeed(event.entity as Llama)
                }
                if(randomNumber == 2) {
                    LlamaAbilities.knockback(event.entity as Llama)
                }
                if(randomNumber == 3) {
                    LlamaAbilities.warp(event.entity as Llama, event.damager as Player)
                }
            }
        }
    }

    @EventHandler
    fun feedLlama(event: PlayerInteractEntityEvent){
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
        if(event.entity.type == EntityType.LLAMA && LlamaSpawn.customLlama.containsKey(event.entity as Llama)) {
            if(event.cause == EntityDamageEvent.DamageCause.FIRE) {
                event.isCancelled = true
                LlamaSpawn.customLlama.remove(event.entity as Llama)
            }
        }
    }

}