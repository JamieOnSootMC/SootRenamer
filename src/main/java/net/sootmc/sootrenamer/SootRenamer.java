package net.sootmc.sootrenamer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SootRenamer extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("rename").setExecutor(this);
        Bukkit.getLogger().info("[SootRenamer] SootRenamer is now enabled");
        Bukkit.getLogger().info("[SootRenamer] Made my JamieIsGeek :)");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[SootRenamer] SootRenamer is now disabled");
        Bukkit.getLogger().info("[SootRenamer] Made my JamieIsGeek :)");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getLabel().equals("rename")) {
            if(sender instanceof Player && sender.hasPermission("sootrenamer.rename")) {
                Player player = (Player) sender;
                RenamerTools rt = RenamerTools.getRt();
                rt.Renamer(player, args);
            } else {
                sender.sendMessage("You cannot rename items!");
            }
        }

        return true;
    }
}
