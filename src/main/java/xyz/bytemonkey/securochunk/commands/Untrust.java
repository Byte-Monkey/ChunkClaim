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
public class Untrust implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(player.getName());
        String tName = args[0];

        if(args.length!=1) {
            player.sendMessage(ChatColor.RED + "Usage: /chunk untrust <player>");
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

        if(playerData.builderNames.contains(tName)) {
            for(int i=0; i<chunksInRadius.size();i++) {
                if(chunksInRadius.get(i).isTrusted(tName)) {
                    chunksInRadius.get(i).builderNames.remove(tName);
                    ChunkClaim.plugin.dataStore.writeChunkToStorage(chunksInRadius.get(i));
                }

            }
            playerData.builderNames.remove(tName);
            ChunkClaim.plugin.dataStore.savePlayerData(player.getName(), playerData);
        }

        player.sendMessage(ChatColor.GREEN + "Untrusted " + tName+ " in all your chunks.");
        return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.claim";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk untrust - Untrust a player";
    }

}
