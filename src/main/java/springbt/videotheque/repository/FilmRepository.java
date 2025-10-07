package springbt.videotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbt.videotheque.model.Film;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByTitleIgnoreCaseContaining(String title);
    //List<Film> findByCategoryId(Long categoryId);

}
