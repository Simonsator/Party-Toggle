package de.simonsator.partyandfriends.partytoggle.velocity;

import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.main.Main;
import de.simonsator.partyandfriends.velocity.party.command.PartyCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PTMain extends PAFExtension {

	public PTMain(Path folder) {
		super(folder);
	}

	@Override
	public void onEnable() {
		try {
			PTConfig config = (new PTConfig(new File(getConfigFolder(), "config.yml"), this));
			ChatManager chatManager = new ChatManager();
			getAdapter().registerListener(chatManager, Main.getInstance());
			getAdapter().registerListener(new RemoveManager(chatManager), Main.getInstance());
			PartyCommand.getInstance().addCommand(
					new PartyToggle(config.getStringList("Names"),
							config.getInt("Priority"), config.getString("Messages.Help"), chatManager, config));
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Party-Toggle";
	}

}
