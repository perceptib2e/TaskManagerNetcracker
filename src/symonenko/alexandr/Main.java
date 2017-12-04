package symonenko.alexandr;

public class Main {

    public static void main(String[] args) throws CustomExeption {

        Task task1 = new Task("Simple IN", 55);
        task1.setActive(true);
        Task task2 = new Task("Repeat left intersect IN 1", 0, 55, 13);
        task2.setActive(true);
        Task task3 = new Task("Repeat left intersect IN 2", 0, 60, 30);
        task3.setActive(true);
        Task task4 = new Task("Repeat inside IN", 51, 58, 2);
        task4.setActive(true);
        Task task5 = new Task("Repeat outside IN", 0, 100, 5);
        task5.setActive(true);
        Task task6 = new Task("Repeat right intersect IN", 55, 100, 20);
        task6.setActive(true);
        Task task7 = new Task("Simple bound IN", 60);
        task7.setActive(true);

        System.out.println(task1.equals(task3));

        System.out.println(task1.hashCode());
        System.out.println(task1.toString());
        System.out.println(task6.toString());

//        ArrayTaskList aTL = new ArrayTaskList();
//        TaskList lTL = new LinkedTaskList();

//        aTL.add(task1);
//        aTL.add(task2);
//        aTL.add(task3);
//        aTL.add(task3);
//        aTL.add(task5);
//        aTL.add(task6);
//
//        lTL.add(task1);
//        lTL.add(task2);
//        lTL.add(task3);
//        lTL.add(task4);
//        lTL.add(task5);
//        lTL.add(task6);

        //ARRAY LIST TEST

//        //checking all filled tasks in symonenko.alexandr.ArrayTaskList
//        System.out.println("\n Length of FILLED symonenko.alexandr.ArrayTaskList = " + aTL.size() + "\n namely:");
//        for (int i = 0; i < aTL.size; i++)
//            System.out.println("in " + i + " cell = " + aTL.getTask(i).getTitle());
//
//        //checking ALL symonenko.alexandr.ArrayTaskList cells
//        System.out.println("\n Length of ALL symonenko.alexandr.ArrayTaskList = " + aTL.arrayTaskList.length + "\n namely:");
//        for (int i = 0; i < aTL.arrayTaskList.length; i++)
//        System.out.println("in " + i + " cell = " + aTL.arrayTaskList[i]);
//
//        //removing task from symonenko.alexandr.ArrayTaskList
//        System.out.println("\n Removing task7");
//        aTL.remove(task7);
//
//        //checking all filled tasks in symonenko.alexandr.ArrayTaskList
//        System.out.println("\n Length of FILLED symonenko.alexandr.ArrayTaskList = " + aTL.size() + "\n namely:");
//        for (int i = 0; i < aTL.size; i++)
//            System.out.println("in " + i + " cell = " + aTL.getTask(i).getTitle());
//
//        //checking ALL symonenko.alexandr.ArrayTaskList cells
//        System.out.println("\n Length of ALL symonenko.alexandr.ArrayTaskList = " + aTL.arrayTaskList.length + "\n namely:");
//        for (int i = 0; i < aTL.arrayTaskList.length; i++)
//            System.out.println("in " + i + " cell = " + aTL.arrayTaskList[i]);

//        //LINKED LIST TEST
//
//        //checking all tasks in symonenko.alexandr.LinkedTaskList
//        System.out.println("\n Length of symonenko.alexandr.LinkedTaskList = " + lTL.size() + "\n namely:");
//        for (int i = 0; i < lTL.size(); i++)
//            System.out.println("in " + i + " cell = " + lTL.getTask(i).getTitle());
//
//        //removing task from symonenko.alexandr.LinkedTaskList
//        System.out.println("\n Removing task7");
//        lTL.remove(task7);
//
//        //checking all tasks in symonenko.alexandr.LinkedTaskList
//        System.out.println("\n Length of symonenko.alexandr.LinkedTaskList = " + lTL.size() + "\n namely:");
//        for (int i = 0; i < lTL.size(); i++)
//            System.out.println("in " + i + " cell = " + lTL.getTask(i).getTitle());
    }
}
