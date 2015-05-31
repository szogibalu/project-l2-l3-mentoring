package com.szogibalu.mentoring.monitoring;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class HelloMain {

    public static void main(String[] args) throws Exception {
	final MBeanServer mBeanServer = ManagementFactory
		.getPlatformMBeanServer();

	final ObjectName objectName = new ObjectName(
		"com.szogibalu.mentoring.monitoring:type=Hello");

	final Hello hello = new Hello();
	mBeanServer.registerMBean(hello, objectName);

	Thread.sleep(Long.MAX_VALUE);

    }

}
