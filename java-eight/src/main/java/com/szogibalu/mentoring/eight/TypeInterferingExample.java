package com.szogibalu.mentoring.eight;

import java.util.Collections;
import java.util.List;

public class TypeInterferingExample {

    static void processStringList(List<String> stringList) {
	// process stringList
    }

    public static void main(String[] args) {
	final List<String> list = Collections.emptyList();
	processStringList(list);

	processStringList(Collections.emptyList());

    }

}
