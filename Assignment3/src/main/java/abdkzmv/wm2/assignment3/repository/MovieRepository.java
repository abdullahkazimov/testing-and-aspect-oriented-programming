package abdkzmv.wm2.assignment3.repository;

import abdkzmv.wm2.assignment3.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
