package sk.tuke.gamestudio.service;

import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentServiceJDBC implements CommentService{
    public static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PW = "postgres";
    public static final String SELECT = "SELECT game, player, comment, commented_at FROM comment WHERE game = ? ";
    public static final String INSERT = "INSERT INTO comment (game,player,comment, commented_at) VALUES (?,?,?,?)";
    // CREATE TABLE comment (game VARCHAR(32)NOT NULL, player VARCHAR(32) NOT NULL, comment VARCHAR(64) NOT NULL, commentedOn TIMESTAMP NOT NULL)
     public static final String DELETE = "DELETE FROM comment";


    @Override
    public void addComment(Comment comment) throws CommentException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(INSERT);
        ) {
            statement.setString(1, comment.getGame());
            statement.setString(2, comment.getPlayer());
            statement.setString(3, comment.getComment());
            statement.setTimestamp(4, new Timestamp(comment.getCommentedAt().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommentException("sql comment insert error");
        }
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException{
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1, game);

            try (ResultSet rs = statement.executeQuery()) {
                List<Comment> res = new ArrayList<>();
                while (rs.next()) {
                    Comment comment =new Comment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
                    res.add(comment);
                }
                return res;
            }
        } catch (SQLException e) {
            throw new CommentException("sql comment selecting error", e);
        }
    }

    @Override
    public void reset() throws CommentException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new CommentException("sql delete error", e);
        }

    }
}
