/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Gerencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface GerenciaFacadeLocal {

    void create(Gerencia gerencia);

    void edit(Gerencia gerencia);

    void remove(Gerencia gerencia);

    Gerencia find(Object id);

    List<Gerencia> findAll();
    
    List<Gerencia> findAllGerencias(int idSucursal);

    List<Gerencia> findRange(int[] range);

    int count();
    
    
    
}
