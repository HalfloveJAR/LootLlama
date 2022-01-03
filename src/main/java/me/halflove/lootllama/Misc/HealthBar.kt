package me.halflove.lootllama.Misc

import org.bukkit.Bukkit
import org.bukkit.ChatColor

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

object HealthBar {

    // Get the current max health at a specific moment in time
    fun calcMaxHealth(): Double {
        var hp: Double = 0.0
        for(player in Bukkit.getOnlinePlayers()){
            hp += 3.0
        }
        return hp
    }

}