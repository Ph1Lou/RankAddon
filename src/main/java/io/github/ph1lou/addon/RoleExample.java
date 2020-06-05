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
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class RoleExample implements Roles, Listener,Cloneable {

    GetWereWolfAPI main;
    WereWolfAPI game;
    UUID uuid;
    Camp camp=Camp.VILLAGER;
    boolean infected=false;

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
        return this.camp;
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
        if (game.getConfig().getScenarioValues().get(ScenarioLG.CAT_EYES)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,Integer.MAX_VALUE,0,false,false));
        }
        //à l'annonce des rôles et quand je rez
    }

    @EventHandler
    public void onWereWolfList(WereWolfListEvent event){

        PlayerWW lg = game.getPlayersWW().get(uuid);

        if(!isWereWolf()){
            return;
        }

        Team team=game.getWereWolfScoreBoard().getTeam(lg.getName());

        if (game.getConfig().getConfigValues().get(ToolLG.RED_NAME_TAG)) {
            if(game.getHosts().contains(uuid)){
                team.setPrefix(game.translate("werewolf.commands.admin.host.tag")+"§4");
            }
            else team.setPrefix("§4");
        }
        lg.setScoreBoard(game.getWereWolfScoreBoard());

        if(!lg.isState(State.ALIVE)) {
            return;
        }

        if (Bukkit.getPlayer(uuid) == null) {
            return;
        }

        Player player = Bukkit.getPlayer(uuid);
        player.sendMessage(game.translate("werewolf.role.werewolf.see_others"));
        player.playSound(player.getLocation(), Sound.WOLF_HOWL, 1, 20);
        player.setScoreboard(game.getWereWolfScoreBoard());
    }

    @EventHandler
    public void onNewWereWolf(NewWereWolfEvent event) {

        PlayerWW plg = game.getPlayersWW().get(uuid);

        if(uuid.equals(event.getUuid())){

            setCamp(Camp.WEREWOLF);

            if (game.getConfig().getTimerValues().get(TimerLG.WEREWOLF_LIST) < 0) {

                Team team=game.getWereWolfScoreBoard().getTeam(plg.getName());

                if (game.getConfig().getConfigValues().get(ToolLG.RED_NAME_TAG)) {
                    if(game.getHosts().contains(uuid)){
                        team.setPrefix(game.translate("werewolf.commands.admin.host.tag")+"§4");
                    }
                    else team.setPrefix("§4");
                }
                plg.setScoreBoard(game.getWereWolfScoreBoard());
            }

            if(Bukkit.getPlayer(uuid)!=null) {
                Player player = Bukkit.getPlayer(uuid);
                player.setScoreboard(game.getWereWolfScoreBoard());
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,Integer.MAX_VALUE,0,false,false));
                player.sendMessage(game.translate("werewolf.role.werewolf.go_to_the_werewolf_camp"));
                player.playSound(player.getLocation(),Sound.WOLF_HOWL, 1, 20);
                if (game.isDay(Day.NIGHT)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE,-1,false,false));
                }
            }
        }
        else if(isWereWolf()){
            if (game.getConfig().getTimerValues().get(TimerLG.WEREWOLF_LIST) < 0) {
                if (plg.isState(State.ALIVE)) {
                    if(Bukkit.getPlayer(uuid) != null){
                        Player lg1 = Bukkit.getPlayer(uuid);
                        lg1.sendMessage(game.translate("werewolf.role.werewolf.new_werewolf"));
                        lg1.playSound(lg1.getLocation(), Sound.WOLF_HOWL, 1, 20);
                    }
                }
            }
        }
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
