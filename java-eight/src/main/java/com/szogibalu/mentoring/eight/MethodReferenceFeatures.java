package com.szogibalu.mentoring.eight;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MethodReferenceFeatures {

    @FunctionalInterface
    interface PersonFactory<T extends Person> {
	T create(String firstName, int age);
    }

    public static void main(String[] args) {

	final String x = "xxx";

	// Class::staticMethod
	print(x, MethodReferenceFeatures::writeStatic);
	print(x, i -> writeStatic(i));

	// object::instanceMethod
	final MethodReferenceFeatures t1 = new MethodReferenceFeatures();
	print(x, t1::write);
	print(x, j -> t1.write(j));

	// Class::instanceMethod
	// Reference to an Instance Method of an Arbitrary Object of a
	// Particular Type
	String[] stringArray = { "Barbara", "James", "Mary", "John",
		"Patricia", "Robert", "Michael", "Linda" };
	Arrays.sort(stringArray, String::compareToIgnoreCase);
	Arrays.sort(stringArray, (String a, String b) -> a
		.compareToIgnoreCase(b));

	// Constructor references
	final PersonFactory<Person> personFactory = Person::new;
	personFactory.create("Balazs", 28);

	final List<String> stringList = Arrays.asList("test");
	final Stream<String> streamString = stringList.stream();
	stringArray = streamString.toArray(String[]::new);

    }

    void write(String x) {
	System.out.println(x);
    }

    static void writeStatic(String x) {
	System.out.println(x);
    }

    static void print(String value, Consumer<String> consumer) {
	consumer.accept(value);
    }

}
