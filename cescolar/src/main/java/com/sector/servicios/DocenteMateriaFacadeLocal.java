/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.automaticcrud.generic.FacadeLocal;
import com.sector.modelo.DocenteMateria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface DocenteMateriaFacadeLocal extends FacadeLocal<DocenteMateria>{


    List<DocenteMateria> findAllPorTurno(int idDocente,int idTurno);
    
}
