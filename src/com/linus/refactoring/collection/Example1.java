package com.linus.refactoring.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example1 {

	public static void main(String[] args) {
		Example1 ex1 = new Example1();
		Author author1 = ex1.new Author("Linus", "T1", "CompanyA");
		Author author2 = ex1.new Author("Mark", "T2", "CompanyB");
		Author author3 = ex1.new Author("Sandra", null, "CompanyA");
		Author author4 = ex1.new Author("May", "T4", "CompanyC");
		Author author5 = ex1.new Author("Spark", "T5", "CompanyA");
		List<Author> authors = Arrays.asList(author1, author2, author3, author4, author5);
		System.out.println(ex1.findTwitterHandlesByCompany(authors, "CompanyA"));
		System.out.println(ex1.findTwitterHandlesByCompany2(authors, "CompanyA"));
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

	private List<String> findTwitterHandlesByCompany2(List<Author> authors, String company) {
		return authors.stream()
				.filter(a -> company.equals(a.getCompany()))
				.map(a -> a.getTwitterHandle())
				.filter(t->t!=null)
				.collect(Collectors.toList());
//		return authors.stream()
//				.filter(a -> company.equals(a.getCompany())&&a.getTwitterHandle()!=null)
//				.map(a -> a.getTwitterHandle())
//				.collect(Collectors.toList());
	}
	
	class Author {
		private String name;
		private String twitterHandle;
		private String company;

		
		public Author(String name, String twitterHandle, String company) {
			super();
			this.name = name;
			this.twitterHandle = twitterHandle;
			this.company = company;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTwitterHandle() {
			return twitterHandle;
		}

		public void setTwitterHandle(String twitterHandle) {
			this.twitterHandle = twitterHandle;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}
	}
}
