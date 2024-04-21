package com.example.sanbongminiserver.dao;

import com.example.sanbongminiserver.model.Employee;
import com.example.sanbongminiserver.model.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
@Service
@RequiredArgsConstructor
public class SupplierDAO {
    private final SupplierRepository supplierRepository;

    @GetMapping
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

}
