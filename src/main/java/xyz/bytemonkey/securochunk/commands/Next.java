package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Location;
import xyz.bytemonkey.securochunk.ChunkClaim;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.Date;

/**
 * Created by Jack on 05/01/2017.
 */
public class Next {

//    else if (args[0].equalsIgnoreCase("next")) {
//        if(player.hasPermission("chunkclaim.admin")) {
//
//            if(args.length==1) {
//                Location location = player.getLocation();
//                if(!ChunkClaim.plugin.config_worlds.contains(location.getWorld().getName())) return true;
//
//                boolean reset = false;
//
//                PlayerData playerData = this.dataStore.getPlayerData(player.getName());
//
//                Chunk chunk = null;
//                String worldName = null;
//                boolean inspected = true;
//                boolean marked = true;
//                boolean permanent = false;
//
//                //int r = (int)(Math.random()*dataStore.chunks.size());
//                int r = this.dataStore.nextChunkId;
//
//                for(int i = 0; i < dataStore.chunks.size(); i++) {
//
//                    int j = (r + i)%dataStore.chunks.size();
//
//                    chunk = dataStore.chunks.get(j);
//                    worldName = chunk.worldName;
//                    inspected = chunk.inspected;
//                    marked = chunk.marked;
//                    permanent = chunk.modifiedBlocks == -1;
//
//                    if(worldName.equals(player.getWorld().getName()) && !inspected && !marked && permanent) {
//                        break;
//                    }
//                }
//                if(chunk==null || !(worldName.equals(player.getWorld().getName()) && !inspected && !marked && permanent)) {
//                    sendMsg(player,"No chunk found.");
//                    return true;
//                }
//
//                chunk.inspected = true;
//                int x = chunk.x*16 +8;
//                int z = chunk.z*16 +8;
//                int y = player.getWorld().getHighestBlockYAt(new Location(player.getWorld(),x,0,z)) + 15;
//
//                Location l = new Location(player.getWorld(),x,y,z,0,90);
//
//                player.teleport(l);
//
//                String adminstring = "ID: " + chunk.x + "|" + chunk.z;
//                if (chunk != null) {
//                    adminstring += ", " + chunk.ownerName;
//                    long loginDays = ((new Date()).getTime()-this.dataStore.getPlayerData(chunk.ownerName).lastLogin.getTime())/(1000 * 60 * 60 * 24);
//                    adminstring += ", Last Login: " + loginDays +" days ago.";
//                }
//                sendMsg(player,adminstring);
//                Visualization visualization = Visualization.FromChunk(
//                        chunk, location.getBlockY(),
//                        VisualizationType.Chunk, location);
//                Visualization.Apply(player, visualization);
//
//                return true;
//
//            }
//            else {
//                sendMsg(player,"Usage: /chunk next");
//                return true;
//            }
//        } else return false;
//    }

}
