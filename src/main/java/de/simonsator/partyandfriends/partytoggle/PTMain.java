package de.simonsator.partyandfriends.partytoggle;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.party.command.PartyCommand;

import java.io.File;
import java.io.IOException;

public class PTMain extends PAFExtension {

	@Override
	public void onEnable() {
		try {
			PTConfig config = (new PTConfig(new File(getConfigFolder(), "config.yml"), this));
			ChatManager chatManager = new ChatManager();
			getAdapter().registerListener(chatManager, this);
			getAdapter().registerListener(new RemoveManager(chatManager), this);
			PartyCommand.getInstance().addCommand(
					new PartyToggle(config.getStringList("Names"),
							config.getInt("Priority"), config.getString("Messages.Help"), chatManager, config));
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
