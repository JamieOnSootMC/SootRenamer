package net.sootmc.sootrenamer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenamerTools {
    private static String prefix = ChatColor.WHITE + "[ " + ChatColor.RED + "Soot" + ChatColor.GOLD + "MC" + ChatColor.WHITE + "] ";


    public static void Renamer(Player player, String[] args) {
        String noColour = stringBuilder(args).toString();
        if(noColour.contains("#") && !(player.hasPermission("sootrenamer.grad"))) {
            player.sendMessage(prefix + "You do not have permission to use gradients!");
        } else {
            ItemStack ci = player.getInventory().getItemInMainHand();
            if (ci == null || ci.getType().equals(Material.AIR)) {
                player.sendMessage(prefix + "You cannot rename nothing!");
            } else {
                ItemMeta meta = ci.getItemMeta();

                String name = colourize(noColour);
                meta.setDisplayName(name);
                ci.setItemMeta(meta);

                player.getInventory().setItemInMainHand(ci);
                player.setExp(player.getExp() - 1);
                player.sendMessage(prefix + "Renamed item to '" + name + ChatColor.RESET + "'");
            }
        }
    }


    private static StringBuffer stringBuilder(String[] args) {
        StringBuffer sb = new StringBuffer();
        for (String arg : args) { sb.append(arg + " "); }
        return sb;
    }

    private static String colourize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
