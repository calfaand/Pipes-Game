package sk.tuke.gamestudio.entity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;

//select r from Rating  r where r.game =: game AND  r.player =: player "
@NamedQuery(name= "UserLogin.getPassword",
            query = "select l from UserLogin l where l.username=: username" //mozno spadne, treba login iba a nie userlog
)


@SpringBootApplication
@Entity
public class UserLogin implements Serializable {

    private String username;
    private String password;



    @GeneratedValue
    @Id
    private int ident;

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }


    public UserLogin(){

    }

    public UserLogin(String username, String password){
        this.username=username;
        this.password=password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {          //mozno vymaz potom
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
