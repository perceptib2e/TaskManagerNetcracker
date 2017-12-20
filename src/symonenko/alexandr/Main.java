package symonenko.alexandr;

import java.util.*;

public class Main {

    public static void main(String[] args) throws CustomExeption {

        Task task1 = new Task("1", new Date(2017,12,1), new Date(2017,10,15), 1);
        task1.setActive(true);
        Task task2 = new Task("2", new Date(2017,12,2), new Date(2017,10,15), 1);
        task2.setActive(true);
        Task task3 = new Task("3", new Date(2017,12,3), new Date(2017,10,15), 1);
        task3.setActive(true);
        Task task4 = new Task("4", new Date(2017,12,4), new Date(2017,10,15), 1);
        task4.setActive(true);
        Task task5 = new Task("5", new Date(2017,12,5), new Date(2017,10,15), 1);
        task5.setActive(true);

        LinkedTaskList li1 = new LinkedTaskList();

        li1.add(task1);
        li1.add(task2);
        li1.add(task3);
        li1.add(task4);
        li1.add(task5);

        Tasks tasks = new Tasks();

        SortedMap<Date, Set<Task>> timeline = new TreeMap<Date, Set<Task>>();
        SortedMap<Date, Set<Task>> calendar = new SortedMap<>;
                (new Date(2017,10,2), new Date(2017,10,4));
        System.out.println(calendar);
    }
}
