package springbt.videotheque.service;

import springbt.videotheque.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findById(Long id);
    List<Category> findAll(String search);
    Category insert(Category album);
    Category updateById(Long id, Category album);
    void deleteById(Long id);
}
