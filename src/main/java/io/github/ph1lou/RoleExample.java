package io.github.ph1lou;


import io.github.ph1lou.pluginlgapi.GetWereWolfAPI;
import io.github.ph1lou.pluginlgapi.WereWolfAPI;
import io.github.ph1lou.pluginlgapi.enumlg.Camp;
import io.github.ph1lou.pluginlgapi.events.DayEvent;
import io.github.ph1lou.pluginlgapi.events.NightEvent;
import io.github.ph1lou.pluginlgapi.rolesattributs.Roles;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class RoleExample implements Roles, Listener {

    GetWereWolfAPI main;
    WereWolfAPI game;
    UUID uuid;
    Camp camp;
    boolean infected;

    public RoleExample(GetWereWolfAPI main, WereWolfAPI game, UUID uuid) {
        this.main=main;
        this.game=game;
        this.uuid=uuid;
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
    public void setCamp(Camp camp) {
        this.camp=camp;
    }

    @Override
    public boolean isCamp(Camp camp) {
        return this.camp==camp;
    }

    @Override
    public Camp getCamp() {
        return Camp.VILLAGER;
    }

    @Override
    public String getDescription() {
        return "Vous êtes RoleExample";
    }

    @Override
    public String getDisplay() {
        return "werewolf.role.role_example.display";
    }

    @Override
    public UUID getPlayerUUID() {
        return uuid;
    }

    @Override
    public void setPlayerUUID(UUID uuid) {
        this.uuid=uuid;
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
        //à l'annonce des rôles et quand je rez
    }

    @Override
    public boolean isWereWolf() {
        return this.infected;
    }

    @Override
    public Boolean getInfected() {
        return this.infected;
    }

    @Override
    public void setInfected(Boolean infected) {
        this.infected=infected;
    }

    @Override
    public Roles publicClone() {
        try {
            return (Roles) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
