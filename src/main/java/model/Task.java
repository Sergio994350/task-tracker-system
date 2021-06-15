package model;

public class Task {
    public int id;
    public String taskName;
    public double taskTime;

    public Task() {
    }

    public Task(int id, String taskName, double taskTime) {
        this.id = id;
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

    public Task(String taskName, double taskTime) {
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public double getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(double taskTime) {
        this.taskTime = taskTime;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", taskName='" + taskName + '\'' +
                ", taskTime=" + taskTime + '}';
    }
}