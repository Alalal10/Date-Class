import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Date implements Comparable<Date> {
    private int year, month, day;

    public Date(int year, int month, int day) {
        if (isValidDate(year, month, day)) {
            this.year = year;
            this.month = month;
            this.day = day;
        } else {
            throw new IllegalArgumentException("Invalid date: " + day + "-" + month + "-" + year);
        }
    }

    public static boolean isValidDate(int year, int month, int day) {
        try {
            return LocalDate.of(year, month, day) != null;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public void updateDate(int year, int month, int day) {
        if (isValidDate(year, month, day)) {
            this.year = year;
            this.month = month;
            this.day = day;
        } else {
            System.out.println("Error: Invalid date update attempt!");
        }
    }

    public String getDayOfWeek() {
        LocalDate date = LocalDate.of(year, month, day);
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public static long dateDifference(Date d1, Date d2) {
        LocalDate date1 = LocalDate.of(d1.year, d1.month, d1.day);
        LocalDate date2 = LocalDate.of(d2.year, d2.month, d2.day);
        return Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }

    @Override
    public int compareTo(Date other) {
        return Comparator.comparingInt((Date d) -> d.year)
                .thenComparingInt(d -> d.month)
                .thenComparingInt(d -> d.day)
                .compare(this, other);
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d (%s)", day, month, year, getDayOfWeek());
    }
}
