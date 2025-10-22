/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.service.impl;

import com.mycompany.hu4.domain.Product;
import com.mycompany.hu4.exception.CampoVacioException;
import com.mycompany.hu4.exception.DatoInvalidoException;
import com.mycompany.hu4.exception.DuplicadoException;
import com.mycompany.hu4.exception.EntidadNoEncontradaException;
import com.mycompany.hu4.exception.PrecioInvalidoException;
import com.mycompany.hu4.exception.StockInvalidoException;
import com.mycompany.hu4.repository.impl.ProductRepositoryImpl;
import com.mycompany.hu4.service.ProductService;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author xxx
 */
public class ProductServiceImpl implements ProductService{
    
    private final ProductRepositoryImpl repo;
    
    public ProductServiceImpl(ProductRepositoryImpl repo) {
        this.repo = repo;
    }
    
    @Override
    public Product registrar(Product p) {
        if (p == null || p.getName() == null || p.getName().isBlank()) {
            throw new CampoVacioException("nombre");
        }
        
        if (p.getPrice() == null) {
            throw new CampoVacioException("precio");
        }
        if (p.getPrice() <= 0) {
            throw new PrecioInvalidoException();
        }
        
        if (p.getStock() == null) {
            throw new CampoVacioException("stock");
        }
        if (p.getStock() < 0) {
            throw new StockInvalidoException();
        }
        // Validar que no exista un producto con el mismo nombre
        List<Product> productos = repo.buscarTodos();
        boolean existe = productos.stream()
                .anyMatch(prod -> prod.getName().equalsIgnoreCase(p.getName().trim()));
        
        if (existe) {
            throw new DuplicadoException(p.getName());
        }
        
        return repo.crear(p);
    }
    
    @Override
    public Product actualizar(Product p) {
        if (p == null || p.getId() == null) {
            throw new DatoInvalidoException("El ID del producto es requerido para actualizar");
        }
        
        if (p.getName() == null || p.getName().isBlank()) {
            throw new CampoVacioException("nombre");
        }
        
        if (p.getPrice() == null) {
            throw new CampoVacioException("precio");
        }
        if (p.getPrice() <= 0) {
            throw new PrecioInvalidoException();
        }
        
        if (p.getStock() == null) {
            throw new CampoVacioException("stock");
        }
        if (p.getStock() < 0) {
            throw new StockInvalidoException();
        }
        
        // Verificar que el producto exista
        Optional<Product> productoExistente = repo.buscarPorId(p.getId());
        if (!productoExistente.isPresent()) {
            throw new EntidadNoEncontradaException("Producto", p.getId());
        }
        
        // Validar que no exista otro producto con el mismo nombre
        List<Product> productos = repo.buscarTodos();
        boolean existeOtro = productos.stream()
                .anyMatch(prod -> !prod.getId().equals(p.getId()) && 
                                 prod.getName().equalsIgnoreCase(p.getName().trim()));
        
        if (existeOtro) {
            throw new DuplicadoException(p.getName());
        }
        
        return repo.actualizar(p);
    }
    
    @Override
    public Product consultarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new DatoInvalidoException("El ID debe ser un número positivo");
        }
        
        Optional<Product> producto = repo.buscarPorId(id);
        if (!producto.isPresent()) {
            throw new EntidadNoEncontradaException("Producto", id);
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
            throw new DatoInvalidoException("El ID debe ser un número positivo");
        }
        
        // Verificar que el producto exista antes de eliminar
        Optional<Product> producto = repo.buscarPorId(id);
        if (!producto.isPresent()) {
            throw new EntidadNoEncontradaException("Producto", id);
        }
        
        repo.eliminar(id);
    }

    @Override
    public List<Product> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
        throw new CampoVacioException("nombre de búsqueda");
    }
    
    return repo.buscarPorNombre(nombre.trim());
    }
    
}
