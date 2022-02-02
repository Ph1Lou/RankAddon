package fr.ph1lou.rank;

import fr.ph1lou.werewolfapi.GetWereWolfAPI;
import fr.ph1lou.werewolfapi.enums.Sound;
import fr.ph1lou.werewolfapi.enums.UniversalMaterial;
import fr.ph1lou.werewolfapi.registers.impl.AddonRegister;
import fr.ph1lou.werewolfapi.registers.interfaces.IRegisterManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        saveDefaultConfig();
        GetWereWolfAPI api = Bukkit.getServicesManager().load(GetWereWolfAPI.class);
        IRegisterManager registerManager = api.getRegisterManager();
        registerManager
                .registerAddon(new AddonRegister("rank_addon.name",
                        "fr",
                        this)
                        .setItem(UniversalMaterial.BOOK.getStack())
                        .setLoreKey(api.getWereWolfAPI().translateArray("rank_addon.description"))
                        .addAuthors("Ph1Lou",UUID.fromString("056be797-2a0b-4807-9af5-37faf5384396"))
                        .setAction((player, inventory) -> Sound.ANVIL_USE.play(player)));

        Bukkit.getPluginManager().registerEvents(new PrefixManager(this,api), this);
    }
}
