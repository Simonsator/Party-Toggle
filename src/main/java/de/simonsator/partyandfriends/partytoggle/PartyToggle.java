package de.simonsator.partyandfriends.partytoggle;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.party.abstractcommands.PartySubCommand;

import java.util.List;

public class PartyToggle extends PartySubCommand {
	private final ChatManager CHAT_MANAGER;
	private final PTConfig CONFIG;

	protected PartyToggle(List<String> pCommands, int pPriority, String pHelp, ChatManager pChatManager, PTConfig pConfig) {
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
