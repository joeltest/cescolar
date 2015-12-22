/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.dto.ElementoDto;
import com.sector.modelo.Adjunto;
import com.sector.modelo.Parametro;
import com.sector.modelo.Sucursal;
import com.sector.modelo.TipoUsuario;
import com.sector.modelo.Usuario;
import com.sector.servicios.AdjuntoFacadeLocal;
import com.sector.servicios.ParametroFacadeLocal;
import com.sector.servicios.UsuarioFacadeLocal;
import com.sector.utils.FacesUtils;
import com.sesion.Sesion;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private AdjuntoFacadeLocal adjuntoService;
    @EJB
    private UsuarioFacadeLocal usuarioService;
    @EJB
    private ParametroFacadeLocal parametroService;

    private List<ElementoDto> listaElemento;
    private ElementoDto elementoSeleccionado;
    private ElementoDto carpetaSeleccionada;

    private List<Usuario> listaUsuarios;
    private Usuario usuarioSeleccionado;
    private Operacion operacion;

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    enum Operacion {

        GUARDAR, MODIFICAR, ELIMINAR
    };

    @PostConstruct
    public void init() {
    }

    public void crearInicio() {
        System.out.println("crear iniciio");
        System.out.println("crear " + getSesion().getUsuarioSesion().toString());
        cargarListas();
    }

    public void cargarListas() {
//        this.listaUsuarios = usuarioService.findAllUsuarios();
    }

    public void seleccionar(Usuario usuario) {
        this.usuarioSeleccionado = usuario;

    }

    public void prepararNuevo(ActionEvent event) {
        usuarioSeleccionado = new Usuario();
        operacion = Operacion.GUARDAR;

    }

    public void prepararModificacion(Usuario usuario) {
        usuarioSeleccionado = usuario;
        operacion = Operacion.MODIFICAR;

    }

    public void prepararEliminacion(Usuario usuario) {
        usuarioSeleccionado = usuario;
        operacion = Operacion.ELIMINAR;

    }

    public void guardar(ActionEvent event) {
        switch (operacion) {
            case GUARDAR:
                //crear las 4 carperas
                guardarUsuario();
//                crearCarpetasDefault();                
                System.out.println("Se guardo sin problemas");                
                break;
            case MODIFICAR:
                modificar();
                break;
            case ELIMINAR:
                eliminar();
                break;

        }
        cargarListas();
    }

    private void guardarUsuario() {
        usuarioSeleccionado.setEliminado("False");
        usuarioSeleccionado.setTipoUsuario(new TipoUsuario(2));
        usuarioSeleccionado.setSucursal(new Sucursal(1));
        usuarioSeleccionado.setFechaGenero(new Date());

        usuarioService.create(usuarioSeleccionado);
    }

    private void eliminar() {
        usuarioSeleccionado.setEliminado("True");
        usuarioService.edit(usuarioSeleccionado);
    }

    private void modificar() {
        usuarioService.edit(usuarioSeleccionado);
    }

//    private void crearCarpetasDefault() {
//        Parametro rutaRaiz = parametroService.findRaizActiva();
//        String elementoRaiz = rutaRaiz.getValor() + usuarioSeleccionado.getCorreo() + rutaRaiz.getSeparador();
//        
//         Adjunto padre = crearFolder(elementoRaiz, usuarioSeleccionado.getNombre(),null);
//        
//        String documentos = "Documentos";
//        String carpetaDocumentos = elementoRaiz + documentos + rutaRaiz.getSeparador();
//        crearFolder(carpetaDocumentos, documentos,padre);
//
//        String imagenes = "Imagenes";
//        String carpetaImagenres = elementoRaiz + imagenes + rutaRaiz.getSeparador();
//        crearFolder(carpetaImagenres, imagenes,padre);
//
//        String importantes = "Importantes";
//        String carpetaImportantes = elementoRaiz + importantes + rutaRaiz.getSeparador();
//        crearFolder(carpetaImportantes, importantes,padre);
//
//        String trabajo = "Trabajo";
//        String carpetaTrabajo = elementoRaiz + trabajo + rutaRaiz.getSeparador();        
//        crearFolder(carpetaTrabajo, trabajo,padre);
//        
//        String compartido = "Compartidos";
//        String carpetaCompartidos = elementoRaiz + trabajo + rutaRaiz.getSeparador();
//        crearFolder(carpetaCompartidos, compartido,padre);
//    }
//
//    public Elemento crearFolder(String rutaCompleta, String nombreFolder,Elemento padre) {
//        System.out.println("crearFolder");
//        System.out.println("Nombre del nuevo directorio " + rutaCompleta);
//        File directorio = new File(rutaCompleta);
//        Elemento folder =null;
//        if (directorio.mkdirs()) {
//            folder = new Elemento();
//            folder.setNombre(nombreFolder);
//            folder.setTipoArchivo("Carpeta");
//            folder.setPeso(String.valueOf("0"));
//            folder.setEsCarpeta("True");
//            folder.setEliminado("False");
//            folder.setFechaGenero(new Date());
//            folder.setHoraGenero(new Date());
//            folder.setUsuario(usuarioSeleccionado);
//            folder.setElementoPadre(padre);
//            folder.setSistema("True");
//            folder.setRuta(rutaCompleta);
//            this.adjuntoService.create(folder);
//            nombreFolder = "";
//            System.out.println("Se creo sin problemas el folder");
//            FacesUtils.addInfoMessage("Se creo sin problemas el folder..");
//        }
//        return folder;
//
//    }
//
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

}
