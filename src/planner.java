import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Planner {
    private static HashMap<String, TreeMap<String, String>> week = new HashMap<>();

    public static void main(String[] args) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days){
            week.put(day, new TreeMap<>());
        }

        addTask("Monday", "09:00", "Team Meeting");
        addTask("Monday", "14:00", "Gym");
        addTask("Wednesday", "10:00", "Doctor Appointment");

        // Try to add a conflicting task
        addTask("Monday", "09:00", "Coffee Break");

        // View the schedule
        viewSchedule();
    }

    public static void addTask(String day, String time, String task){
        if (!week.containsKey(day)){
            System.out.println("Invalid day: "+ day);
            return;
        }

        TreeMap<String, String> dailyTasks = week.get(day);
        if (dailyTasks.containsKey(time)){
            System.out.println("There is a conflict on time. " + day + " already has a task for the time "+ time);
        }else{
            dailyTasks.put(time,task);
            System.out.println("Task added");
        }

    }

    public static void viewSchedule() {
        System.out.println("\nWeekly Schedule:");

        for(String day: week.keySet()){
            System.out.printf("%-20s", day);
        }
        System.out.println();

        TreeSet<String> allTimes = new TreeSet<>();
        for (TreeMap<String, String> dailyTasks : week.values()){
            allTimes.addAll(dailyTasks.keySet());
        }

        for (String time : allTimes) {
            for (String day : week.keySet()) {
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



}