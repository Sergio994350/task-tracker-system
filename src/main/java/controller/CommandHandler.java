package controller;

import model.*;

import java.util.List;

public class CommandHandler {

    public static void commandSwitcher(String command) {
        switch (command) {
            case "0": {   // exit program
                break;
            }
            case "1": {   // 1 - show all users
                showAllUsers();
                break;
            }
            case "2": {   // 2 - show all projects
                showAllProjects();
                break;
            }
            case "3": {   // 3 - show all tasks
                showAllTasks();
                break;
            }
            case "4": {   // 4 - show all subtasks
                showAllSubTasks();
                break;
            }
            case "5": {   // 5 - create, edit, delete or assign user
                createEditDeleteOrAssignUser();
                break;
            }
            case "6": {   // 6 - create, edit, delete or assign project
                createEditDeleteOrAssignProject();
                break;
            }
            case "7": {   // 7 - create, edit, delete or assign task
                createEditDeleteOrAssignTask();
                break;
            }
            case "8": {   // 8 - create, edit, delete or assign subtask
                createEditDeleteOrAssignSubTask();
                break;
            }
            case "9": {   // 9 - show all assignments
                showAllAssignments();
                break;
            }
            case "10": {   // 10 - special report of all tasks by userId & projectId
                showAllTasksByProjectIdAndUserId();
                break;
            }
            case "11": {   // 11 - calculate time (tasks & subtasks) by task ID
                calculateTimeByTaskIdHandler();
                break;
            }
            case "12": {   // 12 - close all tasks & subtasks by task ID
                closeTasksAndSubTasksByTaskId();
                break;
            }
            default: {   // if user enters something wrong
                System.out.println("Please, repeat entering. Something wrong.");
                break;
            }
        }
    }

    // ===========================================================================
    public static void showAllUsers() {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<User> users = dbHandler.getAllUsers();
            for (User user : users) {
                System.out.println(user.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showAllProjects() {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<Project> projects = dbHandler.getAllProjects();
            for (Project project : projects) {
                System.out.println(project.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showAllTasks() {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<Task> tasks = dbHandler.getAllTasks();
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showAllSubTasks() {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<SubTask> subTasks = dbHandler.getAllSubTasks();
            for (SubTask subTask : subTasks) {
                System.out.println(subTask.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showAllAssignments() {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<Assignment> assignments = dbHandler.getAllAssignments();
            for (Assignment assignment : assignments) {
                System.out.println(assignment.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ===========================================================================
    public static void createEditDeleteOrAssignUser() {   // user - head
        System.out.println("1 - create new user, 2 - edit, delete or assign user");
        String scan = MVLogic.commandFromScanner();

        switch (scan) {
            case "1": {   // create new user
                createNewUserHandler();
            }
            break;

            case "2": {   // edit, delete or assign user - head
                System.out.println("Please, enter user ID: ");
                String userIdScanner = MVLogic.commandFromScanner();
                System.out.println("1 - delete, 2 - edit, 3 - assign on project ");
                String cmd = MVLogic.commandFromScanner();

                switch (cmd) {
                    case "1": {   // delete user by id
                        deleteUserByIdHandler(userIdScanner);
                    }
                    break;

                    case "2": {   // edit user by id
                        editUserByIdHandler(userIdScanner);
                    }
                    break;

                    case "3": {   // assign project on user
                        assignUserByIdOnProjectByIdHandler(userIdScanner);
                    }
                    break;
                }
            }
        }
    }

    public static void createNewUserHandler() {
        System.out.println("Please, enter new user's name: ");
        String userNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter new user's surname: ");
        String userSurNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter new user's email: ");
        String userEmailScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.addUser(new User(userNameScanner, userSurNameScanner, userEmailScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteUserByIdHandler(String userId) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.deleteUserById(Integer.parseInt(userId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void editUserByIdHandler(String userIdScanner) {
        System.out.println("Please, enter user name: ");
        String userNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter user surname: ");
        String userSurNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter user email: ");
        String userEmailScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.editUserById(Integer.parseInt(userIdScanner), userNameScanner, userSurNameScanner, userEmailScanner);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void assignUserByIdOnProjectByIdHandler(String userIdScanner) {
        System.out.println("Please, enter project ID to assign on user: ");
        String projectIdToAssignScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.assignUserOnProject(Integer.parseInt(projectIdToAssignScanner), Integer.parseInt(userIdScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ===========================================================================
    public static void createEditDeleteOrAssignProject() {   // project - head
        System.out.println("1 - create new project, 2 - edit, delete or assign project");
        String scan1 = MVLogic.commandFromScanner();

        switch (scan1) {
            case "1": {   // create new project
                createNewProjectHandler();
            }
            break;

            case "2": {   // edit, delete or assign project - head
                System.out.println("Please, enter project ID: ");
                String projectIdScanner = MVLogic.commandFromScanner();
                System.out.println("1 - delete, 2 - edit, 3 - assign on user ");
                String cmd = MVLogic.commandFromScanner();

                switch (cmd) {
                    case "1": {   // delete project by id
                        deleteProjectByIdHandler(projectIdScanner);
                    }
                    break;

                    case "2": {   // edit project by id
                        editProjectByIdHandler(projectIdScanner);
                    }
                    break;

                    case "3": {   // assign project on user
                        assignProjectByIdOnUserByIdHandler(projectIdScanner);
                    }
                    break;
                }
            }
        }
    }

    public static void createNewProjectHandler() {
        System.out.println("Please, enter new Project Name: ");
        String projectNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter new Project Description: ");
        String projectDescriptionScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.addProject(new Project(projectNameScanner, projectDescriptionScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteProjectByIdHandler(String projectId) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.deleteProjectById(Integer.parseInt(projectId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void editProjectByIdHandler(String projectIdScanner) {
        System.out.println("Please, enter new Project Name: ");
        String projectNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter new Project Description: ");
        String projectDescriptionScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.editProjectById(Integer.parseInt(projectIdScanner), projectNameScanner, projectDescriptionScanner);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void assignProjectByIdOnUserByIdHandler(String projectIdScanner) {
        System.out.println("Please, enter user ID to assign on project: ");
        String userIdScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.assignUserOnProject(Integer.parseInt(projectIdScanner), Integer.parseInt(userIdScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ===========================================================================
    public static void createEditDeleteOrAssignTask() {   // task - head
        System.out.println("1 - create new task, 2 - edit, delete or assign task");
        String scan = MVLogic.commandFromScanner();

        switch (scan) {
            case "1": {   // create new task
                createNewTaskHandler();
            }
            break;

            case "2": {   // edit, delete or assign task - head
                System.out.println("Please, enter task ID: ");
                String taskIdScanner = MVLogic.commandFromScanner();
                System.out.println("1 - delete, 2 - edit, 3 - assign task ");
                String cmd = MVLogic.commandFromScanner();

                switch (cmd) {
                    case "1": {   // delete task by id
                        deleteTaskByIdHandler(taskIdScanner);
                    }
                    break;

                    case "2": {   // edit task by id
                        editTaskByIdHandler(taskIdScanner);
                    }
                    break;

                    case "3": {   // assign task on user
                        assignTaskByIdOnUserByIdHandler(taskIdScanner);
                    }
                    break;
                }
            }
        }
    }

    public static void createNewTaskHandler() {
        System.out.println("Please, enter Task Name: ");
        String taskNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter Task Time: ");
        String taskTimeScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.addTask(new Task(taskNameScanner, Double.parseDouble(taskTimeScanner)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteTaskByIdHandler(String taskId) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.deleteTaskById(Integer.parseInt(taskId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void editTaskByIdHandler(String taskIdScanner) {
        System.out.println("Please, enter Task Name: ");
        String taskNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter Task Time: ");
        String taskTimeScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.editTaskById(Integer.parseInt(taskIdScanner), taskNameScanner, Double.parseDouble(taskTimeScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void assignTaskByIdOnUserByIdHandler(String taskIdScanner) {
        System.out.println("Please, enter project ID to assign this task: ");
        String projectIdTaskScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter user ID to assign this task: ");
        String userIdTaskScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.assignTaskOnUser(Integer.parseInt(projectIdTaskScanner),
                    Integer.parseInt(userIdTaskScanner), Integer.parseInt(taskIdScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ===========================================================================
    public static void createEditDeleteOrAssignSubTask() {   // subtask - head
        System.out.println("1 - create new subtask, 2 - edit, delete or assign subtask");
        String scan = MVLogic.commandFromScanner();

        switch (scan) {
            case "1": {   // create new subtask
                createNewSubTaskHandler();
            }
            break;

            case "2": {   // edit, delete or assign subtask - head
                System.out.println("Please, enter subtask ID: ");
                String subTaskIdScanner = MVLogic.commandFromScanner();
                System.out.println("1 - delete, 2 - edit, 3 - assign subtask ");
                String cmd = MVLogic.commandFromScanner();

                switch (cmd) {
                    case "1": {   // delete subtask by id
                        deleteSubTaskByIdHandler(subTaskIdScanner);
                    }
                    break;

                    case "2": {   // edit subtask by id
                        editSubTaskByIdHandler(subTaskIdScanner);
                    }
                    break;

                    case "3": {   // assign subtask on user
                        assignSubTaskByIdOnTaskHandler(subTaskIdScanner);
                    }
                    break;
                }
            }
        }
    }

    public static void createNewSubTaskHandler() {
        System.out.println("Please, enter SubTask Name: ");
        String subTaskNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter SubTask Time: ");
        String subTaskTimeScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.addSubTask(new SubTask(subTaskNameScanner, Double.parseDouble(subTaskTimeScanner)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteSubTaskByIdHandler(String subTaskId) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.deleteSubTaskById(Integer.parseInt(subTaskId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void editSubTaskByIdHandler(String subTaskIdScanner) {
        System.out.println("Please, enter SubTask Name: ");
        String subTaskNameScanner = MVLogic.commandFromScanner();
        System.out.println("Please, enter SubTask Time: ");
        String subTaskTimeScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.editSubTaskById(Integer.parseInt(subTaskIdScanner), subTaskNameScanner, Double.parseDouble(subTaskTimeScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void assignSubTaskByIdOnTaskHandler(String subTaskIdScanner) {
        System.out.println("Please, enter task ID to assign this subtask: ");
        String taskIdSubTaskScanner = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.assignSubTaskOnTask(Integer.parseInt(taskIdSubTaskScanner), Integer.parseInt(subTaskIdScanner));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ===========================================================================
    public static void showAllTasksByProjectIdAndUserId() {
        System.out.println("Please, enter project ID: ");
        String projectId = MVLogic.commandFromScanner();
        System.out.println("Please, enter user ID: ");
        String userId = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            List<Assignment> assignments
                    = dbHandler.getAllAssignmentsByProjectIdAndUserId(Integer.parseInt(projectId),
                    Integer.parseInt(userId));
            for (Assignment assignment : assignments) {
                System.out.println(assignment.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void calculateTimeByTaskIdHandler() {
        System.out.println("Please, enter task ID: ");
        String taskId = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            double timeCalc = dbHandler.calculateTotalTimeByTaskId(Integer.parseInt(taskId));
            System.out.println("Total time for task with id = [" + taskId + "] and all subtasks is [" + timeCalc + "] hours. ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void closeTasksAndSubTasksByTaskId() {
        System.out.println("Please, enter task ID: ");
        String taskId = MVLogic.commandFromScanner();
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.closeAllTasksAndSubTasksAndAssignmentsByTaskId(Integer.parseInt(taskId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}