package com.mvc.service;

import com.mvc.domain.DomainType;
import com.mvc.model.Category;
import com.mvc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    public Category getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public Map<String, String> getMonthCategory() {
        return categoryRepository.getMonthCategory();
    }

    public Category getTag(String name) {
        return categoryRepository.getCategoryByName(name, DomainType.Tag);
    }
}
