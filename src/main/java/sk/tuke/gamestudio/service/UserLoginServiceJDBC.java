package sk.tuke.gamestudio.service;

import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.UserLogin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserLoginServiceJDBC implements UserLoginService {

    public static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PW = "postgres";
    public static final String SELECT = "SELECT username, password FROM user_login WHERE username = ?";
    public static final String INSERT = "INSERT INTO user_login(username, password) VALUES (?, ?)";


    @Override
    public void setUserLogin(UserLogin userLogin) throws Exception {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(INSERT);
        ) {
            statement.setString(1, userLogin.getUsername());
            statement.setString(2, userLogin.getPassword());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("sql login insert error");
        }
    }


    @Override
    public String getUserLoginPassword(String username) {

        String pw="";
        List<UserLogin> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1,username);
            ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    UserLogin userLogin= new UserLogin();
                    userLogin.setUsername(rs.getString("username"));
                    userLogin.setPassword(rs.getString("password"));
                    res.add(userLogin);
                }

                for (UserLogin r :res){
                    if(r.getUsername().equals(username)){
                        pw=r.getPassword();
                    }
                }

        } catch (SQLException e) {
            throw new GamestudioException("login selecting error", e);
        }
        return pw;      //malo by vratit heslo
    }


}
