package net.badbird5907.aetheriacore.spigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * Command Framework - CommandFramework <br>
 * The main command framework class used for controlling the framework.
 *
 */
public class CommandFramework implements CommandExecutor {

	private Map<String, Entry<Method, Object>> commandMap = new HashMap<String, Entry<Method, Object>>();
	private CommandMap map;
	private Plugin plugin;

	/**
	 * Initializes the command framework and sets up the command maps
	 */
	public CommandFramework(Plugin plugin) {
		this.plugin = plugin;
		if (plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
			SimplePluginManager manager = (SimplePluginManager) plugin.getServer().getPluginManager();
			try {
				Field field = SimplePluginManager.class.getDeclaredField("commandMap");
				field.setAccessible(true);
				map = (CommandMap) field.get(manager);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		return handleCommand(sender, cmd, label, args);
	}
	
	/**
	 * Handles commands. Used in the onCommand method in your JavaPlugin class
	 * 
	 * @param sender The {@link CommandSender} parsed from
	 *            onCommand
	 * @param cmd The {@link org.bukkit.command.Command} parsed from onCommand
	 * @param label The label parsed from onCommand
	 * @param args The arguments parsed from onCommand
	 * @return Always returns true for simplicity's sake in onCommand
	 */
	public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		for (int i = args.length; i >= 0; i--) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(label.toLowerCase());
			for (int x = 0; x < i; x++) {
				buffer.append("." + args[x].toLowerCase());
			}
			String cmdLabel = buffer.toString();
			if (commandMap.containsKey(cmdLabel)) {
				Method method = commandMap.get(cmdLabel).getKey();
				Object methodObject = commandMap.get(cmdLabel).getValue();
				Command command = method.getAnnotation(Command.class);
				if (command.permission() != net.badbird5907.aetheriacore.spigot.manager.Permission.NOTHING)
					if (!sender.hasPermission(command.permission().node)) {
						sender.sendMessage(ChatColor.RED + "You don't have permission!");
						return true;
					}
				if (command.playerOnly()) {
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED + "This command is player only!");
						return true;
					}
				}
				try {
					CommandResult result = (CommandResult) method.invoke(methodObject, new Sender(sender), args);
					if(result == CommandResult.SUCCESS)
						return true;
					else if(result.getMsg() == ""){
						return true;
					}else if(result.getMsg() == null){
						return true;
					} else{
						sender.sendMessage(result.getMsg());
						return true;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		sender.sendMessage(ChatColor.RED + "You ran into a unhandled command! Please report this.");
		return true;
	}
	/**
	 * Registers all command and completer methods inside of the object. Similar
	 * to Bukkit's registerEvents method.
	 * 
	 * @param obj The object to register the commands of
	 */
	public void registerCommands(Object obj) {
		for (Method m : obj.getClass().getMethods()) {
			if (m.getAnnotation(Command.class) != null) {
				Command command = m.getAnnotation(Command.class);
				if (m.getParameterTypes().length > 2 || m.getParameterTypes()[0] != Sender.class || m.getParameterTypes()[1] != String[].class) {
					Bukkit.getLogger().severe("Unable to register command " + m.getName() + ". Unexpected method arguments");
					continue;
				}
				registerCommand(command, command.name(), m, obj);
				for (String alias : command.aliases()) {
					registerCommand(command, alias, m, obj);
				}
			} else if (m.getAnnotation(Completer.class) != null) {
				Completer comp = m.getAnnotation(Completer.class);
				if (m.getParameterTypes().length > 2 || m.getParameterTypes().length == 0
						|| m.getParameterTypes()[0] != Sender.class || m.getParameterTypes()[1] != String[].class) {
					Bukkit.getLogger().severe("Unable to register tab completer " + m.getName() + ". Unexpected method arguments");
					continue;
				}
				if (m.getReturnType() != List.class) {
					Bukkit.getLogger().severe("Unable to register tab completer " + m.getName() + ". Unexpected return type");
					continue;
				}
				registerCompleter(comp.name(), m, obj);
				for (String alias : comp.aliases()) {
					registerCompleter(alias, m, obj);
				}
			}
		}
	}

	/**
	 * Registers all the commands under the plugin's help
	 */
	public void registerHelp() {
		Set<HelpTopic> help = new TreeSet<HelpTopic>(HelpTopicComparator.helpTopicComparatorInstance());
		for (String s : commandMap.keySet()) {
			if (!s.contains(".")) {
				org.bukkit.command.Command cmd = map.getCommand(s);
				HelpTopic topic = new GenericCommandHelpTopic(cmd);
				help.add(topic);
			}
		}
		IndexHelpTopic topic = new IndexHelpTopic(plugin.getName(), "All commands for " + plugin.getName(), null, help,
				"Below is a list of all " + plugin.getName() + " commands:");
		Bukkit.getServer().getHelpMap().addTopic(topic);
	}

	public void registerCommand(Command command, String label, Method m, Object obj) {
		commandMap.put(label.toLowerCase(), new AbstractMap.SimpleEntry<Method, Object>(m, obj));
		commandMap.put(this.plugin.getName() + ':' + label.toLowerCase(), new AbstractMap.SimpleEntry<Method, Object>(m, obj));
		String cmdLabel = label.split("\\.")[0].toLowerCase();
		if (map.getCommand(cmdLabel) == null) {
			org.bukkit.command.Command cmd = new BukkitCommand(cmdLabel, this, plugin);
			map.register(plugin.getName(), cmd);
		}
		if (!command.description().equalsIgnoreCase("") && cmdLabel.equals(label)) {
			map.getCommand(cmdLabel).setDescription(command.description());
		}
		if (!command.usage().equalsIgnoreCase("") && cmdLabel.equals(label)) {
			map.getCommand(cmdLabel).setUsage(command.usage());
		}
		if(!(command.permission()==null)&&!(command.permission().getNode()=="")&&!(command.permission() == net.badbird5907.aetheriacore.spigot.manager.Permission.NOTHING)){
		    registerPermission(command.permission().getNode(),"Permission of /" + command.name());
			//if(!Bukkit.getPluginManager().getPermissions().contains())
			//	Bukkit.getPluginManager().addPermission(perm);
		}
	}

	public void registerCompleter(String label, Method m, Object obj) {
		String cmdLabel = label.split("\\.")[0].toLowerCase();
		if (map.getCommand(cmdLabel) == null) {
			org.bukkit.command.Command command = new BukkitCommand(cmdLabel, this, plugin);
			map.register(plugin.getName(), command);
		}
		if (map.getCommand(cmdLabel) instanceof BukkitCommand) {
			BukkitCommand command = (BukkitCommand) map.getCommand(cmdLabel);
			if (command.completer == null) {
				command.completer = new BukkitCompleter();
			}
			command.completer.addCompleter(label, m, obj);
		} else if (map.getCommand(cmdLabel) instanceof PluginCommand) {
			try {
				Object command = map.getCommand(cmdLabel);
				Field field = command.getClass().getDeclaredField("completer");
				field.setAccessible(true);
				if (field.get(command) == null) {
					BukkitCompleter completer = new BukkitCompleter();
					completer.addCompleter(label, m, obj);
					field.set(command, completer);
				} else if (field.get(command) instanceof BukkitCompleter) {
					BukkitCompleter completer = (BukkitCompleter) field.get(command);
					completer.addCompleter(label, m, obj);
				} else {
					Bukkit.getLogger().severe("Unable to register tab completer " + m.getName()
							+ ". A tab completer is already registered for that command!");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	private static void registerPermission(String name, String desc){
        Permission perm = new Permission(name, desc);
        if(!getPermissionsString().contains(name)) {
            Bukkit.getLogger().info("Registering permission \"" + name + "\" because it isn't registered.");
            Bukkit.getPluginManager().getPermissions().add(perm);
        }
        else{
        	if(!(name == ""))
        		Bukkit.getLogger().info("Didn't register permission \"" + name + "\" because it already is registered!");
        }
    }
    private static List<String> getPermissionsString(){
		List<String> perms = new ArrayList<>();
		Bukkit.getPluginManager().getPermissions().forEach(permission -> perms.add(permission.getName()));
		return perms;
	}
}
