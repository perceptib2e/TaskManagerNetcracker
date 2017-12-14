package symonenko.alexandr;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayTaskList extends TaskList {

    protected Task[] arrayTaskList = new Task[1];

    /*
    Long time list (remove method is rare)
    * */

    //constructors

    public ArrayTaskList(int len) {
        this.arrayTaskList = new Task[len];
    }

    public ArrayTaskList() {

    }

    @Override
    public void add(Task task) throws IllegalStateException {
        taskNotNullCheck(task);
        if (size <= arrayTaskList.length - 1) {
            arrayTaskList[size++] = task;
        } else {
            arrayTaskList = Arrays.copyOf(arrayTaskList, arrayTaskList.length + 10);
            arrayTaskList[size++] = task;
        }
    }

    @Override
    public boolean remove(Task task) throws IllegalStateException {
        taskNotNullCheck(task);
        for (int i = 0; i < size; i++) {
            if (arrayTaskList[i].equals(task)) {
                for (int j = i; j < size - 1; j++) {
                    System.out.println("TURNING \'" + arrayTaskList[j].getTitle() + "\' -> INTO -> \'" + arrayTaskList[j + 1].getTitle() + "\'");
                    arrayTaskList[j] = arrayTaskList[j + 1];
                }
                size--;
                arrayTaskList[size] = null;
                return true;
            }
        }
        System.out.println("symonenko.alexandr.Task " + task.getTitle() + " is absent in symonenko.alexandr.ArrayTaskList.");
        return false;
    }

    //returns amount of tasks in list
    @Override
    public int size() {
        return size;
    }

    //returns task on "index" position
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        getTaskCHECK(index);
        return arrayTaskList[index];
    }

    @Override
    public void getTaskCHECK(int index) throws IndexOutOfBoundsException {
        if (index > arrayTaskList.length)
            throw new IndexOutOfBoundsException("symonenko.alexandr.Task in " + index + " cell is out of Array bounds. Current Array size is " + arrayTaskList.length + ".");
        if (arrayTaskList[index] == null)
            throw new IndexOutOfBoundsException("symonenko.alexandr.Task in " + index + " cell is null");
    }

    //iterator
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return size() > current;
            }

            @Override
            public Task next() {
                if (!hasNext()) throw new IllegalStateException	("Cannot find the next task");
                return getTask(current++);
            }

            @Override
            public void remove() {
                if(current == 0) throw new IllegalStateException("TaskList is empty. Nothing to remove");
                ArrayTaskList.this.remove(arrayTaskList[--current]);
            }
        };
    }
}