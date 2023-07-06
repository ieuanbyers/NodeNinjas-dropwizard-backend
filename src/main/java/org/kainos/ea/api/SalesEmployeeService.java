package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;
import org.kainos.ea.db.SalesEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private SalesEmployeeDao salesEmployeeDao = new SalesEmployeeDao();

    private SalesEmployeeValidator salesEmployeeValidator = new SalesEmployeeValidator();
    public int createSalesEmployee(SalesRequest sale) throws InvalidEmployeeException, FailedToCreateEmployeeException {
        try {
              String validation = salesEmployeeValidator.validateSalesEmployee(sale);

          if (validation != null){
                          throw new InvalidEmployeeException(validation);
                    }

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


    public List<SalesEmployee> getAllSales() throws FailedToGetSalesException {
        List<SalesEmployee> saleList = null;
        try {
            saleList = salesEmployeeDao.getAllSales();
            return saleList;
        } catch (SQLException e) {
            e.getMessage();
            throw new FailedToGetSalesException();
        }
    }

    public SalesRequest getSalesById(int id) throws FailedToGetSalesException, SaleDoesNotExistException {
        try {
            SalesRequest sale = salesEmployeeDao.getSalesById(id);
            if (sale == null) {
                throw new SaleDoesNotExistException();

            }
            return sale;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetSalesException();
        }
    }

    public void updateSales(int id, SalesRequest orderRequest) throws InvalidEmployeeException, SaleDoesNotExistException, FailedToUpdateSaleException, SQLException {
            try {
                String validation = salesEmployeeValidator.validateSalesEmployee(orderRequest);
                if (validation != null) {
                    throw new InvalidEmployeeException(validation);
                }
                SalesRequest OrderToUp = salesEmployeeDao.getSalesById(id);
                if (OrderToUp == null) {
                    throw new SaleDoesNotExistException();
                }
                salesEmployeeDao.updateSales(id, orderRequest);

            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new FailedToUpdateSaleException();
            }
    }
    public void deleteSales(int id) throws SaleDoesNotExistException, FailedToDeleteSaleException
    {
        try
        {
            SalesRequest saleToDelete = salesEmployeeDao.getSalesById(id);
            if(saleToDelete == null)
            {
                throw new SaleDoesNotExistException();
            }
                salesEmployeeDao.DeleteSales(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteSaleException();
        }
    }
}
