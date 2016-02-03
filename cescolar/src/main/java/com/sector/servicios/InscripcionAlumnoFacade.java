/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.InscripcionAlumno;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class InscripcionAlumnoFacade extends AbstractFacade<InscripcionAlumno> implements InscripcionAlumnoFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionAlumnoFacade() {
        super(InscripcionAlumno.class);
    }

    @Override
    public InscripcionAlumno buscarInscripcionAlumno(int idAlumno,int idCurso) {
        InscripcionAlumno in = null;
        try{
        in = (InscripcionAlumno) em.createQuery("SELECT i FROM InscripcionAlumno i WHERE i.idAlumno.id = "+idAlumno+"  AND i.idCurso.id = "+idCurso+" AND i.eliminado = 'False'")
                .getSingleResult();
        
        }catch(NoResultException nr){
            return in;
        }
        return in;
    }
    
}
