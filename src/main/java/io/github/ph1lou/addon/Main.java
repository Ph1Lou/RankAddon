package io.github.ph1lou.addon;

import io.github.ph1lou.pluginlgapi.GetWereWolfAPI;
import io.github.ph1lou.pluginlgapi.RoleRegister;
import io.github.ph1lou.pluginlgapi.WereWolfAPI;
import io.github.ph1lou.pluginlgapi.enumlg.Category;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.UUID;

public class Main extends JavaPlugin {

    GetWereWolfAPI ww ;

    @Override
    public void onEnable() {

        ww = (GetWereWolfAPI) Bukkit.getPluginManager().getPlugin("pluginLG");

        ww.loadTranslation(this,"fr");

        try {
            RoleRegister exampleRole = new RoleRegister(ww,"werewolf.role.role_example.display").registerRole(RoleExample.class);
            exampleRole.setLore(Arrays.asList("§fRole Example","§fFait par §bPh1Lou")).addCategory(Category.ADDONS).create();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
