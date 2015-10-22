package com.linus.refactoring.collection.vo;

import java.util.ArrayList;
import java.util.List;

public class Equipment {
	private String name;
	private List<Offering> offerings;

	public Equipment(String name) {
		this.name = name;
	}

	public List<Offering> getOfferings(String region) {
		List<Offering> result = new ArrayList<Offering>();
		if (region != null) {
			for (Offering offering : offerings) {
				if (region.equals(offering.getRegion())) {
					result.add(offering);
				}
			}
		}
		return result;

	}
	
	public String getName() {
		return name;
	}
	
	public List<Offering> getOfferings() {
		return offerings;
	}

	public void setOfferings(List<Offering> offerings) {
		this.offerings = offerings;
	}

}
