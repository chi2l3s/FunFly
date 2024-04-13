package me.chi2l3s.funfly.listeners;

import me.chi2l3s.funfly.FunFly;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FunFly plugin;

    public PlayerJoinListener(FunFly plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        new ElytraCheckTask(player).runTaskTimer(plugin, 0L, 1L);
    }

}
