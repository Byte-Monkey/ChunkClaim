package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.utils.Chunk;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jack on 05/01/2017.
 */
public class List implements SubCommand {
    @Override
    public boolean onCommand(Player player, String[] args) {
        if (player.hasPermission("chunkclaim.admin")) {
            String tName = args[0];

            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Usage: /chunk list <player>");
                return true;
            }

            if (Bukkit.getPlayer(args[0]) != null) {
                if (tName.equals(player.getName())) {
                    player.sendMessage(ChatColor.RED + "You don't trust yourself?");
                    return true;
                }
            } else {
                OfflinePlayer tp = ChunkClaim.plugin.resolvePlayer(args[0]);
                if (tp == null) {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
                if (tName.equals(player.getName())) {
                    player.sendMessage(ChatColor.RED + "You don't trust yourself?");
                    return true;
                }
            }

            ArrayList<Chunk> chunksInRadius = ChunkClaim.plugin.dataStore.getAllChunksForPlayer(tName);

            long loginDays = ((new Date()).getTime() - ChunkClaim.plugin.dataStore.getPlayerData(tName).lastLogin.getTime()) / (1000 * 60 * 60 * 24);
            long joinDays = ((new Date()).getTime() - ChunkClaim.plugin.dataStore.getPlayerData(tName).firstJoin.getTime()) / (1000 * 60 * 60 * 24);
            String adminstring = tName + " | Last Login: " + loginDays + " days ago. First Join: " + joinDays + " days ago.";
            player.sendMessage(ChatColor.GREEN + adminstring);

            for (Chunk chunk : chunksInRadius) {
                adminstring = "ID: " + chunk.x + "|" + chunk.z + "(" + (chunk.x * 16) + "|" + (chunk.z * 16) + ")";
                adminstring += ", Permanent: " + (chunk.modifiedBlocks < 0 ? "true" : ("false (" + chunk.modifiedBlocks + ")"));
                player.sendMessage(ChatColor.GREEN + adminstring);
            }
        }

        return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.admin";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk list <player> - List chunks owned by a player";
    }

}
