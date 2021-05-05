package sk.tuke.gamestudio.service;

import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreServiceJDBC implements ScoreService {

    //statement.executeUpdate("INSERT INTO score(game , player , points , playedOn) VALUES('pipes', 'andrej', 105, '2021-03-12 14:35')");

    public static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PW = "postgres";
    public static final String SELECT = "SELECT game, player, points, played_on FROM score WHERE game= ? ORDER BY points DESC LIMIT 3 ";
    public static final String DELETE = "DELETE FROM score";
    public static final String INSERT = "INSERT INTO score(game,player,points, played_on) VALUES (?,?,?,?)";


    @Override
    public void addScore(Score score) throws ScoreException{
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(INSERT);
        ) {
            statement.setString(1, score.getGame());
            statement.setString(2, score.getPlayer());
            statement.setInt(3, score.getPoints());
            statement.setTimestamp(4, new Timestamp(score.getPlayedOn().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ScoreException("sql insert error");
        }
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1, game);

            try (ResultSet rs = statement.executeQuery()) {
                List<Score> scores = new ArrayList<>();
                while (rs.next()) {
                    scores.add(new Score(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4)));
                }
                return scores;
            }
        } catch (SQLException e) {
            throw new ScoreException("sql selecting error", e);
        }
    }

    @Override
    public void reset() throws ScoreException{
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new ScoreException("sql delete error", e);
        }
    }


}
