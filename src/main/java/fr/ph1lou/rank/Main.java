package fr.ph1lou.rank;

import fr.ph1lou.werewolfapi.GetWereWolfAPI;
import fr.ph1lou.werewolfapi.annotations.Author;
import fr.ph1lou.werewolfapi.annotations.ModuleWerewolf;
import fr.ph1lou.werewolfapi.enums.UniversalMaterial;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@ModuleWerewolf(key = "rank_addon.name",
        loreKeys = "rank_addon.description",
        item = UniversalMaterial.BOOK,
        defaultLanguage = "fr",
        authors = @Author(uuid = "056be797-2a0b-4807-9af5-37faf5384396", name = "Ph1Lou"))
public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        saveDefaultConfig();
        GetWereWolfAPI api = Bukkit.getServicesManager().load(GetWereWolfAPI.class);
        Bukkit.getPluginManager().registerEvents(new PrefixManager(this,api), this);
    }
}
