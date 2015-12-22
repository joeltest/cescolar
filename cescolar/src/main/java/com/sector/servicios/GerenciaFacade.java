/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Gerencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class GerenciaFacade extends AbstractFacade<Gerencia> implements GerenciaFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GerenciaFacade() {
        super(Gerencia.class);
    }

    @Override
    public List<Gerencia> findAllGerencias(int idSucursal) {
        return getEntityManager()
                .createQuery("SELECT o FROM Gerencia o WHERE o.sucursal.id="+idSucursal+" AND o.eliminado = 'False'")
                .getResultList();
        
    }
    
}
