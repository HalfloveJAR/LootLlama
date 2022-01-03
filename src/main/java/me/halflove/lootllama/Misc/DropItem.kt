package me.halflove.lootllama.Misc

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

object DropItem {

    fun itemDrop(loc: Location) {
        val randomNumber = (0..2).random()
        if(randomNumber == 0){
            successfulDrop(loc)
        }
    }

    private fun successfulDrop(loc: Location) {
        val item = ItemStack(Material.DIAMOND)
        loc.world?.dropItemNaturally(loc, item)
    }

}