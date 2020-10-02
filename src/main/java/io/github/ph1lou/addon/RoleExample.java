package io.github.ph1lou.addon;


import io.github.ph1lou.werewolfapi.GetWereWolfAPI;
import io.github.ph1lou.werewolfapi.WereWolfAPI;
import io.github.ph1lou.werewolfapi.events.DayEvent;
import io.github.ph1lou.werewolfapi.events.NightEvent;
import io.github.ph1lou.werewolfapi.rolesattributs.RolesVillage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

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



    // Ne pas mettre de game.translate ici !!!!!!!!!!!!!
    @Override
    public String getDisplay() {
        return "werewolf.role.role_example.display";
    }

    @Override
    public boolean isDisplay(String s) {
        return s.equals(getDisplay());
    }


    @Override
    public void stolen(UUID uuid) {
        //Si qqun récupère ce role en le volant
    }


    @Override
    public void recoverPotionEffect(Player player) {
        super.recoverPotionEffect(player);
        //à l'annonce des rôles et quand je rez
    }

}
