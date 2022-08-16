package net.sootmc.sootrenamer;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

public class RenamerTools {
    private static final String PREFIX = ChatColor.WHITE + "[ " + ChatColor.RED + "Soot" + ChatColor.GOLD + "MC" + ChatColor.WHITE + "] ";
    private static int maximumRepairCost = 40;

    public static void Renamer(Player player, String[] args) {
        String name = String.join(" ", args);

        if (name.contains("#") && !(player.hasPermission("sootrenamer.grad"))) {
            player.sendMessage(PREFIX + "You do not have permission to use gradients!");
            return;
        }

        ItemStack ci = player.getInventory().getItemInMainHand();

        if (ci == null || ci.getType().equals(Material.AIR)) {
            player.sendMessage(PREFIX + "You cannot rename nothing!");
            return;
        }

        ItemMeta meta = ci.getItemMeta();

        name = colourize(name);
        meta.setDisplayName(name);

        GameMode gameMode = player.getGameMode();

        int cost = 1;

        if (gameMode.equals(GameMode.CREATIVE) || gameMode.equals(GameMode.SPECTATOR))
            cost = 0;

        else if (meta instanceof Repairable repairable)
            cost = Math.min(repairable.getRepairCost(), maximumRepairCost);

        if (player.getExp() < cost) {
            player.sendMessage(PREFIX + "You do not have enough experience to rename this item. Required Experience: " + cost);
            return;
        }
        ci.setItemMeta(meta);

        player.getInventory().setItemInMainHand(ci);
        player.setExp(player.getExp() - cost);
        player.sendMessage(PREFIX + "Renamed item to '" + name + ChatColor.RESET + "'");
    }

    private static String colourize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
