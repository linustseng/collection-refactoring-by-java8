package com.linus.refactoring.collection.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.linus.refactoring.collection.service.IReadDataService;

public class ReadDataService implements IReadDataService {

	@Override
	public Map<String, Collection<String>> getBooksReadOn(Date date) {
		Map<String, Collection<String>> result = new HashMap<>();
		result.put("Linus",
				Arrays.asList("War & Piece", "Naruto", "One Piece"));
		result.put("Sandra",
				Arrays.asList("Interator Design", "Naruto"));
		result.put("Martin",
				Arrays.asList("One Piece", "Naruto"));
		return result;
	}

}