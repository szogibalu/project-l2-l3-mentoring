package com.szogibalu.mentoring.eight;

import java.util.Optional;

public class OptionalFeature {

    public static void main(String[] args) {
	final Optional<String> optional1 = Optional.of("Really?");
	final Optional<String> optional2 = Optional.ofNullable(null);

	if (optional1.isPresent()) {
	    System.out.println("Yes...");
	}

	if (optional2.isPresent()) {
	    System.out.println("F@ck you...");
	}
    }

}
