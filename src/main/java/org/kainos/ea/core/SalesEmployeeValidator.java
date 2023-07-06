package org.kainos.ea.core;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesRequest;

public class SalesEmployeeValidator {
    public String validateSalesEmployee(SalesRequest employee) {
        if (employee.getName().length() > 50) {
            return "Employee name must be less than 50 characters";
        }

        if (employee.getSalary() < 10000) {
            return "Employee salary must be greater than £10000";
        }

        if (employee.getNatInsuranceNo().length() != 9) {
            return "Employee national insurance number must be 9 characters";
        }

        return null;
    }
}
