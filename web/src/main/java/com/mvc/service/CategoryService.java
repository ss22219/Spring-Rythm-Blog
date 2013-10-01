package com.mvc.service;

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

    public List<Category> getCategorys() {
        return categoryRepository.getCategorys();
    }

    public Category getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public Map<String,String> getMonthCategory(){
        return categoryRepository.getMonthCategory();
    }
}
