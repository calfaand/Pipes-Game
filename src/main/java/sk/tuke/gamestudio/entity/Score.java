package sk.tuke.gamestudio.entity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Date;

//CREATE TABLE score (game VARCHAR(32) NOT NULL, player VARCHAR(32) NOT NULL, points INTEGER NOT NULL, playedat TIMESTAMP NOT NULL)

@NamedQuery(name = "Score.getTopScores",
        query = "select s from Score s order by s.points desc"
)
@NamedQuery(name = "Score.resetScores",
        query = "delete from Score"
)

@SpringBootApplication

@Entity
public class Score implements Serializable {
    private String game;
    private String player;
    private int points;
    private Date playedOn;

    @GeneratedValue
    @Id
    private int ident;

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public Score() {

    }

    public Score(String game, String player, int points, Date playedAt) {
        this.game = game;
        this.player = player;
        this.points = points;
        this.playedOn = playedAt;

    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(Date playedOn) {
        this.playedOn = playedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }


}

