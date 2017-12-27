package symonenko.alexandr;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskIO {

    /* writing tasks FROM LIST TO binary STREAM */
    public static void write (TaskList tasks, OutputStream out) throws IOException{
        try
                (DataOutputStream dataStreamOut = new DataOutputStream(out)) {
            //согласно практической работе №6, задания №2:
            //1. пишем количество задач
            dataStreamOut.writeInt(tasks.size());
            //2. для всего списка задач пишем:
            for (Task task : tasks){
                //2.1 длину названия
                dataStreamOut.writeInt(task.getTitle().length());
                //2.2 название
                dataStreamOut.writeUTF(task.getTitle());
                //2.3 активность
                dataStreamOut.writeBoolean(task.isActive());
                //2.4 интервал повторения
                dataStreamOut.writeInt(task.getRepeatInterval());
                //3. если повторяется/неповторяется
                if (task.isRepeated()){
                    //3.1 повторяется - время начала, время окончания
                    dataStreamOut.writeLong(task.getStartTime().getTime());
                    dataStreamOut.writeLong(task.getEndTime().getTime());
                }
                //3.2 не повторяется - время задачи
                else dataStreamOut.writeLong(task.getTime().getTime());
            }
        }
    }

    /* reading tasks FROM binary STREAM TO LIST */
    public static void read (TaskList tasks, InputStream in) throws Exception{
        //аналогично к write
        try (DataInputStream dataStreamIn = new DataInputStream(in)){
            //считываем количество задач в списке
            int size = dataStreamIn.readInt();
            //для каждой считываем её поля...
            for (int i = 0; i < size; i++){
                int lenght = dataStreamIn.readInt();
                String title = dataStreamIn.readUTF();
                boolean active = dataStreamIn.readBoolean();
                int interval = dataStreamIn.readInt();
                //повторяющаяся... неповторяющаяся...
                long timeOrStart = dataStreamIn.readLong();
                //создаём задачу и добавляем в список
                if (interval > 0){
                    long end = dataStreamIn.readLong();
                    Task task = new Task(title, new Date(timeOrStart), new Date(end), interval, active);
                    task.setActive(active);
                    tasks.add(task);
                }
                else {
                    Task task = new Task(title, new Date(timeOrStart),active);
                    task.setActive(active);
                    tasks.add(task);
                }
            }
        }
    }

    /* writing tasks FROM LIST TO FILE */
    public static void writeBinary (TaskList tasks, File file) throws IOException{
        try (BufferedOutputStream dataOutStream = new BufferedOutputStream(new FileOutputStream(file))){
            write(tasks, dataOutStream);
        }
    }

    /* reading tasks FROM FILE TO LIST */
    public static void readBinary (TaskList tasks, File file) throws Exception{
        try (BufferedInputStream dataInnStream = new BufferedInputStream(new FileInputStream(file))){
            read(tasks,dataInnStream);
        }
    }

    /* writing tasks FROM LIST TO binary STREAM (IN TEXT FORMAT) */
    public static void write(TaskList tasks, Writer out) throws IOException {
        try (BufferedWriter dataOut = new BufferedWriter(out)) {
            int count = 0;
            for (Task task : tasks) {
                dataOut.append(task.toString());
                //после каждой задачи точка с запятой, после последтей - точка
                dataOut.append(count < tasks.size()-1 ? ";" : ".");
                //каждая задача на отдельном рядке
                dataOut.newLine();
                count++;
            }
        }
    }

    /* reading tasks FROM binary STREAM TO LIST (IN TEXT FORMAT) */
    public static void read(TaskList tasks, Reader in) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("[y-MM-dd HH:mm:ss.S]");
        BufferedReader dataIn = new BufferedReader(in);
        String line = "";
        while ((line = dataIn.readLine()) != null) {
            int start = line.indexOf('\"');
            int finish = line.lastIndexOf('\"');
            String title = line.substring(start + 1, finish);
            boolean active = !line.contains("inactive");

            if (line.contains(" at [")) {
                String stringDate = line.substring(line.indexOf("["), line.indexOf("]") + 1);
                Date dateStart = dateFormat.parse(stringDate);
                Task task = new Task(title, new Date(dateStart.getTime()));
                task.setActive(active);
                tasks.add(task);
            } else {
                String stringStart = line.substring(line.indexOf("["), line.indexOf("]") + 1);
                Date dateStart = dateFormat.parse(stringStart);
                line = line.substring(line.indexOf("]") + 1);
                String stringEnd = line.substring(line.indexOf("["), line.indexOf("]") + 1);
                Date dateEnd = dateFormat.parse(stringEnd);
                line = line.substring(line.indexOf("]") + 1);
                String interval = line.substring(line.lastIndexOf("[", line.indexOf("[") + 1) + 1,
                        line.lastIndexOf("]", line.indexOf("]") + 1));
                String[] forInterval = interval.split(" ");
                long days;
                long hours;
                long minutes;
                long seconds;
                int theInterval = 0;

                switch (forInterval.length) {
                    case 4:
                        minutes = Long.parseLong(forInterval[0]);
                        seconds = Long.parseLong(forInterval[2]);
                        theInterval = (int) (seconds * 1000 + minutes * 60 * 1000) / 1000;
                        break;
                    case 6:
                        hours = Long.parseLong(forInterval[0]);
                        minutes = Long.parseLong(forInterval[2]);
                        seconds = Long.parseLong(forInterval[4]);
                        theInterval = (int) (seconds * 1000 + minutes * 60 * 1000 + hours * 3600 * 1000) / 1000;
                        break;
                    case 8:
                        days = Long.parseLong(forInterval[0]);
                        hours = Long.parseLong(forInterval[2]);
                        minutes = Long.parseLong(forInterval[4]);
                        seconds = Long.parseLong(forInterval[6]);
                        theInterval = (int) (seconds * 1000 + minutes * 60 * 1000 + hours * 3600 * 1000 + days * 8640 * 1000) / 1000;
                        break;
                }
                Task task = new Task(title, new Date(dateStart.getTime()), new Date(dateEnd.getTime()), theInterval);
                task.setActive(active);
                tasks.add(task);
            }
        }
    }

    /* writing tasks FROM LIST TO FILE (IN TEXT FORMAT) */
    public static void writeText (TaskList tasks, File file) throws IOException{
        try (PrintWriter textDataOut = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
            write(tasks,textDataOut);
        }
    }

    /* reading tasks FROM FILE TO LIST (IN TEXT FORMAT) */
    public static void readText (TaskList tasks, File file) throws Exception{
        try (BufferedReader textDataIn = new BufferedReader(new FileReader(file))){
            read(tasks, textDataIn);
        }
    }
}