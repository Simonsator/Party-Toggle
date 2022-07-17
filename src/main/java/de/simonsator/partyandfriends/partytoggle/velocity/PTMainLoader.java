package de.simonsator.partyandfriends.partytoggle.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import de.simonsator.partyandfriends.velocity.VelocityExtensionLoadingInfo;
import de.simonsator.partyandfriends.velocity.main.PAFPlugin;

import java.nio.file.Path;

@Plugin(id = "party-toggle-loader", name = "Party Toggle Loader", version = "1.0.5",
		description = "Loads Party Toggle for Party and Friends", authors = {"Simonsator"}, dependencies = {@Dependency(id = "partyandfriends")})
public class PTMainLoader {
	private final Path folder;

	@Inject
	public PTMainLoader(@DataDirectory final Path folder) {
		this.folder = folder;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		PAFPlugin.loadExtension(new VelocityExtensionLoadingInfo(new PTMain(folder),
				"party-toggle-for-party-and-friends", "Loads Party Toggle for Party and Friends", "1.0.5", "Simonsator"));
	}

}
