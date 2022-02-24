package de.curano.banapiplugin.spigot.commands;

import de.curano.banapiplugin.spigot.data.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanAPICommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (Config.config.getBoolean("enabled", true)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &aaktiviert&7."));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &cdeaktiviert&7."));
        }
        return true;
    }
}
