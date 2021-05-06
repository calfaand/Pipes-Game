package sk.tuke.gamestudio.game.pipes.entity;

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
    private Tile tile;
    private Field field;

   // private List<StateType> Statetype ;  list celeho pola v aktualnej pozicii

    public GamePlay(){

    }

//int row, int col, int moves

//    public GamePlay(Field field) {
//        this.rowCount = field.getRowCount();
//        this.columnCount = field.getColumnCount();
//        this.remainingMoves=field.getRemainingMoves();
//    }

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

    public List<PipeState> addMapOfField(Tile tile){

        states=new ArrayList<>();

        for(int i=0; i<rowCount; i++){
            for (int j=0; j< columnCount; j++){
                states.add(field.getTile(i,j).getState());
            }
        }

        return states;
    }


    @Override
    public String toString() {
        return "GamePlay{" +
                "rowCount=" + rowCount +
                ", columnCount=" + columnCount +
                ", remainingMoves=" + remainingMoves +
                ", states=" + states +
                ", tile=" + tile +
                ", field=" + field +
                '}';
    }
}
