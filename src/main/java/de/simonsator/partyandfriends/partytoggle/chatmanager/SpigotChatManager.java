package de.simonsator.partyandfriends.partytoggle.chatmanager;

import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpigotChatManager extends UniversalChatManager implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onWrite(PlayerChatEvent pEvent) {
		Player player = pEvent.getPlayer();
		String message = pEvent.getMessage();
		if (message.startsWith("/"))
			return;
		if (hasPartyChatNotEnabled(player.getUniqueId()))
			return;
		pEvent.setCancelled(executeChat(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId()), message));
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent pEvent) {
		remove(pEvent.getPlayer().getUniqueId());
	}
}

