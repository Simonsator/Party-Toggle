package de.simonsator.partyandfriends.partytoggle;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.api.party.PartyManager;
import de.simonsator.partyandfriends.api.party.PlayerParty;
import de.simonsator.partyandfriends.party.command.PartyChat;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashSet;
import java.util.UUID;

public class ChatManager implements Listener {
	private final HashSet<UUID> players = new HashSet<>();

	@EventHandler
	public void onWrite(ChatEvent pEvent) {
		final OnlinePAFPlayer p = PAFPlayerManager.getInstance().getPlayer((ProxiedPlayer) pEvent.getSender());
		String message = pEvent.getMessage();
		if (message.startsWith("/"))
			return;
		if (!players.contains(p.getUniqueId()))
			return;
		final PlayerParty party = PartyManager.getInstance().getParty(p);
		if (party == null)
			return;
		pEvent.setCancelled(true);
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
