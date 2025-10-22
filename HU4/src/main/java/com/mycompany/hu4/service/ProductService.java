/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hu4.service;

import com.mycompany.hu4.domain.Product;
import java.util.List;

/**
 *
 * @author xxx
 */
public interface ProductService {
    
    Product registrar(Product p);

    Product actualizar(Product e);

    Product consultarPorId(Long id);

    List<Product> listar();

    void eliminar(Long id);
    
    List<Product> buscarPorNombre(String nombre);
}
