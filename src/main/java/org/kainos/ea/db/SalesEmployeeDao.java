package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public int createSalesEmployee(SalesRequest employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Sales (Name, Salary, BankAccountNo, NatInsuranceNo, CommissionRate) VALUES(?,?,?,?,?);";

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

    public List<SalesEmployee> getAllSales() throws SQLException {{
            try (Connection c = databaseConnector.getConnection()) {
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery("SELECT SalaryEmployeeId, Name, Salary, BankAccountNo, NatInsuranceNo, CommissionRate FROM Sales;");
                List<SalesEmployee> saleList = new ArrayList<>();
                while (rs.next()) {
                    SalesEmployee sale = new SalesEmployee(
                            rs.getInt("SalaryEmployeeId"),
                            rs.getString("Name"),
                            rs.getDouble("Salary"),
                            rs.getString("BankAccountNo"),
                            rs.getString("NatInsuranceNo"),
                            rs.getFloat("CommissionRate")
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

    public SalesRequest getSalesById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT Name, Salary, BankAccountNo, NatInsuranceNo, CommissionRate FROM Sales where SalesEmployeeId = " + id + ";");

        while (rs.next()) {
            return new SalesRequest(
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccountNo"),
                    rs.getString("NatInsuranceNo"),
                    rs.getDouble("ComRate")
            );
        }
        return null;
    }

    public void updateSales(int id, SalesRequest sales)   throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String updateStatement = "UPDATE Sales SET Name = ?, Salary = ?, BankAccountNo = ?, NatInsuranceNo = ?, CommissionRate = ? Where SalesEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, sales.getName());
        st.setDouble(2, sales.getSalary());
        st.setString(3, sales.getBankAccountNo());
        st.setString(4, sales.getNatInsuranceNo());
        st.setDouble(5, sales.getComRate());
        st.setInt(6, id);

        st.executeUpdate();
    }
    public  void DeleteSales(int id) throws SQLException
    {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM Sales Where SalesEmployeeId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1,id);
        st.executeUpdate();
    }
}
