/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Alumno;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface AlumnoFacadeLocal {

    void create(Alumno alumno);

    void edit(Alumno alumno);

    void remove(Alumno alumno);

    Alumno find(Object id);
    
    Alumno login(String numeroControl,String clave);
    
    List<Alumno> findAll();
    

    List<Alumno> findRange(int[] range);

    int count();
    
    Alumno findNumeroControl(String comodin);
    
    
}
