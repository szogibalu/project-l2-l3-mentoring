package com.szogibalu.mentoring.eight;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFeatures {

    public static void main(String[] args) {
	final List<String> letters = Arrays
		.asList("a", "b", "c", "d", "e", "f");

	// eliminating mutability
	final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

	int totalOfValuesDoubled = 0;
	for (final int number : numbers) {
	    totalOfValuesDoubled += number * 2;
	}

	System.out.println(totalOfValuesDoubled);

	System.out.println(numbers.stream().mapToInt(number -> number * 2)
		.sum());

	// laziness
	// double the first even number greater than 3 from the list
	System.out.println(numbers.stream()
		.filter(StreamFeatures::isGreaterThan2)
		.filter(StreamFeatures::isEven)
		.mapToInt(StreamFeatures::doubleIt).findFirst().orElse(0));

	// order matters
	Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
	    System.out.println("filter: " + s);
	    return s.startsWith("a");
	}).map(s -> {
	    System.out.println("map: " + s);
	    return s.toUpperCase();
	})forEach(s -> System.out.println("forEach: " + s));

	// collect
	final List<String> filtered = letters.stream()
		.filter(s -> s.compareTo("b") > 0).collect(Collectors.toList());
	System.out.println(filtered);

	// parallelism
	Arrays.asList("a1", "a2", "b1", "c2", "c1")
		.parallelStream()
		.filter(s -> {
		    System.out.format("filter: %s [%s]\n", s, Thread
			    .currentThread().getName());
		    return true;
		})
		.map(s -> {
		    System.out.format("map: %s [%s]\n", s, Thread
			    .currentThread().getName());
		    return s.toUpperCase();
		})
		.forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread
			.currentThread().getName()));

    }

    public static boolean isGreaterThan2(int number) {
	System.out.println("isGreater " + number);
	return number > 2;
    }

    public static boolean isEven(int number) {
	System.out.println("isEven " + number);
	return number % 2 == 0;
    }

    public static int doubleIt(int number) {
	System.out.println("doubleIt " + number);
	return number * 2;
    }
}
