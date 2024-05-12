package me.chi2l3s.funfly.commands;

import me.chi2l3s.funfly.FunFly;
import me.chi2l3s.funfly.utils.HEXColor;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final FunFly plugin;

    public FlyCommand(FunFly plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)){
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.ELYTRA) {
                    if (player.isFlying()) {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        String flyOff = plugin.getConfig().getString("messages.fly-off");
                        player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', flyOff)));
                    } else {
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        String flyOn = plugin.getConfig().getString("messages.fly-on");
                        player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', flyOn)));

                    }
                } else {
                    String noElytraMessage = plugin.getConfig().getString("messages.no-elytra");

                    player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', noElytraMessage)));
                }
            }else {
                if (player.isFlying()) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    String flyOff = plugin.getConfig().getString("messages.fly-off");
                    player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', flyOff)));
                } else {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    String flyOn = plugin.getConfig().getString("messages.fly-on");
                    player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', flyOn)));

                }
            }
        } else {
            System.out.println(ChatColor.RED + "Вы должны быть игроком!");
        }
        return true;
    }
}


