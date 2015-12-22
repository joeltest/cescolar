/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.constantes.OperacionesEnum;
import com.sector.modelo.AvisoSucursal;
import com.sector.modelo.ComentarioAvisoSucursal;
import com.sector.modelo.Sucursal;
import com.sector.servicios.AvisoSucursalFacadeLocal;
import com.sector.servicios.ComentarioAvisoSucursalFacadeLocal;
import com.sector.servicios.SucursalFacadeLocal;
import com.sector.utils.FacesUtils;
import com.sesion.Sesion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.IdClass;

@ManagedBean
@ViewScoped
public class AvisosBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private AvisoSucursalFacadeLocal avisoService;

    @EJB
    private ComentarioAvisoSucursalFacadeLocal comentarioService;
    
    @EJB
    private SucursalFacadeLocal sucursalService;

    private List<AvisoSucursal> listaAvisos;
    private List<ComentarioAvisoSucursal> listaComentariosAviso;
    private List<SelectItem> listaSucursalItems;

    private int idSucursaSeleccionada;
    private AvisoSucursal avisoSeleccionado;
     private ComentarioAvisoSucursal comentario;
    private OperacionesEnum operacionesEnum;

    @PostConstruct
    public void init() {
        cargarSucursalCombo();
        
        cargarAvisos();
        
    }
    
    
    public void cargarAvisos(){
        
        this.setListaAvisos(avisoService.findAllPorSucursal(idSucursaSeleccionada));
        
    }
    
    public void valueChangeSucursal(ValueChangeEvent va ){
        
        Integer value = (Integer) va.getNewValue();
        
        idSucursaSeleccionada = value;
        
        cargarAvisos();
        
    }
    
    public List<ComentarioAvisoSucursal> cargarComentarios(int idAviso){
        return comentarioService.findAllPorAviso(idAviso);
    }
    
     public void cargarSucursalCombo() {

        List<Sucursal> lista = sucursalService.findAll();

        idSucursaSeleccionada = lista != null ? lista.get(0).getId():-1;
        
        listaSucursalItems = new ArrayList<>();
        
        for (Sucursal u : lista) {
            getListaSucursalItems().add(new SelectItem(u.getId(), u.getNombre()));
            
        }
    }

    //***nuevo aviso
     public void prepararNuevoAviso(ActionEvent event){
         this.avisoSeleccionado = new AvisoSucursal();
         
     }
     
     public void guardarNuevoAviso(ActionEvent ev){
         avisoSeleccionado.setSucursal(new Sucursal(idSucursaSeleccionada));
         avisoSeleccionado.setUsuario(sesion.getUsuarioSesion());
         avisoSeleccionado.setEliminado("False");
         avisoSeleccionado.setFecha(new Date());
         avisoSeleccionado.setHora(new Date());
         avisoService.create(avisoSeleccionado);
         
         cargarAvisos();
         
         FacesUtils.addInfoMessage("Se publico nuevo Aviso");
     }
     
    //***Hacer comentaraios
     public void iniciarNuevoComentario(ActionEvent event){
         
         Integer idAviso = Integer.parseInt(FacesUtils.getRequestParameter("idAviso"));
         this.comentario = new ComentarioAvisoSucursal();
         
         avisoSeleccionado = avisoService.find(idAviso);
         
         this.comentario.setAvisoSucursal(avisoSeleccionado);
     }
     
     public void guardarNuevoComentario(ActionEvent event){
         getComentario().setEliminado("False");
         getComentario().setFecha(new Date());
         getComentario().setHora(new Date());
         
         comentarioService.create(getComentario());
         FacesUtils.addInfoMessage("Comerario realizadp..");
         //cargarAvisos();
         cargarAvisos();
     }
     
     
     
    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public int getIdSucursaSeleccionada() {
        return idSucursaSeleccionada;
    }

    public void setIdSucursaSeleccionada(int idSucursaSeleccionada) {
        this.idSucursaSeleccionada = idSucursaSeleccionada;
    }

    public AvisoSucursal getAvisoSeleccionado() {
        return avisoSeleccionado;
    }

    public void setAvisoSeleccionado(AvisoSucursal avisoSeleccionado) {
        this.avisoSeleccionado = avisoSeleccionado;
    }

    public List<AvisoSucursal> getListaAvisos() {
        return listaAvisos;
    }

    public void setListaAvisos(List<AvisoSucursal> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public List<SelectItem> getListaSucursalItems() {
        return listaSucursalItems;
    }

    public void setListaSucursalItems(List<SelectItem> listaSucursalItems) {
        this.listaSucursalItems = listaSucursalItems;
    }

    public ComentarioAvisoSucursal getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioAvisoSucursal comentario) {
        this.comentario = comentario;
    }
        
        
}
