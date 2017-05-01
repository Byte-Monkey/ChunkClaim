package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.ArrayList;

/**
 * Created by Jack on 05/01/2017.
 */
public class Delete {

//    else if (args[0].equalsIgnoreCase("delete")) {
//
//        if(!player.hasPermission("chunkclaim.admin")) {
//            sendMsg(player,"No permission.");
//            return true;
//        }
//
//
//        Location location = player.getLocation();
//
//        if(args.length==3) {
//
//            int radius;
//            int abd = 0;
//
//            try {
//
//                radius = Integer.parseInt(args[2]);
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
//                OfflinePlayer tp = resolvePlayer(args[1]);
//                if (tp == null) {
//
//                    sendMsg(player,"Player not found.");
//                    return true;
//                }
//                String tName = tp.getName();
//                PlayerData playerData = this.dataStore.getPlayerData(tName);
//
//                org.bukkit.Chunk bukkitChunk = location.getChunk();
//                Chunk chunk = new Chunk(bukkitChunk.getX(),bukkitChunk.getZ(),bukkitChunk.getWorld().getName());
//                ArrayList<Chunk> chunksInRadius = this.getChunksInRadius(chunk, tName,radius);
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
//                this.dataStore.savePlayerData(tName, playerData);
//
//                sendMsg(player,abd + " Chunks deleted in radius "+radius+".");
//
//                return true;
//
//            } catch(Exception e) {
//
//                sendMsg(player,"Usage: /chunk delete [<player> <radius>]");
//                return true;
//            }
//
//        }
//        else if(args.length==1) {
//            Chunk chunk = this.dataStore.getChunkAt(player.getLocation(), null);
//
//
//            if (chunk == null) {
//
//                sendMsg(player,"This chunk is public.");
//                Visualization visualization = Visualization.FromBukkitChunk(location.getChunk(), location.getBlockY(), VisualizationType.Public, location);
//                Visualization.Apply(player, visualization);
//            }
//
//            else {
//                PlayerData playerData = this.dataStore.getPlayerData(chunk.ownerName);
//                this.dataStore.deleteChunk(chunk);
//                playerData.credits++;
//                this.dataStore.savePlayerData(chunk.ownerName, playerData);
//                sendMsg(player,"Chunk deleted.");
//
//                Visualization visualization = Visualization.FromChunk(chunk, location.getBlockY(),VisualizationType.Public, location);
//                Visualization.Apply(player, visualization);
//
//                return true;
//            }
//
//        }
//
//        else {
//            sendMsg(player,"Usage: /chunk delete [<player> <radius>]");
//            return true;
//        }
//
//    }

}
