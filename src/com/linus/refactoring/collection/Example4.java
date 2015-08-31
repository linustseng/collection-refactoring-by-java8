package com.linus.refactoring.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Example4 {
	public static void main(String[] args) {
		Example4 ex4 = new Example4();
		ex4.airportData();
		ex4.airportData2();
	}

	public void airportData() {
		List<FlightData> flightDataSet = getFlightData();
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
				cancellationsMap.put(airport, cancellationsMap.get(airport) + 1);
			} else {
				totalDelayMap.put(airport, totalDelayMap.get(airport) + flightData.getDelay());
			}
		}
		doStastic(countMap, cancellationsMap, totalDelayMap);
	}

	private void doStastic(Map<String, Long> countMap, Map<String, Long> cancellationsMap,
			Map<String, Long> totalDelayMap) {
		Map<String, Double[]> resultMap = new HashMap<>();
		for (Map.Entry<String, Long> countEntry : countMap.entrySet()) {
			String airport = countEntry.getKey();
			Double meanDelay = totalDelayMap.get(airport).doubleValue()
					/ (countEntry.getValue() - cancellationsMap.get(airport));
			Double cancellationRate = cancellationsMap.get(airport).doubleValue() / countEntry.getValue();
			resultMap.put(airport, new Double[] { meanDelay, cancellationRate });
		}
		for (Entry<String, Double[]> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + String.valueOf(entry.getValue()[0]) + " "
					+ String.valueOf(entry.getValue()[1]));
		}
	}

	public void airportData2() {
		List<FlightData> flightDataSet = getFlightData();
		Map<String, Long> countMap = flightDataSet.stream()
				.collect(Collectors.groupingBy(FlightData::getDest, Collectors.counting()));
		Map<String, Long> cancellationsMap = flightDataSet.stream().filter(e -> e.isCancelled())
				.collect(Collectors.groupingBy(FlightData::getDest, Collectors.counting()));
		Map<String, Long> totalDelayMap = flightDataSet.stream().filter(e -> !e.isCancelled())
				.collect(Collectors.groupingBy(FlightData::getDest, Collectors.summingLong(FlightData::getDelay)));
		doStastic(countMap, cancellationsMap, totalDelayMap);
//		flightDataSet.stream()
//				.map(e -> Arrays.asList(1l, e.isCancelled() ? 1l : 0l, e.isCancelled() ? 0L : e.getDelay()))
//				.reduce(Arrays.asList(0l, 0l, 0l),
//						(a, b) -> Arrays.asList(a.get(0) + b.get(0), a.get(1) + b.get(1), a.get(2) + b.get(2))).stream().collect(collector);
	}

	private List<FlightData> getFlightData() {
		FlightData data1 = new FlightData("TWN", false, 100l);
		FlightData data2 = new FlightData("TWN", false, 0l);
		FlightData data3 = new FlightData("TWN", false, 30l);
		FlightData data4 = new FlightData("TWN", true, 100l);
		FlightData data5 = new FlightData("TWN", true, 0l);
		FlightData data6 = new FlightData("TWN", false, 100l);
		FlightData data7 = new FlightData("BKK", false, 10l);
		FlightData data8 = new FlightData("BKK", false, 20l);
		FlightData data9 = new FlightData("BKK", false, 30l);
		FlightData data10 = new FlightData("BKK", true, 0l);
		return Arrays.asList(data1, data2, data3, data4, data5, data6, data7, data8, data9, data10);
	}

	class FlightData {
		private String dest;
		private boolean cancelled;
		private Long delay;

		public FlightData(String dest, boolean cancelled, Long delay) {
			super();
			this.dest = dest;
			this.cancelled = cancelled;
			this.delay = delay;
		}

		public String getDest() {
			return dest;
		}

		public void setDest(String dest) {
			this.dest = dest;
		}

		public boolean isCancelled() {
			return cancelled;
		}

		public void setCancelled(boolean cancelled) {
			this.cancelled = cancelled;
		}

		public Long getDelay() {
			return delay;
		}

		public void setDelay(Long delay) {
			this.delay = delay;
		}
	}
}
