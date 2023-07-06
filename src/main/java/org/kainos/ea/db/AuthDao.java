package org.kainos.ea.db.OrderDao;

import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.OrderRequest;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class AuthDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public boolean validLogin(Login login) {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT Password FROM `User` where Username = " +"'"+ login.getUsername()+"'");

                while (rs.next())
                {
                    return rs.getString("password").equals(login.getPassword());
                }
        } catch (SQLException e) {
           System.err.println(e.getMessage());
        }
        return false;
    }

    public  String generateToken(String username) throws  SQLException
    {
        String token = UUID.randomUUID().toString();
        Date expiry = DateUtils.addHours(new Date(), 8);
        Connection c = databaseConnector.getConnection();
        String insertStatement = "Insert into Token(username, token, Expiry) VALUES (?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setString(1, username);
        st.setString(2, token);
        st.setTimestamp(3,new java.sql.Timestamp(expiry.getTime()));

        st.executeUpdate();

        return token;

    }
    public int Register(Login login) throws SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO `User` (Username, Password, Role) VALUES (?,?,1)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, login.getUsername());
        st.setString(2,login.getPassword());
        st.setInt(3,login.getRole());
        st.executeUpdate();


        ResultSet rs = st.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return  -1;
    }
}
