package io.github.ph1lou.addon;

import io.github.ph1lou.werewolfapi.GetWereWolfAPI;
import io.github.ph1lou.werewolfapi.RoleRegister;
import io.github.ph1lou.werewolfapi.WereWolfAPI;
import io.github.ph1lou.werewolfapi.enumlg.Category;
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
        ww.getAddonsList().add(this);


        try {
            RoleRegister exampleRole = new RoleRegister(this,ww,"werewolf.role.role_example.display").registerRole(RoleExample.class);
            exampleRole.setLore(Arrays.asList("§fRole Example","§fFait par §bPh1Lou")).addCategory(Category.ADDONS).addCategory(Category.VILLAGER).create();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
