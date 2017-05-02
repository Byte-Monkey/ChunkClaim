package xyz.bytemonkey.securochunk.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.Date;

/**
 * Created by Jack on 05/01/2017.
 */
public class View implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        Chunk chunk = ChunkClaim.plugin.dataStore.getChunkAt(player.getLocation(),
                null);
        Location location = player.getLocation();
        PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(player
                .getName());


        if (player.hasPermission("chunkclaim.admin")) {
            String adminstring = "ID: " + location.getChunk().getX() + "|" + location.getChunk().getZ();
            if (chunk != null) {
                adminstring += ", Permanent: " + (chunk.modifiedBlocks < 0 ? "true" : ("false (" + chunk.modifiedBlocks + ")"));
                long loginDays = ((new Date()).getTime() - ChunkClaim.plugin.dataStore.getPlayerData(chunk.ownerName).lastLogin.getTime()) / (1000 * 60 * 60 * 24);
                adminstring += ", Last Login: " + loginDays + " days ago.";
            }
            player.sendMessage(ChatColor.GREEN + adminstring);
            if (chunk != null && !chunk.ownerName.equals(player.getName())) {
                StringBuilder builders = new StringBuilder();
                for (int i = 0; i < chunk.builderNames.size(); i++) {

                    builders.append(chunk.builderNames.get(i));

                    if (i < chunk.builderNames.size() - 1) {

                        builders.append(", ");
                    }
                }
                Visualization visualization = Visualization.FromChunk(
                        chunk, location.getBlockY(),
                        VisualizationType.Chunk, location);
                Visualization.Apply(player, visualization);
                player.sendMessage(ChatColor.GREEN + "Trusted Builders:");
                player.sendMessage(ChatColor.GREEN + builders.toString());
            }
            if (chunk == null) {

                player.sendMessage(ChatColor.RED + "This chunk is public.");
                Visualization visualization = Visualization
                        .FromBukkitChunk(location.getChunk(),
                                location.getBlockY(),
                                VisualizationType.Public, location);
                Visualization.Apply(player, visualization);
                return true;
            } else if (chunk.ownerName.equals(player.getName())) {

                if (chunk.builderNames.size() > 0) {

                    StringBuilder builders = new StringBuilder();
                    for (int i = 0; i < chunk.builderNames.size(); i++) {

                        builders.append(chunk.builderNames.get(i));

                        if (i < chunk.builderNames.size() - 1) {

                            builders.append(", ");
                        }
                    }
                    Visualization visualization = Visualization.FromChunk(
                            chunk, location.getBlockY(),
                            VisualizationType.Chunk, location);
                    Visualization.Apply(player, visualization);
                    player.sendMessage(ChatColor.GREEN + "You own this chunk. Trusted Builders:");
                    player.sendMessage(ChatColor.GREEN + builders.toString());

                } else
                    player.sendMessage(ChatColor.GREEN + "You own this chunk. Use /chunk trust <player> to add other builders.");
                Visualization visualization = Visualization.FromChunk(
                        chunk, location.getBlockY(),
                        VisualizationType.Chunk, location);
                Visualization.Apply(player, visualization);
                return true;
            } else {
                if (chunk.isTrusted(player.getName())) {
                    player.sendMessage(ChatColor.GREEN + chunk.ownerName
                            + " owns this chunk. You have build rights!");
                    if (playerData.lastChunk != chunk) {
                        playerData.lastChunk = chunk;
                        Visualization visualization = Visualization
                                .FromChunk(chunk, location.getBlockY(),
                                        VisualizationType.Chunk, location);
                        Visualization.Apply(player, visualization);
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + chunk.ownerName
                            + " owns this chunk. You can't build here.");
                    if (playerData.lastChunk != chunk) {

                        playerData.lastChunk = chunk;
                        Visualization visualization = Visualization
                                .FromChunk(chunk, location.getBlockY(),
                                        VisualizationType.ErrorChunk,
                                        location);
                        Visualization.Apply(player, visualization);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.basecmd";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk view - View chunks around you";
    }
}