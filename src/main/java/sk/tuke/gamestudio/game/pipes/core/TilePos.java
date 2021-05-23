package sk.tuke.gamestudio.game.pipes.core;

import javax.persistence.*;

@Entity
public class TilePos {

    @Id
    @GeneratedValue
    private Long id;

    public TilePos() {

    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public TilePos(Long id) {
        this.id = id;
    }

    public TilePos(int x, int y, Tile field) {
        this.x = x;
        this.y = y;
        this.field = field;
    }

    private int x,y;

    @OneToOne(cascade = {CascadeType.ALL})
    private Tile field;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @OneToOne
    public Tile getField() {
        return field;
    }

    public void setField(Tile field) {
        this.field = field;
    }
}
