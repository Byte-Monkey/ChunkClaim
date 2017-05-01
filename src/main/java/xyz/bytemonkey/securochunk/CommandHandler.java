package xyz.bytemonkey.securochunk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import xyz.bytemonkey.securochunk.commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;

public class CommandHandler implements CommandExecutor {

    private HashMap<String, SubCommand> commands = new HashMap<>();
    private FileConfiguration c;
    private Plugin plugin;

    public CommandHandler(Plugin plugin) {
        this.c = plugin.getConfig();
        this.plugin = plugin;

        loadCommands();
    }

    private void loadCommands() {
        commands.put("claim", new Claim());
        commands.put("list", new List());
        commands.put("mark", new Mark());
        commands.put("unmark", new Unmark());
        commands.put("next", new Next());
        commands.put("view", new View());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd1, String commandLabel, String[] args) {
        PluginDescriptionFile pdfFile = plugin.getDescription();
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().log(Level.INFO, "Only in-game players can use Chunk Claim commands! ");
            return true;
        }

        Player player = (Player) sender;

        if (cmd1.getName().equalsIgnoreCase("chunk")) {
            if(!ChunkClaim.plugin.config_worlds.contains(player.getWorld().getName())) return true;

            if (args == null || args.length < 1) {
                player.sendMessage(ChatColor.GREEN + "Version " + pdfFile.getVersion() + " by ByteMonkey");
                player.sendMessage(ChatColor.GREEN + "Type /chunk help for command information");
                return true;
            }
            if (args[0].equalsIgnoreCase("help")) {
                if (args.length == 1)
                    help(player);
                return true;
            }
            String sub = args[0];
            Vector<String> l = new Vector<>();
            l.addAll(Arrays.asList(args));
            l.remove(0);
            args = l.toArray(new String[0]);
            if (!commands.containsKey(sub)) {
                player.sendMessage(ChatColor.RED + "Command doesn't exist.");
                player.sendMessage(ChatColor.GREEN + "Type /chunk help for command information");
                return true;
            }
            try {
                commands.get(sub).onCommand(player, args);
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage(ChatColor.RED + "Command error.");
                player.sendMessage(ChatColor.GREEN +  "Type /chunk help for command information");
            }
            return true;
        }
        return false;
    }

    private void help(Player p) {
        p.sendMessage(ChatColor.BLUE + "------------ " + ChatColor.DARK_AQUA + " Chunk Claim Commands" + ChatColor.BLUE + " ------------");
        for (String command : commands.keySet()) {
            try {
                p.sendMessage(commands.get(command).help(p));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
