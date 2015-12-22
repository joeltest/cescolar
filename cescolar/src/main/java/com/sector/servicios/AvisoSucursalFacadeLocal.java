/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.AvisoSucursal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ihsa
 */
@Local
public interface AvisoSucursalFacadeLocal {

    void create(AvisoSucursal avisoSucursal);

    void edit(AvisoSucursal avisoSucursal);

    void remove(AvisoSucursal avisoSucursal);

    AvisoSucursal find(Object id);

    List<AvisoSucursal> findAll();
    
    List<AvisoSucursal> findAllPorSucursal(int idSucursal);

    List<AvisoSucursal> findRange(int[] range);

    int count();
    
}
