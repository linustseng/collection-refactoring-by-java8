package com.linus.refactoring.collection;

import java.util.HashMap;
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
		List<FlightData> flightDataSet = new FlightDataService()
				.getFlightData();

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

	}

	public void airportData2() {
		List<FlightData> flightDataSet = new FlightDataService()
		.getFlightData();
		HashMap<String, Long[]> resultMap = flightDataSet.stream().reduce(
				new HashMap<String, Long[]>(), (e, f) -> {
					e.put(f.getDest(), addValue(e.get(f.getDest()), f));
					return e;
				}, (a, b) -> {
					a.putAll(b);
					return a;
				});

		Utils.doStastic(resultMap);
	}

	private Long[] addValue(Long[] longs, FlightData f) {
		if (longs == null) {
			longs = new Long[] { 0L, 0L, 0L };
		}
		longs[0] = longs[0] + 1L;
		longs[1] = longs[1] + (f.isCancelled() ? 1L : 0L);
		longs[2] = longs[2] + (f.isCancelled() ? 0L : f.getDelay());

		return longs;
	}
}
