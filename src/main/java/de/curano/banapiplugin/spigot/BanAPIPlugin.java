package de.curano.banapiplugin.spigot;

import de.curano.banapiplugin.spigot.commands.BanAPICommand;
import de.curano.banapiplugin.spigot.listener.JoinListener;
import de.curano.banapiplugin.utils.BanAPI;
import de.curano.banapiplugin.spigot.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BanAPIPlugin extends JavaPlugin {

    private static BanAPIPlugin instance;

    public static BanAPIPlugin instance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        Config.load();

        getCommand("banapi").setExecutor(new BanAPICommand());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Config.config.getBoolean("enabled", true) && BanAPI.isBanned(player.getUniqueId())) {
                    player.kickPlayer("BanAPI Ban");
                    try {
                        Thread.sleep(50L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 600, 600);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            BanAPI.sendServerInfos(Config.config.getString("token"), Bukkit.getIp(), Bukkit.getPort(), Config.config.getBoolean("enabled"));
        }, 0, 200);

    }
}
