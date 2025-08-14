
import java.util.Scanner;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Task task1 = new Task("Learn Java");
//        System.out.println(task1); // Output: "[ ] Learn Java"
//
//        task1.setCompleted(true);
//        System.out.println(task1);

        TaskManager manager = new TaskManager();
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("\nPersonal Task Manager");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View All Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Save Tasks to File");
            System.out.println("6. Load Tasks from File");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice) {  // Handle user selection
                case 1:
                    addTask(manager, scanner);
                    break;
                case 2:
                    markTaskAsCompleted(manager, scanner);
                    break;
                case 3:
                    displayAllTask(manager);
                    break;
                case 4:
                    deleteTask(manager, scanner);
                    break;
                case 5:
                    saveTasks(manager, scanner);
                    break;
                case 6:
                    loadTasks(manager, scanner);
                    break;
                case 7:
                    exitProgram(scanner);
                    return;  // Exit program
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }

    }
    private static void addTask(TaskManager manager,Scanner scanner){
        System.out.print("Enter the Task Description: ");
        String description=scanner.nextLine();
        manager.addTask(description);
    }

    private static void markTaskAsCompleted(TaskManager manager,Scanner scanner){
        displayAllTask(manager);
        System.out.println("Enter task number to mark as completed: ");
        int index=scanner.nextInt()-1;
        manager.markTaskAsCompleted(index);

    }

    private static void displayAllTask(TaskManager manager){
        List<Task>tasks=manager.getAllTasks();
        if(tasks.isEmpty()){
            System.out.println("No task available");
        }else{
            for (int i=0;i< tasks.size();i++){
                System.out.println((i+1)+"."+tasks.get(i));
            }
        }

    }
    private static void deleteTask(TaskManager manager,Scanner scanner){
        displayAllTask(manager);
        System.out.println("Enter task number to delete: ");
        int index=scanner.nextInt()-1;
        manager.deleteTask(index);

    }

    private static void saveTasks(TaskManager manager,Scanner scanner){
        displayAllTask(manager);
        System.out.println("Enter filename to save: ");
        String filename=scanner.nextLine();
        manager.saveTaskToFile(filename);
    }

    private static void loadTasks(TaskManager manager ,Scanner scanner){
        displayAllTask(manager);
        System.out.println("Enter filename to load: ");
        String filename=scanner.nextLine();
        manager.loadTasksFromFile(filename);
    }

    private static void exitProgram(Scanner scanner){
        System.out.println("Exiting...");
        scanner.close();

    }
}