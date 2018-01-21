package controller;

import model.ArrayTaskList;
import model.TaskIO;
import view.MainUI;

import java.io.File;
import java.io.IOException;

public class MainController {

    static File tasklistFile = new File("tasklistFile.txt");
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MainController.class.getClass());
    static ArrayTaskList taskList = new ArrayTaskList();

    public static void main(String[] args) {

        logger.info("***TASK MANAGER LAUNCHED***");
        if (!tasklistFile.exists()) try {
            tasklistFile.createNewFile();
        } catch (IOException e) {
            logger.error("Cannot create \"tasklistFile.txt\"");
        }
        logger.info("reading \"tasklistFile.txt\", launching UI code...");
        readList();
        MainUI.Launch();
    }

    // reads tasklistFile.txt
    public static void readList() {
        try {
            TaskIO.readText(taskList, tasklistFile);
        } catch (Exception e) {
            logger.error("Cannot read \"tasklistFile.txt\"");
        }
    }

    //save taskList to the tasklistFile.txt.
    public static void saveList() {
        try {
            TaskIO.writeText(taskList, tasklistFile);
        } catch (IOException e) {
            logger.error("Cannot save \"tasklistFile.txt\"");
        }
    }

    // public access to the taskList
    public static ArrayTaskList getList() {
        return taskList;
    }
}