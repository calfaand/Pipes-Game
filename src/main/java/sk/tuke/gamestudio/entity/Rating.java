package sk.tuke.gamestudio.entity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Rating.resetRating",query = "delete from Rating"),
    @NamedQuery(name = "Rating.getRating", query = "select r from Rating  r where r.game =: game AND  r.player =: player "),
    @NamedQuery(name = "Rating.getAverageRating",query = "select avg(s.rating) FROM Rating s WHERE s.game=:game"),
    @NamedQuery(name = "Rating.selectRating", query = "select r from Rating r where r.game=: game AND r.player=: player"),
    @NamedQuery(name = "Rating.delRating", query = "delete  from Rating r where r.game=: game AND r.player=: player"),


})



@SpringBootApplication

public class Rating implements Serializable {

    @Id
    private String game;
    @Id
    private String player;

    private int rating;
    private Date ratedAt;


    public Rating(){

    }

    public Rating(String game, String player, int rating, Date ratedAt) {
        this.game = game;
        this.player = player;
        this.rating = rating;
        this.ratedAt = ratedAt;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getRatedAt() {
        return ratedAt;
    }

    public void setRatedAt(Date ratedAt) {
        this.ratedAt = ratedAt;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", rating=" + rating +
                ", ratedAt=" + ratedAt +
                '}';
    }
}
