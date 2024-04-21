package com.example.sanbongminiserver.dao;

import com.example.sanbongminiserver.model.Category;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

interface CategoryRepository extends JpaRepository<Category, Integer> {
}
@Service
@RequiredArgsConstructor
public class CategoryDAO {
    private final CategoryRepository categoryRepository;
    
    @GetMapping()
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
