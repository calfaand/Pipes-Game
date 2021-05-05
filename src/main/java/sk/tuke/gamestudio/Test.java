package sk.tuke.gamestudio;

import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;

public class Test {
    public static void main(String[] args) throws Exception {

        ScoreService service = new ScoreServiceJDBC();
        System.out.println(service.getTopScores("pipes"));
        service.reset();

      //  Score score= new Score("pipes", "peto", 3, new Date());
      //  service.addScore(score);

//
//        CommentService service= new CommentServiceJDBC();
//        Comment comment =  new Comment("pipes", "jano", "avg", new Date());
//        service.addComment(comment);
//        System.out.println(service.getComments("pipes"));

//        RatingService service = new RatingServiceJDBC();
//        Rating rating=new Rating("pipes", "jano", 5, new Date());
//        service.setRating(rating);
//        Rating rating1=new Rating("pipes", "mato", 3, new Date());
//        service.setRating(rating1);
       // System.out.println(service.getAverageRating("pipes"));
        //service.getRating("pipes", "mato");


    }
}
