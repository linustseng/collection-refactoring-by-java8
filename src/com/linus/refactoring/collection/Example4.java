package com.linus.refactoring.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linus.refactoring.collection.service.FlightDataService;
import com.linus.refactoring.collection.utils.Utils;
import com.linus.refactoring.collection.vo.FlightData;

public class Example4 {
	public static void main(String[] args) {
		Example4 ex4 = new Example4();
		ex4.airportData();
	}

	public void airportData() {
		List<FlightData> flightDataSet = new FlightDataService().getFlightData();
		Map<String, Long> countMap = new HashMap<>();
		Map<String, Long> cancellationsMap = new HashMap<>();
		Map<String, Long> totalDelayMap = new HashMap<>();

		for (FlightData flightData : flightDataSet) {
			String airport = flightData.getDest();
			if (!countMap.containsKey(airport)) {
				countMap.put(airport, 0l);
				cancellationsMap.put(airport, 0l);
				totalDelayMap.put(airport, 0l);
			}
			
			countMap.put(airport, countMap.get(airport) + 1);
			
			if (flightData.isCancelled()) {
				cancellationsMap
						.put(airport, cancellationsMap.get(airport) + 1);
			} else {
				totalDelayMap.put(airport, totalDelayMap.get(airport)
						+ flightData.getDelay());
			}
		}
		Utils.doStastic(countMap, cancellationsMap, totalDelayMap);
	}
}
