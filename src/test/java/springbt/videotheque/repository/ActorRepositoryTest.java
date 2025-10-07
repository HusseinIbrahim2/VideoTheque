package springbt.videotheque.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springbt.videotheque.model.Actor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ActorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ActorRepository actorRepository;

    @DisplayName("find actor by name")
    @Test
    public void findByNameContainingTest( ) {
        Actor actor1 = new Actor("Chris Evans", "American actor");
        entityManager.persist(actor1);

        List<Actor> actors = actorRepository.findByNameIgnoreCaseContaining("Chris Evans");

        assertThat(actors.size()).isEqualTo(1);

    }
}
