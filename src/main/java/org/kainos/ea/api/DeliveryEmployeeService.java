package org.kainos.ea.api;

import org.kainos.ea.cli.*;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public DeliveryEmployeeRequest getDeliveryEmployeeByID(int id) throws FailedToGetEmployeeException, EmployeeDoesNotExistException {

        try {
            DeliveryEmployeeRequest employee = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if (employee == null) {
                throw new EmployeeDoesNotExistException();
            }
            return employee;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToGetEmployeeException();
        }
    }

    public void updateDeliveryEmployee(int id, DeliveryEmployeeUpdateRequest employee) throws InvalidEmployeeException, EmployeeDoesNotExistException, FailedToUpdateEmployeeException {
        try{
            String validation = deliveryEmployeeValidator.validateDeliveryEmployeeUpdate(employee);
            if (validation != null){
                throw new InvalidEmployeeException(validation);
            }

            EmployeeRequest employeeToUpdate = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if (employeeToUpdate == null){
                throw new EmployeeDoesNotExistException();
            }

            deliveryEmployeeDao.updateDeliveryEmployee(id, employee);
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateEmployeeException();
        }
    }

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws FailedToGetEmployeesException {
        try {
            return deliveryEmployeeDao.getAllEmployees();

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetEmployeesException();
        }
    }

    public void deleteEmployee(int id) throws EmployeeDoesNotExistException, FailedToDeleteEmployeeException {
        try {
            DeliveryEmployeeRequest employeeToDelete = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if(employeeToDelete == null) {
                throw new EmployeeDoesNotExistException();
            }

            deliveryEmployeeDao.deleteDeliveryEmployee(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteEmployeeException();
        }
    }
}
