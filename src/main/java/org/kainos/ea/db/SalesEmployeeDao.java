package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesRequest;

import java.sql.*;

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
}
