package com.mobilefirst;

import java.util.Map;

public class Beacon {
	public String uuid;
	public int major;
	public int minor;
	public double latitude;
	public double longitude;
	public Map<String, String> customData = null;

	public void normalizeUuid() {
		uuid = uuid.toLowerCase();
	}
}
