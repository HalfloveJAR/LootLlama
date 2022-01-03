package me.halflove.lootllama.Listeners

import me.halflove.lootllama.Misc.DropItem
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Llama
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEntityEvent

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
        if(event.entity.location.world?.name == "world") {
            if (event.entityType == EntityType.LLAMA && event.damager is Player) {
                event.damage = 1.0
                DropItem.itemDrop(event.entity.location)
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
        if(event.entityType == EntityType.LLAMA && event.entity.location.world?.name == "world") {
            event.drops.clear()
        }
    }

}