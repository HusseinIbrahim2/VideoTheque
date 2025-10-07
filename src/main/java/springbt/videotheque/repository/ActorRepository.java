package springbt.videotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbt.videotheque.model.Actor;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByNameIgnoreCaseContaining(String name);
}
