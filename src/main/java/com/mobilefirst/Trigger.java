package com.mobilefirst;

import java.util.Map;

public class Trigger {
	public enum WLTriggerType {
		Enter, Exit, DwellInside, DwellOutside
	}

	public enum WLProximity {
		Immediate, Near, Far, Unseen
	}

	public String triggerName;
	public WLTriggerType triggerType;
	public WLProximity proximityState;
	public int dwellingTime;
	public Map<String, String> actionPayload;

}
