package de.curano.banapiplugin.bungee.commands;

import de.curano.banapiplugin.bungee.BanAPIPlugin;
import de.curano.banapiplugin.bungee.data.Config;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Locale;

public class BanAPICommand extends Command {

    public BanAPICommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1 && sender.hasPermission("banapi.command.use")) {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "on":
                    Config.config.set("enabled", true);
                    Config.save();
                    if (!(sender instanceof ProxiedPlayer)) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist nun &aaktiviert&7."));
                    }
                    for (ProxiedPlayer player : BanAPIPlugin.instance().getProxy().getPlayers()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist nun &aaktiviert&7."));
                    }
                    return;
                case "off":
                    Config.config.set("enabled", false);
                    Config.save();
                    if (!(sender instanceof ProxiedPlayer)) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist nun &cdeaktiviert&7."));
                    }
                    for (ProxiedPlayer player : BanAPIPlugin.instance().getProxy().getPlayers()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist nun &cdeaktiviert&7."));
                    }
                    return;
            }
        }

        if (Config.config.getBoolean("enabled", true)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &aaktiviert&7."));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &4BanAPI &8┃ &7Das &eBanAPI-Plugin&7 ist &cdeaktiviert&7."));
        }
    }
}
