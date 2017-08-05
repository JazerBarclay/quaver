package tech.tora.tools.system;

import java.util.UUID;

public class CustomUUID {
	
	public static String generateTimestampUUID(String seed) {
		return UUID.nameUUIDFromBytes((seed + (System.currentTimeMillis() / 1000L)).getBytes()).toString();
	}
	
}
