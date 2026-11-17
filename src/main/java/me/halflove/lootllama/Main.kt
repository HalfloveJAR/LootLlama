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
* https://Halflove.dev
* https://github.com/HalfloveJAR
*
*/

class Main: JavaPlugin() {

    val abilityTable: ArrayList<String> = arrayListOf();

    override fun onEnable(){
        saveDefaultConfig()
        getCommand("lootllama")?.setExecutor(LlamaEvent())
        Bukkit.getPluginManager().registerEvents(Events(), this)
        Storage.createDataFile(this)
        DropItem.generateLootTable()
        generateAbilityTable()
    }

    companion object {
        @JvmStatic lateinit var instance: Main
    }

    init {
        instance = this
    }

    private fun generateAbilityTable() {
        val abilityConfigKeys = config.getConfigurationSection("events.ability-weight")?.getKeys(false).orEmpty()
        for (key in abilityConfigKeys) {
            var abilityWeight = config.getInt("events.ability-weight.$key", 0)
            while (abilityWeight > 0) {
                abilityWeight -= 1
                abilityTable.add(key)
            }
        }
        logger.info(abilityTable.toString())
    }

}