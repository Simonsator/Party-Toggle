package de.simonsator.partyandfriends.partytoggle.velocity.chatmanager;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.party.PartyManager;
import de.simonsator.partyandfriends.api.party.PlayerParty;
import de.simonsator.partyandfriends.party.command.PartyChat;

import java.util.HashSet;
import java.util.UUID;

public class UniversalChatManager {
	private final HashSet<UUID> players = new HashSet<>();

	protected boolean executeChat(PAFPlayer pPlayer, String pMessage) {
		if (pPlayer instanceof OnlinePAFPlayer) {
			OnlinePAFPlayer onlinePAFPlayer = (OnlinePAFPlayer) pPlayer;
			final PlayerParty party = PartyManager.getInstance().getParty(onlinePAFPlayer);
			if (party == null)
				return false;
			PartyChat.getInstance().send(onlinePAFPlayer, pMessage.split(" "));
			return true;
		}
		return false;
	}

	public boolean changeState(UUID pUUID) {
		if (players.remove(pUUID))
			return false;
		players.add(pUUID);
		return true;
	}

	protected boolean hasPartyChatNotEnabled(UUID pUUID) {
		return !players.contains(pUUID);
	}

	public void remove(UUID pUUID) {
		players.remove(pUUID);
	}
}
