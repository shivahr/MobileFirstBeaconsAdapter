package com.mobilefirst;

public class BeaconTriggerAssociation {
	public String uuid;
	public int major;
	public int minor;
	public String triggerName;

	public void normalizeUuid() {
		uuid = uuid.toLowerCase();
	}
}
