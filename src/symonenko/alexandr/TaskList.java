package symonenko.alexandr;

import java.util.Iterator;

public abstract class TaskList implements Iterable<Task> {

    protected int size;

    //abstract methods

    abstract void add(Task task) throws CustomExeption;

    abstract boolean remove(Task task) throws CustomExeption;

    abstract int size();

    abstract Task getTask(int index) throws CustomExeption;

    abstract void getTaskCHECK(int index) throws CustomExeption;

    //methods with body

    public void taskNotNullCheck(Task task) throws IllegalStateException {
        if (task.getTitle() == null) throw new IllegalStateException("No task there.");
    }

    //returns list of tasks from selected time range
    public TaskList incoming(int from, int to) throws CustomExeption {

        TaskList arInnerTasks = new ArrayTaskList();
        for (int i = 0; i < TaskList.this.size(); i++) {
            if (TaskList.this.getTask(i).nextTimeAfter(from) == -1) continue;
            if (TaskList.this.getTask(i).nextTimeAfter(from) <= to) {
                arInnerTasks.add(TaskList.this.getTask(i));
            }
        }
        return arInnerTasks;
    }

    //equals for all subclasses
    @Override
    public boolean equals(Object taskList) {
        if (taskList == null) return false;
        if (this == taskList) return true;
        if (this.getClass() != taskList.getClass()) return false;

        TaskList other = (TaskList) taskList;

        Iterator<Task> iterator = this.iterator();

        if (this.size() != other.size()) return false;

        for (Task t : other) {
            if (!iterator.next().equals(t)) return false;
        }
        return true;
    }

    //hashCode for tasklists
    @Override
    public int hashCode() {
        int code = 0;
        if (this instanceof ArrayTaskList){
            for(Task a : this){
                code = code + a.hashCode();
            }
        }
        if (this instanceof LinkedTaskList){
            for(Task a : this){
                code = code + a.hashCode();
            }
        }
        return code;
    }
}