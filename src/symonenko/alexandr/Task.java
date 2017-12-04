package symonenko.alexandr;

public class Task {
    private String title = "T A S K";
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private int current;

    public void checkTime () throws CustomExeption{
        if (time < 0 || start < 0 || end < 0 || interval < 0)throw new CustomExeption("Time cannot be of negative value.");
    }

    public Task(String title, int time) throws CustomExeption {
        this.title = title;
        this.time = time;
        checkTime();
    }

    public Task (String title, int time, boolean active) throws CustomExeption{
        this.title = title;
        this.time = time;
        this.active = active;
        checkTime();
    }

    public Task(String title, int start, int end, int interval) throws CustomExeption {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        checkTime();
    }

    public Task(String title, int start, int end, int interval, boolean active) throws CustomExeption {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = active;
        checkTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        return interval > 0 ? start : time;
    }

    public void setTime(int time) {
        this.time = time;
        this.interval = 0;
    }

    public int getStartTime() {
        if (interval > 0) {
            return start;
        } else {
            return time;
        }
    }

    public int getEndTime() {
        return interval > 0 ? end : time;
    }

    public int getRepeatInterval() {
//если задача не повторяется, вывести "0"
        return interval;
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return interval > 0;
    }

    public int nextTimeAfter(int current) {
//1. если задача неактивна - возвращаем сразу -1
        if (!active) {
            return -1;
        }
//2. если конец повторяющейся задачи меньше заданного времени - сразу -1
        if (current > end & interval > 0) {
            return -1;
        }
//3. если время неповторяющейся задачи меньше заданного - сразу -1
//4. вовзращаем время для неповторяющейся задачи
        if (time > 0) {
            return current < time ? time : -1;
        }
//5. высчитываем время для повторяющейся

        for (int x = start; x <= end; x = x + interval) {
            if (x > current) return x;
        }

        return -1;
    }

    @Override
    public boolean equals(Object object){
        if (!this.getClass().equals(object.getClass()))
            return false;
        if (this == object) {
            return true;
        }
        if (this.getClass().equals(object.getClass()))
            return (this.title.equals(object.title) && this.time == object.time && this.start == object.start && this.end == object.end && this.interval == object.interval);
        else return false;
    }

    @Override
    public int hashCode() {
        int code;
        code = 31* (title.hashCode() + time + start + end + interval);
        return code;
    }

    @Override
    public String toString() {
        String str = "";
        if (interval == 0) {
            str = "taskinfo: title - \"" + title + "\", time - " + time;
            if (isActive()) str = str + " (active).";
            else str = str + "(not active).";
        }
        if (interval != 0){
            str = "taskinfo: title - \"" + title + "\", start - " + start + ", end - " + end + ", interval - " + interval;
            if (isActive()) str = str + " (active).";
            else str = str + "(not active).";
        }
        return str;
    }
}