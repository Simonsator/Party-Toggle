package de.simonsator.partyandfriends.partytoggle.velocity.chatmanager;

import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeChatManager extends UniversalChatManager implements Listener {
	@EventHandler
	public void onWrite(ChatEvent pEvent) {
		if (!(pEvent.getSender() instanceof ProxiedPlayer)) {
			return;
		}
		ProxiedPlayer player = (ProxiedPlayer) pEvent.getSender();
		String message = pEvent.getMessage();
		if (message.startsWith("/"))
			return;
		if (hasPartyChatNotEnabled(player.getUniqueId()))
			return;
		pEvent.setCancelled(executeChat(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId()), message));
	}

	@EventHandler
	public void onLeave(PlayerDisconnectEvent pEvent) {
		remove(pEvent.getPlayer().getUniqueId());
	}
}
