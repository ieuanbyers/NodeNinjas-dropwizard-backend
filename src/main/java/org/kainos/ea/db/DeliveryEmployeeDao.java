package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.DeliveryEmployeeUpdateRequest;
import org.kainos.ea.cli.EmployeeRequest;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.kainos.ea.db.DatabaseConnector.getConnection;

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

    public void updateDeliveryEmployee(int id, DeliveryEmployeeUpdateRequest employee) throws SQLException{
        Connection c = DatabaseConnector.getConnection();

        String updateStatement = "UPDATE Delivery SET Name = ?, Salary = ?, BankAccountNo = ? WHERE DeliveryEmployeeID = ?;";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, employee.getName());
        st.setDouble(2, employee.getSalary());
        st.setString(3, employee.getBankAccountNo());
        st.setInt(4, id);

        st.executeUpdate();

    }

    public List<DeliveryEmployee> getAllEmployees() throws SQLException {
        Connection c = getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT DeliveryEmployeeID, Name, Salary, BankAccountNo, NatInsuranceNo FROM Delivery;");

        List<DeliveryEmployee> employeeList = new ArrayList<>();

        while (rs.next()) {
            DeliveryEmployee employee = new DeliveryEmployee(
                    rs.getInt("DeliveryEmployeeID"),
                    rs.getString("Name"),
                    rs.getFloat("Salary"),
                    rs.getString("BankAccountNo"),
                    rs.getString("NatInsuranceNo")
            );

            employeeList.add(employee);
        }
        return employeeList;
    }

    public void deleteDeliveryEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM Delivery WHERE DeliveryEmployeeID = ?;";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();
    }


}
