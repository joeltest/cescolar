/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.constantes;

/**
 *
 * @author jorodriguez
 */
public enum TipoUsuarioEnum {
    
    ADMINISTRADOR(3),
    GERENTE(2),
    EMPLEADO(1);

    private TipoUsuarioEnum(int id) {
        this.id = id;
    }
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
