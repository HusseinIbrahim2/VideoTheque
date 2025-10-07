package springbt.videotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbt.videotheque.model.Category;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameIgnoreCaseContaining(String name);
}
