/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Alumno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> implements AlumnoFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }

    @Override
    public Alumno login(String numeroControl, String clave) {
        Alumno al = null;
        try{
         al = (Alumno) em.createQuery("SELECT a FROM Alumno a WHERE a.numeroControl ='"+numeroControl+"' AND a.clave = '"+clave+"' AND a.eliminado = 'False'")
                .getSingleResult();
        }catch(NoResultException nre){
            return al;
        }
        return al;
    }

    @Override
    public Alumno findNumeroControl(String numeroControl) {
         Alumno al = null;
        try{
         al = (Alumno) em.createQuery("SELECT a FROM Alumno a WHERE a.numeroControl ='"+numeroControl+"' AND a.eliminado = 'False'")
                .getSingleResult();
        }catch(NoResultException nre){
            return al;
        }
        return al;
    }
    
//    @Override
//    public List<Alumno> findAll(String numeroControl) {
//         al = (Alumno) em.createQuery("SELECT a FROM Alumno a WHERE a.numeroControl ='"+numeroControl+"' AND a.eliminado = 'False'")
//                .getSingleResult();
//    }
//    
}
