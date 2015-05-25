package com.szogibalu.mentoring.eight;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterNameFeatures {

    public static void main(String[] args) throws Exception {
	final Method method = ParameterNameFeatures.class
		.getMethod("main", String[].class);
	for (final Parameter parameter : method.getParameters()) {
	    System.out.println("Parameter: " + parameter.getName());
	}
    }
}
