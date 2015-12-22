/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Adjunto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface AdjuntoFacadeLocal {

    void create(Adjunto adjunto);

    void edit(Adjunto adjunto);

    void remove(Adjunto adjunto);

    Adjunto find(Object id);

    List<Adjunto> findAll();

    List<Adjunto> findRange(int[] range);

    int count();
    
    
    
}
