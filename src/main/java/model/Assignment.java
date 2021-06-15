package model;

public class Assignment {

    public int id;
    public int projectId;
    public int userId;
    public int taskId;
    public int subTaskId;

    public Assignment(int id, int projectId, int userId, int taskId, int subTaskId) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.taskId = taskId;
        this.subTaskId = subTaskId;
    }

    public Assignment(int projectId, int userId, int taskId, int subTaskId) {
        this.projectId = projectId;
        this.userId = userId;
        this.taskId = taskId;
        this.subTaskId = subTaskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }

    @Override
    public String toString() {
        return "Assignment{" + "id=" + id + ", projectId=" + projectId + ", userId=" + userId +
                ", taskId=" + taskId + ", subTaskId=" + subTaskId + '}';
    }
}