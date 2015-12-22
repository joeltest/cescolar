/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.ComentarioAvisoSucursal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ihsa
 */
@Stateless
public class ComentarioAvisoSucursalFacade extends AbstractFacade<ComentarioAvisoSucursal> implements ComentarioAvisoSucursalFacadeLocal {

    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioAvisoSucursalFacade() {
        super(ComentarioAvisoSucursal.class);
    }

    @Override
    public List<ComentarioAvisoSucursal> findAllPorAviso(int idAviso) {

        List<ComentarioAvisoSucursal> lista
                = getEntityManager()
                .createNamedQuery("ComentarioAvisoSucursal.findAllByAviso")
                .setParameter("idAviso", idAviso)
                .getResultList();

        return lista;
    }

}
