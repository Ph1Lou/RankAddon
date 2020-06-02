package io.github.ph1lou;

import io.github.ph1lou.pluginlgapi.GetWereWolfAPI;
import io.github.ph1lou.pluginlgapi.RoleRegister;
import io.github.ph1lou.pluginlgapi.WereWolfAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.UUID;

public class Main extends JavaPlugin {

    GetWereWolfAPI ww ;

    @Override
    public void onEnable() {

        ww = (GetWereWolfAPI) Bukkit.getPluginManager().getPlugin("pluginLG");

        try {
            RoleRegister exampleRole = new RoleRegister(ww,"werewolf.role.role_example.display").registerRole(RoleExample.class.getConstructor(GetWereWolfAPI.class, WereWolfAPI.class, UUID.class)).setName("ExampleRole");
            exampleRole.setLore(Arrays.asList("Role Example","Fait par Ph1Lou")).create();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
