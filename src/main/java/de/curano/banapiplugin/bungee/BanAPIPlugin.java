package de.curano.banapiplugin.bungee;

import de.curano.banapiplugin.bungee.commands.BanAPICommand;
import de.curano.banapiplugin.bungee.data.Config;
import de.curano.banapiplugin.bungee.listener.JoinListener;
import de.curano.banapiplugin.utils.BanAPI;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class BanAPIPlugin extends Plugin {

    private static BanAPIPlugin instance;

    public static BanAPIPlugin instance() {
        return instance;
    }

    private static void isBanned(UUID uuid, Consumer<Boolean> result) {
        instance().getProxy().getScheduler().runAsync(instance(), () -> {
           result.accept(BanAPI.isBanned(uuid));
        });
    }

    @Override
    public void onEnable() {

        instance = this;

        Config.load();

        this.getProxy().getPluginManager().registerCommand(this, new BanAPICommand("banapi"));

        this.getProxy().getPluginManager().registerListener(this, new JoinListener());

        this.getProxy().getScheduler().schedule(this, () -> {
            if (!Config.config.getBoolean("enabled", true)) {
                return;
            }
            Collection<ProxiedPlayer> onlinePlayers = BanAPIPlugin.instance().getProxy().getPlayers();
            for (ProxiedPlayer player : onlinePlayers) {
                isBanned(player.getUniqueId(), result -> {
                    if (result) {
                        player.disconnect(new TextComponent("\n&4%lBanAPI\n\n&cDu wurdest gebannt!\n\nDu kannst auf dem &eBanAPI-Discord &7einen Entbannungsantrag stellung!\n".replace("&", "§")));
                    }
                });
            };
        }, 30, 30, TimeUnit.SECONDS);

        this.getProxy().getScheduler().schedule(this, () -> {
            BanAPI.sendServerInfos(Config.config.getString("token"), this.getProxy().getConfigurationAdapter().getString("listeners.host", "0.0.0.0:25565").split(":")[0], Integer.parseInt(this.getProxy().getConfigurationAdapter().getString("listeners.host", "0.0.0.0:25565").split(":")[1]), Config.config.getBoolean("enabled", true));
        }, 0, 10, TimeUnit.SECONDS);

    }
}
