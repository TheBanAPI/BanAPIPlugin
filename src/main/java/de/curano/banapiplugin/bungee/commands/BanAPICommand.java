package de.curano.banapiplugin.bungee.commands;

import de.curano.banapiplugin.bungee.data.Config;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class BanAPICommand extends Command {

    public BanAPICommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (Config.config.getBoolean("enabled", true)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &aaktiviert&7."));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &cdeaktiviert&7."));
        }
    }
}
