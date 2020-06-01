package io.github.ph1lou;

import io.github.ph1lou.pluginlgapi.GetWereWolfAPI;
import io.github.ph1lou.pluginlgapi.WereWolfAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class Main extends JavaPlugin {

    GetWereWolfAPI ww ;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
        ww = (GetWereWolfAPI) Bukkit.getPluginManager().getPlugin("pluginLG");
        ww.getExtraTexts().put("werewolf.role.role_example.display","RoleExample");
        try {
            ww.registerRoles().put("werewolf.role.role_example.display", RoleExample.class.getConstructor(GetWereWolfAPI.class, WereWolfAPI.class, UUID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
