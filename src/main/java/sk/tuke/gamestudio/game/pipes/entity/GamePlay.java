package sk.tuke.gamestudio.game.pipes.entity;

import sk.tuke.gamestudio.entity.UserLogin;
import sk.tuke.gamestudio.game.pipes.core.Field;
import sk.tuke.gamestudio.game.pipes.core.PipeState;
import sk.tuke.gamestudio.game.pipes.core.Tile;

import java.util.ArrayList;
import java.util.List;

public class GamePlay {

    private int rowCount;
    private int columnCount;
    private int remainingMoves;
    private List<PipeState> states;
    private Field field;
    private String username;


    public GamePlay(){}


    public GamePlay(int r, int c, int m,String name) {
        this.rowCount = r;
        this.columnCount = c;
        this.remainingMoves=m;
        this.username=name;
        field=new Field(rowCount,columnCount,remainingMoves);
    }



    public List<PipeState> addMapOfField(Field field){

        states=new ArrayList<>();

        for(int i=0; i<rowCount; i++){
            for (int j=0; j< columnCount; j++){
                states.add(field.getTile(i,j).getState());
            }
        }

        return states;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getRemainingMoves() {
        return remainingMoves;
    }

    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }

    @Override
    public String toString() {
        return "GamePlay{" +
                "rowCount=" + rowCount +
                ", columnCount=" + columnCount +
                ", remainingMoves=" + remainingMoves +
                ", field=" + field +
                '}';
    }
}
