package com.linus.refactoring.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.linus.refactoring.collection.vo.Author;

public class Example1 {

	public static void main(String[] args) {
		Example1 ex1 = new Example1();
		Author author1 = new Author("Linus", "T1", "CompanyA");
		Author author2 = new Author("Mark", "T2", "CompanyB");
		Author author3 = new Author("Sandra", null, "CompanyA");
		Author author4 = new Author("May", "T4", "CompanyC");
		Author author5 = new Author("Spark", "T5", "CompanyA");
		List<Author> authors = Arrays.asList(author1, author2, author3, author4, author5);
		
		System.out.println(ex1.findTwitterHandlesByCompany(authors, "CompanyA"));
	}

	private List<String> findTwitterHandlesByCompany(List<Author> authors, String company) {
		List<String> result = new ArrayList<>();
		for (Author author : authors) {
			if (company.equals(author.getCompany())) {
				String twitterHandle = author.getTwitterHandle();
				if (twitterHandle != null) {
					result.add(twitterHandle);
				}
			}
		}
		return result;
	}
}
