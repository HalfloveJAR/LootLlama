package me.halflove.lootllama.managers

import me.halflove.lootllama.misc.HealthBar
import me.halflove.lootllama.misc.Storage
import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Rabbit
import org.bukkit.attribute.Attribute;
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

    var llamaActive: Boolean = false
    var customLlama: HashMap<Rabbit, Boolean> = HashMap()

    // Only world the Llama can spawn in (Spawn)
    private val world: World? = Bukkit.getWorld(Storage.data.get("world-name").toString())
    private val x: Double = Storage.data.get("spawn.x") as Double
    val y: Double = if (Storage.data.get("spawn.y") is Int) Storage.data.get("spawn.y") as Int * 1.0 else Storage.data.get("spawn.y") as Double
    private val z: Double = Storage.data.get("spawn.z") as Double
    val loc: Location = Location(world, x, y, z)

    // Spawn the llama at set location
    fun spawnLlama() {

        // CHECK: If a llama is already active, remove the old entity and clear data
        if (llamaActive || customLlama.isNotEmpty()) {
            // Loop through existing llamas in the hashmap
            for (oldLlama in customLlama.keys) {
                // Remove the entity from the game world
                oldLlama.remove()
            }
            // Clear the map to make room for the new one
            customLlama.clear()
            llamaActive = false
        }

        val lootLlama: Rabbit = world?.spawnEntity(getLocation(), EntityType.RABBIT) as Rabbit
        lootLlama.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 99999999, 1))
        lootLlama.maxHealth = HealthBar.calcMaxHealth()
        lootLlama.health = HealthBar.calcMaxHealth()
        lootLlama.customName = ChatColor.translateAlternateColorCodes('&', "&c&lJ&6&le&e&lr&a&lr&b&ly")
        lootLlama.isCustomNameVisible = true
        lootLlama.rabbitType = Rabbit.Type.THE_KILLER_BUNNY
        lootLlama.getAttribute(Attribute.SCALE)?.baseValue = 3.5;

        // Add the new llama to the map
        customLlama[lootLlama] = true
        llamaActive = true
    }

    // Get location to spawn Llama at
    private fun getLocation(): Location {
        return loc
    }

}