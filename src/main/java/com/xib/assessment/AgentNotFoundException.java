package com.xib.assessment;

public class AgentNotFoundException extends RuntimeException {
	AgentNotFoundException(Long id) {
	    super("Could not find agent " + id);
	}
}
