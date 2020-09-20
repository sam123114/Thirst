package fr.fondationacteon.thirst;

public enum DisplayType {
	BOSSBAR,
	ACTIONBAR,
	SCOREBOARD;
	
	static DisplayType getByName(String s) {
		if(s.equalsIgnoreCase("ACTIONBAR")) {
			return DisplayType.ACTIONBAR;
		} 
		else if(s.equalsIgnoreCase("BOSSBAR")) {
			return DisplayType.BOSSBAR;
		}
		else if(s.equalsIgnoreCase("SCOREBOARD")) {
			return DisplayType.SCOREBOARD;
		} else {
			return null;
		}
	}
}
