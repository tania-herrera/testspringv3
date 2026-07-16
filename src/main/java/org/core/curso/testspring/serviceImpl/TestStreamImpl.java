package org.core.curso.testspring.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.core.curso.testspring.service.ITestStream;

public class TestStreamImpl implements ITestStream {

	@Override
	public void TestStream1() {
		List<String> myList = new ArrayList<String>();
		myList.add("one");
		myList.add("two");
		myList.add("three");
		myList.add("four");
		myList.add("five");
		myList.add("one");
		myList.add("two");
		//
		Predicate<String> p1 = Predicate.isEqual("one");
		Predicate<String> p2 = Predicate.isEqual("two");
		Predicate<String> p3 = Predicate.isEqual("seven");
		//
		List<String> oneOrTwoList = new ArrayList<String>();
		//
		myList.stream()
			.peek(s -> System.out.println("\n\tPrimer peek: " + s))
			//.filter(p1.or(p2))
			.filter(p3)
			.peek(s -> System.out.println("\tSegundo peek: " + s))
			.peek(oneOrTwoList::add)
			.forEach(s -> System.out.println("\tTercer peek: se agregó: " + s));
		System.out.println("La lista oneOrTwoList tiene " 
			+ oneOrTwoList.size()
			+ " elementos --> \t" + oneOrTwoList);
	}
}
