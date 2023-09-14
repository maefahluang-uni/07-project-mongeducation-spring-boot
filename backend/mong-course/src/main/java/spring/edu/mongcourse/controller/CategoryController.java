package spring.edu.mongcourse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.edu.mongcourse.ServerMapper;
import spring.edu.mongcourse.model.Category;
import spring.edu.mongcourse.model.CategoryDTO;
import spring.edu.mongcourse.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryRepository categoryRepository;
    private KafkaTemplate<String, Category> kafkaTemplate;

    private ServerMapper serverMapper;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, KafkaTemplate<String, Category> kafkaTemplate,
            ServerMapper serverMapper) {
        this.categoryRepository = categoryRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.serverMapper = serverMapper;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategorys() {
        List<Category> Categorys = categoryRepository.findAll();
        return ResponseEntity.ok(Categorys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        if (!optCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optCategory);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok("Category was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        if (!optCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        category.setId(id);
        categoryRepository.save(category);
        return ResponseEntity.ok("Category was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> optCategory = categoryRepository.findById(id);

        if (!optCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }

        Category category = optCategory.get();
        serverMapper.updateCategoryFromDto(categoryDTO, category);
        categoryRepository.save(category);

        return ResponseEntity.ok("Category patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Category was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCategory() {
        categoryRepository.deleteAll();
        return ResponseEntity.ok("Category was Deleted");
    }
}
