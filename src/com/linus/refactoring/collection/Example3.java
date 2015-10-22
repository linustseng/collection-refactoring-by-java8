package com.linus.refactoring.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.linus.refactoring.collection.vo.Equipment;
import com.linus.refactoring.collection.vo.Offering;

public class Example3 {

	public static void main(String[] args) {
		Equipment iphone6sRose = new Equipment("Iphone 6s - Rose");
		Equipment iphone6sSilver = new Equipment("Iphone 6s - Silver");
		
		
		Offering offeringTCRose = new Offering("TaiChung", iphone6sRose,
				iphone6sRose);
		Offering offeringTNRose = new Offering("TaiNan", iphone6sRose,
				iphone6sSilver);
		Offering offeringTCSilver = new Offering("TaiChung", iphone6sRose,
				iphone6sSilver);

		iphone6sRose.setOfferings(Arrays.asList(offeringTCRose, offeringTNRose,
				offeringTCSilver));
		iphone6sSilver.setOfferings(Arrays.asList(offeringTCSilver));

		Example3 service = new Example3();
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
		Set<String> checkedRegions = new HashSet<>();
		for (Offering off : equipment.getOfferings()) {
			String region = off.getRegion();
			if (checkedRegions.contains(region)) {
				continue;
			}

			Offering possPref = null;

			for (Offering off2 : equipment.getOfferings(region)) {
				if (off2.isPreferred()) {
					possPref = off2;
					break;
				} else {
					if (possPref == null) {
						possPref = off2;
					}
				}
			}

			possPref.setPreferred(true);

			checkedRegions.add(region);
		}
	}
}
