/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.DocenteMateria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class DocenteMateriaFacade extends AbstractFacade<DocenteMateria> implements DocenteMateriaFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteMateriaFacade() {
        super(DocenteMateria.class);
    }

    @Override
    public List<DocenteMateria> findAllPorTurno(int idDocente, int idTurno) {
        return em.createQuery("SELECT o FROM DocenteMateria o WHERE o.idDocente.id = "+idDocente+" AND o.idTurno.id = "+idTurno+" AND o.eliminado='False'")
                .getResultList();
    }
    
}
