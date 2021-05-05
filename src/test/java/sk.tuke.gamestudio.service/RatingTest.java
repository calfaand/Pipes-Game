package test;

import org.junit.Test;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingException;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class RatingTest {


    @Test
    public void testReset() throws RatingException {
        RatingService service = new RatingServiceJDBC();
        service.reset();
        assertEquals(0, service.getRating("pipes", "player"));
    }

    @Test
    public void testSetScore() throws RatingException {
        RatingService service = new RatingServiceJDBC();
        service.reset();
        Date date = new Date();

        service.setRating(new Rating("pipes", "Andrej",4,new Date()));
        service.setRating(new Rating("pipes", "Andrej",2,date));
        int ratings = service.getRating("pipes", "Andrej");

        assertEquals(ratings, 2);

    }
    @Test
    public void testAvgRating() throws RatingException {
        RatingService service = new RatingServiceJDBC();
        service.reset();
        Date date = new Date();


        service.setRating(new Rating("pipes", "Milan",4,new Date()));
        service.setRating(new Rating("pipes", "Andrej",2,new Date()));

        int avgRating = service.getAverageRating("pipes");

        assertEquals(avgRating,3);

    }

    @Test
    public void testGetRating() throws RatingException {
        RatingService service = new RatingServiceJDBC();
        service.reset();
        Date date = new Date();


        service.setRating(new Rating("pipes", "Andrej",4,date));
        service.setRating(new Rating("pipes", "Peter",2,date));
        service.setRating(new Rating("pipes", "Milan",1,date));
        service.setRating(new Rating("pipes", "Jano",5,date));


        assertEquals(4, service.getRating("pipes", "Andrej"));
        assertEquals(2, service.getRating("pipes", "Peter"));
        assertEquals(1, service.getRating("pipes", "Milan"));
        assertEquals(5, service.getRating("pipes", "Jano"));


    }









}
