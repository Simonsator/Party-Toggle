package de.simonsator.partyandfriends.partytoggle.velocity;

import de.simonsator.partyandfriends.velocity.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.velocity.api.party.abstractcommands.PartySubCommand;
import de.simonsator.partyandfriends.partytoggle.chatmanager.UniversalChatManager;

import java.util.List;

public class PartyToggle extends PartySubCommand {
	private final UniversalChatManager CHAT_MANAGER;
	private final PTConfig CONFIG;

	protected PartyToggle(List<String> pCommands, int pPriority, String pHelp, UniversalChatManager pChatManager, PTConfig pConfig) {
		super(pCommands, pPriority, pHelp, pConfig.getString("Permission"));
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
		return true;
	}
}
