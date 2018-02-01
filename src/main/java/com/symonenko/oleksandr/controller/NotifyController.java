package com.symonenko.oleksandr.controller;

import com.symonenko.oleksandr.model.ArrayTaskList;
import com.symonenko.oleksandr.model.CustomException;
import com.symonenko.oleksandr.model.Task;
import com.symonenko.oleksandr.model.Tasks;
import com.symonenko.oleksandr.view.MainUI;

import java.util.*;

public class NotifyController extends Thread{

    private Calendar cal1;
    private Calendar cal2;
    private static String incoming;
    private static String noteText;
    private static ArrayTaskList incomingArray;

    private AppController ac;
    private NotifyController nc;
    private MainController mc;

    public NotifyController(){
        setDaemon(true);
        start();
        //System.out.println("NotifyController THREAD INITIALIZED");
    }

    public void run(){
        while (true) {
            if (cal1 == null){
                cal1 = Calendar.getInstance();
                if (cal1.getTime().getSeconds() != 0){
                    try {
                        sleep((60 - cal1.getTime().getSeconds())*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(cal1.getTimeInMillis() - 1000);
            cal2 = Calendar.getInstance();
            //checking for 1 minute ahead
            cal2.setTimeInMillis(cal2.getTimeInMillis() + 59000);
            checkIncoming();
            notifyIncoming();

            // 1 minute cooldown
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                mc.getLogger().info("NotifyController error");
            }
        }
    }

    //checking incoming tasks
    public void checkIncoming(){
        try {
            incoming = "";
            Iterable<Task> incArr = Tasks.incoming(MainController.getList(), cal1.getTime(),cal2.getTime());
            incomingArray = (ArrayTaskList) incArr;
            for (Task task : incomingArray) {
                if (task.isActive()){
                    if (incoming.equals("")){
                        incoming = task.getTitle();
                    }
                    else {
                        incoming = incoming  + ", " + task.getTitle();
                    }
                }
            }
        } catch (CustomException e) {
            mc.getLogger().error("Error with check incoming tasks for NotifyController");
        }
    }

    public void notifyIncoming(){
        if (!incoming.equals("")){
            noteText = "It's time to do: \n" + incoming;
            System.out.println(noteText);

            ac.getMui().getShell().getDisplay().syncExec(new Runnable() {
                public void run() {
                    ac.notificationShow(noteText);
                }});
        }
    }

    public void setMcAc (MainController mainController, AppController appController) {
        this.mc = mainController;
        this.ac = appController;
    }
}
