package sk.tuke.gamestudio.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.service.*;


@SpringBootApplication
@Configuration
@EntityScan("sk.tuke.gamestudio.entity")//, "sk.tuke.gamestudio.games.pipes.entity"}
public class GameStudioServer {
    public static void main(String[] args) {
        SpringApplication.run(GameStudioServer.class);
    }

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }

    @Bean
    CommentService commentService(){
        return  new CommentServiceJPA();
    }
    @Bean
    RatingService ratingService(){
        return new RatingServiceJPA();
    }
    @Bean
    UserLoginService userLoginService(){
        return new UserLoginServiceJPA();
    }


}
