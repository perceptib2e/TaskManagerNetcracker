package com.symonenko.oleksandr.model;

import java.util.Iterator;

public class LinkedTaskList extends TaskList {

    /*
    Short time list (remove method is often)
    * */

    private InnerTask first;
    private InnerTask last;
    private int size;

    /* constructor */
    public LinkedTaskList() {
    }

    /* inner class */
    private class InnerTask {
        private Task currTask;
        private InnerTask prevTask;
        private InnerTask nextTask;

        /* constructors */
        private InnerTask(Task task) {
            this.currTask = task;
            this.prevTask = null;
            this.nextTask = null;
        }

        private InnerTask() {
        }
    }

    @Override
    public void add(Task task) throws IllegalStateException {
        taskNotNullCheck(task);
        InnerTask taskToAdd = new InnerTask(task);
        if (size == 0) {
            first = taskToAdd;
            last = first;
            size++;
        } else {
            last.nextTask = taskToAdd;
            taskToAdd.prevTask = last;
            last = taskToAdd;
            size++;
        }
    }

    @Override
    public boolean remove(Task task) throws IllegalStateException {
        taskNotNullCheck(task);
        InnerTask innTaskSearch;
        innTaskSearch = first;
        if (task == first.currTask) {
            first = first.nextTask;
            size--;
            return true;
        }
        if (task == last.currTask) {
            last = last.prevTask;
            last.nextTask = null;
            size--;
            return true;
        }
        else {
            for (int i = 0; i < size; i++){
                if (innTaskSearch.currTask.getTitle().equals(task.getTitle())) {
                    innTaskSearch.prevTask.nextTask = innTaskSearch.nextTask;
                    innTaskSearch.nextTask.prevTask = innTaskSearch.prevTask;
                    size--;
                    return true;
                }
                innTaskSearch = innTaskSearch.nextTask;
            }
        }
        System.out.println("model.Task " + task.getTitle() + " is absent in model.LinkedTaskList.");
        return false;
    }

    /* returns amount of tasks in list */
    @Override
    public int size() {
        return size;
    }

    /* returns task on "index" position */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        InnerTask inn = first;
        getTaskCHECK(index);
        for (int i = 0; i < index; i++) {
            inn = inn.nextTask;
        }
        return inn.currTask;
    }

    @Override
    public void getTaskCHECK(int index) throws IndexOutOfBoundsException {
        if (index >= size)
            throw new IndexOutOfBoundsException("LinkedList has " + size + " tasks (last has index " + (size - 1) + " from 0 in LinkedTaskList). Requested task is " + index + ".");
    }

    /* iterator */
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            InnerTask iterObserver = first;

                      public boolean hasNext() {
                return iterObserver != null;
            }

                        public Task next() {
                if (!hasNext()) throw new IllegalStateException("Cannot find the next task");
                Task task = iterObserver.currTask;
                iterObserver = iterObserver.nextTask;
                return task;
            }

            public void remove() {
                if (iterObserver.prevTask == null) throw new IllegalStateException("Nothing to remove");
                LinkedTaskList.this.remove(iterObserver.prevTask.currTask);
            }
        };
    }
}