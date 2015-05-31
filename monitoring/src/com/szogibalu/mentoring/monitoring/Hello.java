package com.szogibalu.mentoring.monitoring;

import static java.lang.System.currentTimeMillis;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Hello extends NotificationBroadcasterSupport implements HelloMBean {

    private long sequenceNumber;

    private String name;

    @Override
    public String sayHello() {
	return "Hello!";
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public synchronized void setName(String name) {
	final Notification notification = new AttributeChangeNotification(this,
		sequenceNumber++, currentTimeMillis(), "Name Changed.", "name",
		"java.util.String", this.name, name);
	sendNotification(notification);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
	final String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };

	return new MBeanNotificationInfo[] { new MBeanNotificationInfo(types,
		AttributeChangeNotification.class.getName(),
		"MBean's attribute has changed") };
    }
}
