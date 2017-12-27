package symonenko.alexandr;

import java.text.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws CustomExсeption {

        Task task1 = new Task("CREATED TASK", new Date(70,12,1), new Date(2019,10,15), 1);
        task1.setActive(true);

        System.out.println(task1.toString());

    }
}
