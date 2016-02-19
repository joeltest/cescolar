/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Calificacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class CalificacionFacade extends AbstractFacade<Calificacion> implements CalificacionFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionFacade() {
        super(Calificacion.class);
    }

    @Override
    public List<Calificacion> listaCalificacionPorAsignacion(int idAsignacion) {
        return em.createQuery("SELECT c FROM Calificacion c WHERE c.asignacionMateriaAlumno.id = :idAsignacion AND c.eliminado = 'False'")
                .setParameter("idAsignacion", idAsignacion)
                .getResultList();
                
    }

    @Override
    public List<Calificacion> obtenerCardex(int idAsignacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
