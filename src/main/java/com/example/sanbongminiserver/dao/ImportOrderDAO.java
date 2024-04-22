package com.example.sanbongminiserver.dao;

import com.example.sanbongminiserver.model.ImportOrder;
import com.example.sanbongminiserver.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

interface ImportOrderRepository extends JpaRepository<ImportOrder, Integer> {
    List<ImportOrder> findByCodeContaining(String key);
    List<ImportOrder> findAllByOrderByUpdatedAtDesc();
}

@Service
@RequiredArgsConstructor
public class ImportOrderDAO {
    private final ImportOrderRepository importOrderRepository;

    public List<ImportOrder> findAll() {
        return importOrderRepository.findAllByOrderByUpdatedAtDesc();
    }

    public ImportOrder getById(Integer integer) {
        return importOrderRepository.findById(integer).get();
    }

    public ImportOrder save(ImportOrder importOrder) {return importOrderRepository.save(importOrder);}

    public List<ImportOrder> findByCodeContaining(String key) {return importOrderRepository.findByCodeContaining(key);}

    public void deleteAllById(Integer[] ids) {
            importOrderRepository.deleteAllById(List.of(ids));
    }
}
