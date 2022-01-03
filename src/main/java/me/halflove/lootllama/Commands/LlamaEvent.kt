package me.halflove.lootllama.Commands

import me.halflove.lootllama.Managers.LlamaSpawn
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

class LlamaEvent: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.isOp){
            LlamaSpawn.spawnLlama()
        }
        return true
    }
}