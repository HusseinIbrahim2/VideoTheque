package springbt.videotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbt.videotheque.model.Film;
import springbt.videotheque.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository repository;

    @Override
    public Optional<Film> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Film> findAll(String search) {
        if (!"".equals(search))
            return repository.findByTitleIgnoreCaseContaining(search);
        else
            return repository.findAll();
    }

    /*@Override
    public List<Film> findByCategoryId(Long id) {
        return repository.findByCategoryId(id);
    }*/

    @Override
    public Film insert(Film film) {
        return repository.save(film);
    }

    @Override
    public Film updateById(Long id, Film film) {
        Optional<Film> optionalFilm = this.findById(id);
        if (optionalFilm.isPresent()) {
            return repository.save(film);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Film> film = this.findById(id);
        film.ifPresent(value -> repository.delete(value));
    }
}
