package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesRequest;
import org.kainos.ea.client.FailedToCreateEmployeeException;
import org.kainos.ea.client.FailedToGetSalesException;
import org.kainos.ea.client.InvalidEmployeeException;
import org.kainos.ea.db.DeliveryEmployeeDao;
import org.kainos.ea.db.SalesEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private SalesEmployeeDao salesEmployeeDao = new SalesEmployeeDao();

    public int createSalesEmployee(SalesRequest sale) throws InvalidEmployeeException, FailedToCreateEmployeeException {
        try {
            //  String validation = salesEmployeeValidator.validateDeliveryEmployee(employee);

//            if (validation != null){
            //              throw new InvalidEmployeeException(validation);
            //        }

            int id = salesEmployeeDao.createSalesEmployee(sale);

            if (id == -1) {
                throw new FailedToCreateEmployeeException();
            }

            return id;

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateEmployeeException();
        }
    }


    public List<SalesRequest> getAllSales() throws FailedToGetSalesException {
        List<SalesRequest> saleList = null;
        try {
            saleList = salesEmployeeDao.getAllSales();
            return saleList;
        } catch (SQLException e) {
            e.getMessage();
            throw new FailedToGetSalesException();
        }
    }
}
