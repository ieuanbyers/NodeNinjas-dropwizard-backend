package org.kainos.ea.client;

public class FailedToGetEmployeeException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to get employee";
    }
}
