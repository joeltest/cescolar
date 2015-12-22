/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Formato;
import com.sector.modelo.GerenciaFormato;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ihsa
 */
@Stateless
public class GerenciaFormatoFacade extends AbstractFacade<GerenciaFormato> implements GerenciaFormatoFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GerenciaFormatoFacade() {
        super(GerenciaFormato.class);
    }

    @Override
    public List<GerenciaFormato> listaGerenciaFormato(int gerencia) {
        return 
                em.createQuery("SELECT f FROM GerenciaFormato f WHERE f.gerencia.id = "+gerencia                
                + " AND f.eliminado = 'False'")
                .getResultList();
    }
    
}
