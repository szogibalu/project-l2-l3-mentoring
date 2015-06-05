package com.szogibalu.mentoring.eight;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import com.google.common.base.Joiner;

public class StringJoinFeature {

    final static List<String> CARS = Arrays.asList("Audi", "BMW", "Mercedes");

    public static void main(String[] args) {

	final String guavaJoined = Joiner.on(",").join(CARS).toString();
	System.out.println(guavaJoined);

	final StringJoiner joiner = new StringJoiner(",");
	for (final String car : CARS) {
	    joiner.add(car);
	}
	System.out.println(joiner.toString());

	final String joined = String.join(",", CARS.toArray(new String[CARS
		.size()]));
	System.out.println(joined);

    }

}
