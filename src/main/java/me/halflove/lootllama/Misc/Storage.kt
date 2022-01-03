package me.halflove.lootllama.Misc

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

object Storage {

    lateinit var data: FileConfiguration
    lateinit var dataFile: File

    //Creates a data.yml if one doesn't exist
    fun createDataFile(plugin: Plugin){

        if(!plugin.dataFolder.exists()) plugin.dataFolder.mkdir()
        dataFile = File(plugin.dataFolder, "data.yml")

        if (!dataFile.exists()) try {
            dataFile.createNewFile()
        } catch (e: IOException) {
            Bukkit.getServer().logger.severe("Could not create data.yml!")
        }

        data = YamlConfiguration.loadConfiguration(dataFile)

    }

    //Used to save data.yml after a change is made
    private fun saveDataFile(){
        try {
            data.save(dataFile)
        } catch (e: IOException) {
            Bukkit.getServer().logger.severe("Could not save data.yml!")
        }
    }

    //Used to add information to data.yml
    fun addDataToFile(path: String, value: Any?){
        data.set(path, value)
        saveDataFile()
    }

}