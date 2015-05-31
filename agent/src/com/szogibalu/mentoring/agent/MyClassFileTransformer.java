package com.szogibalu.mentoring.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className,
	    Class classBeingRedefined, ProtectionDomain protectionDomain,
	    byte[] classfileBuffer) throws IllegalClassFormatException {
	System.out.println("class file transformer invoked for className: "
		+ className);
	return classfileBuffer;
    }

}