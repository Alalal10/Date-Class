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

        Collections.sort(dateList);
        System.out.println("Sorted dates:");
        for (Date d : dateList) {
            System.out.println(d);
        }

        scanner.close();
    }
}
