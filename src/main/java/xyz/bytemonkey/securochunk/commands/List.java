package xyz.bytemonkey.securochunk.commands;

import org.bukkit.OfflinePlayer;
import xyz.bytemonkey.securochunk.utils.Chunk;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jack on 05/01/2017.
 */
public class List {

//    else if (args[0].equalsIgnoreCase("list")) {
//        if(player.hasPermission("chunkclaim.admin")) {
//            if(args.length==2) {
//
//
//                OfflinePlayer tp = resolvePlayer(args[1]);
//                if (tp == null) {
//
//                    sendMsg(player,"Player not found.");
//                    return true;
//                }
//                String tName = tp.getName();
//
//                ArrayList<Chunk> chunksInRadius = this.dataStore.getAllChunksForPlayer(tName);
//
//                long loginDays = ((new Date()).getTime()-this.dataStore.getPlayerData(tp.getName()).lastLogin.getTime())/(1000 * 60 * 60 * 24);
//                long joinDays = ((new Date()).getTime()-this.dataStore.getPlayerData(tp.getName()).firstJoin.getTime())/(1000 * 60 * 60 * 24);
//                String adminstring = tp.getName() + " | Last Login: " + loginDays +" days ago. First Join: " + joinDays + " days ago.";
//                sendMsg(player,adminstring);
//
//                for(int i=0; i<chunksInRadius.size();i++) {
//
//                    Chunk chunk = chunksInRadius.get(i);
//
//
//                    adminstring = "ID: " + chunk.x + "|" + chunk.z + "("+(chunk.x*16) + "|" + (chunk.z*16)+")";
//                    if (chunk != null) {
//                        adminstring += ", Permanent: " + (chunk.modifiedBlocks<0?"true":("false ("+  chunk.modifiedBlocks + ")"));
//
//                    }
//                    sendMsg(player,adminstring);
//
//
//
//                }
//                return true;
//            }
//            else {
//                sendMsg(player,"Usage: /chunk list <player>");
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }

}
