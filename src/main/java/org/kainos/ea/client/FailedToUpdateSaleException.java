package org.kainos.ea.client;

public class FailedToUpdateSaleException extends Throwable{
    @Override
    public  String getMessage()
    {
        return "Failed to update Sale from the database";
    }
}
