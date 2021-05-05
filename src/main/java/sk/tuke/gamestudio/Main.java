package sk.tuke.gamestudio;

import sk.tuke.gamestudio.service.*;
import sk.tuke.gamestudio.game.pipes.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.pipes.core.Field;

public class Main {
    public static void main(String[] args) throws RatingException, CommentException {

        //Field field = new Field(3,3,9);
       // ConsoleUI ui = new ConsoleUI(field);
        ConsoleUI ui= new ConsoleUI();
        ui.play();




    }

}
