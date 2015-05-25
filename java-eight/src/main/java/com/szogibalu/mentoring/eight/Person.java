package com.szogibalu.mentoring.eight;

public class Person {

    final String name;
    final int age;

    public Person(String name, int age) {
	this.name = name;
	this.age = age;
    }

    @Override
    public String toString() {
	return name;
    }

}
