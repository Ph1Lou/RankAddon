package io.github.ph1lou.rank;

import io.github.ph1lou.werewolfapi.GetWereWolfAPI;
import io.github.ph1lou.werewolfapi.enums.Sounds;
import io.github.ph1lou.werewolfapi.enums.UniversalMaterial;
import io.github.ph1lou.werewolfapi.registers.AddonRegister;
import io.github.ph1lou.werewolfapi.registers.RegisterManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {


    List<String> lore = Arrays.asList("werewolf.rank.desc0","werewolf.rank.desc1","werewolf.rank.desc2");
    @Override
    public void onEnable() {
        saveDefaultConfig();
        GetWereWolfAPI api = (GetWereWolfAPI)Bukkit.getPluginManager().getPlugin("WereWolfPlugin");
        RegisterManager registerManager = api.getRegisterManager();
        registerManager
                .registerAddon(new AddonRegister("werewolf.rank_addon",
                        "fr",
                        this)
                        .setItem(UniversalMaterial.BOOK.getStack())
                        .setLoreKey(lore)
                        .addAuthors("Ph1Lou",UUID.fromString("056be797-2a0b-4807-9af5-37faf5384396"))
                        .setAction((player, inventory) -> Sounds.ANVIL_USE.play(player)));

        Bukkit.getPluginManager().registerEvents(new PrefixManager(this,api), this);
    }
}
