/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.AsignacionMateriaAlumno;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface AsignacionMateriaAlumnoFacadeLocal {

    void create(AsignacionMateriaAlumno asignacionMateriaAlumno);

    void edit(AsignacionMateriaAlumno asignacionMateriaAlumno);

    void remove(AsignacionMateriaAlumno asignacionMateriaAlumno);

    AsignacionMateriaAlumno find(Object id);

    List<AsignacionMateriaAlumno> findAll();
    
    List<AsignacionMateriaAlumno> findAllPorInscripcionAlumno(int idInscripcionAlumno);

    List<AsignacionMateriaAlumno> findRange(int[] range);

    int count();
    
}
