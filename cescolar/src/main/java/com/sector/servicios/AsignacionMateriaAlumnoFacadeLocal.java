/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.automaticcrud.generic.FacadeLocal;
import com.sector.modelo.AsignacionMateriaAlumno;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface AsignacionMateriaAlumnoFacadeLocal extends FacadeLocal<AsignacionMateriaAlumno>{

    List<AsignacionMateriaAlumno> findAllPorInscripcionAlumno(int idInscripcionAlumno);
    
    List<AsignacionMateriaAlumno> findAllPorInscripcionAlumno(int idMateria,int idCurso,int idGrado,int idGrupo, int idTurno);
    
    AsignacionMateriaAlumno findByInscripcionAlumno(int idAlumno,int idCurso);

    
}
