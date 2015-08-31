package com.linus.refactoring.collection;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Example3 {

	public static void main(String[] args) {

	}

	class Service {
		public void markPreferred(Equipment equipment) {
			Set<String> checkedRegions = new HashSet<>();
			for (Offering off : equipment.getOfferings()) {
				String region = off.getRegion();
				if (checkedRegions.contains(region)) {
					continue;
				}

				Offering possPref = possiblePreference(equipment, region);
				possPref.setPreferred(true);
				checkedRegions.add(region);
			}
		}

		public void markPreferred2(Equipment equipment) {
			equipment.getOfferings().stream()
			.map(off -> off.getRegion())
			.distinct()
			.map(r -> possiblePreference(equipment, r))
			.forEach(off -> off.setPreferred(true));

		}

		private Offering possiblePreference(Equipment equipment, String region) {
			Optional<Offering> result = equipment.getOfferings(region).stream().filter(o -> o.isPreferred())
					.findFirst();
			if (result.isPresent())
				return result.get();

			return equipment.getOfferings(region).iterator().next();
		}
	}

	class Offering {
		private String region;
		private String supported;
		private String supplied;
		private boolean isPreferred;

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getSupported() {
			return supported;
		}

		public void setSupported(String supported) {
			this.supported = supported;
		}

		public String getSupplied() {
			return supplied;
		}

		public void setSupplied(String supplied) {
			this.supplied = supplied;
		}

		public boolean isPreferred() {
			return isPreferred;
		}

		public void setPreferred(boolean isPreferred) {
			this.isPreferred = isPreferred;
		}
	}

	class Equipment {
		private String name;
		private Set<Offering> offerings;

		public String getName() {
			return name;
		}

		public Set<Offering> getOfferings(String region) {
			Set<Offering> result = new HashSet<>();
			if (region != null) {
				for (Offering offering : result) {
					if (region.equals(offering.getRegion())) {
						result.add(offering);
					}
				}
			}
			return result;

		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<Offering> getOfferings() {
			return offerings;
		}

		public void setOfferings(Set<Offering> offerings) {
			this.offerings = offerings;
		}

	}
}
