package abdkzmv.wm2.assignment3.controller;

import abdkzmv.wm2.assignment3.exceptions.MovieValidationException;
import abdkzmv.wm2.assignment3.model.dto.MovieDto;
import abdkzmv.wm2.assignment3.model.entity.Movie;
import abdkzmv.wm2.assignment3.model.mapper.MovieMapper;
import abdkzmv.wm2.assignment3.repository.MovieRepository;
import abdkzmv.wm2.assignment3.service.MovieService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping({"/movies/","/movies"})
public class MovieController {

    private MovieService movieService;
    private final MovieRepository movieRepository;

    public MovieController(MovieService movieService,
                           MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @SneakyThrows
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<MovieDto> listMovies() {
        var movieList = movieService.getMovies();

        return MovieMapper.instance.movieListToMovieDtoList(movieList);
    }

    @SneakyThrows
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("movieId") Long id) {
        var res = MovieMapper.instance.movieToMovieDto(movieService.getMovieById(id));

        return (res == null)
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(res);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable("movieId") Long id) {
        movieService.deleteMovie(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody MovieDto saveMovie(@RequestBody Movie movie, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()) {
            throw new MovieValidationException(bindingResult);
        }
        Movie newMovie = movieRepository.save(movie);
        new ResponseEntity(newMovie, HttpStatus.CREATED);
        return MovieMapper.instance.movieToMovieDto(newMovie);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable("movieId") Long id, @RequestBody Movie newMovie,
                                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new MovieValidationException(bindingResult);
        }
        Movie res = movieService.updateMovie(id, newMovie);
        var ans = MovieMapper.instance.movieToMovieDto(res);
        return (ans == null)
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(ans);
    }

    @PatchMapping("/{movieId}")
    public ResponseEntity<MovieDto> partialUpdateMovie(@PathVariable("movieId") Long id, @RequestBody Map<String, Object> updates,
                                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new MovieValidationException(bindingResult);
        }
        Movie movie = movieService.getMovieById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    if(value != null)
                        movie.setTitle((String) value);
                    break;
                case "description":
                    if(value != null)
                        movie.setDescription((String) value);
                    break;
                default:
                    break;
            }
        });

        Movie updatedMovie = movieRepository.save(movie);
        var responseDto = MovieMapper.instance.movieToMovieDto(updatedMovie);
        return (responseDto == null)
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(responseDto);
    }

}
