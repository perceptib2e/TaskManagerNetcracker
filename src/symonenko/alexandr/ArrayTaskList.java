package symonenko.alexandr;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayTaskList extends TaskList {

    /*
    Long time list (remove method is rare)
    * */

    public ArrayTaskList(int len) {
        this.arrayTaskList = new Task[len];
    }
    public ArrayTaskList(){

    }

    @Override
    public void add(Task task) throws CustomExeption {
        taskNotNullCheck(task);
        if (size <= arrayTaskList.length - 1) {
            arrayTaskList[size++] = task;
        } else {
            arrayTaskList = Arrays.copyOf(arrayTaskList, arrayTaskList.length + 10);
            arrayTaskList[size++] = task;
        }
    }

    @Override
    public boolean remove(Task task) throws CustomExeption{
        taskNotNullCheck(task);
        for (int i = 0; i < size; i++) {
            if (arrayTaskList[i].equals(task)) {
                for (int j = i; j < size-1; j++){
                    System.out.println("TURNING \'" + arrayTaskList[j].getTitle() + "\' -> INTO -> \'" + arrayTaskList[j+1].getTitle() + "\'");
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public Task getTask(int index) throws CustomExeption {
        getTaskCHECK(index);
        return arrayTaskList[index];
    }

    @Override
    public void getTaskCHECK(int index) throws CustomExeption {
        if (index > arrayTaskList.length)
            throw new CustomExeption("symonenko.alexandr.Task in " + index + " cell is out of Array bounds. Current Array size is " + arrayTaskList.length + ".");
        if (arrayTaskList[index] == null)
            throw new CustomExeption("symonenko.alexandr.Task in " + index + " cell is null");
    }

    @Override
    public Iterator<Task> iterator() {
        return null;
    }
}