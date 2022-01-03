package me.halflove.lootllama.Managers

import me.halflove.lootllama.Misc.HealthBar
import org.bukkit.*
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Horse
import org.bukkit.entity.Llama
import org.bukkit.inventory.EntityEquipment
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

object LlamaSpawn {

    // Only world the Llama can spawn in (Spawn)
    private val world: World? = Bukkit.getWorld("world")
    val lootLlama: Llama = world?.spawnEntity(getLocation(), EntityType.LLAMA) as Llama

    // Spawn the llama at set location
    fun spawnLlama() {
        lootLlama.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 99999999, 1))
        lootLlama.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 99999999, 2))
        lootLlama.health = HealthBar.calcMaxHealth()
        lootLlama.color = Llama.Color.CREAMY
        lootLlama.isTamed = true
    }

    // Get location to spawn Llama at
    private fun getLocation(): Location {
        val x: Double = 13.0//0.0
        val y: Double = 63.0//81.0
        val z: Double = 140.0//26.0
        return Location(world, x, y, z)
    }

}