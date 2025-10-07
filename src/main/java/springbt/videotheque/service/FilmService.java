package springbt.videotheque.service;

import springbt.videotheque.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> findById(Long id);
    List<Film> findAll(String search);
    //List<Film> findByCategoryId(Long id);
    Film insert(Film film);
    Film updateById(Long id, Film film);
    void deleteById(Long id);
}
