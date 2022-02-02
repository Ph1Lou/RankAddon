package fr.ph1lou.rank;

import fr.ph1lou.werewolfapi.GetWereWolfAPI;
import fr.ph1lou.werewolfapi.enums.StateGame;
import fr.ph1lou.werewolfapi.events.UpdateNameTagEvent;
import fr.ph1lou.werewolfapi.events.UpdatePlayerNameTagEvent;
import fr.ph1lou.werewolfapi.events.game.game_cycle.StartEvent;
import fr.ph1lou.werewolfapi.game.WereWolfAPI;
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
    public void onUpdateScoreBoard(UpdatePlayerNameTagEvent event){

        WereWolfAPI ww = this.api.getWereWolfAPI();

        if(!ww.isState(StateGame.LOBBY) && this.config.isLobbyOnly()) return;

        UUID uuid = event.getPlayerUUID();
        Player player = Bukkit.getPlayer(uuid);

        if(player==null){
            return;
        }

        String prefix=this.config.getPrefix(player)+event.getPrefix();

        event.setPrefix(event.getPrefix() + prefix);

        String suffix = event.getSuffix()+config.getSuffix(player);

        event.setSuffix(event.getSuffix() + suffix);
    }

    @EventHandler
    public void onStart(StartEvent event){
        this.api.getWereWolfAPI().getPlayersWW()
                .forEach(playerWW -> Bukkit.getPluginManager().callEvent(new UpdateNameTagEvent(playerWW)));

    }


}