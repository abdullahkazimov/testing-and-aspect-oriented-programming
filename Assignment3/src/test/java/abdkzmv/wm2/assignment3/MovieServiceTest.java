package abdkzmv.wm2.assignment3;

import abdkzmv.wm2.assignment3.model.entity.Movie;
import abdkzmv.wm2.assignment3.repository.MovieRepository;
import abdkzmv.wm2.assignment3.service.MovieServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;

    @BeforeEach
    public void setUp() {
        movie = new Movie("","");
    }

    @Test
    @DisplayName("savemovie_WhenCalled_ShouldReturnExpectedEntity")
    void savemovie_WhenCalled_ShouldReturnExpectedEntity() {
        //Given


        //Mock movie Storage Repository
        when(movieRepository.save(movie)).thenReturn(movie);


        //When
        Movie actual = movieService.createMovie(movie);


        //Then
        assertNotNull(actual);
        assertEquals(movie, actual);
        verify(movieRepository, times(1)).save(movie);
    }
    //Uncle Bob Clean Code

    @Test
    @DisplayName("getMovies_WhenCalled_ShouldReturnExpectedList")
    void getMovies_WhenCalled_ShouldReturnExpectedList() {
        //Given
        List<Movie> expectedMovies = List.of(
                movie = new Movie("Interstellar","Law of Gravity"),
                movie = new Movie("Math 101","Plot")
        );

        //Mock movie Storage Repository
        when(movieRepository.findAll()).thenReturn(expectedMovies);

        //When
        List<Movie> actualMovies = movieService.getMovies();

        //Then
        assertNotNull(actualMovies);
        assertEquals(expectedMovies, actualMovies);
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void getMovieById_WhenCalled_ShouldReturnExpectedResult() {
        //Given

        //Mock storage
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        //When
        Movie actualMovie = movieService.getMovieById(movie.getId());

        //Then
        assertNotNull(actualMovie);
        assertEquals(movie, actualMovie);
        verify(movieRepository, times(1)).findById(movie.getId());

    }

    @Test
    void createMovie_WhenCalled_MustReturnExpectedResult() {
        //Given
        Movie movieToCreate = new Movie("Inception", "Dreams");
        Movie createdMovie = new Movie("Inception", "Dreams");
        createdMovie.setId(1234L);

        //Mock storage
        when(movieRepository.save(movieToCreate)).thenReturn(createdMovie);

        //When
        Movie actualMovie = movieService.createMovie(movieToCreate);

        //Then
        assertNotNull(actualMovie);
        assertEquals(createdMovie, actualMovie);
        verify(movieRepository, times(1)).save(movieToCreate);
    }


    @Test
    void deleteMovie_WhenCalled_MustReturnExpectedResult(){
        //Given
        Long movieId = 1234L;

        //Mock storage
        when(movieRepository.existsById(movieId)).thenReturn(true);

        //When
        boolean isDeleted = movieService.deleteMovie(movieId);

        //Then
        assertTrue(isDeleted);
        verify(movieRepository, times(1)).deleteById(movieId);
    }

    @Test
    void updateMovie_WhenCalled_MustReturnExpectedResult(){
        //Given
        Long movieId = 1234L;
        Movie existingMovie = new Movie("Interstellar", "Law of Gravity");
        existingMovie.setId(movieId);

        Movie updatedMovie = new Movie("Inception", "Dreams");
        updatedMovie.setId(movieId);

        //Mock storage
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(updatedMovie)).thenReturn(updatedMovie);

        //When
        Movie actualMovie = movieService.updateMovie(movieId, updatedMovie);

        //Then
        assertNotNull(actualMovie);
        assertEquals(updatedMovie, actualMovie);
        verify(movieRepository, times(1)).findById(movieId);
        verify(movieRepository, times(1)).save(updatedMovie);
    }

}
