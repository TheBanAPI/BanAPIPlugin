package de.curano.banapiplugin.spigot.listener;

import de.curano.banapiplugin.utils.BanAPI;
import de.curano.banapiplugin.spigot.data.Config;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        if (Config.config.getBoolean("enabled", true) && BanAPI.isBanned(event.getUniqueId())) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, "BanAPI Ban");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Config.config.getBoolean("enabled", true) && BanAPI.isBanned(event.getPlayer().getUniqueId())) {
            event.setJoinMessage(null);
            event.getPlayer().kickPlayer("\n&4%lBanAPI\n\n&cDu wurdest gebannt!\n\nDu kannst auf dem &eBanAPI-Discord &7einen Entbannungsantrag stellung!\n".replace("&", "§"));
        } else if (Config.config.getBoolean("enabled", true)) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI V1.0 &8┃ &7Das &eBanAPI-Plugin&7 ist &aaktiviert&7."));
        } else {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI V1.0 &8┃ &7Das &eBanAPI-Plugin&7 ist &cdeaktiviert&7."));
        }
    }

}
