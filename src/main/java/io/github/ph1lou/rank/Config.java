
package io.github.ph1lou.rank;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class Config {


	private final Main main;
	private String template;
	private boolean lobbyOnly=false;


	public Config(Main main){
		this.main=main;
		loadKeys();
	}

	private void loadKeys() {
		FileConfiguration config = main.getConfig();
		template = config.getString("template");
		lobbyOnly=config.getBoolean("lobbyOnly");
	}

	public String getPrefix(Player player) {
		return ChatColor.translateAlternateColorCodes('&', getPrefixSuffix(player, "prefix"));
	}

	public String getSuffix(Player player) {
		return ChatColor.translateAlternateColorCodes('&', getPrefixSuffix(player, "suffix"));
	}

	private String getPrefixSuffix(Player player, String type) {

		FileConfiguration config = main.getConfig();
		String prefixSuffix = "";
		Set<String> groups = config.getConfigurationSection("groups").getKeys(false);

		for (String group : groups) {

			if (player.hasPermission(config.getString("groups." + group + ".permission"))) {

				prefixSuffix = config.getString("groups." + group + "." + type);

				break;
			}
		}

		if (config.get("users") != null) {

			String data = config.getString("users." + player.getName() + "." + type);

			if (data != null && !data.isEmpty()) {
				prefixSuffix=data;
			}

			String dataUUID = config.getString("users." + player.getUniqueId() + "." + type);

			if (dataUUID != null && !dataUUID.isEmpty()) {
				prefixSuffix=dataUUID;
			}
		}

		if(prefixSuffix==null) return "";

		return prefixSuffix;
	}

	public String getTemplate() {
		return template;
	}

	public boolean isLobbyOnly() {
		return lobbyOnly;
	}
}