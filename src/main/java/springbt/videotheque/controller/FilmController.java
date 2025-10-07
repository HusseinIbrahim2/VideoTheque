package springbt.videotheque.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbt.videotheque.model.Film;
import springbt.videotheque.service.FilmService;

import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {

    @Autowired
    FilmService service;

    @CrossOrigin
    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms(@RequestParam(value = "search", defaultValue = "") String search) {
        List<Film> listFilm;
        try {
            listFilm = service.findAll(search);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listFilm);
    }

    @CrossOrigin
    @GetMapping("/films/{id}")
    ResponseEntity<Film> getFilmById(@PathVariable(value = "id") long id) {
        Optional<Film> film = service.findById(id);
        return film.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*@CrossOrigin
    @GetMapping("/categories/{id}/films")
    ResponseEntity<List<Film>> getFilmsByCategoryId(@PathVariable(value = "id") long id) {
        List<Film> listFilm;
        try {
            listFilm = service.findByCategoryId(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listFilm);
    }*/

    @CrossOrigin
    @PostMapping("/films")
    ResponseEntity<Film> createFilm(@RequestBody Film film) {
        try {
            Film createdFilm = service.insert(film);
            return ResponseEntity.ok().body(createdFilm);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @PutMapping("/films/{id}")
    ResponseEntity<Film> updateFilm(@PathVariable(value = "id") long id, @Valid @RequestBody Film film) {
        try {
            Film updatedFilm = service.updateById(id, film);
            return ResponseEntity.ok().body(updatedFilm);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/films/{id}")
    ResponseEntity<Film> deleteFilm(@PathVariable(value = "id") long id) {
        Optional<Film> film = service.findById(id);
        if (film.isEmpty())
            return ResponseEntity.notFound().build();
        service.deleteById(film.get().getId());
        return ResponseEntity.accepted().build();
    }
}
