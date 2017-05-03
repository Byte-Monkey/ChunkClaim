/*
    ChunkClaim Plugin for Minecraft Bukkit Servers
    Copyright (C) 2012 Felix Schmidt
    
    This file is part of ChunkClaim.

    ChunkClaim is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ChunkClaim is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ChunkClaim.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.bytemonkey.securochunk;

import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.bytemonkey.securochunk.events.BlockEventHandler;
import xyz.bytemonkey.securochunk.events.EntityEventHandler;
import xyz.bytemonkey.securochunk.events.PlayerEventHandler;
import xyz.bytemonkey.securochunk.events.WorldEventHandler;
import xyz.bytemonkey.securochunk.utils.Chunk;
import xyz.bytemonkey.securochunk.utils.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChunkClaim extends JavaPlugin {
    private static final Logger logger = Logger.getLogger("Minecraft");
    public static ChunkClaim plugin;
    @Getter
    private static Economy econ = null;
    public DataStore dataStore;
    public List<String> config_worlds;
    public boolean config_protectContainers, config_regenerateChunk, config_pvpChunk, config_protectSwitches, config_nextToForce;
    public int config_chunkCost;

    public static void addLogEntry(String entry) {
        logger.info("ChunkClaim: " + entry);
    }

    public void onDisable() {
        for (int i = 0; i < getServer().getOnlinePlayers().size(); i++) {
            Player player = (Player) getServer().getOnlinePlayers().toArray()[i];
            String playerName = player.getName();
            PlayerData playerData = this.dataStore.getPlayerData(playerName);
            this.dataStore.savePlayerData(playerName, playerData);
        }

        this.dataStore.close();
        plugin = null;
    }

    public void onEnable() {
        plugin = this;

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.config_worlds = this.getConfig().getStringList("worlds");
        this.config_protectSwitches = this.getConfig().getBoolean("protectSwitches");
        this.config_protectContainers = this.getConfig().getBoolean("protectContainers");
		this.config_regenerateChunk = this.getConfig().getBoolean("regenerateChunk");
		this.config_chunkCost = this.getConfig().getInt("costPerChunk");
		this.config_pvpChunk = this.getConfig().getBoolean("pvpChunk");
		this.config_nextToForce = this.getConfig().getBoolean("nextToForce");

        try {
            this.dataStore = new FlatFileDataStore();
        } catch (Exception e) {
            addLogEntry("Unable to initialize the file system data store. Details:");
            addLogEntry(e.getMessage());
        }


        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new BlockEventHandler(dataStore), this);
        pluginManager.registerEvents(new PlayerEventHandler(dataStore), this);
        pluginManager.registerEvents(new EntityEventHandler(dataStore), this);
        pluginManager.registerEvents(new WorldEventHandler(dataStore), this);

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        }
        getCommand("chunk").setExecutor(new CommandHandler(this));

        new Metrics(this);
    }

	public void regenerateChunk(Chunk chunk) {
		if(config_regenerateChunk) {
			getServer().getWorld(chunk.worldName).regenerateChunk(chunk.x, chunk.z);
			getServer().getWorld(chunk.worldName).unloadChunkRequest(chunk.x, chunk.z);
		}

	}

    public ArrayList<Chunk> getChunksInRadius(Chunk chunk, String playerName, int radius) {
        ArrayList<Chunk> chunksInRadius = new ArrayList<Chunk>();

        for (int x = chunk.x - radius; x <= chunk.x + radius; x++)
            for (int z = chunk.z - radius; z <= chunk.z + radius; z++) {
                Chunk foundChunk = this.dataStore.getChunkAtPos(x, z, chunk.worldName);
                if (foundChunk != null && foundChunk.ownerName.equals(playerName))
                    chunksInRadius.add(foundChunk);
            }
        return chunksInRadius;
    }

    public void sendMsg(Player player, String message) {
        player.sendMessage(ChatColor.YELLOW + message);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public OfflinePlayer resolvePlayer(String name) {
        OfflinePlayer[] offlinePlayers = Bukkit.getServer().getOfflinePlayers();
        for (OfflinePlayer off : offlinePlayers)
            if (off.getName().equalsIgnoreCase(name))
                return off;
        return null;
    }
}