package org.kainos.ea.client;

public class InvalidLoginException extends Exception {
    @Override
    public  String getMessage()
    {
        return "Failed to get   login from the database";
    }
}
