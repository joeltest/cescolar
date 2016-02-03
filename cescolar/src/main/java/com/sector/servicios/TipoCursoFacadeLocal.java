/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.TipoCurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface TipoCursoFacadeLocal {

    void create(TipoCurso tipoCurso);

    void edit(TipoCurso tipoCurso);

    void remove(TipoCurso tipoCurso);

    TipoCurso find(Object id);

    List<TipoCurso> findAll();

    List<TipoCurso> findRange(int[] range);

    int count();
    
}
