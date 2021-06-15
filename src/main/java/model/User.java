package model;

public class User {
    public int id;
    public String userName;
    public String userSurName;
    public String email;

    public User() {
    }

    public User(int id, String userName, String userSurName, String email) {
        this.id = id;
        this.userName = userName;
        this.userSurName = userSurName;
        this.email = email;
    }

    public User(String userName, String userSurName, String email) {
        this.userName = userName;
        this.userSurName = userSurName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' +
                ", userSurName='" + userSurName + '\'' + ", email='" + email + '\'' +
                '}';
    }
}

