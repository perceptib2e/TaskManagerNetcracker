package symonenko.alexandr;

import java.util.Iterator;

public class LinkedTaskList extends TaskList {

    /*
    Short time list (remove method is often)
    * */

    private InnerTask first;
    private InnerTask last;
    private int size;

    //constructor OUTERclass
    public LinkedTaskList() {
    }

    @Override
    public Iterator<Task> iterator() {
        return null;
    }

    //inner class
    private class InnerTask {
        private Task currTask;
        private InnerTask prevTask;
        private InnerTask nextTask;

        //constructor INNERclass
        private InnerTask(Task task) {
            this.currTask = task;
            this.prevTask = null;
            this.nextTask = null;
        }

        private InnerTask() {
        }
    }

    @Override
    public void add(Task task) throws CustomExeption {
        taskNotNullCheck(task);
        InnerTask taskToAdd = new InnerTask(task);
        if (size == 0) {
            first = taskToAdd;
            last = first;
            size++;
        } else {
            last.nextTask = taskToAdd;
            taskToAdd.prevTask = last;
            last = taskToAdd;
            size++;
        }
    }

    @Override
    public boolean remove(Task task) throws CustomExeption {
        taskNotNullCheck(task);
        InnerTask innTaskSearch;
        innTaskSearch = first;
        if (task == first.currTask) {
            first = first.nextTask;
            first.prevTask = null;
            size--;
            return true;
        }
        if (task == last.currTask) {
            last = last.prevTask;
            last.nextTask = null;
            size--;
            return true;
        }
        else {
            for (int i = 0; i < size; i++){
                if (innTaskSearch.currTask.getTitle().equals(task.getTitle())) {
                    innTaskSearch.prevTask.nextTask = innTaskSearch.nextTask;
                    innTaskSearch.nextTask.prevTask = innTaskSearch.prevTask;
                    size--;
                    return true;
                }
                innTaskSearch = innTaskSearch.nextTask;
            }
        }
        System.out.println("symonenko.alexandr.Task " + task.getTitle() + " is absent in symonenko.alexandr.LinkedTaskList.");
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void getTaskCHECK(int index) throws CustomExeption {
        if (index >= size)
            throw new CustomExeption("LinkedList has " + size + " tasks (last has index " + (size - 1) + " from 0 in symonenko.alexandr.LinkedTaskList). Requested task is " + index + ".");
    }

    @Override
    public Task getTask(int index) throws CustomExeption {
        InnerTask inn = first;
        getTaskCHECK(index);
        for (int i = 0; i < index; i++) {
            inn = inn.nextTask;
        }
        return inn.currTask;
    }
}