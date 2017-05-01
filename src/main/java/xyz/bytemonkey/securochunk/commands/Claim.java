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
public class Claim implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length == 1) {

            Location location = player.getLocation();
            if (!ChunkClaim.plugin.config_worlds.contains(location.getWorld().getName())) return true;

            PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(player.getName());
            Chunk chunk = ChunkClaim.plugin.dataStore.getChunkAt(location, playerData.lastChunk);

            String playerName = player.getName();

            if (chunk == null) {
                if (!player.hasPermission("chunkclaim.claim")) {
                    player.sendMessage(ChatColor.RED + "You don't have permissions for claiming chunks.");
                    return true;
                }
                if (playerData.getCredits() > 0) {

                    if (!player.hasPermission("chunkclaim.admin")) {
                        ArrayList<Chunk> playerChunks = ChunkClaim.plugin.dataStore.getAllChunksForPlayer(playerName);

                        if (playerChunks.size() > 0)
                            if (!ChunkClaim.plugin.dataStore.ownsNear(location, playerName)) {
                                player.sendMessage(ChatColor.RED + "You can only claim a new chunk next to your existing chunks.");
                                return true;
                            }
                    }
                    Chunk newChunk = new Chunk(location, playerName, playerData.builderNames);

                    ChunkClaim.plugin.dataStore.addChunk(newChunk);

                    playerData.credits--;
                    playerData.lastChunk = newChunk;
                    ChunkClaim.plugin.dataStore.savePlayerData(playerName, playerData);

                    player.sendMessage("You claimed this chunk. Credits left: " + playerData.getCredits());

                    Visualization visualization = Visualization.FromChunk(newChunk, location.getBlockY(), VisualizationType.Chunk, location);
                    Visualization.Apply(player, visualization);
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough credits to claim this chunk.");

                    if (playerData.lastChunk != chunk) {
                        playerData.lastChunk = chunk;
                        Visualization visualization = Visualization.FromBukkitChunk(location.getChunk(), location.getBlockY(), VisualizationType.Public, location);
                        Visualization.Apply(player, visualization);
                    }
                }
                return true;
            } else {
                player.sendMessage("This chunk is not public.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Usage: /chunk claim");
            return true;
        }

        return false;
    }

    @Override
    public String permission() {
        return "chunkclaim.claim";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk claim - Claims the chunk you're standing on";
    }
}