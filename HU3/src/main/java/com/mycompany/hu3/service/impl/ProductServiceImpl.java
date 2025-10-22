/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu3.service.impl;

import com.mycompany.hu3.domain.Product;
import com.mycompany.hu3.repository.impl.ProductRepositoryImpl;
import com.mycompany.hu3.service.ProductService;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author xxx
 */
public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryImpl repo;
    
    public ProductServiceImpl(ProductRepositoryImpl repo) {
        this.repo = repo;
    }
    
    @Override
    public Product registrar(Product p) {
        if (p == null || p.getName() == null || p.getName().isBlank()) {
            throw new RuntimeException("Nombre requerido");   
        }
        
        if (p.getPrice() == null || p.getPrice() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a cero");
        }
        
        if (p.getStock() == null || p.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }
        
        // Validar que no exista un producto con el mismo nombre
        List<Product> productos = repo.buscarTodos();
        boolean existe = productos.stream()
                .anyMatch(prod -> prod.getName().equalsIgnoreCase(p.getName().trim()));
        
        if (existe) {
            throw new RuntimeException("Ya existe un producto con el nombre: " + p.getName());
        }
        
        return repo.crear(p);
    }
    
    @Override
    public Product actualizar(Product p) {
        if (p == null || p.getId() == null) {
            throw new RuntimeException("ID del producto requerido");
        }
        
        if (p.getName() == null || p.getName().isBlank()) {
            throw new RuntimeException("Nombre requerido");
        }
        
        if (p.getPrice() == null || p.getPrice() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a cero");
        }
        
        if (p.getStock() == null || p.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }
        
        // Verificar que el producto exista
        Optional<Product> productoExistente = repo.buscarPorId(p.getId());
        if (!productoExistente.isPresent()) {
            throw new RuntimeException("Producto no encontrado con ID: " + p.getId());
        }
        
        // Validar que no exista otro producto con el mismo nombre
        List<Product> productos = repo.buscarTodos();
        boolean existeOtro = productos.stream()
                .anyMatch(prod -> !prod.getId().equals(p.getId()) && 
                                 prod.getName().equalsIgnoreCase(p.getName().trim()));
        
        if (existeOtro) {
            throw new RuntimeException("Ya existe otro producto con el nombre: " + p.getName());
        }
        
        return repo.actualizar(p);
    }
    
    @Override
    public Product consultarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido");
        }
        
        Optional<Product> producto = repo.buscarPorId(id);
        if (!producto.isPresent()) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        
        return producto.get();
    }
    
    @Override
    public List<Product> listar() {
        return repo.buscarTodos();
    }
    
    @Override
    public void eliminar(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido");
        }
        
        // Verificar que el producto exista antes de eliminar
        Optional<Product> producto = repo.buscarPorId(id);
        if (!producto.isPresent()) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        
        repo.eliminar(id);
    }

    @Override
    public List<Product> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
        throw new RuntimeException("El nombre de búsqueda no puede estar vacío");
    }
    
    return repo.buscarPorNombre(nombre.trim());
    }
}