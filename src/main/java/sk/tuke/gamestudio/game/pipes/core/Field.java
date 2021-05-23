package sk.tuke.gamestudio.game.pipes.core;


import javax.persistence.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

@Entity
public class Field {
    private GameState state= GameState.PLAYING;

    @Id
    @GeneratedValue
    private int ident;

    private int actScore;

    private  int rowCount;
    private  int columnCount;
    private int remainingMoves;

//    @Transient
//    private Tile[][] field;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TilePos> tilePos;



    @OneToMany
    private List<Tile> tiles;





    private final long startMillis =  System.currentTimeMillis();

    public Field(int rowCount, int columnCount, int remainingMoves) {
        tiles= new ArrayList<>();

        this.rowCount=rowCount;
        this.columnCount=columnCount;
        this.remainingMoves=remainingMoves;
//        field=new Tile[rowCount][columnCount];
//        generate();

    }
    public Field(){

    }

    public  Field(String path){         //toto je aktualny konstruktor
        tiles= new ArrayList<>();

        File level= new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(level);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;
        rowCount = sc.nextInt();
        columnCount = sc.nextInt();
        remainingMoves = sc.nextInt();
//        this.field = new Tile[rowCount][columnCount];
        this.tilePos = new LinkedList<>();


        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Tile tile = new Tile();
                tile.setState(getPipe(sc.nextInt()));
                tilePos.add(new TilePos(i,j,tile));
//                field[i][j] = new Tile();
//                field[i][j].setState(getPipe(sc.nextInt()));
            }
        }

    }








    public int getRemainingMoves() {
        //gamePlay.setRemainingMoves(remainingMoves);         //urcite tu nastavovat tie rem.moves ?
        return remainingMoves;
    }

    private PipeState getPipe(int i){
        switch (i){
            case 0:return PipeState.SIMPLE;
            case 1:return PipeState.SIMPLE1;
            case 2:return PipeState.BENDED;
            case 3:return PipeState.BENDED1;
            case 4:return PipeState.BENDED2;
            case 5:return PipeState.BENDED3;
            default:
                System.out.println("Wrong input");
                return PipeState.SIMPLE;
        }
    }

//    public void generate(){
//
//        Random random = new Random();
//        for(int i=0; i< rowCount; i++) {
//            for (int j = 0; j < columnCount; j++) {
//                field[i][j]= new Tile();
//                int rand = random.nextInt(6);
//                    switch(rand){
//                        case 0:
//                            field[i][j].setState(PipeState.SIMPLE);
//                            break;
//                        case 1:
//                            field[i][j].setState(PipeState.SIMPLE1);
//                            break;
//                        case 2:
//                            field[i][j].setState(PipeState.BENDED);
//                            break;
//                        case 3:
//                            field[i][j].setState(PipeState.BENDED1);
//                            break;
//                        case 4:
//                            field[i][j].setState(PipeState.BENDED2);
//                            break;
//                        case 5:
//                            field[i][j].setState(PipeState.BENDED3);
//                            break;
//                        default:
//                            System.out.println("random error");
//                            break;
//
//                    }
//            }
//        }
//
//        field[0][0].setState(PipeState.SIMPLE1);                            //START
//        field[rowCount-1][columnCount-1].setState(PipeState.SIMPLE1);       //END TILE   SIMPLE1
//
//
//    }

    public static Direction getOpositeDircetion(Direction direction) {
        return switch (direction) {
            case RIGHT -> Direction.LEFT;
            case LEFT -> Direction.RIGHT;
            case DOWN -> Direction.UP;
            case UP ->Direction.DOWN;
        };
    }

    private Tile getTilePos(int i, int j){
        for (TilePos tile: tilePos) {
            if(i==tile.getX() && j== tile.getY()){
                return tile.getField();
            }
        }
        return null;
    }


    private void isSolved(){
        int i=1, j=0;
        Direction direction= Direction.UP ;
       // Direction nextDirection ;
        do{
           // System.out.println(i +" " +j + " " + direction);
            try {
                //direction = field[i][j].getSecondDirection(direction);
                direction=getTilePos(i,j).getSecondDirection(direction);

            }
            catch (Exception e){
                break;
            }
           // System.out.println(direction);
            switch (direction){
                case UP -> i--;
                case DOWN -> i++;
                case LEFT -> j--;
                case RIGHT -> j++;
            }
            direction=getOpositeDircetion(direction);
           // System.out.println(direction);
            if(i==rowCount-1 && j==columnCount-1){
                setState(GameState.SOLVED);
            }

        }while(true);


    }



    public Tile getTile(int x, int y){
       // return field[x][y];
        return getTilePos(x,y);
    }

    public void rotate(int x, int y){
        if(((x==0 && y==0 )||(x==rowCount-1 && y==columnCount-1 )) || x>getRowCount() || y> getColumnCount() ){
            return;
        }
//        field[x][y].rotateTile();
        getTilePos(x,y).rotateTile();
        decrementMoves();
        isSolved();

    }
    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }


    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }


    private void decrementMoves(){
        remainingMoves--;
        if (remainingMoves==0){
            state=GameState.FAILED;
        }
    }

    public GameState getState() {
        return state;
    }

    private void setState(GameState state) {
        this.state = state;
    }

    public int getActScore() {
        return actScore;
    }

    public void setActScore(int actScore) {
        this.actScore = actScore;
    }

    public int getScore(){
        int score =(rowCount* columnCount)*10 -(int)(System.currentTimeMillis()-startMillis)/1000;
        actScore=score;
        if(score <0)
            return 0;
        else
            return score;

    }


}
