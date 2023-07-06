package org.kainos.ea.client;

public class SaleDoesNotExistException extends Throwable{
        public  String getMessage()
        {
            return "Failed to get   login from the database";
        }
}
