package com.example.sanbongminiserver.controller;

import com.example.sanbongminiserver.dao.*;
import com.example.sanbongminiserver.model.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/order")
    public ImportOrder saveImportOrder(@RequestBody ImportOrder importOrder) {
        List<ImportOrderProduct> importOrderProducts = importOrder.getImportOrderProducts();
        for (ImportOrderProduct value : importOrderProducts) {
            value.setImportOrder(importOrder);
        }
//        if (importOrder.getId() != null) {
//            ImportOrder importOrder1 = importOrderDAO.getById(importOrder.getId());
//            List<ImportOrderProduct> removedImportOrderProducts = new ArrayList<>(importOrder1.getImportOrderProducts());
//            removedImportOrderProducts.removeAll(importOrderProducts);
//            importOrderProductDAO.removeAllByIdIn(removedImportOrderProducts);
//        }
        return importOrderDAO.save(importOrder);
    }

    @DeleteMapping(value = "/order")
    public void deleteImportOrdersById(@RequestBody Integer[] arr) {
        importOrderDAO.deleteAllById(arr);
    }
}
