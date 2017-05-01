package xyz.bytemonkey.securochunk.commands;

import org.bukkit.OfflinePlayer;
import xyz.bytemonkey.securochunk.PlayerData;
import xyz.bytemonkey.securochunk.utils.Chunk;

import java.util.ArrayList;

/**
 * Created by Jack on 05/01/2017.
 */
public class Trust {

//    else if (args[0].equalsIgnoreCase("trust")) {
//
//        PlayerData playerData = this.dataStore.getPlayerData(player.getName());
//
//        if(args.length!=2) {
//            sendMsg(player,"Usage: /chunk trust <player>");
//            return true;
//
//        }
//
//        OfflinePlayer tp = resolvePlayer(args[1]);
//        if (tp == null) {
//
//            sendMsg(player,"Player not found.");
//            return true;
//        }
//        String tName = tp.getName();
//        if(tName.equals(player.getName())) {
//            sendMsg(player,"You don't trust yourself?");
//            return true;
//        }
//
//
//        ArrayList<Chunk> chunksInRadius = this.dataStore.getAllChunksForPlayer(player.getName());
//
//        if(!playerData.builderNames.contains(tName)) {
//
//            for(int i=0; i<chunksInRadius.size();i++) {
//                if(!chunksInRadius.get(i).isTrusted(tName)) {
//                    chunksInRadius.get(i).builderNames.add(tName);
//                    dataStore.writeChunkToStorage(chunksInRadius.get(i));
//                }
//
//            }
//            playerData.builderNames.add(tName);
//            this.dataStore.savePlayerData(player.getName(), playerData);
//
//        }
//        sendMsg(player,"Trusted " + tName+ " in all your chunks.");
//        return true;
//
//
//
//    }

}
