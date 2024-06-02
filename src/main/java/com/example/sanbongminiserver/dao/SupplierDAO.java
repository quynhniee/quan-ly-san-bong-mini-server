package com.example.sanbongminiserver.dao;

import com.example.sanbongminiserver.model.Employee;
import com.example.sanbongminiserver.model.Supplier;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findAllByDeletedIsFalse();

    List<Supplier> findByNameContainingAndDeletedIsFalse(String key);

    @Transactional
    @Modifying
    @Query("UPDATE Supplier e SET e.deleted = true WHERE e.id IN :ids")
    void deleteAllByIdIn(List<Integer> ids);
}
@Service
@RequiredArgsConstructor
public class SupplierDAO {
    private final SupplierRepository supplierRepository;

    @GetMapping
    public List<Supplier> findAll() {
        return supplierRepository.findAllByDeletedIsFalse();
    }

    public Supplier getById(Integer id) {return supplierRepository.findById(id).get();}

    public Supplier save(Supplier supplier) {return supplierRepository.save(supplier);}

    public void deleteAllByIdIn(Integer[] ids) {supplierRepository.deleteAllByIdIn(List.of(ids));}

    public List<Supplier> findByNameContaining(String key) {return supplierRepository.findByNameContainingAndDeletedIsFalse(key);}

}
