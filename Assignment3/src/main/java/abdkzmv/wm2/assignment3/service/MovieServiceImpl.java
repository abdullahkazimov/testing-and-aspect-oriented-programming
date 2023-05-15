package abdkzmv.wm2.assignment3.service;

import abdkzmv.wm2.assignment3.model.entity.Movie;
import abdkzmv.wm2.assignment3.repository.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie createMovie(@Valid Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, @Valid Movie movie) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie == null) {
            return null;
        }
        if(movie.getTitle() != null) {
            existingMovie.setTitle(movie.getTitle());
        }
        if(movie.getDescription() != null) {
            existingMovie.setDescription(movie.getDescription());
        }
        return movieRepository.save(existingMovie);
    }

    public boolean deleteMovie(Long id) {
        movieRepository.deleteById(id);
        return true;
    }
}
