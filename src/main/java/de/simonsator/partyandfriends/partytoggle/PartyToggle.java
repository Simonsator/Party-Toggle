package de.simonsator.partyandfriends.partytoggle;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.party.abstractcommands.PartySubCommand;
import net.md_5.bungee.config.Configuration;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class PartyToggle extends PartySubCommand {
	private final ChatManager CHAT_MANAGER;
	private final Configuration CONFIG;

	protected PartyToggle(String[] pCommands, int pPriority, String pHelp, ChatManager pChatManager, Configuration pConfig) {
		super(pCommands, pPriority, pHelp);
		CHAT_MANAGER = pChatManager;
		CONFIG = pConfig;
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (CHAT_MANAGER.changeState(pPlayer.getUniqueId()))
			pPlayer.sendMessage(PREFIX + CONFIG.getString("Messages.Activated"));
		else
			pPlayer.sendMessage(PREFIX + CONFIG.getString("Messages.Disabled"));

	}

	@Override
	public boolean hasAccess(int pPermissionHeight) {
		return false;
	}
}
