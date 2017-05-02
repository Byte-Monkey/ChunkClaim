package xyz.bytemonkey.securochunk.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.ArrayList;

/**
 * Created by Jack on 05/01/2017.
 */
public class Abandon implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        Chunk chunk = ChunkClaim.plugin.dataStore.getChunkAt(player.getLocation(), null);
        PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(player.getName());
        Location location = player.getLocation();

        if (args.length == 1) {
            int radius;
            int abd = 0;
            try {
                radius = Integer.parseInt(args[1]);
                if (radius < 0) {
                    player.sendMessage(ChatColor.GREEN + "Error: Negative Radius");
                    return true;
                }
                if (radius > 10) {
                    player.sendMessage(ChatColor.GREEN + "Error: Max Radius is 10.");
                    return true;
                }
                ArrayList<Chunk> chunksInRadius = ChunkClaim.plugin.getChunksInRadius(chunk, player.getName(), radius);
                for (Chunk chunk1 : chunksInRadius) {
                    ChunkClaim.plugin.dataStore.deleteChunk(chunk1);
                    ChunkClaim.getEcon().depositPlayer(playerData.playerName, ChunkClaim.plugin.config_chunkCost);
                    abd++;
                }

                ChunkClaim.plugin.dataStore.savePlayerData(player.getName(), playerData);
                player.sendMessage(ChatColor.GREEN + "" + abd + " Chunks abandoned in radius " + radius);
                return true;

            } catch (Exception e) {
                player.sendMessage(ChatColor.GREEN + "Usage: /chunk abandon [radius]");
                return true;
            }

        } else {
            if (chunk == null) {
                player.sendMessage(ChatColor.GREEN + "This chunk is public.");
                Visualization visualization = Visualization.FromBukkitChunk(location.getChunk(), location.getBlockY(), VisualizationType.Public, location);
                Visualization.Apply(player, visualization);
            } else if (chunk.ownerName.equals(player.getName())) {
                ChunkClaim.plugin.dataStore.deleteChunk(chunk);
                ChunkClaim.getEcon().depositPlayer(playerData.playerName, ChunkClaim.plugin.config_chunkCost);
                ChunkClaim.plugin.dataStore.savePlayerData(player.getName(), playerData);
                player.sendMessage(ChatColor.GREEN + "Chunk abandoned.");

                Visualization visualization = Visualization.FromChunk(chunk, location.getBlockY(), VisualizationType.Public, location);
                Visualization.Apply(player, visualization);

                return true;
            } else {
                if (playerData.lastChunk != chunk) {
                    playerData.lastChunk = chunk;
                    Visualization visualization = Visualization.FromChunk(chunk, location.getBlockY(), VisualizationType.ErrorChunk, location);
                    Visualization.Apply(player, visualization);
                }
                player.sendMessage(ChatColor.GREEN + "You don't own this chunk. Only "
                        + chunk.ownerName + " or the staff can delete it.");
                return true;
            }

        }
        return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.claim";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk abandon - Abandons the chunk you are in [or all chunks in a radius]";
    }

}
