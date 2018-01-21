package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import java.io.File;

import model.ArrayTaskList;
import model.CustomExсeption;
import model.LinkedTaskList;
import model.Task;
import model.TaskIO;
import model.TaskList;
import model.Tasks;
import view.MainUI;

public class MainController {

    static File tasklistFile = new File("tasklistFile.txt");
    static ArrayTaskList taskList = new ArrayTaskList();

    public static void main(String[] args) {

        if (!tasklistFile.exists()) try {
            tasklistFile.createNewFile();
        } catch (IOException e) {
//        	System.out.println("cant create file");
        }
        readList();
        MainUI.Launch();
    }

    //1
    // reads tasklistFile.txt and fills the taskList. USED ONCE PROGRAM LAUNCHES
    public static void readList() {
        try {
            TaskIO.readText(taskList, tasklistFile);
        } catch (Exception e) {
//        	System.out.println("cant read");
        }
    }

    //2
    //saves current taskList to the text file tasklistFile.txt. USED ONCE BEFORE CLOSING PROGRAM
    public static void saveList() {
        try {
            TaskIO.writeText(taskList, tasklistFile);
        } catch (IOException e) {
        }
    }

    // public access to the taskList
    public static ArrayTaskList getList() {
        return taskList;
    }
}