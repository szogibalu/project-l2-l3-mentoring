package com.szogibalu.mentoring.eight;

import java.util.Comparator;

public class LambdaFeatures {

    public static void main(String[] args) {

	final Person P1 = new Person("Jaime", 42);
	final Person P2 = new Person("Sansa", 16);

	final Comparator<Person> comparator = (Person p1, Person p2) -> {
	    System.out.println("Comparing persons");
	    return p1.name.compareTo(p2.name);
	};

	final Comparator<Person> comparator1 = (p1, p2) -> p1.name
		.compareTo(p2.name);

	final Comparator<Person> comparator2 = (p1, p2) -> p1.name
		.compareTo(p2.name);

	test((p1, p2) -> p1.name.compareTo(p2.name), P1, P2);
    }

    public static <T> int test(Comparator<T> comparator, T p1, T p2) {
	return comparator.compare(p1, p2);
    }
}
