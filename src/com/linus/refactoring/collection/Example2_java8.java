package com.linus.refactoring.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.linus.refactoring.collection.service.IReadDataService;
import com.linus.refactoring.collection.service.ReadDataService;
import com.linus.refactoring.collection.utils.Utils;

public class Example2_java8 {

	public static void main(String[] args) {
		System.out.println(new Example2_java8().new ReaderFinder().getBooksReadOn(
				Arrays.asList("Sandra", "Linus", "Spark", "May"),
				Arrays.asList("Naruto"), new Date()));
	}

	class ReaderFinder {

		IReadDataService dataService = new ReadDataService();

		public Set<String> getBooksReadOn(Collection<String> readers,
				Collection<String> books, Date date) {
			Map<String, Collection<String>> data = dataService
					.getBooksReadOn(date);
			
			Set<Entry<String, Collection<String>>> entries = data.entrySet();
			
			return entries
					.stream()
					.filter(e -> readers.contains(e.getKey()))
					.filter(e -> Utils.hasIntersection(e.getValue(),
							books))
					.map(e -> e.getKey())
					.collect(Collectors.toSet());
		}
	}
}
