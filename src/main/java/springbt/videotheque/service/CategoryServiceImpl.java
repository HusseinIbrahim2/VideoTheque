package springbt.videotheque.service;


import springbt.videotheque.model.Category;
import springbt.videotheque.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Category> findAll(String search) {
        if (!"".equals(search))
            return repository.findByNameIgnoreCaseContaining(search);
        else
            return repository.findAll();
    }

    @Override
    public Category insert(Category category) {
        return repository.save(category);
    }

    @Override
    public Category updateById(Long id, Category category) {
        Optional<Category> optionalCategory = this.findById(id);
        if (optionalCategory.isPresent()) {
            return repository.save(category);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Category> category = this.findById(id);
        category.ifPresent(value -> repository.delete(value));
    }
}
