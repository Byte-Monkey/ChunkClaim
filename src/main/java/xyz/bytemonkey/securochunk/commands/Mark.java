package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Location;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;

/**
 * Created by Jack on 05/01/2017.
 */
public class Mark {


//			else if (args[0].equalsIgnoreCase("mark")) {
//        if(player.hasPermission("chunkclaim.admin")) {
//            if(args.length==1) {
//
//                Location location = player.getLocation();
//                if(!ChunkClaim.plugin.config_worlds.contains(location.getWorld().getName())) return true;
//
//                PlayerData playerData = dataStore.getPlayerData(player.getName());
//                Chunk chunk = dataStore.getChunkAt(location, playerData.lastChunk);
//
//                if(chunk != null)
//                {
//                    String playerName = player.getName();
//                    ChunkClaim.addLogEntry("Chunk at " + chunk.x + "|" + chunk.z + " has been marked for deletion by " + playerName);
//                    chunk.mark();
//                    chunk.marked = true;
//                    sendMsg(player,"Marked chunk for deletion.");
//
//                } else {
//                    sendMsg(player,"This chunk is public.");
//                }
//                return true;
//            }
//            else {
//                sendMsg(player,"Usage: /chunk mark");
//                return true;
//            }
//        } else return false;
//    }

}
