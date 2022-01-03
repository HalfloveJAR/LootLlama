package me.halflove.lootllama.Listeners

import me.halflove.lootllama.Misc.DropItem
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

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
    fun hitLlama(event: EntityDamageByEntityEvent){
        if(event.entity.location.world?.name == "world") {
            if (event.entityType == EntityType.LLAMA && event.damager is Player) {
                event.damage = 1.0
                DropItem.itemDrop(event.entity.location)
            }
        }
    }

}