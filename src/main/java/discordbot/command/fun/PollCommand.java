package discordbot.command.fun;

import discordbot.command.CommandVisibility;
import discordbot.core.AbstractCommand;
import discordbot.handler.TextHandler;
import discordbot.main.DiscordBot;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class PollCommand extends AbstractCommand {
	private static final int MAX_DURATION_IN_MINUTES = 10;

	public PollCommand(DiscordBot b) {
		super(b);
	}

	@Override
	public String getDescription() {
		return "Strawpoll: propose a question and choices for the chat to vote on";
	}

	@Override
	public String getCommand() {
		return "poll";
	}

	@Override
	public String[] getUsage() {
		return new String[]{
				"poll          //status of active poll ",
				"poll create <question> ;<duration in minutes>;<option1>;<option2>;<etc.> ",
				"              //creates a poll for the duration",
				"poll 1-9      //vote on the options",
		};
	}

	@Override
	public String[] getAliases() {
		return new String[]{};
	}

	@Override
	public CommandVisibility getVisibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String execute(String[] args, IChannel channel, IUser author) {

		if (args.length == 0) {
			return "show overview";
		}
		if (args[0].equalsIgnoreCase("create")) {
			//if poll is NOT going on
			String argument = "";
			for (int i = 1; i < args.length; i++) {
				argument += " " + args[i];
			}
			String[] split = argument.split(";");
			if (split.length > 4) {
				return "Invalid usage! Need at least 2 options " + getUsage()[1];
			}
			if (split[0].trim().length() < 3) {
				return TextHandler.get("command_poll_question_too_short");
			}
			if (!split[1].matches("^\\d$")) {
				return TextHandler.get("command_poll_time_no_number");
			}
			int minutes = Integer.parseInt(split[1]);
			if (minutes <= 0 || minutes > MAX_DURATION_IN_MINUTES) {
				return TextHandler.get("command_poll_time_out_of_range");
			}

		}
		return TextHandler.get("command_not_implemented");
	}
}