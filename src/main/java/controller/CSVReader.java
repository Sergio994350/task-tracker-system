package controller;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CSVReader {
    private static BufferedReader br;
    private static String line;

    public static List<User> readUsersCSV(String csvFileUsers) {
        List<User> usersList = new ArrayList<>();
        int number = 0;
        try {
            br = new BufferedReader(new FileReader(csvFileUsers));
            while ((line = br.readLine()) != null) {
                String[] userMask = line.split("[\\s,]+");
                User user = new User(userMask[0], userMask[1], userMask[2]);
                usersList.add(number++, user);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usersList;
    }

    public static List<Project> readProjectsCSV(String csvFileProjects) {
        List<Project> projectsList = new ArrayList<>();
        int number = 0;
        try {
            br = new BufferedReader(new FileReader(csvFileProjects));
            while ((line = br.readLine()) != null) {
                String[] projectMask = line.split("[\\s,]+");
                Project project = new Project(projectMask[0], projectMask[1]);
                projectsList.add(number++, project);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return projectsList;
    }

    public static List<Task> readTasksCSV(String csvFileTasks) {
        List<Task> tasksList = new ArrayList<>();
        int number = 0;
        try {
            br = new BufferedReader(new FileReader(csvFileTasks));
            while ((line = br.readLine()) != null) {
                String[] taskMask = line.split("[\\s,]+");
                Task task = new Task(taskMask[0], Double.parseDouble(taskMask[1]));
                tasksList.add(number++, task);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tasksList;
    }

    public static List<SubTask> readSubTasksCSV(String csvFileSubTasks) {
        List<SubTask> subTasksList = new ArrayList<>();
        int number = 0;
        try {
            br = new BufferedReader(new FileReader(csvFileSubTasks));
            while ((line = br.readLine()) != null) {
                String[] subTaskMask = line.split("[\\s,]+");
                SubTask subTask = new SubTask(subTaskMask[0], Double.parseDouble(subTaskMask[1]));
                subTasksList.add(number++, subTask);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return subTasksList;
    }

    public static List<Assignment> readAssignmentsCSV(String csvFileAssignments) {
        List<Assignment> assignmentsList = new ArrayList<>();
        int number = 0;
        try {
            br = new BufferedReader(new FileReader(csvFileAssignments));
            while ((line = br.readLine()) != null) {
                String[] assignmentMask = line.split("[\\s,]+");
                Assignment assignment = new Assignment(Integer.parseInt(assignmentMask[0]), Integer.parseInt(assignmentMask[1]),
                        Integer.parseInt(assignmentMask[2]), Integer.parseInt(assignmentMask[3]));
                assignmentsList.add(number++, assignment);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return assignmentsList;
    }
}