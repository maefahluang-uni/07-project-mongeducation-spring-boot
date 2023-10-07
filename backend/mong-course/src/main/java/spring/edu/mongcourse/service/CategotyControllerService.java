package spring.edu.mongcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.edu.mongcourse.ServerMapper;
import spring.edu.mongcourse.model.Category;
import spring.edu.mongcourse.model.CategoryDTO;
import spring.edu.mongcourse.repository.CategoryRepository;

@Service
public class CategotyControllerService {
    private final CategoryRepository categoryRepository;
    private final ServerMapper serverMapper;

    @Autowired
    public CategotyControllerService(CategoryRepository categoryRepository, ServerMapper serverMapper) {
        this.categoryRepository = categoryRepository;
        this.serverMapper = serverMapper;
    }

    public List<Category> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Optional<Category> getCategoryById(Long id) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        return optCategory;
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void mapperCategory(CategoryDTO categoryDTO, Category category) {
        serverMapper.updateCategoryFromDto(categoryDTO, category);
        saveCategory(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteAllCategory() {
        categoryRepository.deleteAll();
    }

}
