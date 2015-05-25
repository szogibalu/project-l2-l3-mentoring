package com.szogibalu.mentoring.eight;


public class LambaScopeFeatures {

    @FunctionalInterface
    interface Converter<F, T> {
	T convert(F from);
    }

    static int outerStaticNum;
    int outerNum;

    public static void main(String[] args) {
	final Converter<String, Integer> stringConverter1 = (from) -> Integer
		.valueOf(from);
	final Integer converted = stringConverter1.convert("123");
	System.out.println(converted);

	final Converter<Integer, String> stringConverter2 = (from) -> {
	    outerStaticNum = 72;
	    return String.valueOf(from);
	};

	new LambaScopeFeatures().testOuteNum();

	final int innerNum = 42;
	final Converter<Integer, String> stringConverter3 = (from) -> {
	    System.out.println(innerNum);
	    return String.valueOf(from);
	};
    }

    void testOuteNum() {
	final Converter<Integer, String> stringConverter4 = (from) -> {
	    outerNum = 23;
	    return String.valueOf(from);
	};

    }

}
