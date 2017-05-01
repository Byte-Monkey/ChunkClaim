package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Location;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.ArrayList;

/**
 * Created by Jack on 05/01/2017.
 */
public class Abandon {
//    else if (args[0].equalsIgnoreCase("abandon")) {
//
//        Chunk chunk = this.dataStore.getChunkAt(player.getLocation(), null);
//        PlayerData playerData = this.dataStore.getPlayerData(player.getName());
//        Location location = player.getLocation();
//
//        if(args.length==2) {
//
//            int radius;
//            int abd = 0;
//
//            try {
//
//                radius = Integer.parseInt(args[1]);
//
//                if(radius<0) {
//
//                    sendMsg(player,"Error: Negative Radius");
//                    return true;
//                }
//
//                if(radius>10) {
//                    sendMsg(player,"Error: Max Radius is 10.");
//                    return true;
//                }
//
//
//                ArrayList<Chunk> chunksInRadius = this.getChunksInRadius(chunk, player.getName(),radius);
//
//
//                for(int i=0; i<chunksInRadius.size();i++) {
//
//                    this.dataStore.deleteChunk(chunksInRadius.get(i));
//                    playerData.credits++;
//                    abd++;
//
//
//                }
//
//                this.dataStore.savePlayerData(player.getName(), playerData);
//
//                sendMsg(player,abd + " Chunks abandoned in radius "+radius+". Credits: " + playerData.getCredits());
//
//                return true;
//
//            } catch(Exception e) {
//
//                sendMsg(player,"Usage: /chunk abandon [radius]");
//                return true;
//            }
//
//        }
//        else if(args.length==1) {
//            if (chunk == null) {
//
//                sendMsg(player,"This chunk is public.");
//                Visualization visualization = Visualization.FromBukkitChunk(location.getChunk(), location.getBlockY(), VisualizationType.Public, location);
//                Visualization.Apply(player, visualization);
//            }
//
//            else if (chunk.ownerName.equals(player.getName())) {
//
//                this.dataStore.deleteChunk(chunk);
//                playerData.credits++;
//                this.dataStore.savePlayerData(player.getName(), playerData);
//                sendMsg(player,"Chunk abandoned. Credits: "	+ playerData.getCredits());
//
//                Visualization visualization = Visualization.FromChunk(chunk, location.getBlockY(),VisualizationType.Public, location);
//                Visualization.Apply(player, visualization);
//
//                return true;
//            }
//
//            else {
//                if (playerData.lastChunk != chunk) {
//                    playerData.lastChunk = chunk;
//                    Visualization visualization = Visualization.FromChunk(chunk, location.getBlockY(),VisualizationType.ErrorChunk, location);
//                    Visualization.Apply(player, visualization);
//                }
//                sendMsg(player,"You don't own this chunk. Only "
//                        + chunk.ownerName + " or the staff can delete it.");
//                return true;
//            }
//
//        }
//
//        else {
//            sendMsg(player,"Usage: /chunk abandon [radius]");
//            return true;
//        }
//    }
}
