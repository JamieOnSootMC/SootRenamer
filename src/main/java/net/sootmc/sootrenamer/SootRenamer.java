package net.sootmc.sootrenamer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SootRenamer extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("rename").setExecutor(this);
        getLogger().info("SootRenamer is now enabled");
        getLogger().info("Made by JamieIsGeek :)");
    }

    @Override
    public void onDisable() {
        getLogger().info("SootRenamer is now disabled");
        getLogger().info("Made by JamieIsGeek :)");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getLabel().equals("rename")) {
            if(sender instanceof Player player && sender.hasPermission("sootrenamer.rename")) {
                RenamerTools.Renamer(player, args);
            } else {
                sender.sendMessage("You cannot rename items!");
            }
        }

        return true;
    }
}
