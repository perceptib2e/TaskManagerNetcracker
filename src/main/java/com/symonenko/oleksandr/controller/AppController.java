package com.symonenko.oleksandr.controller;

import com.symonenko.oleksandr.eclipse.wb.swt.SWTResourceManager;
import com.symonenko.oleksandr.model.CustomException;
import com.symonenko.oleksandr.model.Task;
import com.symonenko.oleksandr.model.Tasks;
import com.symonenko.oleksandr.view.MainUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class AppController {

    private MainUI mui;
    private MainController mc;

    public void setMc(){
        this.mc = MainController.getMc();
    }

    public void setMui() {
        this.mui = MainUI.getMui();
    }

    public MainUI getMui() {
        return this.mui;
    }

    /* methods for menu */
    /* list view, without buttons */
    public void showList() {
        mui.getStartField().setVisible(false);
        mui.getListT().removeAll();
        for (int i = 0; i < MainController.getList().size(); i++){
            mui.getListT().add(MainController.getList().getTask(i).toString());
        }
        mui.getListT().setEnabled(true);
        if (mui.getListT().getVisible()){
            mui.getGrpCalendar().setVisible(false);
            mui.getGrpTasks().setVisible(true);
            mui.getBtnAdd().setVisible(false);
            mui.getBtnEdit().setVisible(false);

            mui.getBtnRemove().setVisible(false);
        }

        mui.getMntmEdit().setEnabled(true);
    }

    /* list view, with buttons */
    public void showListAndButtons() {
        mui.getBtnAdd().setVisible(true);
        mui.getBtnEdit().setVisible(true);
        mui.getBtnRemove().setVisible(true);
    }

    /* calendar view, with button */
    public void showCalendar() {
        mui.getStartField().setVisible(false);
        mui.getGrpTasks().setVisible(false);
        mui.getGrpCalendar().setVisible(true);
        mui.getMntmEdit().setEnabled(false);
    }

    /* program info view (when press "about") */
    public void showProgramInfo() {
        mui.getGrpProgramInfo().setVisible(true);
    }

    /* motivation joke */
    public void motivation() throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://youtu.be/6H2FRxvsd2M"));
    }

    /* methods for task list */
    /* shows add task field */
    public void addTask() {
        mui.getGrpAddEditTask().setVisible(true);
        mui.getGrpAddEditTask().setText("add task");
        mui.getBtnAddEdit().setText("add");
        mui.getBtnAddEdit().setImage(SWTResourceManager.getImage(MainUI.class, "/images/add small.png"));
        mui.getLblNewLabel_2().setImage(SWTResourceManager.getImage(MainUI.class, "/images/add big.png"));
        mui.getLblNewLabel_2().setBounds(199, 47, 68, 117);
        mui.getListT().setEnabled(false);
        mui.getMntmActions().setEnabled(false);
    }

    /* shows edit task field */
    public void editTask() {
        if (mui.getListT().getSelectionCount() > 0) {
            //vision
            mui.getGrpAddEditTask().setVisible(true);
            mui.getGrpAddEditTask().setText("edit task");
            mui.getBtnAddEdit().setText("edit");
            mui.getBtnAddEdit().setImage(SWTResourceManager.getImage(MainUI.class, "/images/edit small.png"));
            mui.getLblNewLabel_2().setImage(SWTResourceManager.getImage(MainUI.class, "/images/edit big.png"));
            mui.getLblNewLabel_2().setBounds(202, 47, 68, 117);
            mui.getListT().setEnabled(false);
            mui.getMntmActions().setEnabled(false);

            //autofill
            Task target = MainController.getList().getTask(mui.getListT().getSelectionIndex());
            Calendar cal_t = Calendar.getInstance();
            cal_t.setTime(target.getStartTime());
            int year1 = cal_t.get(Calendar.YEAR);
            int month1 = cal_t.get(Calendar.MONTH);
            int day1 = cal_t.get(Calendar.DAY_OF_MONTH);
            mui.getText_title().setText(target.getTitle());
            //проверка установки времени
            mui.getDateTime_task_date_1().setDate(year1,month1,day1);
            mui.getDateTime_task_time_1().setTime(target.getStartTime().getHours(), target.getStartTime().getMinutes(), target.getStartTime().getSeconds());
            mui.getBtnTaskIsActive().setSelection(target.isActive());
            if (target.isRepeated()) {
                mui.getBtnTaskIsRepeating().setSelection(true);
                repeatingCheck();
                mui.getText_interval().setText(String.valueOf(target.getRepeatInterval() / 60));
                cal_t.setTime(target.getEndTime());
                int year2 = cal_t.get(Calendar.YEAR);
                int month2 = cal_t.get(Calendar.MONTH);
                int day2 = cal_t.get(Calendar.DAY_OF_MONTH);
                mui.getDateTime_task_date_2().setDate(year2,month2,day2);
                mui.getDateTime_task_time_2().setTime(target.getEndTime().getHours(), target.getStartTime().getMinutes(), target.getStartTime().getSeconds());
            }
            else {
                mui.getText_interval().setEnabled(false);
                mui.getDateTime_task_date_2().setEnabled(false);
                mui.getDateTime_task_time_2().setEnabled(false);
            }
        }
        else {
            error("choose task you want to edit");
            mui.getListT().setEnabled(false);
        }
    }

    /* check for entering repeating or non-repeating task */
    public void repeatingCheck() {
        if(mui.getBtnTaskIsRepeating().getSelection()) {
            mui.getText_interval().setEnabled(true);
            mui.getDateTime_task_date_2().setEnabled(true);
            mui.getDateTime_task_time_2().setEnabled(true);
        }
        else {
            mui.getText_interval().setEnabled(false);
            mui.getDateTime_task_date_2().setEnabled(false);
            mui.getDateTime_task_time_2().setEnabled(false);
        }
    }

    /* add or edit task confirm */
    public void addeditTaskConfirm(){
        if(mui.getText_title().getText().isEmpty() && mui.getBtnTaskIsRepeating().getSelection()) {
            error("fill all required fields");
        }
        if(mui.getText_title().getText().isEmpty() || mui.getBtnTaskIsRepeating().getSelection() && mui.getText_interval().getText().isEmpty()) {
            error("fill all required fields");
        }
        else {
            addOrEditTask();
            mui.getGrpTasks().setEnabled(true);
        }
    }

    /* exit from add or edit task field without changes */
    public void denyAddEdit() {
        mui.getGrpAddEditTask().setVisible(false);
        mui.getGrpTasks().setEnabled(true);
        mui.getListT().setEnabled(true);
        mui.getMntmActions().setEnabled(true);
        mui.getBtnTaskIsRepeating().setSelection(false);
        mui.getText_title().setText("");
        mui.getText_interval().setText("");
    }

    /* remove chosen task */
    public void removeTask() {
        if (mui.getListT().getSelectionCount() > 0) {
            MainController.getList().remove(MainController.getList().getTask(mui.getListT().getSelectionIndex()));
            MainController.saveList();
            showList();
            showListAndButtons();
        }
        else {
            error("choose task you want to remove");
            mui.getListT().setEnabled(false);
        }
    }

    /* methods for error pop-up message */
    /* view of error message */
    public void error(String string) {
        mui.getlblerrTexthere().setText(string);
        mui.getGrpErrorMesage().setVisible(true);
        //1. if error from add/edit field
        if (mui.getGrpAddEditTask().isVisible()) {
            mui.getGrpAddEditTask().setEnabled(false);
            mui.getGrpTasks().setEnabled(false);
        }
        //2. if error from list buttons
        if (mui.getGrpTasks().isVisible() && !mui.getGrpAddEditTask().isVisible()) mui.getGrpTasks().setEnabled(false);
        //3. if error from calendar field
        if (mui.getGrpCalendar().isVisible()) mui.getGrpCalendar().setEnabled(false);

    }

    /* hide error message */
    public void errorClose() {
        mui.getGrpErrorMesage().setVisible(false);
        //1. if error from add/edit field
        if (mui.getGrpAddEditTask().isVisible()) {
            mui.getGrpAddEditTask().setEnabled(true);
        }
        //2. if error from list buttons
        if (mui.getGrpTasks().isVisible() && !mui.getGrpAddEditTask().isVisible()) {
            mui.getGrpTasks().setEnabled(true);
            mui.getListT().setEnabled(true);
        }
        //3. if error from calendar field
        if (mui.getGrpCalendar().isVisible()) mui.getGrpCalendar().setEnabled(true);
    }

    /* methods for notification pop-up message */
    public void notificationShow(String string){
        mui.getLblNotificationTexthere().setText(string);
        mui.getGrpNotificationMesage().setVisible(true);
    }

    public void notificationClose(){
        mui.getGrpNotificationMesage().setVisible(false);
    }

    /* methods for task list adding / editing tasks */
    /* adding task to tasklist or editing task in tasklist */
    public void addOrEditTask() {
        Task tempTask = null;
        int interval = -1;
        String name = mui.getText_title().getText();
        Calendar list_cal_1 = Calendar.getInstance();
        list_cal_1.setTime(new Date(0));
        list_cal_1.set(Calendar.YEAR, mui.getDateTime_task_date_1().getYear());
        list_cal_1.set(Calendar.MONTH, mui.getDateTime_task_date_1().getMonth());
        list_cal_1.set(Calendar.DAY_OF_MONTH, mui.getDateTime_task_date_1().getDay());
        list_cal_1.set(Calendar.HOUR_OF_DAY, mui.getDateTime_task_time_1().getHours());
        list_cal_1.set(Calendar.MINUTE, mui.getDateTime_task_time_1().getMinutes());
        long startLong = list_cal_1.getTimeInMillis();
        boolean activityOfTask = mui.getBtnTaskIsActive().getSelection();
        // if task is not repeatable
        if (!mui.getBtnTaskIsRepeating().getSelection()) {
            try {
                tempTask = new Task(name, new Date(startLong), activityOfTask);

            } catch (Exception e) {
                error("Cannot add/edit non repeating task");
                mc.getLogger().error("Cannot add/edit non repeating task");
            }
        }
        // if task is repeatable
        else {
            try {
                interval = Integer.parseInt(mui.getText_interval().getText()) * 60;
                Calendar list_cal_2 = Calendar.getInstance();
                list_cal_2.setTime(new Date(0));
                list_cal_2.set(Calendar.YEAR, mui.getDateTime_task_date_2().getYear());
                list_cal_2.set(Calendar.MONTH, mui.getDateTime_task_date_2().getMonth());
                list_cal_2.set(Calendar.DAY_OF_MONTH, mui.getDateTime_task_date_2().getDay());
                list_cal_2.set(Calendar.HOUR_OF_DAY, mui.getDateTime_task_time_2().getHours());
                list_cal_2.set(Calendar.MINUTE, mui.getDateTime_task_time_2().getMinutes());
                long endLong = list_cal_2.getTimeInMillis();
                tempTask = new Task(name, new Date(startLong), new Date(endLong), interval, activityOfTask);
            } catch (Exception e) {
                error("Cannot add/edit repeating task");
                mc.getLogger().error("Cannot add/edit repeating task");
            }
        }
        // action with task
        if (mui.getGrpAddEditTask().getText().equals("add task")){
            MainController.getList().add(tempTask);
            MainController.saveList();
            mui.getBtnTaskIsRepeating().setSelection(false);
        }
        else {
            Task deltask = MainController.getList().getTask(mui.getListT().getSelectionIndex());
            MainController.getList().overwriteTask(deltask, tempTask);
            MainController.saveList();
            mui.getBtnTaskIsRepeating().setSelection(false);
        }
        // view changes after successful adding/editing task
        showList();
        showListAndButtons();
        mui.getListT().setEnabled(true);
        mui.getText_interval().setEnabled(false);
        mui.getBtnTaskIsRepeating().setSelection(false);
        mui.getText_title().setText("");
        mui.getText_interval().setText("");
        mui.getGrpAddEditTask().setVisible(false);
        mui.getMntmActions().setEnabled(true);
    }

    /* methods for task list adding / editing tasks */
    /* displays tasks of tasklist in time they must appear */
    public void showCalendarWithData() {
        mui.getListC().removeAll();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(Calendar.YEAR, mui.getDate_cal_from().getYear());
        start.set(Calendar.MONTH, mui.getDate_cal_from().getMonth());
        start.set(Calendar.DAY_OF_MONTH, mui.getDate_cal_from().getDay());
        end.set(Calendar.YEAR, mui.getDate_cal_to().getYear());
        end.set(Calendar.MONTH, mui.getDate_cal_to().getMonth());
        end.set(Calendar.DAY_OF_MONTH, mui.getDate_cal_to().getDay());
        if (start.getTime().after(end.getTime())) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid period set");
        }
        try {
            TreeMap<Date, Set<Task>> treeMap1 = (TreeMap<Date, Set<Task>>) Tasks.calendar(MainController.getList(), start.getTime(), end.getTime());
            for (Map.Entry<Date, Set<Task>> entry: treeMap1.entrySet()) {
                mui.getListC().add(entry.getKey().toString());
                for (Task task: entry.getValue()) {
                    mui.getListC().add(task.getTitle());
                }
            }
        } catch (CustomException e) {
            mc.getLogger().error("Error with showing calendar");
        }
    }
}
