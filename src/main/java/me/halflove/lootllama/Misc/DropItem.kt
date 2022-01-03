package me.halflove.lootllama.Misc

import me.halflove.lootllama.Managers.LlamaAbilities
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

object DropItem {

    var lootTableSize = 0

    fun generateLootTable(){
        val contentsSection: ConfigurationSection? = Storage.data.getConfigurationSection("loot")
        if (contentsSection != null) {
            for(lootTableSlot in contentsSection.getKeys(false)) {
                lootTableSize += 1
            }
        }
    }

    fun itemDrop(loc: Location) {
        val randomNumber = (0..8).random()
        if(randomNumber == 0) {
            val randomNumber2 = (1..lootTableSize).random()
            successfulDrop(loc, getSlotItemStack(randomNumber2))
        }
    }

    private fun successfulDrop(loc: Location, item: ItemStack) {
        loc.world?.dropItemNaturally(loc, item)
    }

    //Builds and returns the requested item (slot) stored in the data.yml
    private fun getSlotItemStack(slot: Int): ItemStack {
        return ItemStack(
            Material.valueOf(Storage.data.get("loot.$slot.type").toString()),
            Storage.data.get("loot.$slot.amount") as Int
        )
    }

}