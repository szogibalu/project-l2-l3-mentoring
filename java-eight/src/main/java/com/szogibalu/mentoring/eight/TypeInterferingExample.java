package com.szogibalu.mentoring.eight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeInterferingExample {

    static <T> T pick(T a1, T a2) {
	return a2;
    }

    void infer() {
	final Serializable s = pick("d", new ArrayList<String>());
    }

    static void processStringList(List<String> stringList) {
	// process stringList
    }

    public static void main(String[] args) {
	final List<String> list = Collections.emptyList();
	processStringList(list);

	processStringList(Collections.emptyList());

    }

}
