package de.curano.banapiplugin;

import de.curano.banapiplugin.listener.JoinListener;
import de.curano.banapiplugin.utils.BanAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BanAPIPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (BanAPI.isBanned(player.getUniqueId())) {
                    player.kickPlayer("BanAPI Ban");
                }
            }
        }, 600, 600);

    }
}
