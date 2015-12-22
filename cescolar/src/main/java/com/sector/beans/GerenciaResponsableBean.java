/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.constantes.OperacionesEnum;
import com.sector.modelo.Gerencia;
import com.sector.modelo.Usuario;
import com.sector.servicios.GerenciaFacadeLocal;
import com.sector.servicios.UsuarioFacadeLocal;
import com.sector.utils.FacesUtils;
import com.sesion.Sesion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class GerenciaResponsableBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private GerenciaFacadeLocal gerenciaService;

    @EJB
    private UsuarioFacadeLocal usuarioService;

    private List<Gerencia> listaGerencias;
    private List<SelectItem> listaUsuarioGerenteItems;

    private Integer idGerenciaSeleccionado;

    private Integer idUsuarioSeleccionado;

    private Gerencia gerencia;
    private String descripcion;
    private Usuario usuario;

    private OperacionesEnum operacion;

    @PostConstruct
    public void init() {

        cargarListaGerencias();

    }

    public void prepararNuevaGerencia(ActionEvent event) {
        System.out.println("Preprar nueva gerenncia");

        //cargar gerencias
        this.gerencia = new Gerencia();

//        this.descripcion = "";

        this.idUsuarioSeleccionado = -1;

        cargarListaGerentesItems();

        this.operacion = OperacionesEnum.INSERTAR;
    }

    
    
    public void prepararModificacion(ActionEvent event) {
        System.out.println("prepararNuevoGerenciaUsuario");

        Integer idGerencia = Integer.valueOf(FacesUtils.getRequestParameter("idGerencia"));

        this.gerencia = gerenciaService.find(idGerencia);

        this.idUsuarioSeleccionado = gerencia.getUsuarioGerente().getId() != null ? gerencia.getUsuarioGerente().getId() : -1;

        cargarListaGerentesItems();

        this.operacion = OperacionesEnum.MODIFICAR;
    }

    public void prepararEliminacion(ActionEvent event) {
        System.out.println("preparar eliminacion");

        Integer idGerencia = Integer.valueOf(FacesUtils.getRequestParameter("idGerencia"));

        this.gerencia = gerenciaService.find(idGerencia);

        this.operacion = OperacionesEnum.ELIMINAR;
    }
    
    public void eliminar(ActionEvent e){
        
        gerencia.setEliminado("True");
        
        gerenciaService.edit(gerencia);
        
        cargarListaGerencias();
        
        FacesUtils.addWarnMessage("Se eliminó sin problemas la gerencia...");
    }
    
    
    
    public void guardarGerencia(ActionEvent ev) {
        System.out.println("guardarGerencia");
        gerencia.setEliminado("False");
        gerencia.setSucursal(sesion.getUsuarioSesion().getSucursal());

        Usuario usuario = usuarioService.find(idUsuarioSeleccionado);

        gerencia.setUsuarioGerente(usuario);
        
        if (operacion == OperacionesEnum.INSERTAR) {
            gerenciaService.create(gerencia);
            FacesUtils.addInfoMessage("Se guardó la gerencia...");
        }else{
            gerenciaService.edit(gerencia);
            FacesUtils.addInfoMessage("Se modifico la gerencia...");
        }   
        
        cargarListaGerencias();
    }


    public void cargarListaGerencias() {

        this.setListaGerencias(gerenciaService.findAllGerencias(sesion.getUsuarioSesion().getSucursal().getId()));

    }

    public void cargarListaGerentesItems() {

        final List<Usuario> lista
                = this.usuarioService.listaGerentes(sesion.getUsuarioSesion().getSucursal().getId());
        this.listaUsuarioGerenteItems = new ArrayList<>();

        for (Usuario u : lista) {
            getListaUsuarioGerenteItems().add(new SelectItem(u.getId(), u.getNombre()));
        }

    }

//
    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Integer getIdGerenciaSeleccionado() {
        return idGerenciaSeleccionado;
    }

    public void setIdGerenciaSeleccionado(Integer idGerenciaSeleccionado) {
        this.idGerenciaSeleccionado = idGerenciaSeleccionado;
    }

    public Gerencia getGerencia() {
        return gerencia;
    }

    public void setGerencia(Gerencia gerencia) {
        this.gerencia = gerencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdUsuarioSeleccionado() {
        return idUsuarioSeleccionado;
    }

    public void setIdUsuarioSeleccionado(Integer idUsuarioSeleccionado) {
        this.idUsuarioSeleccionado = idUsuarioSeleccionado;
    }

    public List<Gerencia> getListaGerencias() {
        return listaGerencias;
    }

    public void setListaGerencias(List<Gerencia> listaGerencias) {
        this.listaGerencias = listaGerencias;
    }

    public List<SelectItem> getListaUsuarioGerenteItems() {
        return listaUsuarioGerenteItems;
    }

    public void setListaUsuarioGerenteItems(List<SelectItem> listaUsuarioGerenteItems) {
        this.listaUsuarioGerenteItems = listaUsuarioGerenteItems;
    }

}
