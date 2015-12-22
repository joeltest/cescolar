/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.servicios.FormatoFacadeLocal;
import com.sector.utils.EstatusEnum;
import com.sesion.Sesion;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class FormatoRequestBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private FormatoFacadeLocal formatoService;
    
    public long getTotalFormatosPendientes(){
        return formatoService.obtenerContadorFormatos(EstatusEnum.PENDIENTE.getId(), sesion.getUsuarioSesion().getId());
    }
    
    public long getTotalFormatosDevueltos(){
        return formatoService.obtenerContadorFormatos(EstatusEnum.DEVUELTO.getId(), sesion.getUsuarioSesion().getId());
    }
    
    
    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

}
