package me.halflove.lootllama

import me.halflove.lootllama.Commands.LlamaEvent
import me.halflove.lootllama.Listeners.DamageLlama
import me.halflove.lootllama.Misc.DropItem
import me.halflove.lootllama.Misc.Storage
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

class Main: JavaPlugin() {

    override fun onEnable(){
        getCommand("lootllama")?.setExecutor(LlamaEvent())
        Bukkit.getPluginManager().registerEvents(DamageLlama(), this)
        Storage.createDataFile(this)
        DropItem.generateLootTable()
    }
}