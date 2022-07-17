package de.simonsator.partyandfriends.partytoggle.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;

public class RemoveManager {
	private final ChatManager CHAT_MANAGER;

	public RemoveManager(ChatManager pChatManager) {
		CHAT_MANAGER = pChatManager;
	}

	@Subscribe
	public void onLeave(DisconnectEvent pEvent) {
		CHAT_MANAGER.remove(pEvent.getPlayer().getUniqueId());
	}
}
