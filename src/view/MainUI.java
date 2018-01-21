package view;

import controller.MainController;
import eclipse.wb.swt.SWTResourceManager;
import model.CustomExсeption;
import model.Task;
import model.Tasks;
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

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


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
    private Label lblTexthere;

    private final static Logger logger = Logger.getLogger(MainUI.class.getClass());

    public static void Launch() {
        try {
            MainUI window = new MainUI();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Trouble with launching MainUI");
        }
    }

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

    //create window content
    protected void createContents() {
        shell = new Shell();
        shell.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/main icon.png"));
        shell.setSize(new Point(650, 490));
        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        shell.setLocation(new Point(600, 300));
        shell.setMinimumSize(new Point(614, 486));
        shell.setSize(614, 486);
        shell.setText("Task Manager (NTC project)");
        shell.setLayout(new FormLayout());


        //////////////////////////////////////////////////////////////////////////////////
        // MENU
        //////////////////////////////////////////////////////////////////////////////////

        Menu menu = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menu);

        mntmActions = new MenuItem(menu, SWT.CASCADE);
        mntmActions.setText("actions");

        Menu menu_1 = new Menu(mntmActions);
        mntmActions.setMenu(menu_1);

        MenuItem mntmTaskList = new MenuItem(menu_1, SWT.CASCADE);
        mntmTaskList.setText("task list");
        mntmTaskList.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/list small.png"));

        Menu menu_3 = new Menu(mntmTaskList);
        mntmTaskList.setMenu(menu_3);



        MenuItem mntmShowTaskList = new MenuItem(menu_3, SWT.NONE);
        mntmShowTaskList.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/list small.png"));
        mntmShowTaskList.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                showList();
            }
        });
        mntmShowTaskList.setToolTipText("(actually)");
        mntmShowTaskList.setText("show");

        mntmEdit = new MenuItem(menu_3, SWT.NONE);
        mntmEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/edit list small.png"));
        mntmEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                showListAndButtons();
            }
        });
        mntmEdit.setText("edit");

        new MenuItem(menu_1, SWT.SEPARATOR);

        MenuItem mntmCalendar = new MenuItem(menu_1, SWT.NONE);
        mntmCalendar.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/calendar small.png"));
        mntmCalendar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                showCalendar();
            }
        });
        mntmCalendar.setToolTipText("shows list of tasks at the specified period ");
        mntmCalendar.setText("calendar");

        new MenuItem(menu_1, SWT.SEPARATOR);

        MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
        mntmExit.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/emergency-exit.png"));
        mntmExit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                logger.info("***TASK MANAGER IS SHOOTING DOWN***");
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
        mntmAbout.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/about small.png"));
        mntmAbout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                grpProgramInfo.setVisible(true);
            }
        });
        mntmAbout.setText("about");

        MenuItem mntmMotivation = new MenuItem(menu_2, SWT.NONE);
        mntmMotivation.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/motivation.png"));
        mntmMotivation.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    motivation();
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mntmMotivation.setText("motivation");

        //////////////////////////////////////////////////////////////////////////////////
        // ERROR MESSAGE FIELD GROUP
        //////////////////////////////////////////////////////////////////////////////////

        grpErrorMesage = new Group(shell, SWT.NONE);
        grpErrorMesage.setText("error mesage");
        grpErrorMesage.setLayout(null);
        FormData fd_grpErrorMesage = new FormData();
        fd_grpErrorMesage.height = 80;
        fd_grpErrorMesage.width = 260;
        fd_grpErrorMesage.top = new FormAttachment(0, 120);
        fd_grpErrorMesage.left = new FormAttachment(0, 165);
        grpErrorMesage.setLayoutData(fd_grpErrorMesage);
        formToolkit.adapt(grpErrorMesage);
        formToolkit.paintBordersFor(grpErrorMesage);

        Button btnOk = new Button(grpErrorMesage, SWT.NONE);
        btnOk.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                errorClose();
            }
        });
        btnOk.setBounds(130, 63, 75, 25);
        formToolkit.adapt(btnOk, true, true);
        btnOk.setText("ok");

        lblTexthere = new Label(grpErrorMesage, SWT.CENTER);
        lblTexthere.setAlignment(SWT.CENTER);
        lblTexthere.setBounds(76, 18, 180, 39);
        formToolkit.adapt(lblTexthere, true, true);
        lblTexthere.setText("texthere");

        Label lblErrorImage = new Label(grpErrorMesage, SWT.NONE);
        lblErrorImage.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/error big.png"));
        lblErrorImage.setBounds(10, 18, 64, 70);
        formToolkit.adapt(lblErrorImage, true, true);

        //////////////////////////////////////////////////////////////////////////////////
        // ABOUT FIELD GROUP
        //////////////////////////////////////////////////////////////////////////////////

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
        lblAutorOleksandSymonenko.setText("autor: Oleksand Symonenko");

        lblNoRights = new Label(grpProgramInfo, SWT.NONE);
        lblNoRights.setBounds(10, 93, 200, 15);
        formToolkit.adapt(lblNoRights, true, true);
        lblNoRights.setText("2017-2018, no rights reserved");

        lblCustomerNetcrackerJava = new Label(grpProgramInfo, SWT.NONE);
        lblCustomerNetcrackerJava.setBounds(10, 62, 200, 15);
        formToolkit.adapt(lblCustomerNetcrackerJava, true, true);
        lblCustomerNetcrackerJava.setText("customer: Netcracker java course");

        lblNewLabel_1 = new Label(grpProgramInfo, SWT.NONE);
        lblNewLabel_1.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/ntc.png"));
        lblNewLabel_1.setBounds(209, 21, 87, 56);
        formToolkit.adapt(lblNewLabel_1, true, true);

        lblSumyUkraine = new Label(grpProgramInfo, SWT.NONE);
        lblSumyUkraine.setBounds(10, 42, 200, 15);
        formToolkit.adapt(lblSumyUkraine, true, true);
        lblSumyUkraine.setText("Sumy, Ukraine");

        //////////////////////////////////////////////////////////////////////////////////
        // START FIELD GROUP
        //////////////////////////////////////////////////////////////////////////////////

        startField = new Composite(shell, SWT.NONE);
        startField.setLayout(new FormLayout());
        FormData fd_startField = new FormData();
        fd_startField.height = 430;
        fd_startField.width = 600;
        startField.setLayoutData(fd_startField);
        formToolkit.adapt(startField);
        formToolkit.paintBordersFor(startField);

        lblNewLabel = new Label(startField, SWT.NONE);
        lblNewLabel.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/homer.jpg"));
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

        //////////////////////////////////////////////////////////////////////////////////
        // CALENDAR FIELD GROUP
        //////////////////////////////////////////////////////////////////////////////////

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
        lblNewLabel_4.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/calendar big.png"));
        FormData fd_lblNewLabel_4 = new FormData();
        fd_lblNewLabel_4.top = new FormAttachment(0, 300);
        fd_lblNewLabel_4.left = new FormAttachment(0, 480);
        lblNewLabel_4.setLayoutData(fd_lblNewLabel_4);
        formToolkit.adapt(lblNewLabel_4, true, true);

        // list_cal_1 label #1
        lblFrom = new Label(grpCalendar, SWT.NONE);
        FormData fd_lblFrom = new FormData();
        lblFrom.setLayoutData(fd_lblFrom);
        formToolkit.adapt(lblFrom, true, true);
        lblFrom.setText("from:");

        // list_cal_1 label #2
        lblTo = new Label(grpCalendar, SWT.NONE);
        FormData fd_lblTo = new FormData();
        fd_lblTo.right = new FormAttachment(lblFrom, 0, SWT.RIGHT);
        lblTo.setLayoutData(fd_lblTo);
        formToolkit.adapt(lblTo, true, true);
        lblTo.setText("to:");

        // list_cal_1 label #3
        lblChooseTimePeriod = new Label(grpCalendar, SWT.NONE);
        fd_lblFrom.top = new FormAttachment(6, 9);
        FormData fd_lblChooseTimePeriod = new FormData();
        fd_lblChooseTimePeriod.top = new FormAttachment(0, 10);
        fd_lblChooseTimePeriod.right = new FormAttachment(100, -15);
        lblChooseTimePeriod.setLayoutData(fd_lblChooseTimePeriod);
        formToolkit.adapt(lblChooseTimePeriod, true, true);
        lblChooseTimePeriod.setText("choose time period:");

        // list_cal_1 datepick #1
        date_cal_from = new DateTime(grpCalendar, SWT.BORDER);
        fd_lblFrom.right = new FormAttachment(date_cal_from, -6);
        FormData fd_dateTime = new FormData();
        fd_dateTime.top = new FormAttachment(lblChooseTimePeriod, 6);
        fd_dateTime.right = new FormAttachment(lblChooseTimePeriod, 0, SWT.RIGHT);
        date_cal_from.setLayoutData(fd_dateTime);
        formToolkit.adapt(date_cal_from);
        formToolkit.paintBordersFor(date_cal_from);

        // list_cal_1 datepick #2
        date_cal_to = new DateTime(grpCalendar, SWT.BORDER);
        fd_lblTo.top = new FormAttachment(date_cal_to, 3, SWT.TOP);
        FormData fd_dateTime_1 = new FormData();
        fd_dateTime_1.top = new FormAttachment(date_cal_from, 6);
        fd_dateTime_1.right = new FormAttachment(lblChooseTimePeriod, 0, SWT.RIGHT);
        date_cal_to.setLayoutData(fd_dateTime_1);
        formToolkit.adapt(date_cal_to);
        formToolkit.paintBordersFor(date_cal_to);

        // list_cal_1 button #1
        btnShow = new Button(grpCalendar, SWT.NONE);
        btnShow.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                showCalendarWithData();
            }
        });
        FormData fd_btnShow = new FormData();
        fd_btnShow.width = 76;
        fd_btnShow.top = new FormAttachment(date_cal_to, 6);
        fd_btnShow.right = new FormAttachment(lblChooseTimePeriod, 0, SWT.RIGHT);
        btnShow.setLayoutData(fd_btnShow);
        formToolkit.adapt(btnShow, true, true);
        btnShow.setText("show");

        // list_cal_1 table
        listC = new List(grpCalendar, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        listC.setLayoutData(new FormData());
        formToolkit.adapt(listC, true, true);
        FormData fd_listC = new FormData();
        fd_listC.bottom = new FormAttachment(0, 400);
        fd_listC.top = new FormAttachment(0, 5);
        fd_listC.right = new FormAttachment(0, 571);
        fd_listC.left = new FormAttachment(0, 7);
        listC.setLayoutData(fd_listC);

        //////////////////////////////////////////////////////////////////////////////////
        // ADD TASK FIELD GROUP
        //////////////////////////////////////////////////////////////////////////////////

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
                repeatingCheck();
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
                denyAddEdit();
            }
        });
        btnX.setBounds(60, 170, 30, 30);
        formToolkit.adapt(btnX, true, true);
        btnX.setText("X");

        btnAddEdit = new Button(grpAddEditTask, SWT.NONE);
        btnAddEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addeditTaskConfirm();
            }
        });
        btnAddEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/add small.png"));
        btnAddEdit.setBounds(96, 170, 168, 30);
        formToolkit.adapt(btnAddEdit, true, true);
        btnAddEdit.setText("add");

        lblNewLabel_2 = new Label(grpAddEditTask, SWT.NONE);
        lblNewLabel_2.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/add big.png"));
        lblNewLabel_2.setBounds(199, 47, 68, 117);
        formToolkit.adapt(lblNewLabel_2, true, true);

        //////////////////////////////////////////////////////////////////////////////////
        // TASKLIST FIELD GROUP
        //////////////////////////////////////////////////////////////////////////////////

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
        lblNewLabel_3.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/list big.png"));
        lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
        FormData fd_lblNewLabel_3 = new FormData();
        fd_lblNewLabel_3.top = new FormAttachment(0, 300);
        fd_lblNewLabel_3.left = new FormAttachment(0, 480);
        lblNewLabel_3.setLayoutData(fd_lblNewLabel_3);
        formToolkit.adapt(lblNewLabel_3, true, true);

        // list add button #1
        btnAdd = new Button(grpTasks, SWT.NONE);
        btnAdd.setAlignment(SWT.RIGHT);
        btnAdd.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/add small.png"));
        btnAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addTask();
                text_interval.setEnabled(false);
                dateTime_task_date_2.setEnabled(false);
                dateTime_task_time_2.setEnabled(false);
            }
        });
        FormData fd_btnAdd = new FormData();
        fd_btnAdd.right = new FormAttachment(0, 561);
        fd_btnAdd.top = new FormAttachment(0, 15);
        fd_btnAdd.left = new FormAttachment(0, 466);
        btnAdd.setLayoutData(fd_btnAdd);
        formToolkit.adapt(btnAdd, true, true);
        btnAdd.setText("add       ");

        // list add button #2
        btnEdit = new Button(grpTasks, SWT.NONE);
        btnEdit.setAlignment(SWT.RIGHT);
        btnEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/edit small.png"));
        btnEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                editTask();
                text_interval.setEnabled(false);
                dateTime_task_date_2.setEnabled(false);
                dateTime_task_time_2.setEnabled(false);
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
        btnRemove.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/remove small.png"));
        btnRemove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                removeTask();
            }
        });
        FormData fd_btnRemove = new FormData();
        fd_btnRemove.left = new FormAttachment(0, 466);
        fd_btnRemove.right = new FormAttachment(0, 561);
        fd_btnRemove.top = new FormAttachment(0, 99);
        btnRemove.setLayoutData(fd_btnRemove);
        formToolkit.adapt(btnRemove, true, true);
        btnRemove.setText("remove");

        // list table
        listT = new List(grpTasks, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        FormData fd_listT = new FormData();
        fd_listT.bottom = new FormAttachment(0, 400);
        fd_listT.top = new FormAttachment(0, 5);
        fd_listT.right = new FormAttachment(0, 571);
        fd_listT.left = new FormAttachment(0, 7);
        listT.setLayoutData(fd_listT);
        formToolkit.adapt(listT, true, true);

        //////////////////////////////////////////////////////////////////////////////////
        // LAUNCH PREPARING
        //////////////////////////////////////////////////////////////////////////////////

        // hide calendar
        grpCalendar.setVisible(false);
        grpTasks.setVisible(false);
        grpProgramInfo.setVisible(false);
        grpAddEditTask.setVisible(false);
        grpErrorMesage.setVisible(false);
        mntmEdit.setEnabled(false);
    }

    //////////////////////////////////////////////////////////////////////////////////
    // CUSTOM METHODS
    //////////////////////////////////////////////////////////////////////////////////

    //****************************************************************************
    // methods for menu
    //****************************************************************************

    // list view, without buttons
    public void showList() {
        startField.setVisible(false);
        listT.removeAll();
        for (int i = 0; i < MainController.getList().size(); i++){
            listT.add(MainController.getList().getTask(i).toString());
        }
        listT.setEnabled(true);
        if (listT.getVisible() == true){
            grpCalendar.setVisible(false);
            grpTasks.setVisible(true);
            btnAdd.setVisible(false);
            btnEdit.setVisible(false);
            btnRemove.setVisible(false);
        }

        mntmEdit.setEnabled(true);
    }

    // list view, with buttons
    public void showListAndButtons() {
        btnAdd.setVisible(true);
        btnEdit.setVisible(true);
        btnRemove.setVisible(true);
    }

    // calendar view, with button
    public void showCalendar() {
        startField.setVisible(false);
        grpTasks.setVisible(false);
        grpCalendar.setVisible(true);
        mntmEdit.setEnabled(false);
    }

    // program info view (when press "about")
    public void showProgramInfo() {
        grpProgramInfo.setVisible(true);
    }

    // motivation joke
    public void motivation() throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://youtu.be/6H2FRxvsd2M"));
    }

    //****************************************************************************
    // methods for task list
    //****************************************************************************

    // shows add task field
    public void addTask() {
        grpAddEditTask.setVisible(true);
        grpAddEditTask.setText("add task");
        btnAddEdit.setText("add");
        btnAddEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/add small.png"));
        lblNewLabel_2.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/add big.png"));
        lblNewLabel_2.setBounds(199, 47, 68, 117);
        listT.setEnabled(false);
        mntmActions.setEnabled(false);
    }

    // shows edit task field
    public void editTask() {
        if (listT.getSelectionCount() > 0) {
            grpAddEditTask.setVisible(true);
            grpAddEditTask.setText("edit task");
            btnAddEdit.setText("edit");
            btnAddEdit.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/edit small.png"));
            lblNewLabel_2.setImage(SWTResourceManager.getImage(MainUI.class, "/eclipse/wb/swt/edit big.png"));
            lblNewLabel_2.setBounds(202, 47, 68, 117);
            listT.setEnabled(false);
            mntmActions.setEnabled(false);

        }
        else {
            error("choose task you want to edit");
            listT.setEnabled(false);
        }
    }

    // check for entering repeating or non-repeating task
    public void repeatingCheck() {
        if(!text_interval.isEnabled()) {
            text_interval.setEnabled(true);
            dateTime_task_date_2.setEnabled(true);
            dateTime_task_time_2.setEnabled(true);
        }
        else {
            text_interval.setEnabled(false);
            dateTime_task_date_2.setEnabled(false);
            dateTime_task_time_2.setEnabled(false);
        }
    }

    // add or edit task confirm
    public void addeditTaskConfirm(){
        if(text_title.getText().isEmpty() && btnTaskIsRepeating.getSelection()) {
            error("fill all required fields");
        }
        if(text_title.getText().isEmpty() || btnTaskIsRepeating.getSelection() && text_interval.getText().isEmpty()) {
            error("fill all required fields");
        }
        else {
            addOrEditTask();
            grpTasks.setEnabled(true);
        }
    }

    // exit from add or edit task field without changes
    public void denyAddEdit() {
        grpAddEditTask.setVisible(false);
        grpTasks.setEnabled(true);
        mntmActions.setEnabled(true);
        btnTaskIsRepeating.setSelection(false);
        text_title.setText("");
        text_interval.setText("");
    }

    // remove chosen task
    public void removeTask() {
        if (listT.getSelectionCount() > 0) {
            MainController.getList().remove(MainController.getList().getTask(listT.getSelectionIndex()));
            MainController.saveList();
            showList();
            showListAndButtons();
        }
        else {
            error("choose task you want to remove");
            listT.setEnabled(false);
        }
    }


    //****************************************************************************
    // methods for error pop-up message
    //****************************************************************************

    //view of error message
    public void error(String string) {
        lblTexthere.setText(string);
        grpErrorMesage.setVisible(true);
        //1. if error from add/edit field
        if (grpAddEditTask.isVisible()) {
            grpAddEditTask.setEnabled(false);
            grpTasks.setEnabled(false);
        }
        //2. if error from list buttons
        if (grpTasks.isVisible() && !grpAddEditTask.isVisible()) grpTasks.setEnabled(false);
        //3. if error from calendar field
        if (grpCalendar.isVisible()) grpCalendar.setEnabled(false);

    }

    //hide error message
    public void errorClose() {
        grpErrorMesage.setVisible(false);
        //1. if error from add/edit field
        if (grpAddEditTask.isVisible()) {
            grpAddEditTask.setEnabled(true);
        }
        //2. if error from list buttons
        if (grpTasks.isVisible() && !grpAddEditTask.isVisible()) {
            grpTasks.setEnabled(true);
            listT.setEnabled(true);
        }
        //3. if error from calendar field
        if (grpCalendar.isVisible()) grpCalendar.setEnabled(true);
    }

    //****************************************************************************
    // methods for task list adding / editing tasks
    //****************************************************************************

    //adding task to tasklist or editing task in tasklist
    public void addOrEditTask() {
        Task tempTask = null;
        int interval = -1;
        String name = text_title.getText();
        Calendar list_cal_1 = Calendar.getInstance();
        list_cal_1.setTime(new Date(0));
        list_cal_1.set(Calendar.YEAR, dateTime_task_date_1.getYear());
        list_cal_1.set(Calendar.MONTH, dateTime_task_date_1.getMonth());
        list_cal_1.set(Calendar.DAY_OF_MONTH, dateTime_task_date_1.getDay());
        list_cal_1.set(Calendar.HOUR_OF_DAY, dateTime_task_time_1.getHours());
        list_cal_1.set(Calendar.MINUTE, dateTime_task_time_1.getMinutes());
        long startLong = list_cal_1.getTimeInMillis();
        boolean activityOfTask = btnTaskIsActive.getSelection();
        // if task is not repeatable
        if (btnTaskIsRepeating.getSelection() == false) {
            try {
                tempTask = new Task(name, new Date(startLong), activityOfTask);

            } catch (Exception e) {
                error("Cannot add/edit non repeating task");
                logger.error("Cannot add/edit non repeating task");
            }
        }
        // if task is repeatable
        else {
            try {
                interval = Integer.parseInt(text_interval.getText()) * 60;
                Calendar list_cal_2 = Calendar.getInstance();
                list_cal_2.setTime(new Date(0));
                list_cal_2.set(Calendar.YEAR, dateTime_task_date_2.getYear());
                list_cal_2.set(Calendar.MONTH, dateTime_task_date_2.getMonth());
                list_cal_2.set(Calendar.DAY_OF_MONTH, dateTime_task_date_2.getDay());
                list_cal_2.set(Calendar.HOUR_OF_DAY, dateTime_task_time_2.getHours());
                list_cal_2.set(Calendar.MINUTE, dateTime_task_time_2.getMinutes());
                long endLong = list_cal_2.getTimeInMillis();
                tempTask = new Task(name, new Date(startLong), new Date(endLong), interval, activityOfTask);
            } catch (Exception e) {
                error("Cannot add/edit repeating task");
                logger.error("Cannot add/edit repeating task");
            }
        }
        // action with task
        if (grpAddEditTask.getText().equals("add task")){
            MainController.getList().add(tempTask);
            MainController.saveList();
        }
        else {
            Task deltask = MainController.getList().getTask(listT.getSelectionIndex());
            MainController.getList().overwriteTask(deltask, tempTask);
            MainController.saveList();
        }
        // view changes after successful adding/editing task
        showList();
        showListAndButtons();
        listT.setEnabled(true);
        text_interval.setEnabled(false);
        btnTaskIsRepeating.setSelection(false);
        text_title.setText("");
        text_interval.setText("");
        grpAddEditTask.setVisible(false);
        mntmActions.setEnabled(true);
    }

    //****************************************************************************
    // methods for task list adding / editing tasks
    //****************************************************************************

    //displays tasks of tasklist in time they must appear
    public void showCalendarWithData() {
        listC.removeAll();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(Calendar.YEAR, date_cal_from.getYear());
        start.set(Calendar.MONTH, date_cal_from.getMonth());
        start.set(Calendar.DAY_OF_MONTH, date_cal_from.getDay());
        end.set(Calendar.YEAR, date_cal_to.getYear());
        end.set(Calendar.MONTH, date_cal_to.getMonth());
        end.set(Calendar.DAY_OF_MONTH, date_cal_to.getDay());
        if (start.getTime().after(end.getTime())) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid period set");
        }
        try {
            TreeMap<Date, Set<Task>> treeMap1 = (TreeMap<Date, Set<Task>>) Tasks.calendar(MainController.getList(), start.getTime(), end.getTime());
            for (Map.Entry<Date, Set<Task>> entry: treeMap1.entrySet()) {
                listC.add(entry.getKey().toString());
                for (Task task: entry.getValue()) {
                    listC.add(task.getTitle());
                }
            }
        } catch (CustomExсeption e) {
            logger.error("Error with showing calendar");
        }
    }
}