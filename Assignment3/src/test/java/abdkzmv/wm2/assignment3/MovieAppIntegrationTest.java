package abdkzmv.wm2.assignment3;


import abdkzmv.wm2.assignment3.model.entity.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieAppIntegrationTest {


    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static TestRestTemplate testRestTemplate;


    @Autowired
    private TestH2Repository testH2Repository;

    @BeforeAll
    public static void init() {
        testRestTemplate = new TestRestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/movies");
    }

    @AfterEach
    public void tearDown() {
        testH2Repository.deleteAll();
    }

    @Test
    public void addMovie() {
        Movie expectedMovie = new Movie("Interstellar", "spaceships...");
        Movie actualMovie = testRestTemplate.postForObject(baseUrl, expectedMovie, Movie.class);
        assertEquals(expectedMovie, actualMovie);
    }

    @Test
    @Sql(statements = "INSERT INTO MOVIES(title, description) VALUES ('Interstellar', 'spaceships...')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void getTestMovie() {
        List<Movie> actualMovies = testRestTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, actualMovies.size());
        assertEquals(1, testH2Repository.findAll().size());
    }

    @Test
    public void testFindMovieById() {
        // Given
        Movie expectedMovie = new Movie("Interstellar", "spaceships...");
        expectedMovie = testH2Repository.save(expectedMovie);

        // When
        String url = baseUrl + "/" + expectedMovie.getId();
        Movie actualMovie = testRestTemplate.getForObject(url, Movie.class);

        // Then
        assertEquals(expectedMovie, actualMovie);
    }

    @Test
    public void testDeleteMovie() {
        // Given
        Movie expectedMovie = new Movie("Interstellar", "spaceships...");
        expectedMovie = testH2Repository.save(expectedMovie);

        // When
        String url = baseUrl + "/" + expectedMovie.getId();
        testRestTemplate.delete(url);

        // Then
        assertEquals(0, testH2Repository.findAll().size());
    }


}
