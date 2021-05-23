package sk.tuke.gamestudio.game.pipes.entity;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.pipes.core.Field;
import sk.tuke.gamestudio.game.pipes.core.PipeState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedQuery( name= "Gameplay.getGamepaly",
        query="SELECT g FROM GamePlay g WHERE g.id =: id"
)

@SpringBootApplication
@Entity

public class GamePlay implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    private Field field ;

    @ElementCollection
    private List<PipeState> states;


    public GamePlay(){}


    public GamePlay(String name) {// Field field

        this.username=name;
      //  this.field=field;
    }


    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PipeState> addMapOfField(Field field){

        states=new ArrayList<>();

        for(int i=0; i<field.getRowCount(); i++){
            for (int j=0; j< field.getColumnCount(); j++){
                states.add(field.getTile(i,j).getState());
            }
        }
        setField(field);

        return states;
    }

//    public Field getField() {
//        return field;
//    }


    @Override
    public String toString() {
        return "GamePlay{" +
                //"field=" + field +
                ", states=" + states +
                ", username='" + username + '\'' +
                '}';
    }
}
