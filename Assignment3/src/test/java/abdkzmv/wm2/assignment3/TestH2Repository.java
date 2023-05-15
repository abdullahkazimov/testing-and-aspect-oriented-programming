package abdkzmv.wm2.assignment3;

import abdkzmv.wm2.assignment3.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Movie,Long> {
}
