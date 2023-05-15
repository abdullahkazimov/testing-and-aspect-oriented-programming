package abdkzmv.wm2.assignment3.init;

import abdkzmv.wm2.assignment3.model.entity.Movie;
import abdkzmv.wm2.assignment3.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Bean
    @Autowired
    public CommandLineRunner init(MovieRepository movieRepo) {
        return (args) -> {
            Movie m1 = movieRepo.save(new Movie("Fight Club", "Do not talk about fight club."));
            Movie m2 = movieRepo.save(new Movie("Oppenheimer", "Another Christopher Nolan movie"));


            movieRepo.save(new Movie("Dark Knight", "Cristian Bale classic"));




            movieRepo.save(m1);
            movieRepo.save(m2);
        };
    }
}
