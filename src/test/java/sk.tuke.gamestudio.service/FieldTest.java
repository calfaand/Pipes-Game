package sk.tuke.gamestudio.service;

import org.junit.Test;

import sk.tuke.gamestudio.game.pipes.core.Field;
import sk.tuke.gamestudio.game.pipes.core.GameState;
import sk.tuke.gamestudio.game.pipes.core.PipeState;


import java.util.Random;

import static org.junit.Assert.assertTrue;

public class FieldTest {

    private Random randomGenerator = new Random();
    private Field field;
    private int rowCount;
    private int columnCount;
    private int moves;

    public FieldTest() {
        rowCount = randomGenerator.nextInt(10) + 5;
        columnCount = rowCount;
        moves = 10;

        field = new Field(rowCount, columnCount, moves);

    }

    @Test
    public void usingAllMoves(){
        field = new Field(rowCount, columnCount, moves);

        for (int i = 0; i < moves; i++){
            field.rotate(2,2);
        }
        System.out.println(field.getState());
        assertTrue (field.getState() == GameState.FAILED);

    }
    @Test
    public void turnSimple(){
        field= new Field(rowCount,columnCount, moves);
        int i;
        int j=0;

        for( i=1; i<rowCount; i++){
            for( j=0; j< columnCount; j++){
                if(field.getTile(i,j).getState()== PipeState.SIMPLE){
                    field.rotate(i,j);
                    field.rotate(i,j);
                    assertTrue(field.getTile(i,j).getState()== PipeState.SIMPLE);

                }
            }
        }
    }

    @Test
    public void turnBended(){
        field= new Field(rowCount,columnCount, moves);
        int i;
        int j=0;

        for( i=1; i<rowCount; i++){
            for( j=0; j< columnCount; j++){
                if(field.getTile(i,j).getState()== PipeState.BENDED){
                    field.rotate(i,j);
                    field.rotate(i,j);
                    field.rotate(i,j);
                    field.rotate(i,j);
                    assertTrue(field.getTile(i,j).getState()== PipeState.BENDED);

                }
            }
        }
    }
    @Test
    public void turnBended1(){
        field= new Field(rowCount,columnCount, moves);
        int i;
        int j=0;

        for( i=1; i<rowCount; i++){
            for( j=0; j< columnCount; j++){
                if(field.getTile(i,j).getState()== PipeState.BENDED1){
                    field.rotate(i,j);
                    field.rotate(i,j);
                    field.rotate(i,j);
                    field.rotate(i,j);
                    assertTrue(field.getTile(i,j).getState()== PipeState.BENDED1);

                }
            }
        }
    }

    @Test
    public void winScenario(){
        field= new Field(3,3,0);
        field.getTile(1,0).setState(PipeState.BENDED);
        field.getTile(1,1).setState(PipeState.SIMPLE);
        field.getTile(1,2).setState(PipeState.BENDED2);

//        ConsoleUI console= new ConsoleUI(field);
//        console.printField();
        field.rotate(1,1);      //musi to byt inak pada
        field.rotate(1,1);

        assertTrue(field.getState()==GameState.SOLVED);


    }


}
