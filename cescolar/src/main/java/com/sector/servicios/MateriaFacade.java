/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Materia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class MateriaFacade extends AbstractFacade<Materia> implements MateriaFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaFacade() {
        super(Materia.class);
    }

    @Override
    public List<Materia> findAllByGrado(int idGrado) {
       List<Materia> lista = em.createQuery("SELECT a FROM Materia a WHERE a.grado.id = "+idGrado)
                .getResultList();
        return lista;
    }

    @Override
    public List<Materia> findAllByDocente(int idDocente) {
           List<Materia> lista = em.createQuery("SELECT a FROM Materia a WHERE a.idDocente.id = "+idDocente)
                .getResultList();
           return lista;
    }
    
}
