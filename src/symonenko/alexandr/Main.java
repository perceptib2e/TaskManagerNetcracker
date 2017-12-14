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

        Task t1 = new Task("1",1);
        Task t2 = new Task("2",2);

        System.out.println(t1.equals(t2));
    }
}
