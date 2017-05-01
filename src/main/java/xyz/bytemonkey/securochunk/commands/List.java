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

            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Usage: /chunk list <player>");
                return true;
            }

            OfflinePlayer tp = ChunkClaim.plugin.resolvePlayer(args[1]);
            if (tp == null) {
                player.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }

            String tName = tp.getName();

            ArrayList<Chunk> chunksInRadius = ChunkClaim.plugin.dataStore.getAllChunksForPlayer(tName);

            long loginDays = ((new Date()).getTime() - ChunkClaim.plugin.dataStore.getPlayerData(tp.getName()).lastLogin.getTime()) / (1000 * 60 * 60 * 24);
            long joinDays = ((new Date()).getTime() - ChunkClaim.plugin.dataStore.getPlayerData(tp.getName()).firstJoin.getTime()) / (1000 * 60 * 60 * 24);
            String adminstring = tp.getName() + " | Last Login: " + loginDays + " days ago. First Join: " + joinDays + " days ago.";
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
