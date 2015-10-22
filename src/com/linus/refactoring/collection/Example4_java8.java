package com.linus.refactoring.collection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.linus.refactoring.collection.service.FlightDataService;
import com.linus.refactoring.collection.utils.Utils;
import com.linus.refactoring.collection.vo.FlightData;

public class Example4_java8 {
	public static void main(String[] args) {
		Example4_java8 ex4 = new Example4_java8();
		ex4.airportData();
	}

	public void airportData() {
		List<FlightData> flightDataSet = new FlightDataService().getFlightData();

		Map<String, Long> countMap = flightDataSet.stream().collect(
				Collectors.groupingBy(FlightData::getDest,
						Collectors.counting()));

		Map<String, Long> cancellationsMap = flightDataSet
				.stream()
				.filter(e -> e.isCancelled())
				.collect(
						Collectors.groupingBy(FlightData::getDest,
								Collectors.counting()));

		Map<String, Long> totalDelayMap = flightDataSet
				.stream()
				.filter(e -> !e.isCancelled())
				.collect(
						Collectors.groupingBy(FlightData::getDest,
								Collectors.summingLong(FlightData::getDelay)));

		Utils.doStastic(countMap, cancellationsMap, totalDelayMap);
		// flightDataSet.stream()
		// .map(e -> Arrays.asList(1l, e.isCancelled() ? 1l : 0l,
		// e.isCancelled() ? 0L : e.getDelay()))
		// .reduce(Arrays.asList(0l, 0l, 0l),
		// (a, b) -> Arrays.asList(a.get(0) + b.get(0), a.get(1) + b.get(1),
		// a.get(2) + b.get(2))).stream().collect(collector);
	}
}
