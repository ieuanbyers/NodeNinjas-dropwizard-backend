package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryEmployeeDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public int createDeliveryEmployee(DeliveryEmployeeRequest employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Delivery (Name, Salary, BankAccountNo, NatInsuranceNo) VALUES(?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setString(1, employee.getName());
        st.setDouble(2, employee.getSalary());
        st.setString(3, employee.getBankAccountNo());
        st.setString(4, employee.getNatInsuranceNo());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()){
            return rs.getInt(1);
        }

        return -1;
    }
}
