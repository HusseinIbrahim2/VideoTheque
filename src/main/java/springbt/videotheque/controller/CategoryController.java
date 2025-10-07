package springbt.videotheque.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import springbt.videotheque.service.CategoryService;
import springbt.videotheque.model.Category;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryService service;

    @CrossOrigin
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory(@RequestParam(value = "search", defaultValue = "") String search) {
        List<Category> listCategory;
        try {
            listCategory = service.findAll(search);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listCategory);
    }

    @CrossOrigin
    @GetMapping("/categories/{id}")
    ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") long id) {
        Optional<Category> category = service.findById(id);
        return category.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping("/categories")
    ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        try {
            Category createdCategory = service.insert(category);
            return ResponseEntity.ok().body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @PutMapping("/categories/{id}")
    ResponseEntity<Category> updateCategory(@PathVariable(value = "id") long id, @Valid @RequestBody Category category) {
        try {
            Category updatedCategory = service.updateById(id, category);
            return ResponseEntity.ok().body(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/categories/{id}")
    ResponseEntity<Category> deleteCategory(@PathVariable(value = "id") long id) {
        Optional<Category> category = service.findById(id);
        if (category.isEmpty())
            return ResponseEntity.notFound().build();
        service.deleteById(category.get().getId());
        return ResponseEntity.accepted().build();
    }
}
