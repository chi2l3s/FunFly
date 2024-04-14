package me.chi2l3s.funfly;

import me.chi2l3s.funfly.commands.FlyCommand;
import me.chi2l3s.funfly.commands.FunFlyReloadCommand;
import me.chi2l3s.funfly.listeners.OnDamageListener;
import me.chi2l3s.funfly.listeners.PlayerFlyListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FunFly extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getServer().getPluginManager().registerEvents(new OnDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerFlyListener(this),this);
        getCommand("funfly").setExecutor(new FunFlyReloadCommand(this));
        saveDefaultConfig();

    }
}