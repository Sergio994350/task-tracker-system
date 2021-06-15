package model;

public class SubTask {
    public int id;
    public String subTaskName;
    public double subTaskTime;

    public SubTask(int id, String subTaskName, double subTaskTime) {
        this.id = id;
        this.subTaskName = subTaskName;
        this.subTaskTime = subTaskTime;
    }

    public SubTask(String subTaskName, double subTaskTime) {
        this.subTaskName = subTaskName;
        this.subTaskTime = subTaskTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public double getSubTaskTime() {
        return subTaskTime;
    }

    public void setSubTaskTime(double subTaskTime) {
        this.subTaskTime = subTaskTime;
    }

    @Override
    public String toString() {
        return "SubTask{" + "id=" + id + ", subTaskName='" + subTaskName + '\'' +
                ", subTaskTime=" + subTaskTime + '}';
    }
}
