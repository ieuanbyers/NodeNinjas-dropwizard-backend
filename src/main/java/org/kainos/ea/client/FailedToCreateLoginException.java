package org.kainos.ea.client;

public class FailedToCreateLoginException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "Failed to create new  login to the database";
    }
}
