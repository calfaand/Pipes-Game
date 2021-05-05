package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.UserLogin;
import sk.tuke.gamestudio.service.UserLoginService;


@RestController
@RequestMapping("/api/userlogin")

public class UserLoginServiceRest implements UserLoginService {

    @Autowired
    private UserLoginService userLoginService;

    //localhost:8080/api/userlogin ....."username" :    , "password":
    @PostMapping
    public void setUserLogin(@RequestBody UserLogin userLogin) throws Exception {
        userLoginService.setUserLogin(userLogin);
    }

    @GetMapping("/{name}")
    public String getUserLoginPassword(@PathVariable String name){
        System.err.println(userLoginService.getUserLoginPassword(name));
        return userLoginService.getUserLoginPassword(name);
    }

}
