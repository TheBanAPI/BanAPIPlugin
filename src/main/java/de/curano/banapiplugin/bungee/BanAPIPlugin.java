package de.curano.banapiplugin.bungee;

import de.curano.banapiplugin.bungee.commands.BanAPICommand;
import de.curano.banapiplugin.bungee.data.Config;
import de.curano.banapiplugin.bungee.listener.JoinListener;
import de.curano.banapiplugin.utils.BanAPI;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class BanAPIPlugin extends Plugin {

    private static BanAPIPlugin instance;

    public static BanAPIPlugin instance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        Config.load();

        this.getProxy().getPluginManager().registerCommand(this, new BanAPICommand("banapi"));

        this.getProxy().getPluginManager().registerListener(this, new JoinListener());

        this.getProxy().getScheduler().schedule(this, () -> {
            for (ProxiedPlayer player : this.getProxy().getPlayers()) {
                if (Config.config.getBoolean("enabled", true) && BanAPI.isBanned(player.getUniqueId())) {
                    player.disconnect(new TextComponent("BanAPI Ban"));
                    try {
                        Thread.sleep(50L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 30, 30, TimeUnit.SECONDS);

    }
}
