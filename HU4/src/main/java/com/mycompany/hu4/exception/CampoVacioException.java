/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.exception;

/**
 *
 * @author xxx
 */
public class CampoVacioException extends DatoInvalidoException{
    
    public CampoVacioException(String nombreCampo) {
        super("El campo '" + nombreCampo + "' no puede estar vacío");
    }
    
}
