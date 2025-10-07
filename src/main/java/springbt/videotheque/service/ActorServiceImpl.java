package springbt.videotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbt.videotheque.repository.ActorRepository;
import springbt.videotheque.model.Actor;
import java.util.Optional;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository repository;

    @Override
    public Optional<Actor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Actor> findAll(String search) {
        if (! "".equals(search))
            return repository.findByNameIgnoreCaseContaining(search);
        else
            return repository.findAll();
    }

    @Override
    public Actor insert(Actor actor) {
        return repository.save(actor);
    }

    @Override
    public Actor updateById(Long id, Actor actor) {
        Optional<Actor> optionalActor = this.findById(id);
        if(optionalActor.isPresent()) {
            return repository.save(actor);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Actor> actor = this.findById(id);
        actor.ifPresent(value -> repository.delete(value));
    }
}
