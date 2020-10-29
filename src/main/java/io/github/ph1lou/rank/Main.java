package io.github.ph1lou.rank;

import io.github.ph1lou.werewolfapi.GetWereWolfAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        GetWereWolfAPI api = (GetWereWolfAPI)Bukkit.getPluginManager().getPlugin("WereWOlfPlugin");
        Bukkit.getPluginManager().registerEvents(new PrefixManager(this,api), this);
    }
}
