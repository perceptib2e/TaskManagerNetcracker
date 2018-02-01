package com.symonenko.oleksandr.view;

import com.symonenko.oleksandr.controller.MainController;
import com.symonenko.oleksandr.controller.AppController;
import com.symonenko.oleksandr.controller.NotifyController;
import com.symonenko.oleksandr.eclipse.wb.swt.*;


import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import java.io.IOException;
import java.net.URISyntaxException;


public class MainUI {

    protected Shell shell;
    private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
    private Group grpTasks;
    private Group grpCalendar;
    private Button btnAdd;
    private Button btnEdit;
    private Button btnRemove;
    private Label lblFrom;
    private Label lblTo;
    private Label lblChooseTimePeriod;
    private DateTime date_cal_from;
    private DateTime date_cal_to;
    private Button btnShow;
    private MenuItem mntmEdit;
    private Label lblNewLabel;
    private Label lblWelcome;
    private Composite startField;
    private Button btnUnderstood;
    private Label lblAutorOleksandSymonenko;
    private Label lblNoRights;
    private Label lblCustomerNetcrackerJava;
    private Label lblNewLabel_1;
    private Label lblSumyUkraine;
    private Group grpProgramInfo;
    private Group grpAddEditTask;
    private Text text_title;
    private Text text_interval;
    private DateTime dateTime_task_date_1;
    private DateTime dateTime_task_time_1;
    private Button btnTaskIsActive;
    private Button btnTaskIsRepeating;
    private DateTime dateTime_task_date_2;
    private DateTime dateTime_task_time_2;
    private Button btnX;
    private Button btnAddEdit;
    private Label lblNewLabel_3;
    private Label lblNewLabel_4;
    private List listT;
    private List listC;
    private MenuItem mntmActions;
    private Label lblNewLabel_2;
    private Group grpErrorMesage;
    private Label lblerrTexthere;
    private Group grpNotificationMesage;
    private Label lblNotificationTexthere;

    private static MainController mc;
    private static AppController ac;

    private static MainUI mui = new MainUI();

    public static void Launch(MainController mainController) {
        mc = mainController;
        ac.setMui();
        try {
            mui.open();
        } catch (Exception e) {
            e.printStackTrace();
            mc.getLogger().error("Trouble with launching MainUI");
        }
    }

    public void setAc(AppController ac) {
        this.ac = ac;
    }

//    public void setNc(NotifyController nc){
//        this.nc = nc;
//    }

    /*open the window*/
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /* create window content */
    protected void createContents() {
        shell = new Shell();
        shell.setImage(SWTResourceManager.getImage(MainUI.class, "/images/main icon.png"));
        shell.setSize(new Point(650, 490));
        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        shell.setLocation(new Point(600, 300));
        shell.setMinimumSize(new Point(614, 486));
        shell.setSize(614, 486);
        shell.setText("Task Manager (NTC project)");
        shell.setLayout(new FormLayout());

        /* MENU */
        Menu menu = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menu);

        mntmActions = new MenuItem(menu, SWT.CASCADE);
        mntmActions.setText("actions");

        Menu menu_1 = new Menu(mntmActions);
        mntmActions.setMenu(menu_1);

        MenuItem mntmTaskList = new MenuItem(menu_1, SWT.CASCADE);
        mntmTaskList.setText("task list");
        mntmTaskList.setImage(SWTResourceManager.getImage(MainUI.class, "/images/list small.png"));

        Menu menu_3 = new Menu(mntmTaskList);
        mntmTaskList.setMenu(menu_3);

        MenuItem mntmShowTaskList = new MenuItem(menu_3, SWT.NONE);
        mntmShowTaskList.setImage(SWTResourceManager.getImage(MainUI.class, "/images/list small.png"));
        mntmShowTaskList.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.showList();
            }
        });
        mntmShowTaskList.setToolTipText("(actually)");
        mntmShowTaskList.setText("show");

        mntmEdit = new MenuItem(menu_3, SWT.NONE);
        mntmEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/images/edit list small.png"));
        mntmEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.showListAndButtons();
            }
        });
        mntmEdit.setText("edit");

        new MenuItem(menu_1, SWT.SEPARATOR);

        MenuItem mntmCalendar = new MenuItem(menu_1, SWT.NONE);
        mntmCalendar.setImage(SWTResourceManager.getImage(MainUI.class, "/images/calendar small.png"));
        mntmCalendar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.showCalendar();
            }
        });
        mntmCalendar.setToolTipText("shows list of tasks at the specified period ");
        mntmCalendar.setText("calendar");

        new MenuItem(menu_1, SWT.SEPARATOR);

        MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
        mntmExit.setImage(SWTResourceManager.getImage(MainUI.class, "/images/emergency-exit.png"));
        mntmExit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                mc.getLogger().info("***TASK MANAGER IS SHOOTING DOWN***");
                shell.dispose();
            }
        });
        mntmExit.setToolTipText("actually exit from this program");
        mntmExit.setText("exit");

        MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
        mntmHelp.setText("help");

        Menu menu_2 = new Menu(mntmHelp);
        mntmHelp.setMenu(menu_2);

        MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
        mntmAbout.setImage(SWTResourceManager.getImage(MainUI.class, "/images/about small.png"));
        mntmAbout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.showProgramInfo();
            }
        });
        mntmAbout.setText("about");

        MenuItem mntmMotivation = new MenuItem(menu_2, SWT.NONE);
        mntmMotivation.setImage(SWTResourceManager.getImage(MainUI.class, "/images/motivation.png"));
        mntmMotivation.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    ac.motivation();
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mntmMotivation.setText("motivation");

        /* ERROR MESSAGE FIELD GROUP */
        grpErrorMesage = new Group(shell, SWT.NONE);
        grpErrorMesage.setText("error message");
        grpErrorMesage.setLayout(null);
        FormData fd_grpErrorMesage = new FormData();
        fd_grpErrorMesage.height = 80;
        fd_grpErrorMesage.width = 260;
        fd_grpErrorMesage.top = new FormAttachment(0, 120);
        fd_grpErrorMesage.left = new FormAttachment(0, 165);
        grpErrorMesage.setLayoutData(fd_grpErrorMesage);
        formToolkit.adapt(grpErrorMesage);
        formToolkit.paintBordersFor(grpErrorMesage);

        Button btnErrOk = new Button(grpErrorMesage, SWT.NONE);
        btnErrOk.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.errorClose();
            }
        });
        btnErrOk.setBounds(130, 63, 75, 25);
        formToolkit.adapt(btnErrOk, true, true);
        btnErrOk.setText("ok");


        lblerrTexthere = new Label(grpErrorMesage, SWT.CENTER);
        lblerrTexthere.setAlignment(SWT.CENTER);
        lblerrTexthere.setBounds(76, 18, 180, 39);
        formToolkit.adapt(lblerrTexthere, true, true);
        lblerrTexthere.setText("texthere");

        Label lblErrorImage = new Label(grpErrorMesage, SWT.NONE);
        lblErrorImage.setImage(SWTResourceManager.getImage(MainUI.class, "/images/error big.png"));
        lblErrorImage.setBounds(10, 18, 64, 70);
        formToolkit.adapt(lblErrorImage, true, true);
        
        /* NOTIFICATION MESSAGE FIELD GROUP */
        grpNotificationMesage = new Group(shell, SWT.NONE);
        grpNotificationMesage.setText("notification");
        grpNotificationMesage.setLayout(null);
        FormData fd_grpNotificationMesage = new FormData();
        fd_grpNotificationMesage.height = 80;
        fd_grpNotificationMesage.width = 260;
        fd_grpNotificationMesage.top = new FormAttachment(0, 120);
        fd_grpNotificationMesage.left = new FormAttachment(0, 165);
        grpNotificationMesage.setLayoutData(fd_grpNotificationMesage);
        formToolkit.adapt(grpNotificationMesage);
        formToolkit.paintBordersFor(grpNotificationMesage);

        Button btnNotificationOk = new Button(grpNotificationMesage, SWT.NONE);
        btnNotificationOk.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.notificationClose();
            }
        });
        btnNotificationOk.setBounds(130, 63, 75, 25);
        formToolkit.adapt(btnNotificationOk, true, true);
        btnNotificationOk.setText("ok");

        lblNotificationTexthere = new Label(grpNotificationMesage, SWT.CENTER);
        lblNotificationTexthere.setAlignment(SWT.CENTER);
        lblNotificationTexthere.setBounds(76, 18, 180, 39);
        formToolkit.adapt(lblNotificationTexthere, true, true);
        lblNotificationTexthere.setText("texthere");

        Label lblNotificationImage = new Label(grpNotificationMesage, SWT.NONE);
        lblNotificationImage.setImage(SWTResourceManager.getImage(MainUI.class, "/images/notification big.png"));
        lblNotificationImage.setBounds(10, 18, 64, 70);
        formToolkit.adapt(lblNotificationImage, true, true);

        /* ABOUT FIELD GROUP */
        grpProgramInfo = new Group(shell, SWT.SHADOW_ETCHED_IN);
        grpProgramInfo.setLayout(null);
        FormData fd_grpProgramInfo = new FormData();
        fd_grpProgramInfo.left = new FormAttachment(0, 145);
        fd_grpProgramInfo.top = new FormAttachment(0, 150);
        fd_grpProgramInfo.height = 100;
        fd_grpProgramInfo.width = 300;
        grpProgramInfo.setLayoutData(fd_grpProgramInfo);
        grpProgramInfo.setText("program info:");
        formToolkit.adapt(grpProgramInfo);
        formToolkit.paintBordersFor(grpProgramInfo);

        btnUnderstood = new Button(grpProgramInfo, SWT.NONE);
        btnUnderstood.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                grpProgramInfo.setVisible(false);
            }
        });
        btnUnderstood.setBounds(209, 83, 87, 25);
        formToolkit.adapt(btnUnderstood, true, true);
        btnUnderstood.setText("understood");

        lblAutorOleksandSymonenko = new Label(grpProgramInfo, SWT.NONE);
        lblAutorOleksandSymonenko.setBounds(10, 21, 200, 15);
        formToolkit.adapt(lblAutorOleksandSymonenko, true, true);
        lblAutorOleksandSymonenko.setText("autor: Oleksandr Symonenko");

        lblNoRights = new Label(grpProgramInfo, SWT.NONE);
        lblNoRights.setBounds(10, 93, 200, 15);
        formToolkit.adapt(lblNoRights, true, true);
        lblNoRights.setText("2017-2018, no rights reserved");

        lblCustomerNetcrackerJava = new Label(grpProgramInfo, SWT.NONE);
        lblCustomerNetcrackerJava.setBounds(10, 62, 200, 15);
        formToolkit.adapt(lblCustomerNetcrackerJava, true, true);
        lblCustomerNetcrackerJava.setText("customer: Netcracker java course");

        lblNewLabel_1 = new Label(grpProgramInfo, SWT.NONE);
        lblNewLabel_1.setImage(SWTResourceManager.getImage(MainUI.class, "/images/ntc.png"));
        lblNewLabel_1.setBounds(209, 21, 87, 56);
        formToolkit.adapt(lblNewLabel_1, true, true);

        lblSumyUkraine = new Label(grpProgramInfo, SWT.NONE);
        lblSumyUkraine.setBounds(10, 42, 200, 15);
        formToolkit.adapt(lblSumyUkraine, true, true);
        lblSumyUkraine.setText("Sumy, Ukraine");

        /* START FIELD GROUP */
        startField = new Composite(shell, SWT.NONE);
        startField.setLayout(new FormLayout());
        FormData fd_startField = new FormData();
        fd_startField.height = 430;
        fd_startField.width = 600;
        startField.setLayoutData(fd_startField);
        formToolkit.adapt(startField);
        formToolkit.paintBordersFor(startField);

        lblNewLabel = new Label(startField, SWT.NONE);
        lblNewLabel.setImage(SWTResourceManager.getImage(MainUI.class, "/images/homer.jpg"));
        FormData fd_lblNewLabel = new FormData();
        fd_lblNewLabel.top = new FormAttachment(0, 99);
        fd_lblNewLabel.left = new FormAttachment(0, 122);
        lblNewLabel.setLayoutData(fd_lblNewLabel);
        formToolkit.adapt(lblNewLabel, true, true);

        lblWelcome = new Label(startField, SWT.NONE);
        FormData fd_lblWelcome = new FormData();
        fd_lblWelcome.top = new FormAttachment(0, 62);
        fd_lblWelcome.left = new FormAttachment(0, 263);
        lblWelcome.setLayoutData(fd_lblWelcome);
        formToolkit.adapt(lblWelcome, true, true);
        lblWelcome.setText("welcome!");

        /* CALENDAR FIELD GROUP */
        grpCalendar = new Group(shell, SWT.NONE);
        grpCalendar.setText("calendar");
        grpCalendar.setLayout(new FormLayout());
        FormData fd_grpCalendar = new FormData();
        fd_grpCalendar.bottom = new FormAttachment(0, 425);
        fd_grpCalendar.right = new FormAttachment(0, 591);
        fd_grpCalendar.top = new FormAttachment(0);
        fd_grpCalendar.left = new FormAttachment(0, 7);
        grpCalendar.setLayoutData(fd_grpCalendar);
        formToolkit.adapt(grpCalendar);
        formToolkit.paintBordersFor(grpCalendar);

        lblNewLabel_4 = new Label(grpCalendar, SWT.NONE);
        lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        lblNewLabel_4.setImage(SWTResourceManager.getImage(MainUI.class, "/images/calendar big.png"));
        FormData fd_lblNewLabel_4 = new FormData();
        fd_lblNewLabel_4.top = new FormAttachment(0, 300);
        fd_lblNewLabel_4.left = new FormAttachment(0, 480);
        lblNewLabel_4.setLayoutData(fd_lblNewLabel_4);
        formToolkit.adapt(lblNewLabel_4, true, true);

        lblFrom = new Label(grpCalendar, SWT.NONE);
        FormData fd_lblFrom = new FormData();
        lblFrom.setLayoutData(fd_lblFrom);
        formToolkit.adapt(lblFrom, true, true);
        lblFrom.setText("from:");

        lblTo = new Label(grpCalendar, SWT.NONE);
        FormData fd_lblTo = new FormData();
        fd_lblTo.right = new FormAttachment(lblFrom, 0, SWT.RIGHT);
        lblTo.setLayoutData(fd_lblTo);
        formToolkit.adapt(lblTo, true, true);
        lblTo.setText("to:");

        lblChooseTimePeriod = new Label(grpCalendar, SWT.NONE);
        fd_lblFrom.top = new FormAttachment(6, 9);
        FormData fd_lblChooseTimePeriod = new FormData();
        fd_lblChooseTimePeriod.top = new FormAttachment(0, 10);
        fd_lblChooseTimePeriod.right = new FormAttachment(100, -15);
        lblChooseTimePeriod.setLayoutData(fd_lblChooseTimePeriod);
        formToolkit.adapt(lblChooseTimePeriod, true, true);
        lblChooseTimePeriod.setText("choose time period:");

        /* list datepick "from" */
        date_cal_from = new DateTime(grpCalendar, SWT.BORDER);
        fd_lblFrom.right = new FormAttachment(date_cal_from, -6);
        FormData fd_dateTime = new FormData();
        fd_dateTime.top = new FormAttachment(lblChooseTimePeriod, 6);
        fd_dateTime.right = new FormAttachment(lblChooseTimePeriod, 0, SWT.RIGHT);
        date_cal_from.setLayoutData(fd_dateTime);
        formToolkit.adapt(date_cal_from);
        formToolkit.paintBordersFor(date_cal_from);

        /* list datepick "to" */
        date_cal_to = new DateTime(grpCalendar, SWT.BORDER);
        fd_lblTo.top = new FormAttachment(date_cal_to, 3, SWT.TOP);
        FormData fd_dateTime_1 = new FormData();
        fd_dateTime_1.top = new FormAttachment(date_cal_from, 6);
        fd_dateTime_1.right = new FormAttachment(lblChooseTimePeriod, 0, SWT.RIGHT);
        date_cal_to.setLayoutData(fd_dateTime_1);
        formToolkit.adapt(date_cal_to);
        formToolkit.paintBordersFor(date_cal_to);

        /* shows result task list in chosen period */
        btnShow = new Button(grpCalendar, SWT.NONE);
        btnShow.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.showCalendarWithData();
            }
        });
        FormData fd_btnShow = new FormData();
        fd_btnShow.width = 76;
        fd_btnShow.top = new FormAttachment(date_cal_to, 6);
        fd_btnShow.right = new FormAttachment(lblChooseTimePeriod, 0, SWT.RIGHT);
        btnShow.setLayoutData(fd_btnShow);
        formToolkit.adapt(btnShow, true, true);
        btnShow.setText("show");

        /* list_cal_1 table */
        listC = new List(grpCalendar, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        listC.setLayoutData(new FormData());
        formToolkit.adapt(listC, true, true);
        FormData fd_listC = new FormData();
        fd_listC.bottom = new FormAttachment(0, 400);
        fd_listC.top = new FormAttachment(0, 5);
        fd_listC.right = new FormAttachment(0, 571);
        fd_listC.left = new FormAttachment(0, 7);
        listC.setLayoutData(fd_listC);

        /* ADD TASK FIELD GROUP */
        grpAddEditTask = new Group(shell, SWT.NONE);
        grpAddEditTask.setText("add task");
        grpAddEditTask.setLayout(null);
        FormData fd_grpAddTask = new FormData();
        fd_grpAddTask.height = 195;
        fd_grpAddTask.width = 270;
        fd_grpAddTask.top = new FormAttachment(0, 24);
        fd_grpAddTask.left = new FormAttachment(0, 295);
        grpAddEditTask.setLayoutData(fd_grpAddTask);
        formToolkit.adapt(grpAddEditTask);
        formToolkit.paintBordersFor(grpAddEditTask);

        Label lblName = new Label(grpAddEditTask, SWT.NONE);
        lblName.setAlignment(SWT.RIGHT);
        lblName.setBounds(10, 23, 45, 15);
        formToolkit.adapt(lblName, true, true);
        lblName.setText("title:");

        Label lblTime = new Label(grpAddEditTask, SWT.NONE);
        lblTime.setAlignment(SWT.RIGHT);
        lblTime.setBounds(10, 47, 45, 15);
        formToolkit.adapt(lblTime, true, true);
        lblTime.setText("time:");

        text_title = new Text(grpAddEditTask, SWT.BORDER);
        text_title.setBounds(61, 20, 202, 21);
        formToolkit.adapt(text_title, true, true);

        dateTime_task_date_1 = new DateTime(grpAddEditTask, SWT.BORDER);
        dateTime_task_date_1.setBounds(61, 44, 76, 24);
        formToolkit.adapt(dateTime_task_date_1);
        formToolkit.paintBordersFor(dateTime_task_date_1);

        dateTime_task_time_1 = new DateTime(grpAddEditTask, SWT.BORDER | SWT.TIME | SWT.SHORT);
        dateTime_task_time_1.setBounds(143, 44, 50, 24);
        formToolkit.adapt(dateTime_task_time_1);
        formToolkit.paintBordersFor(dateTime_task_time_1);

        btnTaskIsActive = new Button(grpAddEditTask, SWT.CHECK);
        btnTaskIsActive.setBounds(61, 73, 93, 16);
        formToolkit.adapt(btnTaskIsActive, true, true);
        btnTaskIsActive.setText("task is active");

        btnTaskIsRepeating = new Button(grpAddEditTask, SWT.CHECK);
        btnTaskIsRepeating.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.repeatingCheck();
            }
        });
        btnTaskIsRepeating.setBounds(61, 92, 100, 16);
        formToolkit.adapt(btnTaskIsRepeating, true, true);
        btnTaskIsRepeating.setText("task is repeating");

        Label lblEvery = new Label(grpAddEditTask, SWT.NONE);
        lblEvery.setAlignment(SWT.RIGHT);
        lblEvery.setBounds(10, 116, 45, 15);
        formToolkit.adapt(lblEvery, true, true);
        lblEvery.setText("every");

        text_interval = new Text(grpAddEditTask, SWT.BORDER | SWT.CENTER);
        text_interval.setBounds(61, 113, 76, 21);
        formToolkit.adapt(text_interval, true, true);

        Label lblMinutes = new Label(grpAddEditTask, SWT.NONE);
        lblMinutes.setBounds(143, 116, 55, 15);
        formToolkit.adapt(lblMinutes, true, true);
        lblMinutes.setText("minutes");

        Label lblDue = new Label(grpAddEditTask, SWT.NONE);
        lblDue.setAlignment(SWT.RIGHT);
        lblDue.setBounds(10, 144, 45, 15);
        formToolkit.adapt(lblDue, true, true);
        lblDue.setText("due:");

        dateTime_task_date_2 = new DateTime(grpAddEditTask, SWT.BORDER);
        dateTime_task_date_2.setBounds(61, 140, 76, 24);
        formToolkit.adapt(dateTime_task_date_2);
        formToolkit.paintBordersFor(dateTime_task_date_2);

        dateTime_task_time_2 = new DateTime(grpAddEditTask, SWT.BORDER | SWT.TIME | SWT.SHORT);
        dateTime_task_time_2.setBounds(143, 140, 50, 24);
        formToolkit.adapt(dateTime_task_time_2);
        formToolkit.paintBordersFor(dateTime_task_time_2);

        btnX = new Button(grpAddEditTask, SWT.NONE);
        btnX.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.denyAddEdit();
            }
        });
        btnX.setBounds(60, 170, 30, 30);
        formToolkit.adapt(btnX, true, true);
        btnX.setText("X");

        btnAddEdit = new Button(grpAddEditTask, SWT.NONE);
        btnAddEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.addeditTaskConfirm();
            }
        });
        btnAddEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/images/add small.png"));
        btnAddEdit.setBounds(96, 170, 168, 30);
        formToolkit.adapt(btnAddEdit, true, true);
        btnAddEdit.setText("add");

        lblNewLabel_2 = new Label(grpAddEditTask, SWT.NONE);
        lblNewLabel_2.setImage(SWTResourceManager.getImage(MainUI.class, "/images/add big.png"));
        lblNewLabel_2.setBounds(199, 47, 68, 117);
        formToolkit.adapt(lblNewLabel_2, true, true);

        /* TASKLIST FIELD GROUP */
        grpTasks = new Group(shell, SWT.NONE);
        grpTasks.setText("tasks");
        grpTasks.setLayout(new FormLayout());
        FormData fd_grpTasks = new FormData();
        fd_grpTasks.bottom = new FormAttachment(0, 425);
        fd_grpTasks.right = new FormAttachment(0, 591);
        fd_grpTasks.top = new FormAttachment(0);
        fd_grpTasks.left = new FormAttachment(0, 7);
        grpTasks.setLayoutData(fd_grpTasks);
        formToolkit.adapt(grpTasks);
        formToolkit.paintBordersFor(grpTasks);

        lblNewLabel_3 = new Label(grpTasks, SWT.SHADOW_NONE);
        lblNewLabel_3.setImage(SWTResourceManager.getImage(MainUI.class, "/images/list big.png"));
        lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        FormData fd_lblNewLabel_3 = new FormData();
        fd_lblNewLabel_3.top = new FormAttachment(0, 300);
        fd_lblNewLabel_3.left = new FormAttachment(0, 480);
        lblNewLabel_3.setLayoutData(fd_lblNewLabel_3);
        formToolkit.adapt(lblNewLabel_3, true, true);

        /* list add/edit button. ADD */
        btnAdd = new Button(grpTasks, SWT.NONE);
        btnAdd.setAlignment(SWT.RIGHT);
        btnAdd.setImage(SWTResourceManager.getImage(MainUI.class, "/images/add small.png"));
        btnAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.addTask();
            }
        });
        FormData fd_btnAdd = new FormData();
        fd_btnAdd.right = new FormAttachment(0, 561);
        fd_btnAdd.top = new FormAttachment(0, 15);
        fd_btnAdd.left = new FormAttachment(0, 466);
        btnAdd.setLayoutData(fd_btnAdd);
        formToolkit.adapt(btnAdd, true, true);
        btnAdd.setText("add       ");

        /* list add/edit button. EDIT */
        btnEdit = new Button(grpTasks, SWT.NONE);
        btnEdit.setAlignment(SWT.RIGHT);
        btnEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/images/edit small.png"));
        btnEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.editTask();
            }
        });
        FormData fd_btnEdit = new FormData();
        fd_btnEdit.right = new FormAttachment(0, 561);
        fd_btnEdit.top = new FormAttachment(0, 57);
        fd_btnEdit.left = new FormAttachment(0, 466);
        btnEdit.setLayoutData(fd_btnEdit);
        formToolkit.adapt(btnEdit, true, true);
        btnEdit.setText("edit       ");

        // list button #3
        btnRemove = new Button(grpTasks, SWT.NONE);
        btnRemove.setAlignment(SWT.RIGHT);
        btnRemove.setImage(SWTResourceManager.getImage(MainUI.class, "/images/remove small.png"));
        btnRemove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ac.removeTask();
            }
        });
        FormData fd_btnRemove = new FormData();
        fd_btnRemove.left = new FormAttachment(0, 466);
        fd_btnRemove.right = new FormAttachment(0, 561);
        fd_btnRemove.top = new FormAttachment(0, 99);
        btnRemove.setLayoutData(fd_btnRemove);
        formToolkit.adapt(btnRemove, true, true);
        btnRemove.setText("remove");

        /* list table */
        listT = new List(grpTasks, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        FormData fd_listT = new FormData();
        fd_listT.bottom = new FormAttachment(0, 400);
        fd_listT.top = new FormAttachment(0, 5);
        fd_listT.right = new FormAttachment(0, 571);
        fd_listT.left = new FormAttachment(0, 7);
        listT.setLayoutData(fd_listT);
        formToolkit.adapt(listT, true, true);

        grpCalendar.setVisible(false);
        grpTasks.setVisible(false);
        grpProgramInfo.setVisible(false);
        grpAddEditTask.setVisible(false);
        grpErrorMesage.setVisible(false);
        mntmEdit.setEnabled(false);
        grpNotificationMesage.setVisible(false);
    }

    /* getters / setters */

    public static MainUI getMui() {
        return mui;
    }

    public Group getGrpTasks() {
        return grpTasks;
    }

    public Group getGrpCalendar() {
        return grpCalendar;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public DateTime getDate_cal_from() {
        return date_cal_from;
    }

    public DateTime getDate_cal_to() {
        return date_cal_to;
    }

    public MenuItem getMntmEdit() {
        return mntmEdit;
    }

    public Composite getStartField() {
        return startField;
    }

    public Group getGrpProgramInfo() {
        return grpProgramInfo;
    }

    public Group getGrpAddEditTask() {
        return grpAddEditTask;
    }

    public Text getText_title() {
        return text_title;
    }

    public Text getText_interval() {
        return text_interval;
    }

    public DateTime getDateTime_task_date_1() {
        return dateTime_task_date_1;
    }

    public DateTime getDateTime_task_time_1() {
        return dateTime_task_time_1;
    }

    public Button getBtnTaskIsActive() {
        return btnTaskIsActive;
    }

    public Button getBtnTaskIsRepeating() {
        return btnTaskIsRepeating;
    }

    public DateTime getDateTime_task_date_2() {
        return dateTime_task_date_2;
    }

    public DateTime getDateTime_task_time_2() {
        return dateTime_task_time_2;
    }

    public Button getBtnAddEdit() {
        return btnAddEdit;
    }

    public List getListT() {
        return listT;
    }

    public List getListC() {
        return listC;
    }

    public MenuItem getMntmActions() {
        return mntmActions;
    }

    public Label getLblNewLabel_2() {
        return lblNewLabel_2;
    }

    public Group getGrpErrorMesage() {
        return grpErrorMesage;
    }

    public Label getlblerrTexthere() {
        return lblerrTexthere;
    }

    public Group getGrpNotificationMesage() {
        return grpNotificationMesage;
    }

    public Label getLblNotificationTexthere() {
        return lblNotificationTexthere;
    }

    public void setLblNotificationTexthere(Label lblNotificationTexthere) {
        this.lblNotificationTexthere = lblNotificationTexthere;
    }

    public static AppController getAc() {
        return ac;
    }

    public Shell getShell() {
        return shell;
    }
}