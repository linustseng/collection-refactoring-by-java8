package com.linus.refactoring.collection.service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface IReadDataService {
	public Map<String, Collection<String>> getBooksReadOn(Date date);
}
