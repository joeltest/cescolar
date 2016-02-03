/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Anio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface AnioFacadeLocal {

    void create(Anio anio);

    void edit(Anio anio);

    void remove(Anio anio);

    Anio find(Object id);

    List<Anio> findAll();

    List<Anio> findRange(int[] range);

    int count();
    
}
