package de.curano.banapiplugin.bungee.data;

import de.curano.banapiplugin.bungee.BanAPIPlugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Config {

    public static Configuration config;

    public static void load() {
        File configFile = new File(BanAPIPlugin.instance().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try (InputStream in = BanAPIPlugin.instance().getResourceAsStream("config.yml")) {
                Files.copy(in, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(BanAPIPlugin.instance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(BanAPIPlugin.instance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
