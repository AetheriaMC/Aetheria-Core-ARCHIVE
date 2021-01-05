package net.badbird5907.aetheriacore.spigot.error;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.warn;

public class Error extends RuntimeException {
	public Error() {
		warn("An Unspecified error has occurred!");
	}

	public Error(String s) {
		warn("ERROR: " + s);
	}
}