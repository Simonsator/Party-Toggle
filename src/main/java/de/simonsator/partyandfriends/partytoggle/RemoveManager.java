package de.simonsator.partyandfriends.partytoggle;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Simonsator
 * @version 1.0.0 03.10.16
 */
public class RemoveManager implements Listener {
	private final ChatManager CHAT_MANAGER;

	public RemoveManager(ChatManager pChatManager) {
		CHAT_MANAGER = pChatManager;
	}

	@EventHandler
	public void onLeave(PlayerDisconnectEvent pEvent) {
		CHAT_MANAGER.remove(pEvent.getPlayer().getUniqueId());
	}
}
