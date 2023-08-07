package me.halflove.lootllama.managers

import me.halflove.lootllama.misc.DropItem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Llama
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/*
*
* @author Halflove
*
* http://Halflove.us
* https://github.com/HalfloveJAR
*
*/

object LlamaAbilities {

    val plugin: Plugin? = Bukkit.getServer().pluginManager.getPlugin("LootLlama")
    var thisStuckCheckID: Int = -1

    fun baby(llama: Llama) {
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    player.playSound(player.location, Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 2.0F, 1.0F);
                }
            }
        }
        llama.setBaby()
        if (plugin != null) {
            Bukkit.getServer().scheduler.scheduleSyncDelayedTask(plugin, {
                llama.setAdult()
                for(player: Player in Bukkit.getOnlinePlayers()) {
                    if(player.location.world == llama.location.world) {
                        if(player.location.distance(llama.location) < 10 && LlamaSpawn.llamaActive) {
                            player.playSound(player.location, Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 2.0F, 1.0F);
                        }
                    }
                }
            }, 40)
        }
    }

    fun warp(llama: Llama, attacker: Player) {
        if (llama.isAdult) {
            llama.teleport(LlamaSpawn.loc)
        }
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    if (llama.isAdult)
                        player.playSound(player.location, Sound.ENTITY_SHULKER_TELEPORT, 2.0F, 2.0F);
                }
            }
        }
    }

    fun knockback(llama: Llama) {
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    player.velocity = player.location.direction.multiply(-1.1);
                    player.playSound(player.location, Sound.ENTITY_BAT_TAKEOFF, 2.0F, 1.0F);
                }
            }
        }
    }

    fun superSpeed(llama: Llama) {
        for(player: Player in Bukkit.getOnlinePlayers()) {
            if(player.location.world == llama.location.world) {
                if(player.location.distance(llama.location) < 10) {
                    player.playSound(player.location, Sound.ENTITY_EVOKER_CELEBRATE, 2.0F, 2.0F);
                }
            }
        }
        llama.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 150, 3))
    }

    fun grabLoot(attacker: Player) {
        attacker.inventory.addItem(DropItem.getRandomItem())
        attacker.playSound(attacker.location, Sound.ENTITY_COW_HURT, 2.0F, 2.0F);
        attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oYou pull some loot from Larry's fur! Ouch!"))
    }

    fun grabKey(attacker: Player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p VoteCrate 1 ${attacker.name}")
        attacker.playSound(attacker.location, Sound.ENTITY_COW_HURT, 2.0F, 0.0F);
        attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&oYou yank a Vote Crate Key from behind Larry's ear! MOOOOOOOO!"))
    }

    fun grabRareKey(attacker: Player, mostHits: Int) {

        val randomNumber = (0..3).random()

        if (randomNumber == 0) {
            grabLegendaryKey(attacker, mostHits)
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p RareCrate 1 ${attacker.name}")
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "broadcast &5${attacker.name}&f hit Larry the most times and earned a &lRare Crate Key&r&f (${mostHits} total hits)!")
            for(player: Player in Bukkit.getOnlinePlayers()) {
                player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 0.0F)
            }
        }
    }

    fun grabLegendaryKey(attacker: Player, mostHits: Int) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p LegendaryCrate 1 ${attacker.name}")
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "broadcast &5${attacker.name}&f hit Larry the most times and earned a &l&5Legendary Crate Key&r&f (${mostHits} total hits)!")
        for(player: Player in Bukkit.getOnlinePlayers()) {
            player.playSound(player.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 2.0F, 0.0F)
        }
    }

    fun grabParticipationAward(player: Player, hits: Int) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give p VoteCrate 1 ${player.name}")
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cLarry &7-> &c&lYou&r&6] &fYou hit me &l$hits &r&ftimes and earned a &lVote Crate key&r&f!"))
        player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_FLUTE, 2.0F, 0.0F)
    }

}