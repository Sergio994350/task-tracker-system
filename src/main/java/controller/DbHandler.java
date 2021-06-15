package controller;

import model.*;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {

    // address for connection
    public static final String CON_STR = "jdbc:sqlite:src/main/data/tasklist.db";
    public static DbHandler instance = null;
    public static Connection connection;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    public DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(CON_STR);
    }

    // create DB and tables
    public static void createDBAndTables() throws SQLException {
        connection = DriverManager.getConnection(CON_STR);
        try (Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users; CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " userName VARCHAR(50), userSurName VARCHAR(50), email VARCHAR(50));" +
                    " DROP TABLE IF EXISTS projects; CREATE TABLE projects (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " projectName VARCHAR(50), projectDescription VARCHAR(50));" +
                    " DROP TABLE IF EXISTS tasks; CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " taskName VARCHAR(50), taskTime DOUBLE);" +
                    " DROP TABLE IF EXISTS subtasks; CREATE TABLE subtasks (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " subTaskName VARCHAR(50), subTaskTime DOUBLE);" +
                    " DROP TABLE IF EXISTS assignments; CREATE TABLE assignments (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " projectId INTEGER, userId INTEGER, taskId INTEGER, subTaskId INTEGER," +
                    " FOREIGN KEY (projectId) REFERENCES projects(id), FOREIGN KEY (userId) REFERENCES users(id)," +
                    " FOREIGN KEY (taskId) REFERENCES tasks(id), FOREIGN KEY (subTaskId) REFERENCES subtasks(id))";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("[ Database was created successfully ]");
        System.out.println("[ Tables were created successfully ]");
    }

    // add data from CSV to DB
    public static void addDataFromCSVToDataBase() {
        //read csv files with initial data
        String csvFileUsers = "src/main/data/users.csv";
        String csvFileProjects = "src/main/data/projects.csv";
        String csvFileTasks = "src/main/data/tasks.csv";
        String csvFileSubTasks = "src/main/data/subtasks.csv";
        String csvFileAssignments = "src/main/data/assignments.csv";

        List<User> usersArrayList = CSVReader.readUsersCSV(csvFileUsers);
        List<Project> projectsArrayList = CSVReader.readProjectsCSV(csvFileProjects);
        List<Task> tasksArrayList = CSVReader.readTasksCSV(csvFileTasks);
        List<SubTask> subTasksArrayList = CSVReader.readSubTasksCSV(csvFileSubTasks);
        List<Assignment> assignmentsArrayList = CSVReader.readAssignmentsCSV(csvFileAssignments);

        try {
            DbHandler dbHandler = DbHandler.getInstance();
            for (User user : usersArrayList) {
                dbHandler.addUser(user);
            }
            for (Project project : projectsArrayList) {
                dbHandler.addProject(project);
            }
            for (Task task : tasksArrayList) {
                dbHandler.addTask(task);
            }
            for (SubTask subTask : subTasksArrayList) {
                dbHandler.addSubTask(subTask);
            }
            for (Assignment assignment : assignmentsArrayList) {
                dbHandler.addAssignment(assignment);
            }
            System.out.println("[ Data from CSV files successfully added to DB ]");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // get all Users
    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            List<User> users = new ArrayList<User>();
            ResultSet resultSet = statement
                    .executeQuery("SELECT `id`, `userName`, `userSurName`, `email` FROM `users`");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        resultSet.getString("userSurName"),
                        resultSet.getString("email")));
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    // get all Projects
    public List<Project> getAllProjects() {
        try (Statement statement = connection.createStatement()) {
            List<Project> projects = new ArrayList<Project>();
            ResultSet resultSet = statement
                    .executeQuery("SELECT `id`, `projectName`, `projectDescription` FROM `projects`");
            while (resultSet.next()) {
                projects.add(new Project(resultSet.getInt("id"),
                        resultSet.getString("projectName"),
                        resultSet.getString("projectDescription")));
            }
            return projects;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // get all Tasks
    public List<Task> getAllTasks() {
        try (Statement statement = connection.createStatement()) {
            List<Task> tasks = new ArrayList<Task>();
            ResultSet resultSet = statement
                    .executeQuery("SELECT `id`, `taskName`, `taskTime` FROM `tasks`");
            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getInt("id"),
                        resultSet.getString("taskName"),
                        resultSet.getDouble("taskTime")));
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // get all SubTasks
    public List<SubTask> getAllSubTasks() {
        try (Statement statement = connection.createStatement()) {
            List<SubTask> subTasks = new ArrayList<SubTask>();
            ResultSet resultSet = statement
                    .executeQuery("SELECT `id`, `subTaskName`, `subTaskTime` FROM `subtasks`");
            while (resultSet.next()) {
                subTasks.add(new SubTask(resultSet.getInt("id"),
                        resultSet.getString("subTaskName"),
                        resultSet.getDouble("subTaskTime")));
            }
            return subTasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // add User to DB
    public void addUser(User user) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `users`(`userName`, `userSurName`, `email`) " +
                    "VALUES('" + user.userName + "', '" + user.userSurName + "', '" + user.email + "')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // delete user by ID
    public void deleteUserById(int id) {
        if (isRowExists(id, "users")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM `users` WHERE `id` = '" + id + "'");
                System.out.println("User with id = [" + id + "] was deleted successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No User found in DataBase with this id //");
        }
    }

    // edit user by ID
    public void editUserById(int id, String userName, String userSurName, String email) {
        if (isRowExists(id, "users")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("UPDATE `users` SET `userName` = '" + userName + "', " +
                        "`userSurName` = '" + userSurName + "', " + "`email` = '" + email + "' WHERE `id` = '" + id + "'");
                System.out.println("User with id = [" + id + "] was edited successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No User found in DataBase with this id //");
        }
    }

    // add Project to DB
    public void addProject(Project project) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `projects`(`projectName`, `projectDescription`) " +
                    "VALUES('" + project.projectName + "', '" + project.projectDescription + "')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // delete project by ID
    public void deleteProjectById(int id) {
        if (isRowExists(id, "projects")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM `projects` WHERE `id` = '" + id + "'");
                System.out.println("Project with id = [" + id + "] was deleted successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No Project found in DataBase with this id //");
        }
    }

    // edit project by ID
    public void editProjectById(int id, String projectName, String projectDescription) {
        if (isRowExists(id, "projects")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("UPDATE `projects` SET `projectName` = '" + projectName + "', " +
                        "`projectDescription` = '" + projectDescription + "' WHERE `id` = '" + id + "'");
                System.out.println("Project with id = [" + id + "] was edited successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No Project found in DataBase with this id //");
        }
    }

    // add Task to DB
    public void addTask(Task task) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `tasks`(`taskName`, `taskTime`) " +
                    "VALUES('" + task.taskName + "', '" + task.taskTime + "')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // delete task by ID
    public void deleteTaskById(int id) {
        if (isRowExists(id, "tasks")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM `tasks` WHERE `id` = '" + id + "'");
                System.out.println("Task with id = [" + id + "] was deleted successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No Task found in DataBase with this id //");
        }
    }

    // edit task by ID
    public void editTaskById(int id, String taskName, double taskTime) {
        if (isRowExists(id, "tasks")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("UPDATE `tasks` SET `taskName` = '" + taskName + "', " +
                        "`taskTime` = '" + taskTime + "' WHERE `id` = '" + id + "'");
                System.out.println("Task with id = [" + id + "] was edited successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No Task found in DataBase with this id //");
        }
    }

    // add SubTask to DB
    public void addSubTask(SubTask subTask) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `subtasks`(`subTaskName`, `subTaskTime`) " +
                    "VALUES('" + subTask.subTaskName + "', '" + subTask.subTaskTime + "')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // delete SubTask by ID
    public void deleteSubTaskById(int id) {
        if (isRowExists(id, "subtasks")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM `subtasks` WHERE `id` = '" + id + "'");
                System.out.println("SubTask with id = [" + id + "] was deleted successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No SubTask found in DataBase with this id //");
        }
    }

    // edit SubTask by ID
    public void editSubTaskById(int id, String subTaskName, double subTaskTime) {
        if (isRowExists(id, "subtasks")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("UPDATE `subtasks` SET `subTaskName` = '" + subTaskName + "', " +
                        "`subTaskTime` = '" + subTaskTime + "' WHERE `id` = '" + id + "'");
                System.out.println("SubTask with id = [" + id + "] was edited successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No SubTask found in DataBase with this id //");
        }
    }

    // get all Assignments
    public List<Assignment> getAllAssignments() {
        try (Statement statement = connection.createStatement()) {
            List<Assignment> assignments = new ArrayList<Assignment>();
            ResultSet resultSet = statement
                    .executeQuery("SELECT `id`, `projectId`, `userId`, `taskId`, `subTaskId` " +
                            "FROM `assignments`");
            while (resultSet.next()) {
                assignments.add(new Assignment(
                        resultSet.getInt("id"),
                        resultSet.getInt("projectId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("taskId"),
                        resultSet.getInt("subTaskId")));
            }
            return assignments;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // add Assignment to DB
    public void addAssignment(Assignment assignment) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `assignments`(`projectId`, `userId`, `taskId`, `subTaskId`) " +
                    "VALUES('" + assignment.projectId + "', '" + assignment.userId + "', '"
                    + assignment.taskId + "', '" + assignment.subTaskId + "')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // delete assignment by task ID
    public void deleteAssignmentByTaskId(int taskId) {
        if (isRowExists(taskId, "tasks")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM `assignments` WHERE `taskId` = '" + taskId + "'");
                System.out.println("Assignments with TaskId = [" + taskId + "] was deleted successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No Task found in DataBase with this id //");
        }
    }

    // assign user on project
    public void assignUserOnProject(int projectId, int userId) {
        if (isRowExists(userId, "users") &&
                (isRowExists(projectId, "projects"))) {
            Assignment assignment = new Assignment(projectId, userId, 0, 0);
            try (Statement statement = connection.createStatement()) {
                addAssignment(assignment);
                System.out.println("User with id = [" + userId + "] was assigned on Project with id = ["
                        + projectId + "] successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No User or Project found in DataBase with this id //");
        }
    }

    // assign task on user
    public void assignTaskOnUser(int projectId, int userId, int taskId) {
        if (isRowExists(projectId, "projects") && isRowExists(userId, "users") &&
                (isRowExists(taskId, "tasks"))) {
            Assignment assignment = new Assignment(projectId, userId, taskId, 0);
            try (Statement statement = connection.createStatement()) {
                addAssignment(assignment);
                System.out.println("Task with id = [" + taskId + "] " +
                        "was assigned on Project with id = ["
                        + projectId + "] and User with id = [" + userId + "] successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No Task or User or Project found in DataBase with this id //");
        }
    }

    // get All Assignments By UserId And ProjectId
    public List<Assignment> getAllAssignmentsByProjectIdAndUserId(int projectId, int userId) {
        try (Statement statement = connection.createStatement()) {
            List<Assignment> assignments2 = new ArrayList<Assignment>();
            ResultSet resultSet = statement
                    .executeQuery("SELECT `id`, `projectId`, `userId`, `taskId`, `subTaskId` " +
                            "FROM `assignments` WHERE `projectId` = '" + projectId + "' " +
                            "AND `userId` = '" + userId + "';");
            while (resultSet.next()) {
                assignments2.add(new Assignment(
                        resultSet.getInt("id"),
                        resultSet.getInt("projectId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("taskId"),
                        resultSet.getInt("subTaskId")));
            }
            return assignments2;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // assign subtask on task
    public void assignSubTaskOnTask(int taskId, int subTaskId) {
        if (isRowExists(taskId, "tasks") && (isRowExists(subTaskId, "subtasks"))) {
            Assignment assignment = new Assignment(0, 0, taskId, subTaskId);
            try (Statement statement = connection.createStatement()) {
                addAssignment(assignment);
                System.out.println("SubTask with id = [" + subTaskId + "] " +
                        "was assigned on Task with id = [" + taskId + "] successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("// Mistake: No Task or SubTask found in DataBase with this id //");
        }
    }

    // calculate time (tasks + subtasks) by task ID
    public double calculateTotalTimeByTaskId(int taskId) {
        double timeCalc = 0;
        try (Statement statement = connection.createStatement()) {
            timeCalc = statement
                    .executeQuery("SELECT (SUM(St.subTaskTime) + T.taskTime) AS time FROM subtasks AS St, tasks AS T, assignments AS A " +
                            "WHERE A.taskId = T.id AND A.subTaskId = St.id AND T.id = '"
                            + taskId + "';").getDouble(1);
            return timeCalc;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // close all tasks & subtasks by task ID
    public void closeAllTasksAndSubTasksAndAssignmentsByTaskId(int taskId) {
        deleteAssignmentByTaskId(taskId);
        while (isRowExists(taskId, "tasks")) {
            deleteTaskById(taskId);
        }
    }

    public boolean isRowExists(int id, String tableName) {
        boolean exist = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM '" + tableName + "' WHERE `id` = ?")) {
            statement.setObject(1, id);
            if (statement.executeQuery().next()) {
                exist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

}