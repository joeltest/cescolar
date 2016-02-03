/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Alumno;
import com.sector.modelo.Docente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class DocenteFacade extends AbstractFacade<Docente> implements DocenteFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }

    @Override
    public Docente login(String usuario, String clave) {
        Docente al = null;
        try{
           al = (Docente) em.createQuery("SELECT a FROM Docente a WHERE a.usuario ='"+usuario+"' AND a.clave = '"+clave+"' AND a.eliminado = 'False'")
                .getSingleResult();
           
        }catch(NoResultException nre){
            return al;
        }
        return al;
    }
    
}
