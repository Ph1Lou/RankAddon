package io.github.ph1lou.rank;

import io.github.ph1lou.werewolfapi.AddonRegister;
import io.github.ph1lou.werewolfapi.GetWereWolfAPI;
import io.github.ph1lou.werewolfapi.RegisterManager;
import io.github.ph1lou.werewolfapi.enumlg.Sounds;
import io.github.ph1lou.werewolfapi.enumlg.UniversalMaterial;
import io.github.ph1lou.werewolfapi.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.UUID;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        GetWereWolfAPI api = (GetWereWolfAPI)Bukkit.getPluginManager().getPlugin("WereWolfPlugin");
        RegisterManager registerManager = api.getRegisterManager();
        registerManager
                .registerAddon(new AddonRegister("werewolf.rank_addon",
                        "fr",
                        this,
                        new ItemBuilder(UniversalMaterial.BOOK.getType())
                        .setDisplayName("Addon de §bRang")
                        .setLore(Arrays.asList("Customise les Prefix","et les Suffix","Fait Par Ph1Lou"))
                        .build()
                )
                .addAuthors("Ph1Lou",UUID.fromString("056be797-2a0b-4807-9af5-37faf5384396"))
                .setAction(Sounds.ANVIL_USE::play));

        Bukkit.getPluginManager().registerEvents(new PrefixManager(this,api), this);
    }
}
