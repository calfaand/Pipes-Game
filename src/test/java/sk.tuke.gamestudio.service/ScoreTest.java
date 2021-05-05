package test;

import org.junit.Test;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;

import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ScoreTest {

    @Test
    public void testReset(){
        ScoreService service = new ScoreServiceJDBC();
        service.reset();
        assertEquals(0, service.getTopScores("pipes").size());
    }



    @Test
    public void testAddScore(){
        ScoreService service = new ScoreServiceJDBC();
        service.reset();
        Date date = new Date();
        service.addScore(new Score("pipes", "Andrej",3, date));

        List<Score> scores = service.getTopScores("pipes");

        assertEquals(1, scores.size());
        assertEquals("pipes", scores.get(0).getGame());
        assertEquals("Andrej", scores.get(0).getPlayer());
        assertEquals(3, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedOn());

    }

    @Test
    public void testGetTopScores(){
        ScoreService service = new ScoreServiceJDBC();
        service.reset();
        Date date = new Date();

        service.addScore(new Score("pipes", "Andrej",5, date));
        service.addScore(new Score("pipes", "Jano",3, date));
        service.addScore(new Score("pipes", "Kubo",2, date));

        List<Score> score = service.getTopScores("pipes");

        assertEquals(3, score.size());

        assertEquals("pipes", score.get(0).getGame());
        assertEquals("Andrej", score.get(0).getPlayer());
        assertEquals(5, score.get(0).getPoints());
        assertEquals(date, score.get(0).getPlayedOn());

        assertEquals("pipes", score.get(1).getGame());
        assertEquals("Jano", score.get(1).getPlayer());
        assertEquals(3, score.get(1).getPoints());
        assertEquals(date, score.get(1).getPlayedOn());

        assertEquals("pipes", score.get(2).getGame());
        assertEquals("Kubo", score.get(2).getPlayer());
        assertEquals(2, score.get(2).getPoints());
        assertEquals(date, score.get(2).getPlayedOn());


    }



}
