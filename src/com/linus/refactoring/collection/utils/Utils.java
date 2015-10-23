package com.linus.refactoring.collection.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Utils {
	static public <T> Set<T> intersection(Collection<T> a, Collection<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		return result;
	}

	static public <T> boolean hasIntersection(Collection<T> a, Collection<T> b) {
		return !intersection(a, b).isEmpty();
	}
	
	static public void doStastic(Map<String, Long> countMap,
			Map<String, Long> cancellationsMap, Map<String, Long> totalDelayMap) {
		Map<String, Double[]> resultMap = new HashMap<>();
		for (Map.Entry<String, Long> countEntry : countMap.entrySet()) {
			String airport = countEntry.getKey();
			Double meanDelay = totalDelayMap.get(airport).doubleValue()
					/ (countEntry.getValue() - cancellationsMap.get(airport));
			Double cancellationRate = cancellationsMap.get(airport)
					.doubleValue() / countEntry.getValue();
			meanDelay = round2Digital(meanDelay);
			cancellationRate = round2Digital(cancellationRate);
			resultMap
					.put(airport, new Double[] { meanDelay, cancellationRate });
		}
		
		printOutResult(resultMap);
	}
	
	static public void doStastic(Map<String, Long[]> countingMap) {
		Map<String, Double[]> resultMap = new HashMap<>();
		for (Map.Entry<String, Long[]> countEntry : countingMap.entrySet()) {
			String airport = countEntry.getKey();
			Double meanDelay = countEntry.getValue()[2].doubleValue()
					/ (countEntry.getValue()[0] - countEntry.getValue()[1]);
			Double cancellationRate =  countEntry.getValue()[1]
					.doubleValue() / countEntry.getValue()[0];
			meanDelay = round2Digital(meanDelay);
			cancellationRate = round2Digital(cancellationRate);
			resultMap
					.put(airport, new Double[] { meanDelay, cancellationRate });
		}
		
		printOutResult(resultMap);
	}

	private static void printOutResult(Map<String, Double[]> resultMap) {
		System.out.println("Airport\t\tMean Delay\tCancel %");
		System.out.println("------------------------------------------");
		for (Entry<String, Double[]> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + "\t\t"
					+ String.valueOf(entry.getValue()[0]) + "\t\t"
					+ String.valueOf(entry.getValue()[1]));
		}
	}

	private static double round2Digital(Double value) {
		return BigDecimal.valueOf(value).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}