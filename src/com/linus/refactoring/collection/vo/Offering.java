package com.linus.refactoring.collection.vo;

public class Offering {
	private String region;
	private Equipment supported;
	private Equipment supplied;
	private boolean isPreferred;

	public Offering(String region,Equipment supported,Equipment supplied){
		this.region = region;
		this.supplied = supplied;
		this.supported = supported;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Equipment getSupported() {
		return supported;
	}

	public Equipment getSupplied() {
		return supplied;
	}

	public boolean isPreferred() {
		return isPreferred;
	}

	public void setPreferred(boolean isPreferred) {
		this.isPreferred = isPreferred;
	}

	public boolean isMatch(Equipment eqp) {
		return eqp.getName().equals(supplied.getName());
	}
}
