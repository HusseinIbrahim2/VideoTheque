package springbt.videotheque.service;

import springbt.videotheque.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    Optional<Actor> findById(Long id);
    List<Actor> findAll(String search);
    Actor insert(Actor actor);
    Actor updateById(Long id, Actor actor);
    void deleteById(Long id);
}
