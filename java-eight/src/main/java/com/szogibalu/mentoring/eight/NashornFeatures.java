package com.szogibalu.mentoring.eight;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornFeatures {

    public static void main(String[] args) throws ScriptException {

	final ScriptEngineManager manager = new ScriptEngineManager();
	final ScriptEngine engine = manager.getEngineByName("JavaScript");

	System.out.println(engine.getClass().getName());
	System.out.println(engine
		.eval("function f() { return 'Hello '; }; f() +  'World';"));
    }

}
