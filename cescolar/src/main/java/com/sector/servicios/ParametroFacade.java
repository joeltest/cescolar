/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Parametro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class ParametroFacade extends AbstractFacade<Parametro> implements ParametroFacadeLocal {

    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroFacade() {
        super(Parametro.class);
    }

    @Override
    public Parametro findActivo() {
        //
        return (Parametro) em.createNamedQuery("Parametro.findByActivo")
                .setParameter("activo", "True")
                .setMaxResults(1)
                .getSingleResult();

    }

}
