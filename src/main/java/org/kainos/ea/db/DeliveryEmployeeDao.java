package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DeliveryEmployeeDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public DeliveryEmployeeRequest getDeliveryEmployeeById(int id) throws SQLException{
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        String selectStatement = "SELECT Name, Salary, BankAccountNo, NatInsuranceNo FROM Delivery WHERE DeliveryEmployeeID = " + id;

        ResultSet rs = st.executeQuery(selectStatement);

        while (rs.next()){
            return new DeliveryEmployeeRequest(
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccountNo"),
                    rs.getString("NatInsuranceNo")
            );
        }
        return null;
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Delivery (Name, Salary, BankAccountNo, NatInsuranceNo) VALUES(?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

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
