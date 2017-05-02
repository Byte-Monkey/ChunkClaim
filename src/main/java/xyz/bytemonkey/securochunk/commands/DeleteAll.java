package xyz.bytemonkey.securochunk.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.bytemonkey.securochunk.ChunkClaim;

/**
 * Created by Jack on 05/01/2017.
 */
public class DeleteAll implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (!player.hasPermission("chunkclaim.admin")) {
            player.sendMessage(ChatColor.GREEN + "No permission.");
            return true;
        }
        if (args.length == 1) {
            OfflinePlayer tp = ChunkClaim.plugin.resolvePlayer(args[1]);
            if (tp == null) {

                player.sendMessage(ChatColor.GREEN + "Player not found.");
                return true;
            }
            String tName = tp.getName();

            player.sendMessage(ChatColor.GREEN + "" + ChunkClaim.plugin.dataStore.deleteChunksForPlayer(tName) + " chunks deleted.");
            return true;
        } else {
            player.sendMessage(ChatColor.GREEN + "Usage: /chunk deleteall <player>");
            return true;
        }
    }

    @Override
    public String permission() {
        return "chunkclaim.admin";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk deleteall <player> - Delete all chunks owned by a player";
    }

}
