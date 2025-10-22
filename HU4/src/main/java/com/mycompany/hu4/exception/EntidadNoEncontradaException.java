/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.exception;

/**
 *
 * @author xxx
 */
public class EntidadNoEncontradaException extends PersistenciaException {
    
    public EntidadNoEncontradaException(String entidad, Long id) {
        super(entidad + " con ID " + id + " no encontrado");
    }
    
    public EntidadNoEncontradaException(String mensaje) {
        super(mensaje);
    }
    
}
