/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.exception;

/**
 *
 * @author xxx
 */
public class ConexionException extends PersistenciaException {
    
    public ConexionException(String message, Throwable cause) {
        super("Error de conexión a la base de datos: " + message, cause);
    }
    
}
