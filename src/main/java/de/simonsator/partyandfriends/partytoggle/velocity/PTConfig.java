package de.simonsator.partyandfriends.partytoggle.velocity;


import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class PTConfig extends ConfigurationCreator {

	protected PTConfig(File file, PAFExtension pPlugin) throws IOException {
		super(file, pPlugin);
		readFile();
		loadDefaultValues();
		saveFile();
		process();
	}

	private void loadDefaultValues() {
		set("Names", "toggle", "toggle-chat");
		set("Priority", 1000);
		set("Permission", "");
		set("Messages.Activated",
				"&7From now on all you write will be automatically written into the party chat, as long as you are in a party");
		set("Messages.Disabled",
				"&7From now on you can write again normal into the chat");
		set("Messages.Help",
				"&8/&5Party toggle  &8- &7Toggles if you write directly into the party chat");
	}
}
