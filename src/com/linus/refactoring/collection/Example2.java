package com.linus.refactoring.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Example2 {

	public static void main(String[] args) {
		System.out.println(new Example2().new ReaderFinder()
				.getBooksReadOn(Arrays.asList("Sandra", "Linus", "Spark", "May"), Arrays.asList("Naruto"), new Date()));
		System.out.println(new Example2().new ReaderFinder().getBooksReadOn2(
				Arrays.asList("Sandra", "Linus", "Spark", "May"), Arrays.asList("Naruto"), new Date()));
	}

	class ReaderFinder {

		DataService dataService = null;

		public ReaderFinder() {
			dataService = new DataServiceImpl();
		}

		public Set<String> getBooksReadOn(Collection<String> readers, Collection<String> books, Date date) {
			Set<String> result = new HashSet<>();
			Map<String, Collection<String>> data = dataService.getBooksReadOn(date);
			for (Map.Entry<String, Collection<String>> entry : data.entrySet()) {
				for (String bookId : books) {
					if (entry.getValue().contains(bookId) && readers.contains(entry.getKey())) {
						result.add(entry.getKey());
					}
				}
			}
			return result;
		}

		public Set<String> getBooksReadOn2(Collection<String> readers, Collection<String> books, Date date) {
			Map<String, Collection<String>> data = dataService.getBooksReadOn(date);
			Set<Entry<String, Collection<String>>> entries = data.entrySet();
			return entries.stream().filter(e -> readers.contains(e.getKey()))
					.filter(e -> new Utils().hasIntersection(e.getValue(), books)).map(e -> e.getKey())
					.collect(Collectors.toSet());
		}
	}

	class Utils {
		public <T> Set<T> intersection(Collection<T> a, Collection<T> b) {
			Set<T> result = new HashSet<T>(a);
			result.retainAll(b);
			return result;
		}

		public <T> boolean hasIntersection(Collection<T> a, Collection<T> b) {
			return !intersection(a, b).isEmpty();
		}
	}

	class DataServiceImpl implements DataService {

		@Override
		public Map<String, Collection<String>> getBooksReadOn(Date date) {
			Map<String, Collection<String>> result = new HashMap<>();
			result.put("Linus", Arrays.asList("War & Piece", "Naruto", "One Piece"));
			return result;
		}

	}

	interface DataService {
		public Map<String, Collection<String>> getBooksReadOn(Date date);
	}
}
