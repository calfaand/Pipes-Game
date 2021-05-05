package sk.tuke.gamestudio.entity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;

@NamedQuery(name = "Comment.getComments",
        query = "select c from Comment c order by c.commentedAt desc"
)
@NamedQuery(name = "Comment.resetComments",
        query = "delete from Comment"
)


@SpringBootApplication
@Entity
public class Comment {
    private String game;
    private String player;
    private Date commentedAt;
    private String comment;

    @GeneratedValue
    @Id
    private int ident;

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public Comment(){

    }
    public Comment(String game,String player, String comment, Date playedAt){
        this.game=game;
        this.player=player;
        this.comment=comment;
        this.commentedAt =playedAt;
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

    public String getComment() {
        return comment;
    }


    public Date getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(Date commentedAt) {
        this.commentedAt = commentedAt;
    }


    @Override
    public String toString() {
        return "Comment Of Game{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", comment= '"+ comment + '\'' +
                ", commentedAt= " +commentedAt +
                '}';
    }


}
