package symonenko.alexandr;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Serializable;

public class Task implements Cloneable, Serializable{
    private String title = "T A S K";
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;

    /* constructors */
    public Task(String title, Date time) throws CustomExсeption {
        this.title = title;
        this.time = time;
        checkTime();
    }

    public Task(String title, Date time, boolean active) throws CustomExсeption{
        this.title = title;
        this.time = time;
        this.active = active;
        checkTime();
    }

    public Task(String title, Date start, Date end, int interval) throws CustomExсeption {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        checkTime();
    }

    public Task(String title, Date start, Date end, int interval, boolean active) throws CustomExсeption {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = active;
        checkTime();
    }

    public void checkTime() throws CustomExсeption{

        //for non-repetitive tasks
        if (this.interval == 0){
            if (this.time.compareTo(new Date(0)) < 0){
                throw new CustomExсeption("Time cannot be < 0");
            }
        }
        //for repetitive tasks
        if (this.interval != 0){
            if (this.start.before(new Date(0)) || this.end.before(new Date(0)) || this.interval < 0){
                throw new CustomExсeption("Time cannot be < 0");
            }
        }
    }

    /* getters and setters */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /* returning time or starting time (if task is repeatable) #1 */
    public Date getTime() {
        return (this.interval != 0) ? start : time;
    }

    /* setting time, and canceling repeating (if it was) */
    public void setTime(Date time) {
        if (this.interval != 0) {
            this.time = time;
            this.interval = 0;
            this.start = null;
            this.end = null;
        } else {
            this.time = time;
            this.start = null;
            this.end = null;
        }
    }

    /* returning time or starting time (if task repeats) #2 */
    public Date getStartTime() {
        if (this.interval == 0) {
            return time;
        } else {
            return start;
        }
    }

    /* setting start,end and interval for the task that repeats */
    public void setTime(Date start, Date end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /* returning time or ending time (if task repeats) */
    public Date getEndTime() {
        return this.interval != 0 ? end : time;
    }

    public int getRepeatInterval() {
        //если задача не повторяется, вывести "0"
        if (this.interval > 0)
            return interval;
        else return 0;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isRepeated() {
        return this.interval > 0;
    }

    public Date nextTimeAfter(Date current) {
        if (current == null) throw new IllegalArgumentException("Chosen date is NULL!");
        if (!active) return null;
        if (!isRepeated() && isActive() && getTime().after(current)) return getTime();
        if (isRepeated() && isActive() && getEndTime().after(current)) {
            Date temp = (Date) getStartTime().clone();
            while(temp.compareTo(current) <= 0) {
                temp.setTime(temp.getTime() + getRepeatInterval()*1000);
                if (temp.after(end)) return null;
            } return temp;
        }
        return null;
    }

    /* equals for Task class objects */
    @Override
    public boolean equals(Object object){
        if (object == null)
            return false;
        if (!this.getClass().equals(object.getClass()))
            return false;
        if (this == object) {
            return true;
        }
        Task other = (Task) object;
        if (interval == other.interval && interval > 0){
            return (title.equals(other.title) &&
                    start.equals(other.start) &&
                    end.equals(other.end) &&
                    interval == other.interval &&
                    active == other.active);
        }
        if (interval == other.interval && interval == 0){
            return (title.equals(other.title) &&
                    time.equals(other.time) &&
                    active == other.active);
        }
        return false;
    }

    /* hashCode for Task class objects */
    @Override
    public int hashCode(){
        int code = 0;
        if (interval == 0){
            code = 31* (title.hashCode() + time.hashCode());
            if (active){
                code = code + 1;
            }
        }
        else {
            code = 31* (title.hashCode() + start.hashCode() + end.hashCode() + interval);
            if (active){
                code = code + 1;
            }
        }
        return code;
    }

    /* toString for Task class objects */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("[y-MM-dd HH:mm:ss.S]");
        String ifNotActive = "";
        if (!isActive()) ifNotActive = " inactive";
        if(isRepeated()) {
            return "\"" + title + "\" from " + dateFormat.format(getStartTime()) + " to "
                    + dateFormat.format(getEndTime()) + " every [" + makeDate(getRepeatInterval()) + "]" + ifNotActive;
        } else {
            return "\"" + title + "\" at " + dateFormat.format(getTime()) + ifNotActive;
        }
    }

    /* method for transfer int interval to Date */
    public String makeDate(int inter){
        Date date = new Date((long) inter * 1000);
        long milisecs = date.getTime();
        long days = milisecs / 86400000;
        long hours = (milisecs % 86400000) / 3600000;
        long mins = ((milisecs % 86400000) % 3600000) / 60000;
        long secs = (((milisecs % 86400000) % 3600000) % 60000) / 1000;

        String d = days == 1 ? " day " : " days ";
        String h = hours == 1 ? " hour " : " hours ";
        String m = mins == 1 ? " minute " : " minutes ";
        String s = secs == 1 ? " second" : " seconds";

        if (days == 0) return hours + h + mins + m + secs + s;
        if (days == 0 && hours == 0) return mins + m + secs + s;
        return days + d + hours + h + mins + m + secs + s;
    }
}