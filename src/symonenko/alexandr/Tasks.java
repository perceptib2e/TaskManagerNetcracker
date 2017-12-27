package symonenko.alexandr;

import java.util.*;

public class Tasks {

    //returns list of tasks from selected time range
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end) throws CustomExсeption {

        TaskList arOfTasks = new ArrayTaskList();
        for (Task task : tasks) {
            Date date = task.nextTimeAfter(start);
            if (date != null && date.compareTo(end) <= 0) arOfTasks.add(task);
        }
        return arOfTasks;
    }

    public static SortedMap<Date, Set<Task>> calendar (Iterable<Task> tasks, Date start, Date end) throws CustomExсeption {
        //создаём коллекцию что будем возвращать
        TreeMap<Date, Set<Task>> setOfTasks = new TreeMap<>();
        //создаём список задач (массив) что будет заполнен с помощью инкоминг()
        TaskList tempListOfTasks = (ArrayTaskList) incoming(tasks, start, end);
        //идём по списку и добавляем все нужные задачи
        for (Task task : tempListOfTasks){
            //создаём время от которого ищем задачу
            Date date = task.nextTimeAfter(start);
            //если время соответствует условию (не больше границ calendar), то задача добавляется
            while (date.compareTo(end) <= 0){
                //пороверяется, если есть такой уже ключ "Date" то добавляется задача в этот ключ, нет - создаётся новый ключ и добавляется к нему
                if (setOfTasks.containsKey(date)){
                    setOfTasks.get(date).add(task);
                }
                else {
                    HashSet innerSet = new HashSet();
                    innerSet.add(task);
                    setOfTasks.put(date, innerSet);
                }
                if (!task.isRepeated()){
                    break;
                }
                //меняем время от которого ищем задачу
                if (task.nextTimeAfter(date) != null){
                    date = task.nextTimeAfter(date);
                }
                else {
                    break;
                }
            }
        }
        return setOfTasks;
    }
}