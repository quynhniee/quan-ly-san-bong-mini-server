package com.example.sanbongminiserver.dao;


import com.example.sanbongminiserver.model.ImportOrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

interface ImportOrderProductRepository extends JpaRepository<ImportOrderProduct, Integer> {
    List<ImportOrderProduct> findAllByImportOrderId(Integer id);
}

@Service
@RequiredArgsConstructor
public class ImportOrderProductDAO {
    private final ImportOrderProductRepository importOrderProductRepository;

    public List<ImportOrderProduct> findAll() {return importOrderProductRepository.findAll();}

    public List<ImportOrderProduct> findAllByImportOrderId(Integer id) {return importOrderProductRepository.findAllByImportOrderId(id);}

    public ImportOrderProduct save(ImportOrderProduct entity) {return importOrderProductRepository.save(entity);}

    public List<ImportOrderProduct> saveAll(List<ImportOrderProduct> entities) {return importOrderProductRepository.saveAll(entities);}

}
