/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.AsignacionMateriaAlumno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class AsignacionMateriaAlumnoFacade extends AbstractFacade<AsignacionMateriaAlumno> implements AsignacionMateriaAlumnoFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionMateriaAlumnoFacade() {
        super(AsignacionMateriaAlumno.class);
    }


    @Override
    public List<AsignacionMateriaAlumno> findAllPorInscripcionAlumno(int idInscripcionAlumno) {
        return em.createQuery("SELECT i FROM AsignacionMateriaAlumno i WHERE i.idIncripcionAlumno.id = "+idInscripcionAlumno)
                .getResultList();
    }
    
}
