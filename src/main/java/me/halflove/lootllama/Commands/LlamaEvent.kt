package me.halflove.lootllama.Commands

import me.halflove.lootllama.Managers.LlamaAbilities
import me.halflove.lootllama.Managers.LlamaSpawn
import me.halflove.lootllama.Misc.Storage
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Llama
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
        if(sender.isOp && args.isEmpty()){
            if(Storage.data.get("spawn.x") != null && !LlamaSpawn.llamaActive) {
                Bukkit.broadcastMessage("")
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c♫"))
                Bukkit.broadcastMessage(
                    ChatColor.translateAlternateColorCodes(
                        '&',
                        "  &b♫  &e♫  &fA &dLoot Llama &fhas spawned at &6/spawn"
                    )
                )
                Bukkit.broadcastMessage(
                    ChatColor.translateAlternateColorCodes(
                        '&',
                        "&a♫   &c♫          &fCome fight him for valuable loot!"
                    )
                )
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "  &e♫ "))
                Bukkit.broadcastMessage("")
                LlamaSpawn.spawnLlama()
                for (player: Player in Bukkit.getOnlinePlayers()) {
                    player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_BIT, 2.0F, 0.0F);
                    LlamaAbilities.plugin?.let {
                        Bukkit.getServer().scheduler.scheduleSyncDelayedTask(it, {
                            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_BIT, 2.0F, 1.0F);
                        }, 2)
                    }
                    LlamaAbilities.plugin?.let {
                        Bukkit.getServer().scheduler.scheduleSyncDelayedTask(it, {
                            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_BIT, 2.0F, 2.0F);
                        }, 4)
                    }
                }
            }else if(LlamaSpawn.llamaActive){
                sender.sendMessage("Loot Llama currently active")
            }else{
                sender.sendMessage("Spawn location not defined, try /llama setspawn")
            }
        }else if(sender.isOp && args[0] == "setspawn"){
            val player: Player = sender as Player
            Storage.addDataToFile("spawn.x", player.location.x)
            Storage.addDataToFile("spawn.y", player.location.y)
            Storage.addDataToFile("spawn.z", player.location.z)
            sender.sendMessage("Llama spawn set successfully")
        }
        return true
    }
}