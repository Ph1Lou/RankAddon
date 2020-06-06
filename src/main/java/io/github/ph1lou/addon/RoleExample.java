package io.github.ph1lou.addon;


import io.github.ph1lou.pluginlgapi.GetWereWolfAPI;
import io.github.ph1lou.pluginlgapi.PlayerWW;
import io.github.ph1lou.pluginlgapi.WereWolfAPI;
import io.github.ph1lou.pluginlgapi.enumlg.*;
import io.github.ph1lou.pluginlgapi.events.DayEvent;
import io.github.ph1lou.pluginlgapi.events.NewWereWolfEvent;
import io.github.ph1lou.pluginlgapi.events.NightEvent;
import io.github.ph1lou.pluginlgapi.events.WereWolfListEvent;
import io.github.ph1lou.pluginlgapi.rolesattributs.Roles;
import io.github.ph1lou.pluginlgapi.rolesattributs.RolesVillage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class RoleExample extends RolesVillage {


    public RoleExample(GetWereWolfAPI main, WereWolfAPI game, UUID uuid) {
        super(main,game,uuid);
    }

    @EventHandler
    public void onNight(NightEvent event) {

        //Je fais des trucs la nuit
    }

    @EventHandler
    public void onDay(DayEvent event) {

      //Je fais des trucs le Jours
    }


    @Override
    public String getDescription() {
        return game.translate("werewolf.role.role_example.description");
    }

    @Override
    public String getDisplay() {
        return "werewolf.role.role_example.display";
    }

    @Override
    public Boolean isDisplay(String s) {
        return s.equals(getDisplay());
    }


    @Override
    public void stolen(UUID uuid) {
        //Si qqun récupère ce role en le volant
    }

    @Override
    public void recoverPower(Player player) {
        // à l'annonce des rôles
    }

    @Override
    public void recoverPotionEffect(Player player) {
        super.recoverPotionEffect(player);
        //à l'annonce des rôles et quand je rez
    }

}
