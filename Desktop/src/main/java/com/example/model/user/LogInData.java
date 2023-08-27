package com.example.model.user;

public class LogInData {
    public final static User loggedInUser = new User();

    public static void setLoggedInUser(User user){
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setRole(user.getRole());
        loggedInUser.setPassword(user.getPassword());
        loggedInUser.setId(user.getId());
    }
}
