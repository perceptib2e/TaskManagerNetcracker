package symonenko.alexandr;

import java.text.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws CustomExсeption {

        Task task1 = new Task("CREATED TASK", new Date(70,12,1), new Date(2019,10,15), 1);
        task1.setActive(true);

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       // System.out.println(task1.toString());

        Date testDate = new Date(0, 0, 0);
        testDate.setYear(2017);

        //System.out.println(dateFormat.format(testDate));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(cal.getTime()));
        System.out.println(task1.toString());


        Date date = new Date();
        //System.out.println(dateFormat.format(date));
    }
}
