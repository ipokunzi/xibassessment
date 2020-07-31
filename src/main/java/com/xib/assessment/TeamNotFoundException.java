package com.xib.assessment;

public class TeamNotFoundException extends RuntimeException {
	TeamNotFoundException(Long id) {
	    super("Could not find team " + id);
	}
}
