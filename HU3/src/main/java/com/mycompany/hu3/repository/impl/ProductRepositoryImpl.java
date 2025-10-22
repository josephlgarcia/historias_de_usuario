/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu3.repository.impl;

import com.mycompany.hu3.dbconnection.DbConnection;
import com.mycompany.hu3.domain.Product;
import com.mycompany.hu3.infra.config.AppConfig;
import com.mycompany.hu3.repository.ProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author xxx
 */
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Product crear(Product p) {
        String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
        try (Connection conn = new DbConnection(new AppConfig()).open(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No se insertó ningún registro");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    p.setId(id);
                    return p;
                }
            }

            throw new SQLException("Sin ID generado");

        } catch (SQLException ex) {
            throw new RuntimeException("Error al crear producto: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Product> buscarPorId(Long id) {
        String sql = "SELECT id, name, price, stock FROM products WHERE id = ?";
        try (Connection conn = new DbConnection(new AppConfig()).open(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));
                    return Optional.of(product);
                }
            }

            return Optional.empty();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar producto por ID: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Product> buscarTodos() {
        String sql = "SELECT id, name, price, stock FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection conn = new DbConnection(new AppConfig()).open(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }

            return products;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar todos los productos: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Product actualizar(Product p) {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE id = ?";
        try (Connection conn = new DbConnection(new AppConfig()).open(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setLong(4, p.getId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No se actualizó ningún registro. Producto no encontrado con ID: " + p.getId());
            }

            return p;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar producto: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = new DbConnection(new AppConfig()).open(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No se eliminó ningún registro. Producto no encontrado con ID: " + id);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar producto: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Product> buscarPorNombre(String nombre) {
        String sql = "SELECT id, name, price, stock FROM products WHERE name LIKE ?";
        List<Product> products = new ArrayList<>();

        try (Connection conn = new DbConnection(new AppConfig()).open(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Agregar % para búsqueda parcial
            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));
                    products.add(product);
                }
            }

            return products;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar productos por nombre: " + ex.getMessage(), ex);
        }
    }
}
