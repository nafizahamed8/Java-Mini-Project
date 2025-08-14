public class Task {
    private String description; //stores the task details
    private boolean isCompleted;//tracking whether the task is done

    // private means this variable can only be accessed in task class

    // a constructor is called when a new task is created

    public Task(String description){
        this.description=description;//assigns the imput description to the task

        this.isCompleted=false;//new task are always incomplete by default


    }

    public String getDescription(){
        return description;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    //getter allowed control access to private field

    public void setCompleted(boolean completed){
         isCompleted=completed;


    }
    //setter allows modifying isCompleted from outside of the class

    public String toString(){
        String status=isCompleted ? "##":"[]";
        return status +" " + description;
    }



}
