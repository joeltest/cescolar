/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.automaticcrud.generic.FacadeLocal;
import com.sector.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jorodriguez
 */
@Local
public interface UsuarioFacadeLocal extends FacadeLocal<Usuario>{

    Usuario login(String correo,String clave);
    
    List<Usuario> listaGerentes(int idSucursal);
    
    
}
