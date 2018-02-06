package com.tank.camelspringboot.multicastandwiretap;

import java.io.Serializable;
import java.time.Instant;

public class TimeDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Instant bgnInstant;
	private Instant endInstant;
	public Instant getBgnInstant() {
		return bgnInstant;
	}
	public void setBgnInstant(Instant bgnInstant) {
		this.bgnInstant = bgnInstant;
	}
	public Instant getEndInstant() {
		return endInstant;
	}
	public void setEndInstant(Instant endInstant) {
		this.endInstant = endInstant;
	}
	@Override
	public String toString() {
		return "TimeDTO [bgnInstant=" + bgnInstant + ", endInstant=" + endInstant + "]";
	}
	
}
