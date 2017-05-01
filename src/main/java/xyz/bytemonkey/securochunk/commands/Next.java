package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jack on 05/01/2017.
 */
public class Next implements SubCommand {

    private Map<String, Integer> playerNextInfo = new HashMap<>();

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (player.hasPermission("chunkclaim.admin")) {

            Location location = player.getLocation();
            if (!ChunkClaim.plugin.config_worlds.contains(location.getWorld().getName())) return true;

            boolean reset = false;

            PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(player.getName());

            Chunk chunk = null;
            String worldName = null;
            boolean inspected = true;
            boolean marked = true;
            boolean permanent = false;

            //int r = (int)(Math.random()*dataStore.chunks.size());
            int r = ChunkClaim.plugin.dataStore.nextChunkId;

            int j;

            if (playerNextInfo.containsKey(player.getName())) {
                j = playerNextInfo.get(player.getName());
                playerNextInfo.put(player.getName(), j++);
            } else {
                j = 0;
                playerNextInfo.put(player.getName(), 1);
            }

            int size = ChunkClaim.plugin.dataStore.chunks.size();
            if(j >= size) {
                playerNextInfo.remove(player.getName());
                return true;
            }

            for (int i = 0; i < size; i++) {
                chunk = ChunkClaim.plugin.dataStore.chunks.get(j);
                worldName = chunk.worldName;
                inspected = chunk.inspected;
                marked = chunk.marked;
                permanent = chunk.modifiedBlocks == -1;
                if (worldName.equals(player.getWorld().getName()) && !inspected && !marked && permanent) {
                    break;
                }
            }
            if (chunk == null) {
                player.sendMessage(ChatColor.RED + "No chunk found.");
                return true;
            }

            chunk.inspected = true;
            int x = chunk.x * 16 + 8;
            int z = chunk.z * 16 + 8;
            int y = player.getWorld().getHighestBlockYAt(new Location(player.getWorld(), x, 0, z)) + 15;

            Location l = new Location(player.getWorld(), x, y, z, 0, 90);

            player.teleport(l);

            String adminstring = "ID: " + chunk.x + "|" + chunk.z;
            if (chunk != null) {
                adminstring += ", " + chunk.ownerName;
                long loginDays = ((new Date()).getTime() - ChunkClaim.plugin.dataStore.getPlayerData(chunk.ownerName).lastLogin.getTime()) / (1000 * 60 * 60 * 24);
                adminstring += ", Last Login: " + loginDays + " days ago.";
            }
            player.sendMessage(ChatColor.RED + adminstring);
            Visualization visualization = Visualization.FromChunk(
                    chunk, location.getBlockY(),
                    VisualizationType.Chunk, location);
            Visualization.Apply(player, visualization);
        }
        return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.admin";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk next - Teleports you to a random claimed chunk (useful for inspections)";
    }

}
