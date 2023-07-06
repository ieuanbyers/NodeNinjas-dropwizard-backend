package org.kainos.ea.client;

public class FailedToCreateEmployeeException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to create employee";
    }
}
