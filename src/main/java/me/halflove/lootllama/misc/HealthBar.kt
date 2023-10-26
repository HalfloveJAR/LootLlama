package me.halflove.lootllama.misc

import org.bukkit.Bukkit

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
            hp += 14.5
        }
        if (hp > 200) hp = 200.0;
        return hp
    }

}