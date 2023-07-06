package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public int createSalesEmployee(SalesRequest employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Sales (Name, Salary, BankAccountNo, NatInsuranceNo, comRate) VALUES(?,?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, employee.getName());
        st.setDouble(2, employee.getSalary());
        st.setString(3, employee.getBankAccountNo());
        st.setString(4, employee.getNatInsuranceNo());
        st.setDouble(5, employee.getComRate());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()){
            return rs.getInt(1);
        }

        return -1;
    }

    public List<SalesRequest> getAllSales() throws SQLException {{
            try (Connection c = databaseConnector.getConnection()) {
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery("SELECT Name, Salary, BankAccountNo, NatInsuranceNo, comRate FROM Sales;");
                List<SalesRequest> saleList = new ArrayList<>();
                while (rs.next()) {
                    SalesRequest sale = new SalesRequest(
                            rs.getString("Name"),
                            rs.getDouble("Salary"),
                            rs.getString("BankAccountNo"),
                            rs.getString("NatInsuranceNo"),
                            rs.getDouble("ComRate")
                    );
                    saleList.add(sale);
                }
                return saleList;

            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
    }
}
