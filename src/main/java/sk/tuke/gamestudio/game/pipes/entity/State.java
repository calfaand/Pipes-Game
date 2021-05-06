package sk.tuke.gamestudio.game.pipes.entity;

import sk.tuke.gamestudio.game.pipes.core.PipeState;

import javax.persistence.*;

public class State {


    private int row;
    private int col;

    private StateType type;

    public State(){
    }
    public State (StateType type, int row, int col){
        this.type=type;
        this.row=row;
        this.col=row;
    }


    public void setType(StateType type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "State{" +

                ", type=" + type +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
