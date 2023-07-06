package org.kainos.ea.client;

public class FailedToGetSalesException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "Failed to generate Sales from the database";
    }
}
