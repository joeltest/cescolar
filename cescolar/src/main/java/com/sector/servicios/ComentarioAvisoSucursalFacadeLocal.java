/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.ComentarioAvisoSucursal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ihsa
 */
@Local
public interface ComentarioAvisoSucursalFacadeLocal {

    void create(ComentarioAvisoSucursal comentarioAvisoSucursal);

    void edit(ComentarioAvisoSucursal comentarioAvisoSucursal);

    void remove(ComentarioAvisoSucursal comentarioAvisoSucursal);

    ComentarioAvisoSucursal find(Object id);

    List<ComentarioAvisoSucursal> findAll();
    
    List<ComentarioAvisoSucursal> findAllPorAviso(int idAviso);

    List<ComentarioAvisoSucursal> findRange(int[] range);

    int count();
    
}
