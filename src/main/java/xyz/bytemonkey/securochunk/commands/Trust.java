package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;

import java.util.ArrayList;

/**
 * Created by Jack on 05/01/2017.
 */
public class Trust implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(player.getName());
        String tName = args[0];

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /chunk trust <player>");
            return true;
        }

        if(Bukkit.getPlayer(args[0]) != null) {
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

        ArrayList<Chunk> chunksInRadius = ChunkClaim.plugin.dataStore.getAllChunksForPlayer(player.getName());

        if (!playerData.builderNames.contains(tName)) {

            for(Chunk chunk : chunksInRadius) {
                if (!chunk.isTrusted(tName)) {
                    chunk.builderNames.add(tName);
                    ChunkClaim.plugin.dataStore.writeChunkToStorage(chunk);
                }
            }
            playerData.builderNames.add(tName);
            ChunkClaim.plugin.dataStore.savePlayerData(player.getName(), playerData);

        }
        player.sendMessage(ChatColor.GREEN + "Trusted " + tName + " in all your chunks.");
        return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.claim";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk trust - Trust a player in all chunks you own.";
    }

}
