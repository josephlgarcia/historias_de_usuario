/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hu3.repository;

import com.mycompany.hu3.domain.Product;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author xxx
 */
public interface Repository<T, ID> {

    Product crear(T t);

    Optional<T> buscarPorId(ID id);

    List<T> buscarTodos();

    T actualizar(T t);

    void eliminar(ID id);
}
