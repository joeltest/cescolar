/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.GerenciaFormato;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ihsa
 */
@Local
public interface GerenciaFormatoFacadeLocal {

    void create(GerenciaFormato gerenciaFormato);

    void edit(GerenciaFormato gerenciaFormato);

    void remove(GerenciaFormato gerenciaFormato);

    GerenciaFormato find(Object id);

    List<GerenciaFormato> findAll();

    List<GerenciaFormato> findRange(int[] range);

    int count();
    
    
    List<GerenciaFormato> listaGerenciaFormato(int gerencia);
    
}
