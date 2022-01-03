package me.halflove.lootllama.Managers

import me.halflove.lootllama.Misc.HealthBar
import org.bukkit.*
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Horse
import org.bukkit.entity.Llama
import org.bukkit.inventory.EntityEquipment
import org.bukkit.inventory.ItemStack
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

    // Spawn the llama at set location
    fun spawnLlama() {
        val lootLlama: Llama = world?.spawnEntity(getLocation(), EntityType.LLAMA) as Llama
        lootLlama.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 99999999, 1))
        lootLlama.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 99999999, 2))
        lootLlama.health = HealthBar.calcMaxHealth()
        lootLlama.color = Llama.Color.CREAMY
        lootLlama.isTamed = true

        val randomNumber = (0..12).random()
        if(randomNumber == 0){
            lootLlama.inventory.decor = ItemStack(Material.CYAN_CARPET)
        }
        if(randomNumber == 1){
            lootLlama.inventory.decor = ItemStack(Material.MAGENTA_CARPET)
        }
        if(randomNumber == 2){
            lootLlama.inventory.decor = ItemStack(Material.RED_CARPET)
        }
        if(randomNumber == 3){
            lootLlama.inventory.decor = ItemStack(Material.BLUE_CARPET)
        }
        if(randomNumber == 4){
            lootLlama.inventory.decor = ItemStack(Material.BROWN_CARPET)
        }
        if(randomNumber == 5){
            lootLlama.inventory.decor = ItemStack(Material.GREEN_CARPET)
        }
        if(randomNumber == 6){
            lootLlama.inventory.decor = ItemStack(Material.LIGHT_BLUE_CARPET)
        }
        if(randomNumber == 7){
            lootLlama.inventory.decor = ItemStack(Material.LIME_CARPET)
        }
        if(randomNumber == 8){
            lootLlama.inventory.decor = ItemStack(Material.ORANGE_CARPET)
        }
        if(randomNumber == 9){
            lootLlama.inventory.decor = ItemStack(Material.PINK_CARPET)
        }
        if(randomNumber == 10){
            lootLlama.inventory.decor = ItemStack(Material.PURPLE_CARPET)
        }
        if(randomNumber == 11){
            lootLlama.inventory.decor = ItemStack(Material.YELLOW_CARPET)
        }
        if(randomNumber == 12){
            lootLlama.inventory.decor = ItemStack(Material.WHITE_CARPET)
        }
    }

    // Get location to spawn Llama at
    private fun getLocation(): Location {
        val x: Double = 13.0//0.0
        val y: Double = 63.0//81.0
        val z: Double = 140.0//26.0
        return Location(world, x, y, z)
    }

}