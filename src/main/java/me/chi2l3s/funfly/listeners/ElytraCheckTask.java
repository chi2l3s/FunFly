package me.chi2l3s.funfly.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ElytraCheckTask extends BukkitRunnable {

    private final Player player;

    public ElytraCheckTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (player.isFlying()) {
            ItemStack elytra = player.getInventory().getChestplate();
            if (elytra == null || elytra.getType() != Material.ELYTRA) {
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        }
    }
}

