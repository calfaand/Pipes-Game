package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.entity.UserLogin;
import sk.tuke.gamestudio.game.pipes.core.Field;
import sk.tuke.gamestudio.game.pipes.core.GameState;
import sk.tuke.gamestudio.game.pipes.core.Tile;
import sk.tuke.gamestudio.game.pipes.entity.GamePlay;
import sk.tuke.gamestudio.service.*;

import java.util.Date;
import java.util.Random;

import static java.lang.Integer.parseInt;


@Controller
@RequestMapping("/pipes")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PipesController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserController userController;

    private boolean win;
    private boolean lose;

    private Field field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level1_0.txt");


    @RequestMapping
    public String pipes(@RequestParam(required = false) String row,@RequestParam(required = false) String column, Model model,@RequestParam(required = false) String difficulty ) throws RatingException, CommentException {
        win=false;
        lose=false;

       try{
           if(field.getState()== GameState.PLAYING){
               field.rotate(parseInt(row), parseInt(column));


                if(field.getState() == GameState.SOLVED && userController.isLogged()) {
                    win=true;
                   scoreService.addScore(new Score("pipes", userController.getLoggedUser().getUsername(), field.getScore(), new Date()));
               }}
                if(field.getState()==GameState.FAILED && userController.isLogged()){
                    lose=true;
                }

       }catch (Exception e){
           //ked je chyba nech nespadne server
       }
        prepareModel(model);

       return "pipes" ;
    }

    @RequestMapping("/saveGame")
    public String saveGame(){
        int rowC=field.getRowCount();
        int colC=field.getColumnCount();
        int remM=field.getRemainingMoves();
        String logged=userController.getLoggedUser().getUsername();
        GamePlay gameplay = new GamePlay(rowC, colC, remM,logged);
      //  for (int row = 0; row < field.getRowCount(); row++) {
           // for(int col=0; col<field.getColumnCount(); col++){
               // Tile tile = field.getTile(row, col);
                //gameplay.addMapOfField(tile);

                gameplay.addMapOfField(field);


          //  }
          //}
        System.err.println(gameplay);
        return "pipes";
    }

    @RequestMapping("/new")
    public String newGame(Model model,@RequestParam(required = false) String difficulty) throws RatingException, CommentException {
        win=false;
        lose=false;
        Random random= new Random();
        int rand=random.nextInt(2);

        if(difficulty!=null){
            switch (difficulty) {           //rovno urobi return
                case "1":
                    switch (rand) {
                        case 0 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level1_0.txt");
                        case 1 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level1_1.txt");
                    }
                    break;
                case "2":
                    switch (rand) {
                        case 0 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level2_0.txt");
                        case 1 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level2_1.txt");
                    }
                    break;
                case "3":
                    switch (rand) {
                        case 0 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level3_0.txt");
                        case 1 -> field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level3_1.txt");
                    }
                    break;
            }
            }

        else
            field = new Field("C:\\Users\\andre\\OneDrive\\Počítač\\gamestudio-4516\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\pipes\\core\\levels\\Level1_0.txt");
        prepareModel(model);
        return "pipes";
    }




    public String getHtmlField(){
        win=false;
        lose=false;
        StringBuilder sb= new StringBuilder();
        sb.append("<table>\n");

        for (int row = 0; row < field.getRowCount(); row++) {
            sb.append("<tr>\n");
            for (int column = 0; column < field.getColumnCount(); column++) {
                Tile tile = field.getTile(row,column);
                if(row==0&& column ==0 || row== field.getRowCount()-1 && column== field.getColumnCount()-1){        //just to get that start and end tile picture
                    sb.append("<td>");
                    sb.append(String.format("<a href='/pipes?row=%d&column=%d'>\n",row, column));
                    sb.append("<img src='/images/pipes/stend.png'>");
                    sb.append("</a>\n");
                    sb.append("</td>\n");
                    continue;
                }
                sb.append("<td>");
                sb.append(String.format("<a href='/pipes?row=%d&column=%d'>\n",row, column));
                sb.append("<img src='/images/pipes/" +  getImageName(tile) +".png'>");
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    private void prepareModel(Model model)throws CommentException, RatingException{

        model.addAttribute("scores", scoreService.getTopScores("pipes") );
        model.addAttribute("comment", commentService.getComments("pipes"));
        model.addAttribute("rating", ratingService.getAverageRating("pipes"));

    }

    private String getImageName(Tile tile){
        switch (tile.getState()){
            case SIMPLE :
                return "simple";
            case SIMPLE1:
                return "simple1";
            case BENDED:
                return "bended";
            case BENDED1:
                return "bended1";
            case BENDED2:
                return "bended2";
            case BENDED3:
                return "bended3";
            default:
                throw  new IllegalArgumentException("unsupported tile state "+ tile.getState());
        }
    }

    public boolean isWin(){
        return win;
    }

    public boolean isLose() {
        return lose;
    }
    public int getRemainingMoves(){
        return field.getRemainingMoves();

    }

    @RequestMapping("/addComms")
    public String addRatComms(String rating, String contentOfComment, Model model) throws RatingException, CommentException {
        System.out.println(contentOfComment);


        if(rating!=null){
            ratingService.setRating(new Rating("pipes", userController.getLoggedUser().getUsername(), parseInt(rating),new Date()));
        }

        if(contentOfComment!=null ){
            if(!contentOfComment.equals("")) {
                commentService.addComment(new Comment("pipes", userController.getLoggedUser().getUsername(), contentOfComment, new Date()));
            }
        }

        model.addAttribute(ratingService);
        model.addAttribute(commentService);

        return "redirect:/pipes";
    }




}
