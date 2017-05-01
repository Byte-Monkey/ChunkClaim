package xyz.bytemonkey.securochunk.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;

/**
 * Created by Jack on 05/01/2017.
 */
public class Mark implements SubCommand {
    @Override
    public boolean onCommand(Player player, String[] args) {
        if(player.hasPermission("chunkclaim.admin")) {
                Location location = player.getLocation();

                if(!ChunkClaim.plugin.config_worlds.contains(location.getWorld().getName())) return true;

                PlayerData playerData = ChunkClaim.plugin.dataStore.getPlayerData(player.getName());
                Chunk chunk = ChunkClaim.plugin.dataStore.getChunkAt(location, playerData.lastChunk);

                if(chunk != null)
                {
                    String playerName = player.getName();
                    ChunkClaim.addLogEntry("Chunk at " + chunk.x + "|" + chunk.z + " has been marked for deletion by " + playerName);
                    chunk.mark();
                    chunk.marked = true;
                    player.sendMessage(ChatColor.RED + "Marked chunk for deletion.");

                } else
                    player.sendMessage(ChatColor.RED + "This chunk is public.");
                return true;
        } else
            return true;
    }

    @Override
    public String permission() {
        return "chunkclaim.admin";
    }

    @Override
    public String help(Player p) {
        return ChatColor.GREEN + "/chunk mark - Mark chunk for deletion";
    }

}
