package me.halflove.lootllama.commands

import me.halflove.lootllama.managers.LlamaAbilities
import me.halflove.lootllama.managers.LlamaSpawn
import me.halflove.lootllama.misc.Storage
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Llama
import org.bukkit.entity.Player
import kotlin.properties.Delegates

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

class LlamaEvent: CommandExecutor {

    private var countdownTask by Delegates.notNull<Int>()

    private fun countdown(sender: CommandSender, seconds: Int){
        var timeLeft = seconds
        for (player: Player in Bukkit.getOnlinePlayers()) {
            player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 0.0F);
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "broadcast &fLarry the Loot Llama will spawn at &5/Warp Arena&f in &d$timeLeft seconds&r&f. &nMake some space in your inventory!&r")

        LlamaAbilities.plugin?.let {
            countdownTask = Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(it, {
                if (timeLeft > 0) {
                    if (timeLeft <= 5 || timeLeft == 10) {
                        for (player: Player in Bukkit.getOnlinePlayers()) {
                            if (timeLeft <= 5) {
                                player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_BIT, 2.0F, 1.0F);
                            } else {
                                player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_BIT, 2.0F, 0.0F);
                            }
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "broadcast &fA Loot Llama will spawn at &5/Warp Arena&f in &d$timeLeft seconds")
                    }
                    timeLeft--
                } else {
                    countdownComplete(sender)
                }
            }, 60L, 20L)
        }
    }

    private fun countdownComplete(sender: CommandSender) {
        Bukkit.getServer().scheduler.cancelTask(countdownTask)
        if(Storage.data.get("spawn.x") != null) {
            Bukkit.broadcastMessage("")
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c♫"))
            Bukkit.broadcastMessage(
                ChatColor.translateAlternateColorCodes(
                    '&',
                    "  &b♫  &e♫  &fA &dLoot Llama &fhas spawned at &6/Warp Arena"
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
        }else{
            sender.sendMessage("Spawn location not defined, try /llama setspawn")
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.hasPermission("mcf.admin") && args.isEmpty()){
            countdown(sender, 25)
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