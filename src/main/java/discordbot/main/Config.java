package discordbot.main;


import com.wezinkhof.configuration.ConfigurationOption;

public class Config {

	//bot enabled? must be set to true in order to run
	@ConfigurationOption
	public static boolean BOT_ENABLED = false;

	@ConfigurationOption
	public static boolean BOT_AUTO_UPDATE = false;

	//display name of the bot
	@ConfigurationOption
	public static String BOT_NAME = "NovaBot";

	//Bot's own channel on its own server
	@ConfigurationOption
	public static String BOT_CHANNEL_ID = "225170823898464256";

	//token used to login to discord
	@ConfigurationOption
	public static String BOT_TOKEN = "mybottokenhere";

	//prefix for all commands !help etc.
	@ConfigurationOption
	public static boolean BOT_CHATTING_ENABLED = true;

	//default prefix to mark messages as commands
	@ConfigurationOption
	public static String BOT_COMMAND_PREFIX = "!";

	//save the usage of commands?
	@ConfigurationOption
	public static boolean BOT_COMMAND_LOGGING = true;

	//Reply to non existing commands?
	@ConfigurationOption
	public static boolean BOT_COMMAND_SHOW_UNKNOWN = false;

	//location of the soundcloud jar
	@ConfigurationOption
	public static String MUSIC_DOWNLOAD_SOUNDCLOUD_EXE = "H:/";

	//token used to connect to soundcloud
	@ConfigurationOption
	public static String MUSIC_DOWNLOAD_SOUNDCLOUD_API_TOKEN = "some-token";

	//location of youtubedl.exe
	@ConfigurationOption
	public static String YOUTUBEDL_EXE = "H:/youtube-dl.exe";

	//folder with the binary files required for youtubedl
	@ConfigurationOption
	public static String YOUTUBEDL_BIN = "H:/music/bin/";

	//folder with the binary files required for youtubedl
	@ConfigurationOption
	public static String SOX_LOCATION = "/bin/sox";

	//directory where all the music is stored
	@ConfigurationOption
	public static String MUSIC_DIRECTORY = "H:/music/";

	//mysql hostname
	@ConfigurationOption
	public static String DB_HOST = "localhost";

	//mysql user
	@ConfigurationOption
	public static String DB_USER = "root";

	//mysql password
	@ConfigurationOption
	public static String DB_PASS = "";

	//mysql database name
	@ConfigurationOption
	public static String DB_NAME = "discord";

	//enable economy globally
	@ConfigurationOption
	public static boolean MODULE_ECONOMY_ENABLED = true;

	//enable poe globally
	@ConfigurationOption
	public static boolean MODULE_POE_ENABLED = true;

	//enable hearthstone globally
	@ConfigurationOption
	public static boolean MODULE_HEARTHSTONE_ENABLED = true;

	//enable music globally
	@ConfigurationOption
	public static boolean MODULE_MUSIC_ENABLED = true;

	//name of the currency
	@ConfigurationOption
	public static String ECONOMY_CURRENCY_NAME = "";

	//emoticon of the currency
	@ConfigurationOption
	public static String ECONOMY_CURRENCY_ICON = "";

	//a new user starts with this balance
	@ConfigurationOption
	public static int ECONOMY_START_BALANCE = 50;
	//Use trello integration
	@ConfigurationOption
	public static boolean TRELLO_ACTIVE = false;

	@ConfigurationOption
	public static String GOOGLE_API_KEY = "google-api-key-here";

	//Use trello integration
	@ConfigurationOption
	public static String TRELLO_API_KEY = "api-key-here";

	@ConfigurationOption
	public static String TRELLO_BOARD_ID = "57beb462bac8baf93c4bba47";

	@ConfigurationOption
	public static String TRELLO_LIST_BUGS = "57beb482265f090f6a425e01";

	@ConfigurationOption
	public static String TRELLO_LIST_IN_PROGRESS = "57beb4850d0e12837dca475d";

	@ConfigurationOption
	public static String TRELLO_LIST_PLANNED = "57beb4b9146625cc9f255073";

	//the trello token
	@ConfigurationOption
	public static String TRELLO_TOKEN = "token-here";

	public static String EOL = System.getProperty("line.separator");
	public static long DELETE_MESSAGES_AFTER = 120000;
	public static boolean SHOW_KEYPHRASE = false;
	public static String CREATOR_ID = "97433066384928768";
}
