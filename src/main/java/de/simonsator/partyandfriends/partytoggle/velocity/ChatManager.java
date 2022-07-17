package de.simonsator.partyandfriends.partytoggle.velocity;


import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import de.simonsator.partyandfriends.velocity.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.velocity.api.party.PartyManager;
import de.simonsator.partyandfriends.velocity.api.party.PlayerParty;
import de.simonsator.partyandfriends.velocity.party.command.PartyChat;

import java.util.HashSet;
import java.util.UUID;

public class ChatManager {
	private final HashSet<UUID> players = new HashSet<>();

	@Subscribe
	public void onWrite(PlayerChatEvent pEvent) {
		final OnlinePAFPlayer p = PAFPlayerManager.getInstance().getPlayer((Player) pEvent.getPlayer());
		String message = pEvent.getMessage();
		if (message.startsWith("/"))
			return;
		if (!players.contains(p.getUniqueId()))
			return;
		final PlayerParty party = PartyManager.getInstance().getParty(p);
		if (party == null)
			return;
		pEvent.setResult(PlayerChatEvent.ChatResult.denied());
		PartyChat.getInstance().send(p, message.split(" "));
	}

	public boolean changeState(UUID pUUID) {
		if (players.remove(pUUID))
			return false;
		players.add(pUUID);
		return true;
	}

	public void remove(UUID pUUID) {
		players.remove(pUUID);
	}
}
