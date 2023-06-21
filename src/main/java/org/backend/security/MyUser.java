package org.backend.security;

import org.backend.requests.VacationRental;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class MyUser implements Principal {
    private String username;
    private String password;
    private String role;

    public static String validateLogin(String username, String password){
        System.out.println("Validating login for username: " + username + ", password: " + password);
        Username u1 = new Username(username);
        VacationRental vr = VacationRental.getVacationRental();
        vr.addUsername(u1);
        System.out.println("ALLE USERNAMES:" + vr.getUsernamesVR());

        String role = null;
        for(MyUser user : allUsers){
            if(user.username.equals(username) && user.password.equals(password)){
                role = user.role;
            }
        }
        return role;
    }


    public static boolean createUser(String username, String password, String role){
        if (getUserByName(username) == null) {
            allUsers.add(new MyUser(username, password, role));
        }
        return false;
    }

    public static MyUser getUserByName(String username){
        return allUsers.stream().filter(user -> user.username.equals(username)).findFirst().orElse(null);
    }

    @Override
    public String getName() {
        return username;
    }

    private MyUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private static List<MyUser> allUsers = new ArrayList<>();

    public String getRole() {
        return role;
    }
}
