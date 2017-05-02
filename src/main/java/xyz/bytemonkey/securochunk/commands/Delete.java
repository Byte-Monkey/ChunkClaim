package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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
public class Delete implements SubCommand {
    @Override
    public boolean onCommand(Player player, String[] args) {
        if (!player.hasPermission("chunkclaim.admin")) {
            player.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        Location location = player.getLocation();

        if (args.length == 2) {
            int radius;
            int abd = 0;

            try {
                radius = Integer.parseInt(args[2]);
                if (radius < 0) {
                    player.sendMessage(ChatColor.RED + "Error: Negative Radius");
                    return true;
                }

                if (radius > 10) {
                    player.sendMessage(ChatColor.RED + "Error: Max Radius is 10.");
                    return true;
                }
                OfflinePlayer tp = ChunkClaim.plugin.resolvePlayer(args[1]);
                if (tp == null) {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
                String tName = tp.getName();
                PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(tName);

                org.bukkit.Chunk bukkitChunk = location.getChunk();
                Chunk chunk = new Chunk(bukkitChunk.getX(), bukkitChunk.getZ(), bukkitChunk.getWorld().getName());
                ArrayList<Chunk> chunksInRadius = ChunkClaim.plugin.getChunksInRadius(chunk, tName, radius);

                for (Chunk chunk1 : chunksInRadius) {
                    ChunkClaim.plugin.dataStore.deleteChunk(chunk1);

                    ChunkClaim.getEcon().depositPlayer(Bukkit.getOfflinePlayer(ChunkClaim.plugin.dataStore.getPlayerData(chunk.ownerName).playerName),
                            ChunkClaim.plugin.getConfig().getInt("costPerChunk"));
                    abd++;
                }
                ChunkClaim.plugin.dataStore.savePlayerData(tName, playerData);
                player.sendMessage(ChatColor.RED + "" + abd + " Chunks deleted in radius " + radius + ".");

                return true;
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "Usage: /chunk delete [<player> <radius>]");
                return true;
            }

        }

        Chunk chunk = ChunkClaim.plugin.dataStore.getChunkAt(player.getLocation(), null);
        if (chunk == null) {
            player.sendMessage(ChatColor.RED + "This chunk is public.");
            Visualization visualization = Visualization.FromBukkitChunk(location.getChunk(), location.getBlockY(), VisualizationType.Public, location);
            Visualization.Apply(player, visualization);
        } else {
            PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(chunk.ownerName);
            ChunkClaim.plugin.dataStore.deleteChunk(chunk);

            //I know a lot of this is a mess but I'll clean it up later

            ChunkClaim.getEcon().depositPlayer(Bukkit.getOfflinePlayer(ChunkClaim.plugin.dataStore.getPlayerData(chunk.ownerName).playerName),
                    ChunkClaim.plugin.getConfig().getInt("costPerChunk"));

            ChunkClaim.plugin.dataStore.savePlayerData(chunk.ownerName, playerData);
            player.sendMessage(ChatColor.RED + "Chunk deleted.");

            Visualization visualization = Visualization.FromChunk(chunk, location.getBlockY(), VisualizationType.Public, location);
            Visualization.Apply(player, visualization);
        }

        return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.claim";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk delete - Delete the chunk you are in";
    }
}
