package symonenko.alexandr;

import java.io.Serializable;
import java.util.Iterator;

public abstract class TaskList implements Iterable<Task>, Serializable {

    protected int size;

    /* abstract methods */

    abstract void add(Task task) throws CustomExсeption;

    abstract boolean remove(Task task) throws CustomExсeption;

    abstract int size();

    abstract Task getTask(int index) throws CustomExсeption;

    abstract void getTaskCHECK(int index) throws CustomExсeption;

    /* methods with body */

    public void taskNotNullCheck(Task task) throws IllegalStateException {
        if (task == null) throw new IllegalStateException("No task there.");
    }

    /* equals for all subclasses */
    @Override
    public boolean equals(Object taskList) {
        if (taskList == null) return false;
        if (this == taskList) return true;
        if (this.getClass() != taskList.getClass()) return false;

        TaskList other = (TaskList) taskList;

        Iterator<Task> iterator = this.iterator();

        if (this.size() != other.size()) return false;

        for (Task t : other) {
            if (!(iterator.next().equals(t))) return false;
        }
        return true;
    }

    /* hashCode for tasklists */
    @Override
    public int hashCode() {
        int code = 1;
        for(Task a : this){
            code = code + a.hashCode();
        }
        return code;
    }
}