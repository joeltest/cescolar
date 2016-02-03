/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automaticcrud;

import com.automaticcrud.generic.FacadeLocal;
import com.sector.modelo.Sucursal;
import com.sector.servicios.SucursalFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ihsa
 */
@ManagedBean
@SessionScoped
public class SucursalBean extends BaseCrud<Sucursal> {

    @EJB
    SucursalFacadeLocal servicio;

    public SucursalBean() {
        super(Sucursal.class);
    }

    @PostConstruct
    public void init() {
        try {
            preprarNuevoRegistro();
        } catch (InstantiationException ex) {
            Logger.getLogger(SucursalBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SucursalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminacion(ActionEvent event){   
        prepararEliminacion(event);
        getSelected().setEliminado("True");
        persistir();
    }
    
    @Override
    protected FacadeLocal<Sucursal> getService() {
        return servicio;
    }

}
