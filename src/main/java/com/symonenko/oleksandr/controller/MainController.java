package com.symonenko.oleksandr.controller;

import org.apache.log4j.Logger;
import com.symonenko.oleksandr.model.ArrayTaskList;
import com.symonenko.oleksandr.model.TaskIO;
import com.symonenko.oleksandr.view.MainUI;

import java.io.File;
import java.io.IOException;


public class MainController {

    static File tasklistFile = new File("tasklistFile.txt");
    final static Logger logger = Logger.getLogger(MainController.class.getClass());
    static ArrayTaskList taskList = new ArrayTaskList();

    public static void main(String[] args) {
        MainController mc = new MainController();
        AppController ac = new AppController();
        MainUI mui = new MainUI();
        mui.setAc(ac);
        logger.info("***TASK MANAGER LAUNCHED***");
        if (!tasklistFile.exists()) try {
            tasklistFile.createNewFile();
        } catch (IOException e) {
            logger.error("Cannot create \"tasklistFile.txt\"");
        }
        logger.info("reading \"tasklistFile.txt\", launching UI code...");
        readList();
        mui.Launch(mc);
    }

    /* reads tasklistFile.txt */
    public static void readList() {
        try {
            TaskIO.readText(taskList, tasklistFile);
        } catch (Exception e) {
            logger.error("Cannot read \"tasklistFile.txt\"");
        }
    }

    /* save taskList to the tasklistFile.txt. */
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