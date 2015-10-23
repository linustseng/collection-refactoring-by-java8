package com.linus.refactoring.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.linus.refactoring.collection.vo.Equipment;
import com.linus.refactoring.collection.vo.Offering;

public class Example3_java8 {

	public static void main(String[] args) {
		Equipment iphone6sRose = new Equipment("Iphone 6s - Rose");
		Equipment iphone6sSilver = new Equipment("Iphone 6s - Silver");

		Offering offeringTCRose = new Offering("TaiChung", iphone6sRose,
				iphone6sRose);
		Offering offeringTNRose = new Offering("TaiNan", iphone6sRose,
				iphone6sSilver);
		Offering offeringTCSilver = new Offering("TaiChung", iphone6sSilver,
				iphone6sSilver);
		Offering offeringTNSilver = new Offering("TaiNan", iphone6sSilver,
				iphone6sSilver);

		iphone6sRose.setOfferings(Arrays.asList(offeringTCRose));
		iphone6sSilver.setOfferings(Arrays.asList(offeringTNRose,
				offeringTCSilver, offeringTNSilver));

		Example3_java8 service = new Example3_java8();
		service.markPreferred(iphone6sRose);
		service.showPreferred(iphone6sRose);

		service.markPreferred(iphone6sSilver);
		service.showPreferred(iphone6sSilver);
	}

	public void showPreferred(Equipment equip) {
		System.out.println(equip.getName());
		System.out.println("------------------");
		for (Offering offering : equip.getOfferings()) {
			if (offering.isPreferred()) {
				System.out.println(offering.getRegion());
			}
		}
		System.out.println("\n\n");
	}
	
	public void markPreferred(Equipment equipment) {
		equipment.getOfferings().stream().map(off -> off.getRegion())
				.distinct().map(r -> possiblePreference(equipment, r))
				.forEach(off -> off.setPreferred(true));

	}

	private Offering possiblePreference(Equipment equipment, String region) {
		Optional<Offering> result = null;
		List<Offering> allOfferings = equipment.getOfferings(region);
		result = allOfferings.stream().filter(o -> o.isPreferred()).findFirst();
		if (result.isPresent())
			return result.get();

		result = allOfferings.stream().filter(o -> o.isMatch(equipment))
				.findFirst();

		if (result.isPresent())
			return result.get();
		return allOfferings.get(0);
	}

}
