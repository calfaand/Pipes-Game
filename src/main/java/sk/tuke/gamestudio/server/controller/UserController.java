package sk.tuke.gamestudio.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.UserLogin;
import sk.tuke.gamestudio.game.pipes.entity.GamePlay;
import sk.tuke.gamestudio.service.*;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)

public class UserController {


    private UserLogin loggedUser;
    private boolean neuspesne;
    private boolean registered;

    @Autowired
    private UserLoginService userLoginService= new UserLoginServiceJPA();



    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(String username, String password) throws NoSuchAlgorithmException {
        registered=false;

//        String myHash = hash(password);

        String pw= userLoginService.getUserLoginPassword(username);
        if(pw.equals("")|| !hash(password).equals(pw)  ){//  !myHash.equals(pw)
            neuspesne=true;
        }else{
            String myHash = hash(password);
            neuspesne=false;

            if(myHash.equals(pw)){

                loggedUser= new UserLogin(username, password);
                return "redirect:/pipes";
            }
        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(){
        registered=false;
        loggedUser= null;

        return "redirect:/";
    }
    public UserLogin getLoggedUser() {
        return loggedUser;
    }

    public boolean isLogged(){
        return loggedUser!=null;

    }
    public  boolean isNeuspesne(){
        return neuspesne;
    }
    public boolean isRegistered(){
        return registered;
    }

    @RequestMapping("/register")
    public String register(UserLogin userlogin) throws Exception {


        String myHash = hash(userlogin.getPassword());     //zahashujem pw
        userlogin.setPassword(myHash);

        userLoginService.setUserLogin(userlogin);
        registered=true;

        return "redirect:/";

    }

//    @RequestMapping("/saveGame")
//    public String saveGame(GamePlay gamePlay){
//
//
//
//    }



    public  String hash(String password) throws NoSuchAlgorithmException {

        String hash = "35454B055CC325EA1AF2126E27707052";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return myHash;
    }









}
