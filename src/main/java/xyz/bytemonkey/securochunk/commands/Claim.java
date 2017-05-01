package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Location;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.ArrayList;

/**
 * Created by Jack on 05/01/2017.
 */
public class Claim {

//    else if (args[0].equalsIgnoreCase("claim")) {
//        if(args.length==1) {
//
//            Location location = player.getLocation();
//            if(!ChunkClaim.plugin.config_worlds.contains(location.getWorld().getName())) return true;
//
//            PlayerData playerData = dataStore.getPlayerData(player.getName());
//            Chunk chunk = dataStore.getChunkAt(location, playerData.lastChunk);
//
//            String playerName = player.getName();
//
//            if(chunk == null) {
//
//
//                if(!player.hasPermission("chunkclaim.claim")) {
//                    sendMsg(player,"You don't have permissions for claiming chunks.");
//                    return true;
//                }
//                if(playerData.getCredits() > 0) {
//
//                    if(config_nextToForce && !player.hasPermission("chunkclaim.admin"))
//                    {
//                        ArrayList<Chunk> playerChunks = dataStore.getAllChunksForPlayer(playerName);
//
//                        if(playerChunks.size()>0)
//                        {
//                            if(!dataStore.ownsNear(location, playerName)) {
//                                sendMsg(player,"You can only claim a new chunk next to your existing chunks.");
//                                return true;
//                            }
//                        }
//                    }
//
//
//                    Chunk newChunk = new Chunk(location,playerName,playerData.builderNames);
//
//                    this.dataStore.addChunk(newChunk);
//
//                    playerData.credits--;
//                    playerData.lastChunk=newChunk;
//                    //newChunk.modify();
//                    this.dataStore.savePlayerData(playerName, playerData);
//
//                    sendMsg(player,"You claimed this chunk. Credits left: " + playerData.getCredits());
//
//                    Visualization visualization = Visualization.FromChunk(newChunk, location.getBlockY(), VisualizationType.Chunk, location);
//                    Visualization.Apply(player, visualization);
//
//                } else {
//
//                    sendMsg(player,"Not enough credits to claim this chunk.");
//
//                    if(playerData.lastChunk!=chunk) {
//                        playerData.lastChunk=chunk;
//                        Visualization visualization = Visualization.FromBukkitChunk(location.getChunk(), location.getBlockY(), VisualizationType.Public, location);
//                        Visualization.Apply(player, visualization);
//                    }
//                }
//                return true;
//            } else {
//                sendMsg(player,"This chunk is not public.");
//            }
//        }
//        else {
//            sendMsg(player,"Usage: /chunk claim");
//            return true;
//        }
//
//    }

}
