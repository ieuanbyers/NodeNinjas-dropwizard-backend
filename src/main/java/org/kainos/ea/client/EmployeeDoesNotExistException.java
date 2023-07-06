package org.kainos.ea.client;

public class EmployeeDoesNotExistException extends Exception {
    @Override
    public String getMessage(){
        return "Employee does not exist in database";
    }
}
