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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

public class ChunkClaim extends JavaPlugin {
	public static ChunkClaim plugin;
	private static final Logger logger = Logger.getLogger("Minecraft");

	public DataStore dataStore;

	/**
	 *
	 *
	 * ADD COMMAND HANDLER
	 * REMOVE MASS OF COMMANDS IN MAIN
	 * REMOVE AND REPLACE CREDITS
	 * CREDITS REPLACE WITH MONEY SYS
	 *
	 *
	 */

	public List<String> config_worlds;
	public boolean config_protectContainers;
	public boolean config_protectSwitches;
	public boolean config_mobsForCredits;
	public int config_mobPrice;
	float config_creditsPerHour;
	float config_maxCredits;
	float config_startCredits;
	public int config_minModBlocks;
	public float config_autoDeleteDays;
	public boolean config_nextToForce;
	private boolean config_regenerateChunk;

	@Getter private static Economy econ = null;

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

		// copy default config
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();

		this.config_worlds = this.getConfig().getStringList("worlds");
		this.config_protectSwitches = this.getConfig().getBoolean("protectSwitches");
		this.config_protectContainers = this.getConfig().getBoolean("protectContainers");
		this.config_mobsForCredits = this.getConfig().getBoolean("mobsForCredits");
		this.config_mobPrice = this.getConfig().getInt("mobPrice");
		this.config_startCredits = (float) this.getConfig().getDouble("startCredits");
		this.config_creditsPerHour = (float) this.getConfig().getDouble("creditsPerHour");;
		this.config_maxCredits = (float) this.getConfig().getDouble("maxCredits");
		this.config_minModBlocks = this.getConfig().getInt("minModBlocks");
		this.config_autoDeleteDays = (float) this.getConfig().getDouble("autoDeleteDays");
		this.config_nextToForce = this.getConfig().getBoolean("nextToForce");
		this.config_regenerateChunk = this.getConfig().getBoolean("regenerateChunk");
		
		try {
			this.dataStore = new FlatFileDataStore();
		} catch (Exception e) {
			addLogEntry("Unable to initialize the file system data store. Details:");
			addLogEntry(e.getMessage());
		}
		

		// register for events
		PluginManager pluginManager = this.getServer().getPluginManager();

		// register block events
		BlockEventHandler blockEventHandler = new BlockEventHandler(dataStore);
		pluginManager.registerEvents(blockEventHandler, this);

		// register player events
		PlayerEventHandler playerEventHandler = new PlayerEventHandler(
				dataStore);
		pluginManager.registerEvents(playerEventHandler, this);

		// register entity events
		EntityEventHandler entityEventHandler = new EntityEventHandler(
				dataStore);
		pluginManager.registerEvents(entityEventHandler, this);

		// register world events
		WorldEventHandler worldEventHandler = new WorldEventHandler(dataStore);
		pluginManager.registerEvents(worldEventHandler, this);

		if (this.config_creditsPerHour > 0) {
			DeliverCreditsTask task = new DeliverCreditsTask();
			this.getServer()
					.getScheduler()
					.scheduleSyncRepeatingTask(this, task, 20L * 60 * 60,
							20L * 60 * 60);
		}

		if (!setupEconomy() ) {
			getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
		}
		getCommand("chunk").setExecutor(new CommandHandler(this));

        new Metrics(this);
	}

	public ArrayList<Chunk> getChunksInRadius(Chunk chunk, String playerName, int radius) {
		
		ArrayList<Chunk> chunksInRadius = new ArrayList<Chunk>();

		for(int x = chunk.x-radius; x <= chunk.x+radius; x++) {
			for(int z = chunk.z-radius; z <= chunk.z+radius; z++) {
				
				Chunk foundChunk = this.dataStore.getChunkAtPos(x,z,chunk.worldName);
				
				if(foundChunk!=null && foundChunk.ownerName.equals(playerName)) {
					
					chunksInRadius.add(foundChunk);
					
				}
				
			}
		}
		
		return chunksInRadius;
	}
	
	public void regenerateChunk(Chunk chunk) {
		if(config_regenerateChunk) {
			getServer().getWorld(chunk.worldName).regenerateChunk(chunk.x, chunk.z);
			getServer().getWorld(chunk.worldName).unloadChunkRequest(chunk.x, chunk.z);
		}

	}

	public static void addLogEntry(String entry) {
		logger.info("ChunkClaim: " + entry);
	}
	public void sendMsg(Player player, String message) {
		player.sendMessage(ChatColor.YELLOW +  message);
	}

	public void broadcast(String message) {

		for(int i = 0; i < getServer().getOnlinePlayers().size(); i++) {
			Player player = (Player) getServer().getOnlinePlayers().toArray()[i];
			player.sendMessage(message);
		}
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
        for (int i = 0; i < offlinePlayers.length; i++) {
            if (offlinePlayers[i].getName().equalsIgnoreCase(name)) {
                return offlinePlayers[i];
            }
        }
        return null;
    }
}