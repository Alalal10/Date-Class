Date-Class

This is the class that describes the date. It has a year, a month, and a day. It checks if the date is correct, gets the day of the week for that date, updates the date if necessary, and calculates the difference between the two dates.

    import java.time.*;
    import java.time.format.TextStyle;
    import java.time.temporal.ChronoUnit;
    import java.util.*;

    class Date implements Comparable<Date> {
      private int year, month, day;
      
We pass on the year, month and day. This code checks if the date is correct (for example, January 32 is incorrect). If the date is correct, it is saved; if not, an error is discarded.

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
This method shows the day of the week for a given date.

    public String getDayOfWeek() {
        LocalDate date = LocalDate.of(year, month, day);
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
This method counts how many days have passed between two dates.

    public static long dateDifference(Date d1, Date d2) {
        LocalDate date1 = LocalDate.of(d1.year, d1.month, d1.day);
        LocalDate date2 = LocalDate.of(d2.year, d2.month, d2.day);
        return Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }
This method allows you to compare two dates. For example, if we want to know which date comes first, we can use this method for sorting.

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


Nexy part of code is that the programme starts by asking the user for the number of dates they want to enter. After that, in the cycle, the programme asks you to enter a day, month, and year for each date. The entered data is added to the dateList.

    import java.util.*;

    public class Main {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of dates: ");
        int numDates = scanner.nextInt();
        List<Date> dateList = new ArrayList<>();

        for (int i = 0; i < numDates; i++) {
            System.out.print("Enter day, month, and year for date " + (i + 1) + ": ");
            int day = scanner.nextInt();
            int month = scanner.nextInt();
            int year = scanner.nextInt();
            try {
                Date newDate = new Date(year, month, day);
                dateList.add(newDate);
                System.out.println("Day of the week: " + newDate.getDayOfWeek());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }
After entering the dates, the programme prompts the user to select two dates from the list and calculate the difference between them in days. After calculating the difference, the programme asks if the user wants to continue comparing other dates.

        String continueComparing = "yes";
        while (continueComparing.equalsIgnoreCase("yes") && dateList.size() > 1) {
            System.out.print("Choose two dates to calculate the difference.\nEnter the index of the first date (1-based): ");
            int firstIndex = scanner.nextInt() - 1;
            System.out.print("Enter the index of the second date (1-based): ");
            int secondIndex = scanner.nextInt() - 1;

            if (firstIndex >= 0 && firstIndex < dateList.size() && secondIndex >= 0 && secondIndex < dateList.size()) {
                long diff = Date.dateDifference(dateList.get(firstIndex), dateList.get(secondIndex));
                System.out.println("Difference between selected dates: " + diff + " days");
            } else {
                System.out.println("Invalid index. Please try again.");
            }

            System.out.print("Do you want to compare the difference between other dates? (yes/no): ");
            continueComparing = scanner.next();
        }
Here, the programme offers the user the option to update the date from the list. The user selects the date that needs to be updated and enters a new one. The programme updates the date in the list and shows it.

        String continueUpdating = "yes";
        while (continueUpdating.equalsIgnoreCase("yes")) {
            System.out.print("Do you want to update a date? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("yes")) {
                System.out.print("Enter the index (1-based) of the date to update: ");
                int index = scanner.nextInt() - 1;
                if (index >= 0 && index < dateList.size()) {
                    System.out.print("Enter new day, month, and year: ");
                    int day = scanner.nextInt();
                    int month = scanner.nextInt();
                    int year = scanner.nextInt();
                    dateList.get(index).updateDate(year, month, day);
                    System.out.println("Updated date: " + dateList.get(index));
                } else {
                    System.out.println("Invalid index.");
                }
            }

            System.out.print("Do you want to update another date? (yes/no): ");
            continueUpdating = scanner.next();
        }
After the user has completed all the updates and comparisons, the programme sorts the list of dates in ascending order and displays them.

        Collections.sort(dateList);
        System.out.println("Sorted dates:");
        for (Date d : dateList) {
            System.out.println(d);
        }

        scanner.close();
    }
    }
