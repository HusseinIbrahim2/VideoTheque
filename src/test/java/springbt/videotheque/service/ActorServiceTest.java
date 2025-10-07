package springbt.videotheque.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import springbt.videotheque.repository.ActorRepository;
import springbt.videotheque.service.ActorService;
import springbt.videotheque.service.ActorServiceImpl;
import springbt.videotheque.model.Actor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {

    @Mock
    ActorRepository repository;

    @InjectMocks
    ActorService service = new ActorServiceImpl();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Find all actors")
    @Test
    public void findAllTest(){
        List<Actor> actors = new ArrayList<>();
        actors.add(new Actor( "Robert Downey Jr","Famous for Iron"));
        actors.add(new Actor( "Chris Evans", "Famous for Captain America"));
        actors.add(new Actor( "Scarlett Johansson", "Famous for Black Widow"));

        when(repository.findAll()).thenReturn(actors);
        when(repository.findByNameIgnoreCaseContaining("Sc")).thenReturn(
                actors.stream()
                        .filter(e -> e.getName().startsWith("Sc"))
                        .collect(Collectors.toList())
        );

        List<Actor> outputActors = service.findAll("");

        //THEN
        assertThat(outputActors.size()).isEqualTo(3);

        //WHEN
        outputActors = service.findAll("Sc");

        //THEN
        assertThat(outputActors.size()).isEqualTo(1);
    }

    @DisplayName("find all actors by name")
    @Test
    public void findAllByNameTest() {
        List<Actor> actors = new ArrayList<>();
        final String str = "Ch";
        actors.add(new Actor( "Chris Evans","Famous for Iron"));
        actors.add(new Actor( "Christian Bale", "Famous for Captain America"));
        actors.add(new Actor( "Chris Hemsworth", "Famous for Black Widow"));

        when(repository.findByNameIgnoreCaseContaining(str)).thenReturn(actors);

        List<Actor> outputActors = service.findAll(str);

        //THEN
        assertThat(outputActors.size()).isEqualTo(3);
    }

    @DisplayName("insert Actor")
    @Test
    public void insertTest() {
        long idActor = 50;
        Actor actor = new Actor( "Chris Evans","Famous for Iron");
        actor.setId(idActor);

        when(repository.save(actor)).thenReturn(actor);

        Actor outputActor = service.insert(actor);

        //THEN
        assertThat(outputActor.getId()).isEqualTo(idActor);
        assertThat(outputActor.getName()).isEqualTo("Chris Evans");

    }

}
