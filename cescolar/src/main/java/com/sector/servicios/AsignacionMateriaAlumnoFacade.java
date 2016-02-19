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
import javax.persistence.NoResultException;
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
        System.out.println("findAllPorInscripcionAlumno " + idInscripcionAlumno);
        return em.createQuery("SELECT i FROM AsignacionMateriaAlumno i WHERE i.idIncripcionAlumno.id = " + idInscripcionAlumno)
                .getResultList();
    }

    @Override
    public List<AsignacionMateriaAlumno> findAllPorInscripcionAlumno(int idMateria, int idCurso, int idGrado, int idGrupo, int idTurno) {
        return em.createQuery("SELECT i FROM AsignacionMateriaAlumno i "
                + " WHERE i.idMateria.id = :idMateria "
                + " AND i.idIncripcionAlumno.idCurso.id =:idCurso "
                + " AND i.idIncripcionAlumno.idGrado.id = :idGrado "
                + " AND i.idIncripcionAlumno.idGrupo.id = :idGrupo "
                + " AND i.idIncripcionAlumno.idTurno.id = :idTurno "
                + " AND i.eliminado = 'False'"
                + " ORDER BY i.idIncripcionAlumno.idAlumno.nombre ASC")
                .setParameter("idMateria", idMateria)
                .setParameter("idCurso", idCurso)
                .setParameter("idGrado", idGrado)
                .setParameter("idGrupo", idGrupo)
                .setParameter("idTurno", idTurno)
                .getResultList();
    }

    @Override
    public AsignacionMateriaAlumno findByInscripcionAlumno(int idAlumno, int idMateria, int idCurso, int idGrado, int idGrupo, int idTurno) {

        try {
            AsignacionMateriaAlumno a = (AsignacionMateriaAlumno) em.createQuery("SELECT i FROM AsignacionMateriaAlumno i "
                    + " WHERE i.idMateria.id = :idMateria "
                    + " AND i.idIncripcionAlumno.idCurso.id =:idCurso "
                    + " AND i.idIncripcionAlumno.idGrado.id = :idGrado "
                    + " AND i.idIncripcionAlumno.idGrupo.id = :idGrupo "
                    + " AND i.idIncripcionAlumno.idTurno.id = :idTurno "
                    + " AND i.idIncripcionAlumno.idAlumno.id = :idAlumno "
                    + " AND i.eliminado = 'False'"
                    + " ORDER BY i.idIncripcionAlumno.idAlumno.nombre ASC")
                    .setParameter("idMateria", idMateria)
                    .setParameter("idCurso", idCurso)
                    .setParameter("idGrado", idGrado)
                    .setParameter("idGrupo", idGrupo)
                    .setParameter("idTurno", idTurno)
                    .setParameter("idAlumno", idAlumno)
                    .getSingleResult();
            return a;
        } catch (NoResultException e) {
            return null;
        }
    }

}
