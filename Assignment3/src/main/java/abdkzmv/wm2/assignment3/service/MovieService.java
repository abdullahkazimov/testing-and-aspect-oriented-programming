package abdkzmv.wm2.assignment3.service;

import abdkzmv.wm2.assignment3.model.entity.Movie;
import jakarta.validation.Valid;

import java.util.List;

public interface MovieService {
    public List<Movie> getMovies();
    public Movie getMovieById(Long id);
    public Movie createMovie(@Valid Movie movie);
    public Movie updateMovie(Long id, @Valid Movie movie);
    public boolean deleteMovie(Long id);
}
