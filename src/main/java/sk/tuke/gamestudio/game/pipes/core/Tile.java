package sk.tuke.gamestudio.game.pipes.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tile {

    @Id
    @GeneratedValue
    private int ident;

    private PipeState state;

    public PipeState getState() {
        return state;
    }

    public void setState(PipeState state) {      //set tile state
        this.state = state;
    }


    public void rotateTile() {
        switch (state) {
            case SIMPLE:
                this.state = PipeState.SIMPLE1;      // - |
                break;
            case SIMPLE1:
                this.state = PipeState.SIMPLE;       // | -
                break;
            case BENDED:
                this.state = PipeState.BENDED1;      // up-right --> right-down
                break;
            case BENDED1:
                this.state = PipeState.BENDED2;      //right-down --> left-down
                break;
            case BENDED2:
                this.state = PipeState.BENDED3;      //left-down --> left-up
                break;
            case BENDED3:
                this.state = PipeState.BENDED;       //left-up --> up-right
                break;
            default:
                throw new IllegalArgumentException("Unsupported tile state");
        }
    }

    public Direction getSecondDirection(Direction direction) throws Exception {

        switch (state) {
            case SIMPLE1: {
                // - |
                return switch (direction) {
                    case UP -> Direction.DOWN;
                    case DOWN -> Direction.UP;
                    case LEFT -> throw new Exception("bad direction");
                    case RIGHT -> throw new Exception("bad direction");

                };

            }
            case SIMPLE: {            // | -
                return switch (direction) {
                    case UP -> throw new Exception("bad direction");
                    case DOWN -> throw new Exception("bad direction");
                    case LEFT -> Direction.RIGHT;
                    case RIGHT -> Direction.LEFT;

                };

            }
            case BENDED: {
                // up-right
                return switch (direction) {
                    case UP -> Direction.RIGHT;
                    case DOWN -> throw new Exception("bad direction");
                    case LEFT -> throw new Exception("bad direction");
                    case RIGHT -> Direction.UP;

                };


            }
            case BENDED1: {
                //right-down
                return switch (direction) {
                    case UP -> throw new Exception("bad direction");
                    case DOWN -> Direction.RIGHT;
                    case LEFT -> throw new Exception("bad direction");
                    case RIGHT -> Direction.DOWN;

                };

            }
            case BENDED2: {
                //left-down
                return switch (direction) {
                    case UP -> throw new Exception("bad direction");
                    case DOWN -> Direction.LEFT;
                    case LEFT -> Direction.DOWN;
                    case RIGHT -> throw new Exception("bad direction");

                };


            }
            case BENDED3: {
                //left-up
                return switch (direction) {
                    case UP -> Direction.LEFT;
                    case DOWN -> throw new Exception("bad direction");
                    case LEFT -> Direction.UP;
                    case RIGHT -> throw new Exception("bad direction");

                };

            }
            default:
                throw new IllegalArgumentException("Unsupported previous state");


        }

    }

    @Override
    public String toString() {
        return "Tile{" +
                "state=" + state +
                '}';
    }
}
