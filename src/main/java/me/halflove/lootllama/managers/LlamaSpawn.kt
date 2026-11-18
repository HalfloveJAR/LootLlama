package me.halflove.lootllama.managers

import me.halflove.lootllama.Main
import me.halflove.lootllama.misc.HealthBar
import me.halflove.lootllama.misc.Storage
import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Llama
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/*
*
* @author Halflove
*
* https://Halflove.dev
* https://github.com/HalfloveJAR
*
*/

object LlamaSpawn {

    var llamaActive: Boolean = false
    var customLlama: HashMap<Llama, Boolean> = HashMap()

    // Only world the Llama can spawn in (Spawn)
    private val world: World? = Bukkit.getWorld(Storage.data.get("spawn.world").toString())
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

        val lootLlama: Llama = world?.spawnEntity(getLocation(), EntityType.LLAMA) as Llama
        lootLlama.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 99999999, 1))
        lootLlama.maxHealth = HealthBar.calcMaxHealth()
        lootLlama.health = HealthBar.calcMaxHealth()
        lootLlama.customName = ChatColor.translateAlternateColorCodes('&', Main.instance.config.getString("entity.name") ?: "&c&lL&6&la&e&lr&a&lr&b&ly")
        lootLlama.isCustomNameVisible = true

        val llamaColorPool = listOf(
            Llama.Color.CREAMY,
            Llama.Color.BROWN,
            Llama.Color.GRAY,
            Llama.Color.WHITE
        )

        lootLlama.color = llamaColorPool.random()
        lootLlama.isCarryingChest = true
        lootLlama.isTamed = true

        // Add the new llama to the map
        customLlama[lootLlama] = true

        val carpetPool = listOf(
            ItemStack(Material.CYAN_CARPET),
            ItemStack(Material.MAGENTA_CARPET),
            ItemStack(Material.RED_CARPET),
            ItemStack(Material.BLUE_CARPET),
            ItemStack(Material.BROWN_CARPET),
            ItemStack(Material.GREEN_CARPET),
            ItemStack(Material.LIGHT_BLUE_CARPET),
            ItemStack(Material.LIME_CARPET),
            ItemStack(Material.ORANGE_CARPET),
            ItemStack(Material.PINK_CARPET),
            ItemStack(Material.PURPLE_CARPET),
            ItemStack(Material.YELLOW_CARPET),
            ItemStack(Material.WHITE_CARPET),
            ItemStack(Material.GRAY_CARPET),
            ItemStack(Material.BLACK_CARPET)
            )

        val randomCarpet = carpetPool.random()
        lootLlama.inventory.decor = randomCarpet
        llamaActive = true
    }

    // Get location to spawn Llama at
    private fun getLocation(): Location {
        return loc
    }

}