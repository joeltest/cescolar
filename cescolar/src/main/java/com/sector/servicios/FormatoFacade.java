/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Formato;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class FormatoFacade extends AbstractFacade<Formato> implements FormatoFacadeLocal {
    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormatoFacade() {
        super(Formato.class);
    }

    @Override
    public List<Formato> obteberListaFormatos(int estatus,int idUsuario) {
        
        return (List<Formato>) em.createQuery("SELECT f FROM Formato f WHERE f.usuarioGenero.id = "+idUsuario
                + " AND f.estatus.id = "+estatus
                + " AND f.eliminado = 'False'")
                .getResultList();
    }

    @Override
    public long obtenerContadorFormatos(int estatus, int idUsuario) {
         return (long) em.createQuery("SELECT COUNT(f) FROM Formato f WHERE f.usuarioGenero.id = "+idUsuario
                + " AND f.estatus.id = "+estatus
                + " AND f.eliminado = 'False'")
                .getSingleResult();
    }
    
    @Override
    public List<Formato>  obtenerFormatosPorGerencia(int idGerenicia) {
         return (List<Formato>) em.createQuery("SELECT f FROM Formato f WHERE f.gerenciaAprueba.id = "+idGerenicia
                + " AND f.adjunto.esRepositorio = 'True'"
                + " AND f.eliminado = 'False'")
                .getResultList();
    }
    
}
