package io.github.ph1lou.rank;

import io.github.ph1lou.werewolfapi.GetWereWolfAPI;
import io.github.ph1lou.werewolfapi.WereWolfAPI;
import io.github.ph1lou.werewolfapi.enums.StateGame;
import io.github.ph1lou.werewolfapi.events.UpdateNameTagEvent;
import io.github.ph1lou.werewolfapi.events.UpdatePlayerNameTag;
import io.github.ph1lou.werewolfapi.events.game.game_cycle.StartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.UUID;


public class PrefixManager implements Listener {

    private final Config config;
    private final GetWereWolfAPI api;

    public PrefixManager(Main main, GetWereWolfAPI api){
        this.config= new Config(main);
        this.api=api;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        WereWolfAPI ww = this.api.getWereWolfAPI();
        if(!ww.isState(StateGame.LOBBY) && this.config.isLobbyOnly()) return;

        Player player = event.getPlayer();
        String prefix = this.config.getPrefix(player);
        String suffix = this.config.getSuffix(player);
        String template = this.config.getTemplate();

        if (template== null) template = "[prefix][name] [suffix]";

        String formattedName = template.replaceAll("\\[prefix]", prefix).replaceAll("\\[name]", "%s").replaceAll("\\[suffix]", suffix);

        event.setFormat(String.format(event.getFormat(),formattedName,"%s"));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onUpdateScoreBoard(UpdatePlayerNameTag event){

        WereWolfAPI ww = this.api.getWereWolfAPI();
        if(!ww.isState(StateGame.LOBBY) && this.config.isLobbyOnly()) return;

        UUID uuid = event.getPlayerUUID();
        Player player = Bukkit.getPlayer(uuid);

        if(player==null){
            return;
        }

        String prefix=this.config.getPrefix(player)+event.getPrefix();

        if(prefix.length()<=14){
            event.setPrefix(prefix);
        }

        String suffix = event.getSuffix()+config.getSuffix(player);

        if(suffix.length()<=16){
            event.setSuffix(suffix);
        }
    }

    @EventHandler
    public void onStart(StartEvent event){
        this.api.getWereWolfAPI().getPlayerWW()
                .forEach(playerWW -> Bukkit.getPluginManager().callEvent(new UpdateNameTagEvent(playerWW)));

    }


}