package discordbot.command.informative;

import discordbot.command.CommandCategory;
import discordbot.core.AbstractCommand;
import discordbot.guildsettings.defaults.SettingCommandPrefix;
import discordbot.handler.GuildSettings;
import discordbot.handler.TextHandler;
import discordbot.main.Config;
import discordbot.main.DiscordBot;
import discordbot.util.DisUtil;
import discordbot.util.Misc;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * !help
 * help function
 */
public class Help extends AbstractCommand {
	public Help(DiscordBot b) {
		super(b);
	}

	@Override
	public String getDescription() {
		return "An attempt to help out";
	}

	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public String[] getUsage() {
		return new String[]{
				"help            //index of all commands",
				"help <command>  //usage for that command"};
	}

	@Override
	public String[] getAliases() {
		return new String[]{};
	}

	@Override
	public String execute(String[] args, IChannel channel, IUser author) {
		String commandPrefix = GuildSettings.getFor(channel, SettingCommandPrefix.class);
		if (args.length > 0) {
			AbstractCommand c = bot.commands.getCommand(DisUtil.filterPrefix(args[0], channel));
			if (c != null) {
				String ret = " :information_source: Help > " + c.getCommand() + " :information_source:" + Config.EOL;
				ArrayList<String> aliases = new ArrayList<>();
				aliases.add(commandPrefix + c.getCommand());
				for (String alias : c.getAliases()) {
					aliases.add(commandPrefix + alias);
				}
				ret += ":keyboard: **Accessible though:** " + Config.EOL +
						Misc.makeTable(aliases, 16, 3);
				if (c.getAliases().length > 0) {
					ret += "Aliases: " + Config.EOL +
							Misc.makeTable(Arrays.asList(c.getAliases()));
				}
				ret += ":notepad_spiral: **Description:** " + Config.EOL +
						Misc.makeTable(c.getDescription());
				if (c.getUsage().length > 0) {
					ret += ":gear: **Usages**:```php" + Config.EOL;
					for (String line : c.getUsage()) {
						ret += line + Config.EOL;
					}
					ret += "```";
				}
				return ret;
			}
			return TextHandler.get("command_help_donno");
		} else {
			String ret = "I know the following commands: " + Config.EOL + Config.EOL;
			HashMap<CommandCategory, ArrayList<String>> commandList = new HashMap<>();
			AbstractCommand[] commandObjects = bot.commands.getCommandObjects();
			for (AbstractCommand command : commandObjects) {
				if (!command.isListed() || !command.isEnabled()) {
					continue;
				}
				if (!commandList.containsKey(command.getCommandCategory())) {
					commandList.put(command.getCommandCategory(), new ArrayList<>());
				}
				commandList.get(command.getCommandCategory()).add(command.getCommand());
			}
			commandList.forEach((k, v) -> Collections.sort(v));
			ret += styleTablePerCategory(commandList);
//			ret += styleIndentedTable(commandList);
//			ret += styleOneTable(commandList);
			return ret + "for more details about a command use **" + commandPrefix + "help <command>**" + Config.EOL;
		}
	}

	private String styleOneTable(HashMap<CommandCategory, ArrayList<String>> map) {
		ArrayList<String> list = new ArrayList<>();
		int columns = 4;
		int index = 0;
		for (CommandCategory category : CommandCategory.values()) {
			if (map.containsKey(category)) {
				while (index % columns != 0) {
					index++;
					list.add("");
				}
				list.add(" > " + category.getPackageName());
				for (int i = 1; i < columns; i++) {
					list.add("");
					index++;
				}
				index++;
				for (String cmd : map.get(category)) {
					list.add(cmd);
					index++;
				}
				for (int i = 0; i < columns; i++) {
					list.add("");
					index++;
				}
			}
		}
		return Misc.makeTable(list, 16, columns);
	}

	private String styleTablePerCategory(HashMap<CommandCategory, ArrayList<String>> map) {
		String table = "";
		for (CommandCategory category : CommandCategory.values()) {
			if (map.containsKey(category)) {
				table += category.getEmoticon() + " " + category.getDisplayName() + Config.EOL;
				table += Misc.makeTable(map.get(category));
			}
		}
		return table;
	}

	private String styleIndentedTable(HashMap<CommandCategory, ArrayList<String>> map) {
		ArrayList<String> list = new ArrayList<>();
		int columns = 4;
		int index = 0;
		for (CommandCategory category : CommandCategory.values()) {
			if (map.containsKey(category)) {
				list.add(category.getPackageName());
				index++;
				for (int i = 0; i < columns; i++) {
					list.add("");
					index++;
				}
				for (String cmd : map.get(category)) {
					if (index % columns == 0) {
						list.add("");
						index++;
					}
					list.add(cmd);
					index++;
				}
				while (index % columns != 0) {
					index++;
					list.add("");
				}
			}
		}
		return Misc.makeTable(list, 16, columns);
	}
}