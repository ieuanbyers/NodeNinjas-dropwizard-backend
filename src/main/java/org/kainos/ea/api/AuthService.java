package org.kainos.ea.api;

import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.db.OrderDao.AuthDao;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao = new AuthDao();

    public String login (Login login) throws FailedtoGenerateTokenException, FailedToLoginException {
        if(authDao.validLogin(login))
        {
            try{
                return authDao.generateToken(login.getUsername());
            }
            catch (SQLException e)
            {
                throw new FailedtoGenerateTokenException();
            }
        }
        throw new FailedToLoginException();
    }
    public int Register(Login log) throws FailedToCreateLoginException, InvalidLoginException {
        try {
            int id = authDao.Register(log);
            if (id == 1) {
                throw new FailedToCreateLoginException();

            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateLoginException();
        }
    }

}
