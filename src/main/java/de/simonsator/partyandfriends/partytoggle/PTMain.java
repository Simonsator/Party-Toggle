package de.simonsator.partyandfriends.partytoggle;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.adapter.ServerSoftware;
import de.simonsator.partyandfriends.party.command.PartyCommand;
import de.simonsator.partyandfriends.partytoggle.chatmanager.BungeeChatManagerFactory;
import de.simonsator.partyandfriends.partytoggle.chatmanager.SpigotChatManagerFactory;
import de.simonsator.partyandfriends.partytoggle.chatmanager.UniversalChatManager;

import java.io.File;
import java.io.IOException;

public class PTMain extends PAFExtension {

	@Override
	public void onEnable() {
		try {
			PTConfig config = (new PTConfig(new File(getConfigFolder(), "config.yml"), this));
			UniversalChatManager chatManager;
			ServerSoftware serverSoftware = getAdapter().getServerSoftware();
			switch (serverSoftware) {
				case BUNGEECORD:
					chatManager = BungeeChatManagerFactory.createChatManager();
					break;
				case SPIGOT:
					chatManager = SpigotChatManagerFactory.createChatManager();
					break;
				default:
					throw new RuntimeException("Unsupported server software " + serverSoftware);
			}
			getAdapter().registerListener(chatManager, this);
			PartyCommand.getInstance().addCommand(
					new PartyToggle(config.getStringList("Names"),
							config.getInt("Priority"), config.getString("Messages.Help"), chatManager, config));
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
