package de.curano.banapiplugin.bungee.listener;

import de.curano.banapiplugin.bungee.data.Config;
import de.curano.banapiplugin.utils.BanAPI;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class JoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPostLogin(PostLoginEvent event) {
        if (Config.config.getBoolean("enabled", true) && BanAPI.isBanned(event.getPlayer().getUniqueId())) {
            event.getPlayer().disconnect(new TextComponent("BanAPI Ban"));
        }
    }

}
