package com.example.sanbongminiserver.dao;

import com.example.sanbongminiserver.model.Category;
import com.example.sanbongminiserver.model.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByDeletedIsFalse();
    List<Product> findByNameContainingAndDeletedIsFalse(String key);

    List<Product> findAllByCategoryAndDeletedIsFalse(@NotNull Category category);

    @Transactional
    @Modifying
    @Query("UPDATE Product e SET e.deleted = true WHERE e.id IN :ids")
    void deleteAllById(List<Integer> ids);

}

@Service
@RequiredArgsConstructor
public class ProductDAO {
    private final ProductRepository productRepository;

    public Product getById(Integer integer) {
        return productRepository.findById(integer).get();
    }

    public List<Product> findAllByCategory (Category category) {return productRepository.findAllByCategoryAndDeletedIsFalse(category);}

    public void deleteAllById(Integer[] arr) {
        productRepository.deleteAllById(Arrays.asList(arr));
    }

    public List<Product> findAll() {
        return productRepository.findAllByDeletedIsFalse();
    }

    public List<Product> findByNameContaining(String key) {
        return  productRepository.findByNameContainingAndDeletedIsFalse(key);
    }

    public Product save(Product entity) {
        return productRepository.save(entity);
    }

}
