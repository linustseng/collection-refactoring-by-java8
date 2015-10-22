package com.linus.refactoring.collection;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.linus.refactoring.collection.vo.Equipment;
import com.linus.refactoring.collection.vo.Offering;

public class Example3_java8 {

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

}
