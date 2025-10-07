package springbt.videotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbt.videotheque.model.Actor;
import jakarta.validation.Valid;
import springbt.videotheque.service.ActorService;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    ActorService service;

    @CrossOrigin
    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getAllActors(@RequestParam(value = "search", defaultValue = "") String search) {
        List<Actor> listActor;
        try {
            listActor = service.findAll(search);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listActor);
    }

    @CrossOrigin
    @GetMapping("/actors/{id}")
    ResponseEntity<Actor> getActorById(@PathVariable(value = "id") long id) {
        Optional<Actor> actor = service.findById(id);
        return actor.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping("/actors")
    ResponseEntity<Actor> createActor(@Valid @RequestBody Actor actor) {
        try {
            Actor createdActor = service.insert(actor);
            return ResponseEntity.ok().body(createdActor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @PutMapping("/actors/{id}" )
    ResponseEntity<Actor> updateActor(@PathVariable(value = "id") long id, @Valid @RequestBody Actor actor) {
        try {
            Actor updatedActor = service.updateById(id, actor);
            return ResponseEntity.ok().body(updatedActor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/actors/{id}")
    ResponseEntity<Actor> deleteActor(@PathVariable(value = "id") long id) {
       Optional<Actor> actor = service.findById(id);
         if (actor.isEmpty())
              return ResponseEntity.notFound().build();
         service.deleteById(actor.get().getId());
         return ResponseEntity.accepted().build();
    }
}

