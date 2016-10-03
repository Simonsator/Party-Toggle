package de.simonsator.partyandfriends.partytoggle;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class PTConfig extends ConfigurationCreator {

	protected PTConfig(File file) throws IOException {
		super(file);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Names", "toggle", "toggle-chat");
		set("Priority", 1000);
		set("Messages.Activated",
				"&7From now on all you write will be automatically written into the party chat, as long as you are in a party");
		set("Messages.Disabled",
				"&7From now on you can write again normal into the chat");
		set("Messages.Help",
				"&8/&5Party toggle  &8- &7Toggles if you write directly into the party chat");
	}

	@Override
	public void reloadConfiguration() throws IOException {
		configuration = (new PTConfig(FILE)).getCreatedConfiguration();
	}
}
