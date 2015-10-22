package com.linus.refactoring.collection.service;

import java.util.Arrays;
import java.util.List;

import com.linus.refactoring.collection.vo.FlightData;

public class FlightDataService implements IFlightDataService {

	@Override
	public List<FlightData> getFlightData() {
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
		return Arrays.asList(data1, data2, data3, data4, data5, data6, data7,
				data8, data9, data10);
	}

}