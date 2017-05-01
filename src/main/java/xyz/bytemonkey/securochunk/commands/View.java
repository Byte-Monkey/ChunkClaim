package xyz.bytemonkey.securochunk.commands;

import org.bukkit.Location;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.visual.Visualization;
import xyz.bytemonkey.securochunk.visual.VisualizationType;

import java.util.Date;

/**
 * Created by Jack on 05/01/2017.
 */
public class View {


//    Chunk chunk = this.dataStore.getChunkAt(player.getLocation(),
//            null);
//    Location location = player.getLocation();
//    PlayerData playerData = this.dataStore.getPlayerData(player
//            .getName());



//    if(player.hasPermission("chunkclaim.admin")) {
//        String adminstring = "ID: " + location.getChunk().getX() + "|" + location.getChunk().getZ();
//        if (chunk != null) {
//            adminstring += ", Permanent: " + (chunk.modifiedBlocks<0?"true":("false ("+  chunk.modifiedBlocks + ")"));
//            long loginDays = ((new Date()).getTime()-this.dataStore.getPlayerData(chunk.ownerName).lastLogin.getTime())/(1000 * 60 * 60 * 24);
//            adminstring += ", Last Login: " + loginDays +" days ago.";
//        }
//        sendMsg(player,adminstring);
//        if(chunk != null && !chunk.ownerName.equals(player.getName())) {
//            StringBuilder builders = new StringBuilder();
//            for (int i = 0; i < chunk.builderNames.size(); i++) {
//
//                builders.append(chunk.builderNames.get(i));
//
//                if (i < chunk.builderNames.size() - 1) {
//
//                    builders.append(", ");
//                }
//            }
//            Visualization visualization = Visualization.FromChunk(
//                    chunk, location.getBlockY(),
//                    VisualizationType.Chunk, location);
//            Visualization.Apply(player, visualization);
//            sendMsg(player,"Trusted Builders:");
//            sendMsg(player,builders.toString());
//        }
//    }



//    if (chunk == null) {
//
//        sendMsg(player,"This chunk is public.");
//        Visualization visualization = Visualization
//                .FromBukkitChunk(location.getChunk(),
//                        location.getBlockY(),
//                        VisualizationType.Public, location);
//        Visualization.Apply(player, visualization);
//        return true;
//    }
//
//
//				else if (chunk.ownerName.equals(player.getName())) {
//
//        if (chunk.builderNames.size() > 0) {
//
//            StringBuilder builders = new StringBuilder();
//            for (int i = 0; i < chunk.builderNames.size(); i++) {
//
//                builders.append(chunk.builderNames.get(i));
//
//                if (i < chunk.builderNames.size() - 1) {
//
//                    builders.append(", ");
//                }
//            }
//            Visualization visualization = Visualization.FromChunk(
//                    chunk, location.getBlockY(),
//                    VisualizationType.Chunk, location);
//            Visualization.Apply(player, visualization);
//            sendMsg(player,"You own this chunk. Trusted Builders:");
//            sendMsg(player,builders.toString());
//
//        } else {
//            sendMsg(player,"You own this chunk. Use /chunk trust <player> to add other builders.");
//        }
//        Visualization visualization = Visualization.FromChunk(
//                chunk, location.getBlockY(),
//                VisualizationType.Chunk, location);
//        Visualization.Apply(player, visualization);
//        return true;
//    }
//
//				else {
//
//        if (chunk.isTrusted(player.getName())) {
//
//            sendMsg(player,chunk.ownerName
//                    + " owns this chunk. You have build rights!");
//            if (playerData.lastChunk != chunk) {
//                playerData.lastChunk = chunk;
//                Visualization visualization = Visualization
//                        .FromChunk(chunk, location.getBlockY(),
//                                VisualizationType.Chunk, location);
//                Visualization.Apply(player, visualization);
//            }
//        } else {
//
//            sendMsg(player,chunk.ownerName
//                    + " owns this chunk. You can't build here.");
//            if (playerData.lastChunk != chunk) {
//
//                playerData.lastChunk = chunk;
//                Visualization visualization = Visualization
//                        .FromChunk(chunk, location.getBlockY(),
//                                VisualizationType.ErrorChunk,
//                                location);
//                Visualization.Apply(player, visualization);
//            }
//        }
//        return true;
//    }

}
