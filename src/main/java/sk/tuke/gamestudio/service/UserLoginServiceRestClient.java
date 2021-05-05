package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.UserLogin;

import java.util.Arrays;
import java.util.List;

//= "https://localhost:8080/api";

public class UserLoginServiceRestClient implements UserLoginService {


    @Value("${remote.server.api}")
    private  String url;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void setUserLogin(UserLogin userLogin) throws Exception {
            restTemplate.postForEntity(url+"/userlogin",userLogin,UserLogin.class);
    }

    @Override
    public String getUserLoginPassword(String username) {
        return restTemplate.getForEntity(url+"/userlogin"+username, String.class).getBody();
    }



}
