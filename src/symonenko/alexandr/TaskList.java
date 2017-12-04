package symonenko.alexandr;

public abstract class TaskList implements Iterable<Task> {

    protected int size;
    protected Task[] arrayTaskList = new Task[1];

    //abstract
    abstract void add(Task task) throws  CustomExeption;
    abstract boolean remove(Task task) throws CustomExeption;
    abstract int size();
    abstract Task getTask(int index) throws CustomExeption;
    abstract void getTaskCHECK(int index) throws CustomExeption;

    //with method body

    public void taskNotNullCheck (Task task)throws CustomExeption{
        if (task.getTitle() == null)throw new CustomExeption("No task there.");
    }

    public TaskList incoming (int from, int to) throws CustomExeption{

        TaskList arInnerTasks = new ArrayTaskList();
            for (int i = 0; i < TaskList.this.size(); i++) {
            if (TaskList.this.getTask(i).nextTimeAfter(from) == -1) continue;
            if (TaskList.this.getTask(i).nextTimeAfter(from) <= to) {
                arInnerTasks.add(TaskList.this.getTask(i));
            }
        }
    return arInnerTasks;
    }

    public boolean equals (TaskList taskList){
        if (this instanceof ArrayTaskList && taskList instanceof ArrayTaskList){
            if (this.size() == taskList.size()){
                for (int i = 0; i < size(); i++) {
                    if (this.arrayTaskList[i].equals(taskList[i]));
                }
            }
        }
    }
}