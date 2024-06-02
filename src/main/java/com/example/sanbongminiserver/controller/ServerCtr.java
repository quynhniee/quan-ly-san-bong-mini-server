package com.example.sanbongminiserver.controller;

import com.example.sanbongminiserver.dao.*;
import com.example.sanbongminiserver.model.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Validated
public class ServerCtr {
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;
    private final EmployeeDAO employeeDAO;
    private final SupplierDAO supplierDAO;
    private final StatusDAO statusDAO;
    private final ImportOrderDAO importOrderDAO;
    private final ImportOrderProductDAO importOrderProductDAO;

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @GetMapping(value = "/product", params = {"key"})
    public  List<Product> getProductsByNameContaining(@RequestParam final String key) {
        return productDAO.findByNameContaining(key);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productDAO.getById(id);
    }

    @GetMapping(value = "/product", params = {"category"})
    public List<Product> getProductByCategory(@RequestParam final Integer category) {
        System.out.println(category);
        return productDAO.findAllByCategory(new Category(category));
    }

    @DeleteMapping(value = "product")
    public void deleteProductsById(@RequestBody Integer[] arr) {
        productDAO.deleteAllById(arr);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            Product oProduct = productDAO.getById(product.getId());
            Product nProduct = product;
            nProduct.setId(oProduct.getId());
            return productDAO.save(nProduct);
        }
        return productDAO.save(product);
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    @GetMapping("/supplier")
    public List<Supplier> getAllSuppliers() {
        return supplierDAO.findAll();
    }

    @DeleteMapping(value = "/supplier")
    public void deleteSuppliersById(@RequestBody Integer[] arr) {
        System.out.println("arr = " + Arrays.toString(arr));
        supplierDAO.deleteAllByIdIn(arr);
    }

    @GetMapping(value = "/supplier", params = {"key"})
    public  List<Supplier> getSupplierByNameContaining(@RequestParam final String key) {
        return supplierDAO.findByNameContaining(key);
    }

//    @PostMapping("/supplier")
//    public Supplier saveSupplier(@RequestBody Supplier supplier) {
//
//        if (supplier.getId() != null) {
//            Supplier oSupplier = supplierDAO.getById(supplier.getId());
//            Supplier nSupplier = supplier;
//            nSupplier.setId(oSupplier.getId());
//            System.out.println("nSupplier = " + nSupplier);
//            return supplierDAO.save(nSupplier);
//        }
//        return supplierDAO.save(supplier);
//    }

    @PostMapping("/supplier")
    public ResponseEntity<?> saveSupplier(@RequestBody Supplier supplier) {
        try {
            if (supplier.getId() != null) {
                Supplier oSupplier = supplierDAO.getById(supplier.getId());
                Supplier nSupplier = supplier;
                nSupplier.setId(oSupplier.getId());
                supplierDAO.save(nSupplier);
            } else {
                supplierDAO.save(supplier);
            }
            return new ResponseEntity<>(supplier, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            String errorMessage;
            if (e.getMostSpecificCause().getMessage().contains("tax_code")) {
                errorMessage = "Tax Code is duplicated";
            } else if (e.getMostSpecificCause().getMessage().contains("email")) {
                errorMessage = "Email is duplicated";
            } else {
                errorMessage = "Data integrity violation";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

     @GetMapping("/status")
    public List<Status> getAllStatuses() {
        return statusDAO.findAll();
    }

    @GetMapping("/order")
    public List<ImportOrder> getAllImportOrders() {
        return importOrderDAO.findAll();
    }

    @GetMapping(value = "/order", params = {"key"})
    public  List<ImportOrder> getImportOrderByCodeContaining(@RequestParam final String key) {
        return importOrderDAO.findByCodeContaining(key);
    }

    @GetMapping("/order/{id}")
    public ImportOrder getImportOrder(@PathVariable Integer id) {
        return importOrderDAO.getById(id);
    }

//    @PostMapping("/order")
//    public ImportOrder saveImportOrder(@RequestBody ImportOrder importOrder) {
//        List<ImportOrderProduct> importOrderProducts = importOrder.getImportOrderProducts();
//        for (ImportOrderProduct value : importOrderProducts) {
//            value.setImportOrder(importOrder);
//        }
//        return importOrderDAO.save(importOrder);
//    }

    @PostMapping("/order")
public ResponseEntity<?> saveImportOrder(@RequestBody ImportOrder importOrder) {
    try {
        List<ImportOrderProduct> importOrderProducts = importOrder.getImportOrderProducts();
        for (ImportOrderProduct value : importOrderProducts) {
            value.setImportOrder(importOrder);
        }
        importOrderDAO.save(importOrder);
        return new ResponseEntity<>(importOrder, HttpStatus.CREATED);
    } catch (DataIntegrityViolationException e) {
        return new ResponseEntity<>("Code is duplicated", HttpStatus.BAD_REQUEST);
    }
}

    @DeleteMapping(value = "/order")
    public void deleteImportOrdersById(@RequestBody Integer[] arr) {
        importOrderDAO.deleteAllById(arr);
    }
}
