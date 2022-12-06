package de.simonsator.partyandfriends.velocity.partytoggle.chatmanager;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayerManager;

public class VelocityChatManager extends UniversalChatManager {
	@Subscribe
	public void onWrite(PlayerChatEvent pEvent) {
		Player player = pEvent.getPlayer();
		String message = pEvent.getMessage();
		if (message.startsWith("/"))
			return;
		if (hasPartyChatNotEnabled(player.getUniqueId()))
			return;
		if (executeChat(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId()), message)) {
			pEvent.setResult(PlayerChatEvent.ChatResult.denied());
		}
	}

	@Subscribe
	public void onLeave(DisconnectEvent pEvent) {
		remove(pEvent.getPlayer().getUniqueId());
	}
}
