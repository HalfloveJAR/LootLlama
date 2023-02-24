package me.halflove.lootllama

import me.halflove.lootllama.commands.LlamaEvent
import me.halflove.lootllama.misc.DropItem
import me.halflove.lootllama.misc.Storage
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
        Bukkit.getPluginManager().registerEvents(Events(), this)
        Storage.createDataFile(this)
        DropItem.generateLootTable()
    }

    companion object {
        @JvmStatic lateinit var instance: Main
    }

    init {
        instance = this
    }

}