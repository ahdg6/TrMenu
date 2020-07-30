package me.arasple.mc.trmenu.listeners.bukkit

import io.izzel.taboolib.module.db.local.LocalPlayer
import io.izzel.taboolib.module.inject.TListener
import me.arasple.mc.trmenu.data.MetaPlayer.setMeta
import me.arasple.mc.trmenu.utils.Tasks
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author Arasple
 * @date 2020/7/30 11:55
 */
@TListener
class ListenerPlayerJoin : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player

        Tasks.run(true) {
            LocalPlayer.get(player).getConfigurationSection("TrMenu.Data")?.let {
                val keys = it.getKeys(true)
                keys.forEach { key ->
                    player.setMeta("{data:$key}", it.get(key).toString())
                }
            }
        }
    }

}