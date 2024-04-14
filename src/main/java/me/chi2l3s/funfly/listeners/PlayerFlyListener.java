package me.chi2l3s.funfly.listeners;

import me.chi2l3s.funfly.FunFly;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class PlayerFlyListener implements Listener {

    private final FunFly plugin;
    private final HashMap<UUID, Location> playerLocations = new HashMap<>();
    private final HashMap<UUID, Integer> moveCounters = new HashMap<>();

    public PlayerFlyListener(FunFly plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        UUID playerId = player.getUniqueId();
        Location currentLocation = player.getLocation();

        int reduceDurability = plugin.getConfig().getInt("reduce-durability");
        boolean removeIfBroken = plugin.getConfig().getBoolean("remove-if-broken");

        if (player.isFlying() && player.getGameMode() != GameMode.CREATIVE) {
            ItemStack elytra = player.getInventory().getChestplate();
            if (elytra != null && elytra.getType() == Material.ELYTRA) {
                Location previousLocation = playerLocations.get(playerId);
                int moveCounter = moveCounters.getOrDefault(playerId, 0) + 1;

                if (previousLocation != null && hasMoved(previousLocation, currentLocation) && moveCounter >= 15) {
                    Damageable damageable = (Damageable) elytra.getItemMeta();
                    if (damageable.getDamage() < elytra.getType().getMaxDurability() - reduceDurability) {
                        damageable.setDamage(damageable.getDamage() + reduceDurability);
                        elytra.setItemMeta((ItemMeta) damageable);
                    } else {
                        // Элитра сломана
                        if (removeIfBroken) {
                            player.getInventory().setChestplate(null);
                        } else {
                            damageable.setDamage(elytra.getType().getMaxDurability());
                            elytra.setItemMeta((ItemMeta) damageable);
                        }
                        player.setFlying(false);
                        player.setAllowFlight(false);
                    }
                    moveCounter = 0;
                }

                playerLocations.put(playerId, currentLocation);
                moveCounters.put(playerId, moveCounter);
            }
        }
    }

    private boolean hasMoved(Location loc1, Location loc2) {
        return loc1.getBlockX() != loc2.getBlockX() || loc1.getBlockY() != loc2.getBlockY() || loc1.getBlockZ() != loc2.getBlockZ();
    }





    @EventHandler
        public void elytraCheck(PlayerMoveEvent e) {
            Player player = e.getPlayer();
            if (player.isFlying() && player.getGameMode() != GameMode.CREATIVE) {
                ItemStack elytra = player.getInventory().getChestplate();
                if (elytra == null || elytra.getType() != Material.ELYTRA) {
                    player.setFlying(false);
                    player.setAllowFlight(false);
                }
            }
        }
    }

