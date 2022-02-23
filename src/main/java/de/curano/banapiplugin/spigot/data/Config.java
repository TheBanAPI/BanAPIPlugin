package de.curano.banapiplugin.spigot.data;

import de.curano.banapiplugin.spigot.BanAPIPlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public static YamlConfiguration config;

    public static void load() {
        File configFile = new File(BanAPIPlugin.instance().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            BanAPIPlugin.instance().saveResource("config.yml", false);
        }

        try {
            config = new YamlConfiguration();
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            config.save(new File(BanAPIPlugin.instance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
