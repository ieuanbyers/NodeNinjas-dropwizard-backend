package org.kainos.ea.client;

public class FailedToDeleteSaleException extends Throwable{
    @Override
    public  String getMessage()
    {
        return "Failed to Delete Sale from the database";
    }
}
