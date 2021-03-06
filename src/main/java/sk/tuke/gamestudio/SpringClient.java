package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.pipes.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.pipes.core.Field;
import sk.tuke.gamestudio.service.*;



@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
    pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {
    public static void main(String[] args) {
       new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
        //SpringApplication.run(SpringClient.class);
    }

//    @Bean
//    public ConsoleUI consoleUI(Field field) {
//        return new ConsoleUI(field);
//    }

    @Bean
    public CommandLineRunner runner(ConsoleUI ui) {
        return args -> ui.play();
    }

    @Bean
    public ScoreService scoreService() {
        // return new ScoreServiceJDBC();
       //return new ScoreServiceJPA();
        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentService commentService() {
      // return new CommentServiceJPA();
        return new CommentServiceRestClient();
    }
    @Bean
    public RatingService ratingService(){
        //return new RatingServiceJPA();
        return new RatingServiceRestClient();
    }

    @Bean
    public Field field() {
        return new Field();
    }

    @Bean(name="login")
    public UserLoginService userLogin(){
       // return new UserLoginServiceJPA();
        return new UserLoginServiceRestClient();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
