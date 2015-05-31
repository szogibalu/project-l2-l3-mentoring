package com.szogibalu.mentoring.agent;

import java.lang.instrument.Instrumentation;

public class MyJavaAgent {

    public static void premain(String args, Instrumentation inst)
	    throws Exception {
	System.out.println("premain method invoked with args: " + args
		+ " and inst:  " + inst);
	inst.addTransformer(new MyClassFileTransformer());
    }
}