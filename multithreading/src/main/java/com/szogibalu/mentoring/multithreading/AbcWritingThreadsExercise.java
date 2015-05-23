package com.szogibalu.mentoring.multithreading;

import static java.lang.Thread.currentThread;

public class AbcWritingThreadsExercise {

    private static final int HIGHEST_PRIORITY = 1;
    private static final int LOWEST_PRIORITY = 3;

    public static class WriterThread implements Runnable {

	private final int priority;
	private final String text;
	private final Writer writer;

	public WriterThread(Writer writer, int priority, String text) {
	    this.priority = priority;
	    this.text = text;
	    this.writer = writer;
	}

	@Override
	public void run() {
	    for (int i = 1; i <= 100; i++) {
		writer.print(priority, text);
	    }
	}
    }

    private static class Writer {

	private int actual = HIGHEST_PRIORITY;

	public synchronized void print(int priority, String text) {
	    while (priority != actual) {
		try {
		    wait();
		} catch (final InterruptedException e) {
		    currentThread().interrupt();
		}
	    }

	    System.out.println(text);

	    if (priority == LOWEST_PRIORITY) {
		actual = HIGHEST_PRIORITY;
	    } else {
		actual = priority + 1;
	    }

	    notifyAll();
	}
    }

    public static void main(String[] args) {
	final Writer writer = new Writer();

	new Thread(new WriterThread(writer, 3, "c")).start();
	new Thread(new WriterThread(writer, 2, "b")).start();
	new Thread(new WriterThread(writer, 1, "a")).start();
    }
}
