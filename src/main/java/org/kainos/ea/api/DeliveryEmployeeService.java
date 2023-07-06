package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.FailedToCreateEmployeeException;
import org.kainos.ea.client.InvalidEmployeeException;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;

import java.sql.SQLException;

public class DeliveryEmployeeService {

    private DeliveryEmployeeDao deliveryEmployeeDao = new DeliveryEmployeeDao();
    private DeliveryEmployeeValidator deliveryEmployeeValidator = new DeliveryEmployeeValidator();

    public int createDeliveryEmployee(DeliveryEmployeeRequest employee) throws InvalidEmployeeException, FailedToCreateEmployeeException {
        try{
            String validation = deliveryEmployeeValidator.validateDeliveryEmployee(employee);

            if (validation != null){
                throw new InvalidEmployeeException(validation);
            }

            int id = deliveryEmployeeDao.createDeliveryEmployee(employee);

            if (id == -1){
                throw new FailedToCreateEmployeeException();
            }

            return id;

        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToCreateEmployeeException();
        }
    }
}
