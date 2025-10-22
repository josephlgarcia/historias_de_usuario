/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hu4.repository;

import com.mycompany.hu4.domain.Product;
import java.util.List;

/**
 *
 * @author xxx
 */
public interface ProductRepository extends Repository<Product, Long> {
    List<Product> buscarPorNombre(String nombre);
}
