package me.halflove.lootllama.misc

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
        val randomNumber = (1..100).random()
        if(randomNumber >= 69) {
            successfulDrop(loc, getRandomItem())
        }
    }

    private fun successfulDrop(loc: Location, item: ItemStack) {
        loc.world?.dropItemNaturally(loc, item)
    }

    fun getRandomItem(): ItemStack {
        val randomNumber = (1..lootTableSize).random()
        return getSlotItemStack(randomNumber)
    }

    //Builds and returns the requested item (slot) stored in the data.yml
    fun getSlotItemStack(slot: Int): ItemStack {

        val stringAmount: String = Storage.data.get("loot.$slot.amount").toString()

        val amount = if (stringAmount.contains('-')) {
            val splitAmount = stringAmount.split("-")
            val randomNumber = (splitAmount[0].toInt() .. splitAmount[1].toInt()).random()
            randomNumber
        } else {
            Storage.data.get("loot.$slot.amount") as Int
        }

        return ItemStack(Material.valueOf(Storage.data.get("loot.$slot.type").toString()), amount)
    }

}