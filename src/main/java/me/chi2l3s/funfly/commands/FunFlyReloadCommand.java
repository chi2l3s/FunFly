package me.chi2l3s.funfly.commands;

import me.chi2l3s.funfly.FunFly;
import me.chi2l3s.funfly.utils.HEXColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FunFlyReloadCommand implements CommandExecutor {

    private final FunFly plugin;

    public FunFlyReloadCommand(FunFly plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {
                if (args[0].equals("reload")) {
                    String pluginReloadMessage = plugin.getConfig().getString("messages.config-reload");
                    player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&', pluginReloadMessage)));
                    plugin.reloadConfig();
                }
            } else {
                player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&',"          &#69f5f2FunFly V0.1 by chi2l3s            ")));
                player.sendMessage(" ");
                player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&',"                  &#4ae06dКоманды:        ")));
                player.sendMessage(" ");
                player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&',"&#4ae06d/fly &f- включить режим полета")));
                player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&',"&#4ae06d/funfly reload &f- перезагрузить конфигурацию плагина")));
                player.sendMessage(HEXColor.message(ChatColor.translateAlternateColorCodes('&',"&#4ae06d/funfly &f- это меню")));
                player.sendMessage(" ");
            }

            return true;
        } else {
            System.out.println(ChatColor.RED + "Вы должны быть игроком!");
        }
        return true;
    }
}


