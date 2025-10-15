import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Scanner;

public class Planner {
    private static HashMap<String, TreeMap<String, String>> week = new HashMap<>();
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (String day : DAYS) {
            week.put(day, new TreeMap<>());
        }

        System.out.println("Welcome to Weekly Planner!");
        System.out.println("---------------------------");

        while (true) {
            System.out.println("\n1. Add a task");
            System.out.println("2. View schedule");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTaskInteractive(scanner);
                    break;
                case "2":
                    viewSchedule();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addTaskInteractive(Scanner scanner) {
        System.out.print("Enter the day (e.g., Monday): ");
        String day = capitalize(scanner.nextLine().trim());

        if (!week.containsKey(day)) {
            System.out.println("Invalid day. Please enter a valid day of the week.");
            return;
        }

        System.out.print("Enter the time (HH:MM): ");
        String time = scanner.nextLine().trim();

        System.out.print("Enter the task description: ");
        String task = scanner.nextLine().trim();

        TreeMap<String, String> dailyTasks = week.get(day);

        if (dailyTasks.containsKey(time)) {
            System.out.println("Conflict! " + day + " already has a task at " + time + ".");
            System.out.print("Do you want to replace it? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("y")) {
                System.out.println("Task not added.");
                return;
            }
        }

        dailyTasks.put(time, task);
        System.out.println("Task added successfully!");
    }

    private static void viewSchedule() {
        System.out.println("\nWeekly Schedule:\n");

        // Print header
        for (String day : DAYS) {
            System.out.printf("%-20s", day);
        }
        System.out.println();

        // Collect all unique times
        TreeSet<String> allTimes = new TreeSet<>();
        for (TreeMap<String, String> dailyTasks : week.values()) {
            allTimes.addAll(dailyTasks.keySet());
        }

        // Print each row
        for (String time : allTimes) {
            for (String day : DAYS) {
                String task = week.get(day).getOrDefault(time, "");
                if (!task.isEmpty()) {
                    System.out.printf("%-20s", time + " " + task);
                } else {
                    System.out.printf("%-20s", "");
                }
            }
            System.out.println();
        }
    }

    private static String capitalize(String text) {
        if (text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
