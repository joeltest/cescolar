/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.utils;

/**
 *
 * @author jorodriguez
 */
public enum EstatusEnum {

    PENDIENTE(1),
    DEVUELTO(2),
    CANCELADO(3);

    private final int id;

    private EstatusEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
