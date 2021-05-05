package sk.tuke.gamestudio.game.pipes.consoleui;


import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.game.pipes.core.Tile;
import sk.tuke.gamestudio.game.pipes.core.Field;
import sk.tuke.gamestudio.game.pipes.core.GameState;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.*;
import sk.tuke.gamestudio.service.*;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {
    private  Field field;
    private final Scanner scanner = new Scanner(System.in);
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final Pattern INPUT_PATTERN = Pattern.compile("([R])([A-I])([1-9])");
  //  private ScoreService scoreService = new ScoreServiceJDBC();
    @Autowired
    private RatingService ratingService ;
   // private CommentServiceJDBC commentService= new CommentServiceJDBC();
    private int moves;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserLoginService userLoginService;


    public ConsoleUI(Field field,ScoreService scoreService, RatingService ratingService, CommentService commentService) {
        this.field = field;
        this.scoreService= scoreService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }
    public  ConsoleUI(){

    }

//    public ConsoleUI(Field field){
//        this.field= field;
//    }
    public void printField() {

        for (int column = 0; column < field.getColumnCount(); column++) {
            System.out.print("  ");
            System.out.print(column + 1);
        }
        System.out.println();

        for (int row = 0; row < field.getRowCount(); row++) {
            System.out.print((char) (row + 'A'));
            for (int column = 0; column < field.getColumnCount(); column++) {
                Tile tile = field.getTile(row, column);
                System.out.print(" ");

                switch (field.getTile(row, column).getState()) {
                    case SIMPLE:
                        System.out.print(ANSI_BLUE + "= " + ANSI_RESET);
                        break;
                    case SIMPLE1:
                        System.out.print(ANSI_BLUE + "║ " + ANSI_RESET);
                        break;
                    case BENDED2:
                        System.out.print(ANSI_BLUE + "╗ " + ANSI_RESET);
                        break;
                    case BENDED3:
                        System.out.print(ANSI_BLUE + "╝ " + ANSI_RESET);
                        break;
                    case BENDED:
                        System.out.print(ANSI_BLUE + "╚ " + ANSI_RESET);
                        break;
                    case BENDED1:
                        System.out.print(ANSI_BLUE + "╔ " + ANSI_RESET);
                        break;
                }
            }
            System.out.println();
        }
    }

    private void processInput() {
        System.out.print("Enter command (X - exit, RA1 - rotate): ");
        //String line = scanner.nextLine().toUpperCase();
        String line = new Scanner(System.in).nextLine().trim().toUpperCase();

        if ("X".equals(line))
            System.exit(0);

        Matcher matcher = INPUT_PATTERN.matcher(line);

        if (matcher.matches()) {

                int row = matcher.group(2).charAt(0) - 'A';
                int column = Integer.parseInt(matcher.group(3)) - 1;

                if ("R".equals(matcher.group(1))) {
                    field.rotate(row, column);
                }

        }
        else {
            System.err.println("BAD COMMAND!!! "+line);

        }

    }

//    -------------------------------------------------------------------------------------


    private void chooseLevel(){
        String input;
        int level=0;
        Random random= new Random();
        int rand= random.nextInt(2);

        Scanner sc= new Scanner(System.in);

        while(level>3 || level<1){
            System.out.println("Choose level (1, 2 or 3)");
            input= sc.next();
            try{
                level=Integer.parseInt(input);
            }
            catch(NumberFormatException ex){
                System.out.println("Please enter valid input");
            }

        }
        switch (level){
            case 1:
                switch (rand) {
                    case 0 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level1_0.txt");
                    case 1 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level1_1.txt");
                }
                 break;
            case 2:
                switch (rand) {
                    case 0 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level2_0.txt");
                    case 1 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level2_1.txt");
                }
                break;
            case 3:
                switch (rand) {
                    case 0 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level3_0.txt");
                    case 1 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level3_1.txt");
                }
                break;
        }

    }






//   -------------------------------------------------------------------------------------
    public void play() throws RatingException, CommentException {

//        System.out.println("TOP SCORES:");
//        System.out.println(scoreService.getTopScores("pipes"));
        chooseLevel();
        do {
            printField();
            processInput();
        } while (field.getState() == GameState.PLAYING);

        printField();

        if (field.getState() == GameState.FAILED) {
            System.out.println("Game failed!");
            System.exit(0);

        }
        else {
//            System.out.println("Game solved!");
//            System.out.println("YOUR SCORE "+ field.getScore());
//            scoreService.addScore(new Score("pipes", System.getProperty("user.name"), field.getScore(), new Date()));
//            ratingService.setRating(new Rating("pipes", System.getProperty("user.name"), publishRating(), new Date()));
//            commentService.addComment(new Comment("pipes", System.getProperty("user.name"), publishComment(), new Date()));
//            System.out.println("TOP SCORES:");
//            System.out.println(scoreService.getTopScores("pipes"));
//            System.out.println("RATING:");
//            System.out.print("RATING OF PLAYER "+ System.getProperty("user.name") + " IS " );
//            System.out.println(ratingService.getRating("pipes", System.getProperty("user.name")));
//
//            System.out.print( "AVG RATING IS ");
//            System.out.println(ratingService.getAverageRating("pipes"));
//            System.out.println("COMMENTS");
//            System.out.println(commentService.getComments("pipes"));

            System.exit(0);

        }
    }

    public int publishRating(){
        Scanner scanner =new Scanner(System.in);
        int rat = 0;
        System.out.println("Rate this game: ");
        try {
            rat = scanner.nextInt();
            while(rat<0 || rat >5){
                System.out.println("Rate this game: ");
                rat = scanner.nextInt();
            }
        }
        catch(Exception e) {
            System.out.println("daco je zle");
        }

        return rat;

    }
    public String publishComment(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Make short comment about this game: ");
        String comm= scanner.nextLine();

        return comm;
    }

}
