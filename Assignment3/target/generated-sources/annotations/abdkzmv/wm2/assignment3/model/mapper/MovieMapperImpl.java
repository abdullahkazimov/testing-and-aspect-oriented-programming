package abdkzmv.wm2.assignment3.model.mapper;

import abdkzmv.wm2.assignment3.model.dto.MovieDto;
import abdkzmv.wm2.assignment3.model.entity.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-14T18:58:29+0400",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class MovieMapperImpl implements MovieMapper {

    @Override
    public List<MovieDto> movieListToMovieDtoList(List<Movie> movie) {
        if ( movie == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<MovieDto>( movie.size() );
        for ( Movie movie1 : movie ) {
            list.add( movieToMovieDto( movie1 ) );
        }

        return list;
    }

    @Override
    public MovieDto movieToMovieDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setId( movie.getId() );
        movieDto.setTitle( movie.getTitle() );
        movieDto.setDescription( movie.getDescription() );

        return movieDto;
    }
}
