package com.szogibalu.mentoring.eight;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateTimeFeatures {

    public static void main(String[] args) {
	final Clock clock = Clock.systemUTC();
	System.out.println(clock.instant());

	final LocalDate date = LocalDate.now();
	final LocalDate dateFromClock = LocalDate.now(clock);

	System.out.println(date);
	System.out.println(dateFromClock);

	final LocalTime time = LocalTime.now();
	final LocalTime timeFromClock = LocalTime.now(clock);

	System.out.println(time);
	System.out.println(timeFromClock);

	final LocalDateTime datetime = LocalDateTime.now();
	final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

	System.out.println(datetime);
	System.out.println(datetimeFromClock);

	final ZonedDateTime zonedDatetime = ZonedDateTime.now();
	final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
	final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId
		.of("America/Los_Angeles"));

	System.out.println(zonedDatetime);
	System.out.println(zonedDatetimeFromClock);
	System.out.println(zonedDatetimeFromZone);

	final LocalDateTime from = LocalDateTime
		.of(2014, Month.APRIL, 16, 0, 0, 0);
	final LocalDateTime to = LocalDateTime
		.of(2015, Month.APRIL, 16, 23, 59, 59);

	final Duration duration = Duration.between(from, to);
	System.out.println("Duration in days: " + duration.toDays());
	System.out.println("Duration in hours: " + duration.toHours());

	final LocalDate fromDate = LocalDate.of(2014, Month.APRIL, 16);
	final LocalDate toDate = LocalDate.of(2015, Month.APRIL, 17);

	final Period period = Period.between(fromDate, toDate);
	System.out.println("Period in days: " + period.getDays());

	final Instant now = Instant.now();
	System.out.println(now);

	final Date oldDate = Date.from(now);
	System.out.println(oldDate);

	final Date ts = new Date();
	final Instant instant = Instant.ofEpochMilli(ts.getTime());
	System.out.println(instant);
    }
}
