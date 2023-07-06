package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {
    private String username;
    private String password;
    private int role;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Login(@JsonProperty("username") String username, @JsonProperty("password") String password, int role) {
         setRole(role);
        setPassword(password);
        setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
