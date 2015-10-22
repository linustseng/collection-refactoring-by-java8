package com.linus.refactoring.collection.vo;

public class FlightData {
	private String dest;
	private boolean cancelled;
	private Long delay;

	public FlightData(String dest, boolean cancelled, Long delay) {
		super();
		this.dest = dest;
		this.cancelled = cancelled;
		this.delay = delay;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Long getDelay() {
		return delay;
	}

	public void setDelay(Long delay) {
		this.delay = delay;
	}
}
