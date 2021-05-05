package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.UserLogin;

import java.util.List;

public interface UserLoginService {

    void setUserLogin(UserLogin userLogin) throws Exception;
    String getUserLoginPassword(String username);
}
