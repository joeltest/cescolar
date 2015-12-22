/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.Formato;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface FormatoFacadeLocal {

    void create(Formato formato);

    void edit(Formato formato);

    void remove(Formato formato);

    Formato find(Object id);

    List<Formato> findAll();

    List<Formato> findRange(int[] range);

    int count();
        
    List<Formato> obteberListaFormatos(int estatus,int idUsuario);
    
    long obtenerContadorFormatos(int estatus,int idUsuario);
    
     List<Formato>  obtenerFormatosPorGerencia(int idGerenicia);
    
}
