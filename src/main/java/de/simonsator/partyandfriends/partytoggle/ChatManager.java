package de.simonsator.partyandfriends.partytoggle;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.api.party.PartyManager;
import de.simonsator.partyandfriends.api.party.PlayerParty;
import de.simonsator.partyandfriends.main.Main;
import de.simonsator.partyandfriends.party.subcommand.Chat;
import de.simonsator.partyandfriends.utilities.SubCommand;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashSet;
import java.util.UUID;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class ChatManager implements Listener {
	private HashSet<UUID> players = new HashSet<>();
	private final SubCommand chatCommand = Main.getInstance().getPartyCommand().getSubCommand(Chat.class);

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
		chatCommand.onCommand(p, message.split(" "));
	}

	public boolean changeState(UUID pUUID) {
		if (players.remove(pUUID))
			return false;
		players.add(pUUID);
		return true;
	}

}
