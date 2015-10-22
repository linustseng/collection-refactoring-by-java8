package com.linus.refactoring.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.linus.refactoring.collection.service.IReadDataService;
import com.linus.refactoring.collection.service.ReadDataService;

public class Example2 {

	public static void main(String[] args) {
		System.out.println(new Example2().new ReaderFinder().getBooksReadOn(
				Arrays.asList("Sandra", "Linus", "Spark", "May"),
				Arrays.asList("Naruto"), new Date()));
	}

	class ReaderFinder {

		/*
		 * dataService.getBooksReadOn(readDate) return format
		 * Map<Reader:String, Collection<Book:String>>
		 */
		IReadDataService dataService = new ReadDataService();
		
		public Set<String> getBooksReadOn(Collection<String> readers, Collection<String> books, Date date) {
			
			Set<String> result = new HashSet<>();
			
			Map<String, Collection<String>> data = dataService.getBooksReadOn(date);
			
			for (Map.Entry<String, Collection<String>> entry : data.entrySet()) {
				for (String bookId : books) {
					if (entry.getValue().contains(bookId)
							&& readers.contains(entry.getKey())) {
						result.add(entry.getKey());
					}
				}
			}
			return result;
		}
	}
}
