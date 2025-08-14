import java.util.ArrayList;
import java.util.List;
import java.io.*;//for file operation saving and loading task
public class TaskManager {
    private List<Task>tasks;
    //external class cannot modify it directly

    public TaskManager(){
        tasks=new ArrayList<>();// INITIALIZING and empty list
    }

    public void addTask(String description){
        tasks.add(new Task(description));
    }

    public void markTaskAsCompleted(int index){
        if(index>=0 && index < tasks.size()){
            tasks.get(index).setCompleted(true);
        }
    }

    public void deleteTask(int index){
        if(index>=0 && index < tasks.size()){
            tasks.remove(index);
        }

    }

    public List<Task>getAllTasks(){
        return new ArrayList<>(tasks); //get a compy of all task
    }


    public void saveTaskToFile(String filename){
        //try with resources automatically close the file when done
        try(PrintWriter writer=new PrintWriter(new FileWriter(filename))){
            for (Task task:tasks){
                writer.println(task.getDescription()+ ","+task.isCompleted());
            }
        }catch (IOException e){
            System.out.println("Error savings task: "+ e.getMessage());
        }


    }

    public void loadTasksFromFile(String filename) {
        tasks.clear();  // Reset the current task list
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Task task = new Task(parts[0]);
                    task.setCompleted(Boolean.parseBoolean(parts[1]));
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }



}
