import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔] " : "[ ] ") + description;
    }
}

public class ToDoListApp {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the To-Do List Application!");

        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> updateTask();
                case 4 -> markTaskAsCompleted();
                case 5 -> deleteTask();
                case 6 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n==== To-Do List Menu ====");
        System.out.println("1. Add a new task");
        System.out.println("2. View all tasks");
        System.out.println("3. Update a task");
        System.out.println("4. Mark a task as completed");
        System.out.println("5. Delete a task");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addTask() {
        System.out.print("Enter the task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
            return;
        }
        System.out.println("\n==== To-Do List ====");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void updateTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter the task number to update: ");
        int taskNumber = getTaskNumber();

        if (isValidTaskNumber(taskNumber)) {
            System.out.print("Enter the new description: ");
            String newDescription = scanner.nextLine();
            tasks.get(taskNumber - 1).updateDescription(newDescription);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void markTaskAsCompleted() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter the task number to mark as completed: ");
        int taskNumber = getTaskNumber();

        if (isValidTaskNumber(taskNumber)) {
            tasks.get(taskNumber - 1).markAsCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter the task number to delete: ");
        int taskNumber = getTaskNumber();

        if (isValidTaskNumber(taskNumber)) {
            tasks.remove(taskNumber - 1);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static int getTaskNumber() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= tasks.size();
    }
}
