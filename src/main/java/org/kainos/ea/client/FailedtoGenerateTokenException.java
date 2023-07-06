package org.kainos.ea.client;

public class FailedtoGenerateTokenException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "Failed to generate token from the database";
    }
}
