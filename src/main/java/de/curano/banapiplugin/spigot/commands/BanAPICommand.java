package de.curano.banapiplugin.spigot.commands;

import de.curano.banapiplugin.spigot.data.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class BanAPICommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1 && sender.hasPermission("banapi.command.use")) {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "on":
                    Config.config.set("enabled", true);
                    Config.save();
                    sender.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist nun &aaktiviert&7."));
                    return true;
                case "off":
                    Config.config.set("enabled", false);
                    Config.save();
                    sender.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist nun &cdeaktiviert&7."));
                    return true;
            }
        }

        if (Config.config.getBoolean("enabled", true)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &aaktiviert&7."));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &cdeaktiviert&7."));
        }
        return true;
    }
}
