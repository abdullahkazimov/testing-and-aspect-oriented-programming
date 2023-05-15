package abdkzmv.wm2.assignment3.model.mapper;

import abdkzmv.wm2.assignment3.model.dto.MovieDto;
import abdkzmv.wm2.assignment3.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MovieMapper {

    MovieMapper instance = Mappers.getMapper(MovieMapper.class);

    List<MovieDto> movieListToMovieDtoList(List<Movie> movie);

    MovieDto movieToMovieDto(Movie movie);
}
