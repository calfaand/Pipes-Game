package test;

import org.junit.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.CommentServiceJDBC;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CommentTest {

    @Test
    public void testReset() throws CommentException {
        CommentService service = new CommentServiceJDBC();
        service.reset();
        assertEquals(0, service.getComments("pipes").size());

    }

    @Test
    public void testAddComment() throws CommentException {
        CommentService service = new CommentServiceJDBC();
        service.reset();
        Date date = new Date();

        service.addComment(new Comment("pipes", "Andrej", "good game", date));

        List<Comment> comments = service.getComments("pipes");
        assertEquals(1, comments.size());
        assertEquals("pipes", comments.get(0).getGame());
        assertEquals("Andrej", comments.get(0).getPlayer());
        assertEquals("good game", comments.get(0).getComment());
        assertEquals(date, comments.get(0).getCommentedAt());

    }

    @Test
    public void testGetComments() throws CommentException {

        CommentService service = new CommentServiceJDBC();
        service.reset();
        Date date = new Date();

        service.addComment(new Comment("pipes", "Andrej", "good game", date));
        service.addComment(new Comment("pipes", "Milan", "avg game", date));
        service.addComment(new Comment("pipes", "Jozef", "best game", date));

        List<Comment> comments = service.getComments("pipes");


        assertEquals(3, comments.size());

        assertEquals("pipes", comments.get(0).getGame());
        assertEquals("Andrej", comments.get(0).getPlayer());
        assertEquals("good game", comments.get(0).getComment());
        assertEquals(date, comments.get(0).getCommentedAt());

        assertEquals("pipes", comments.get(1).getGame());
        assertEquals("Milan", comments.get(1).getPlayer());
        assertEquals("avg game", comments.get(1).getComment());
        assertEquals(date, comments.get(1).getCommentedAt());

        assertEquals("pipes", comments.get(2).getGame());
        assertEquals("Jozef", comments.get(2).getPlayer());
        assertEquals("best game", comments.get(2).getComment());
        assertEquals(date, comments.get(2).getCommentedAt());



    }




}
