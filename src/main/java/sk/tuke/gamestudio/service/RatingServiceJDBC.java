package sk.tuke.gamestudio.service;

import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RatingServiceJDBC implements RatingService {
    public static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PW = "postgres";
    public static final String SELECT = "SELECT game, player, rating, rated_at FROM rating WHERE game = ? and player = ? "; //game, player, rating, ratedat
    public static final String SELECT_AVG = "SELECT game, player, rating, rated_at FROM rating WHERE game = ? "; //game, player, rating, ratedat
    public static final String SELECT_M = "SELECT EXISTS (SELECT game, player FROM rating WHERE game = ? and player = ?)";

    public static final String DELETE = "DELETE FROM rating";
    public static final String INSERT = "INSERT INTO rating (game, player, rating, rated_at) VALUES (?, ?, ?, ?)";
    public static final String DELETE_ROW = "DELETE FROM rating WHERE game = ? and player = ?";

    //CREATE TABLE rating ( game VARCHAR(64) NOT NULL, player VARCHAR(64) NOT NULL, rating INT NOT NULL, ratedOn TIMESTAMP NOT NULL)


    @Override
    public void setRating(Rating rating) throws RatingException {

        if(rating.getRating()>5 || rating.getRating()<1) {
            return;
        }
        int res = 0;
        List<Rating> ratings = new ArrayList<>();


        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(SELECT_M);
             PreparedStatement statement1= connection.prepareStatement(DELETE_ROW);
             PreparedStatement statement2= connection.prepareStatement(INSERT);
        ) {

            statement.setString(1, rating.getGame());
            statement.setString(2, rating.getPlayer());


            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                statement1.setString(1, rating.getGame());
                statement1.setString(2, rating.getPlayer());

                statement1.executeUpdate();
            }
            statement2.setString(1, rating.getGame());
            statement2.setString(2, rating.getPlayer());
            statement2.setInt(3, rating.getRating());
            statement2.setTimestamp(4, new Timestamp(rating.getRatedAt().getTime()));
            statement2.executeUpdate();


        } catch (SQLException e) {
            throw new RatingException("rating inserting score", e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        int res = 0, sum = 0;
        List<Rating> ratings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(SELECT_AVG)
        ) {
            statement.setString(1, game);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4));
                ratings.add(rating);
            }

            for (Rating rating : ratings) {
                sum += rating.getRating();
            }
            if (ratings.size() != 0) {
                res = sum / ratings.size();
            }

        } catch (SQLException e) {
            throw new RatingException("sql avg rating ", e);
        }

        return res;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {

        int res = 0;
        List<Rating> ratings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {

            statement.setString(1,game);
            statement.setString(2,player);


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4));
                ratings.add(rating);
            }

            for (Rating r : ratings){
                if(r.getGame().equals(game) && r.getPlayer().equals(player)){
                    res=r.getRating();
                }
            }

        } catch (SQLException e) {
            throw new RatingException("sql rating getRating error", e);
        }
        return res;
    }

    @Override
    public void reset() throws RatingException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PW);
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new RatingException("sql rating delete error", e);
        }
    }
}

