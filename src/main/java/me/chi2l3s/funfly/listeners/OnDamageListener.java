package me.chi2l3s.funfly.listeners;

import me.chi2l3s.funfly.FunFly;
import me.chi2l3s.funfly.utils.HEXColor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamageListener implements Listener {

    private final FunFly plugin;

    public OnDamageListener(FunFly plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player player){
            if (player.isFlying()){
                String onDamageMessage = plugin.getConfig().getString("messages.get-damage-on-fly");
                int damageAmount = plugin.getConfig().getInt("damage-on-damage");
                player.setFlying(false);
                player.setAllowFlight(false);
                player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', onDamageMessage)));
                player.setHealth(player.getHealth()-damageAmount);
                if (player.getHealth() <= damageAmount){
                    player.setHealth(0);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player player){
            if (player.isFlying()){
                String onDamageMessage = plugin.getConfig().getString("messages.damage-on-fly");
                int damageAmount = plugin.getConfig().getInt("damage-on-damage");
                player.setFlying(false);
                player.setAllowFlight(false);
                player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', onDamageMessage)));
                player.setHealth(player.getHealth()-damageAmount);
                if (player.getHealth() <= damageAmount){
                    player.setHealth(0);
                }
            }
        }
    }
}
