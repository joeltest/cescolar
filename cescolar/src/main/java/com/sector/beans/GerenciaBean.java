/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.constantes.OperacionesEnum;
import com.sector.modelo.Adjunto;
import com.sector.modelo.Estatus;
import com.sector.modelo.Formato;
import com.sector.modelo.Gerencia;
import com.sector.modelo.GerenciaFormato;
import com.sector.modelo.Parametro;
import com.sector.servicios.AdjuntoFacadeLocal;
import com.sector.servicios.FormatoFacadeLocal;
import com.sector.servicios.GerenciaFacadeLocal;
import com.sector.servicios.GerenciaFormatoFacadeLocal;
import com.sector.servicios.ParametroFacade;
import com.sector.servicios.ParametroFacadeLocal;
import com.sector.utils.EstatusEnum;
import com.sector.utils.FacesUtils;
import com.sesion.Sesion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class GerenciaBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private ParametroFacadeLocal parametroService;

    @EJB
    private AdjuntoFacadeLocal adjuntoService;

    @EJB
    private GerenciaFacadeLocal gerenciaService;

    @EJB
    private GerenciaFormatoFacadeLocal gerenciaFormatoService;

    private List<GerenciaFormato> listaRelaciones;
    private List<SelectItem> listaGerenciaItems;

    private Integer idGerenciaSeleccionado;
    private Adjunto adjunto;
    private Gerencia gerencia;
    private GerenciaFormato gerenciaFormato;
//    private String descripcion;
    private Parametro parametro;

    private OperacionesEnum operacionesEnum;

    @PostConstruct
    public void init() {
        parametro = parametroService.findActivo();

        if (parametro == null) {
            FacesUtils.addErrorMessage("Existe un error en la configuración del sistema, por favor contacte al equipo de soporte..");
        } else {
            //traer tab pendientes
            cargarGerenciasCombo();
        }
    }

    public void preprarNuevo(ActionEvent event) {
        System.out.println("Preprar nuevo registro");
        setOperacionesEnum(OperacionesEnum.INSERTAR);

        gerenciaFormato = new GerenciaFormato();

        this.adjunto = new Adjunto();

    }

    public void prepararModificacion(ActionEvent even) {
        Integer idRelacion = Integer.parseInt(FacesUtils.getRequestParameter("idRelacion"));

        setOperacionesEnum(OperacionesEnum.MODIFICAR);

        this.gerenciaFormato = gerenciaFormatoService.find(idRelacion);

        this.idGerenciaSeleccionado = gerenciaFormato.getId();

        this.adjunto = gerenciaFormato.getAdjunto();

        this.gerencia = gerenciaFormato.getGerencia();

    }

    public void prepararEliminacion(ActionEvent even) {

        Integer idRelacion = Integer.parseInt(FacesUtils.getRequestParameter("idRelacion"));

        setOperacionesEnum(OperacionesEnum.ELIMINAR);

        this.gerenciaFormato = gerenciaFormatoService.find(idRelacion);

    }

    public void valueChangeGerencia(ValueChangeEvent ev) {
        System.out.println("valueChangeGerencia");

        int value = (Integer) ev.getNewValue();

        this.gerencia = gerenciaService.find(value);

        this.setListaRelaciones(gerenciaFormatoService.listaGerenciaFormato(value));

    }
    
    public void actualizarListaGerencias(){
        
    }
    

    public void guardarRelacionGerenciaFormato(ActionEvent event) {

        if (getOperacionesEnum() == OperacionesEnum.INSERTAR) {

            //guardar adjunto
            adjuntoService.create(adjunto);

            //guardar gerencia formato
            GerenciaFormato gerenciaFormato = new GerenciaFormato();
            gerenciaFormato.setAdjunto(adjunto);
            gerenciaFormato.setGerencia(gerencia);

            gerenciaFormato.setEliminado("False");
            gerenciaFormato.setFechaGenero(new Date());
            gerenciaFormato.setGenero(sesion.getUsuarioSesion());

            gerenciaFormatoService.create(gerenciaFormato);

            System.out.println("guardado");

            this.listaRelaciones = gerenciaFormatoService.listaGerenciaFormato(gerencia.getId());

            FacesUtils.addInfoMessage("Se registró exitosamente el formato...");

        } else {

            gerenciaFormato.setGerencia(gerencia);

            gerenciaFormatoService.edit(gerenciaFormato);

            System.out.println("Editado");

            this.listaRelaciones = gerenciaFormatoService.listaGerenciaFormato(gerencia.getId());

            FacesUtils.addInfoMessage("Se modifico exitosamente el formato...");
            

        }

        //enviar correo
    }

    public void eliminar(ActionEvent ev) {

        File fichero = new File(gerenciaFormato.getAdjunto().getRuta());

        if (fichero.exists()) {
            if (fichero.delete()) {

                gerenciaFormato.setEliminado("True");
                gerenciaFormatoService.edit(gerenciaFormato);
                System.out.println("Se elimino sin probolemas ");

                System.out.println("Cargar ca");

                this.listaRelaciones = gerenciaFormatoService.listaGerenciaFormato(gerencia.getId());

                FacesUtils.addInfoMessage("Se eliminó el formato...");
            } else {
                System.out.println("Existió un error al tratar de eliminar el archivo.. por favor contacte al administrador.");
                FacesUtils.addInfoMessage("Existió un error al tratar de eliminar el archivo.. por favor contacte al administrador..");
            }
        } else {
            FacesUtils.addInfoMessage("No existe el archivo.. por favor contacte al administrador..");
        }

    }

    public void cargarGerenciasCombo() {

        List<Gerencia> lista = gerenciaService.findAll();

        this.listaGerenciaItems = new ArrayList<>();

        for (Gerencia u : lista) {
            getListaGerenciaItems().add(new SelectItem(u.getId(), u.getNombre() + "(" + u.getUsuarioGerente().getNombre() + ")s"));
        }
    }

    public void guardarRelacionGerenciaFormato() {
//        elementoSeleccionado.setg
    }

//
//    public void eliminarArchivo(ActionEvent event) {
//        System.out.println("eliminarArchivo " + elementoSeleccionado.getRuta());
//        File fichero = new File(elementoSeleccionado.getRuta());
//        if (fichero.exists()) {
//            if (fichero.delete()) {
//                final Elemento elemento = elementoService.find(elementoSeleccionado.getId());
//                elemento.setEliminado("True");
//                elementoService.edit(elemento);
//                System.out.println("Se elimino sin probolemas ");
//                System.out.println("Cargar ca");
//                cargarElementoParaDetalle(carpetaSeleccionada);
//                FacesUtils.addInfoMessage("Se eliminó el archivo " + elemento.getNombre());
//            } else {
//                System.out.println("Existió un error al tratar de eliminar el archivo.. por favor contacte al administrador.");
//                FacesUtils.addInfoMessage("Existió un error al tratar de eliminar el archivo.. por favor contacte al administrador..");
//            }
//        } else {
//            FacesUtils.addInfoMessage("No existe el archivo.. por favor contacte al administrador..");
//        }
//
//    }
//
    public String crearFolder() {
        System.out.println("crearFolder");
//        String nombreDirectorio = carpetaSeleccionada.getRuta()+folderDto.getNombre()+"\\";
        String rutaCompleta = parametro.getValor() + gerencia.getId() + parametro.getSeparador();
        System.out.println("Nombre del nuevo directorio " + rutaCompleta);
        File directorio = new File(rutaCompleta);
        directorio.mkdirs();
        return rutaCompleta;
    }
//

    public void uploadAttachment(FileUploadEvent event) {
        System.out.println("uploadAttachment para la carpeta");
        try {
            UploadedFile file = event.getFile();
            adjunto = new Adjunto();
            adjunto.setNombre(file.getFileName());
            adjunto.setTipoArchivo(file.getContentType());
            adjunto.setEliminado("False");
            adjunto.setFechaGenero(new Date());
            adjunto.setHoraGenero(new Date());
            adjunto.setEsRepositorio("False");
            adjunto.setGenero(sesion.getUsuarioSesion());
            adjunto.setSistema("False");
            String rutaCarpeta = crearFolder();
            adjunto.setRuta(rutaCarpeta + file.getFileName());
            copyFile(adjunto.getRuta(), event.getFile().getInputstream());
            FacesUtils.addInfoMessage("Se adjunto el formato...");
            System.out.println("Se copio");
        } catch (IOException ex) {
            System.out.println("Excepcion al subir archivo" + ex.getMessage());
            FacesUtils.addErrorMessage("Existió un error al adjuntar al archivo...");
        }

    }

    public void copyFile(String rutaCompleta, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            //OutputStream out = new FileOutputStream(new File(parametroRaiz.getValor() + fileName));
            OutputStream out = new FileOutputStream(new File(rutaCompleta));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//
//    /* Compartir con */
//    
//    
//    public void iniciarCompartirCon(ActionEvent event) {
//        System.out.println("Cargar " + elementoSeleccionado.getNombre());
//        cargarUsuariosCombo();
////        return "compartirArchivo.xhtml?faces-redirect=true";
//    }
//    
//    public void cargarUsuariosCombo() {
////        List<Usuario> lista = usuarioService.findAllUsuariosCompartir(elementoSeleccionado.getId(),sesion.getUsuarioSesion().getId());
////        listaUsuarioCompartirItems = new ArrayList<>();
////        for (Usuario u : lista) {
////            listaUsuarioCompartirItems.add(new SelectItem(u.getId(), u.getNombre()));
////        }
//       
//    }
//
////    public void buscarUsuariosCompartir(ActionEvent event){
////        System.out.println("Usuarioc compartir con");
////        this.listaUsuarioCompartir = usuarioService.findAllUsuarios(this.nombreUsuarioCompartir);
////        
////    }
////    
//    
//    
//    public void compartirElemento(ActionEvent event) {
//        System.out.println("Compartir con");
//        //Integer idUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuarioCompartir"));
//        System.out.println("Id usario " + this.getIdUsuarioCompartir());
//
//        compartirService.compartirCon(getIdUsuarioCompartir(),elementoSeleccionado.getId(), true, comentario);
//
//        cargarCompartidos();
//    }
//    
//    private void cargarCompartidos(){
//        this.listaUsuarioCompartidos = compartirService.findAllCompartidos(elementoSeleccionado.getId());
//    }
//
//    public List<SelectItem> getListaUsuarioCompartirItems() {
//        return this.listaUsuarioCompartirItems;
//    }
//
//    
//    public void quitarCompartir(Integer id){
//        ComparteCon compar = compartirService.find(id);
//        compar.setEliminado("True");
//        this.compartirService.edit(compar);
//        System.out.println("Se quito el compartido ");
//        cargarCompartidos();
//        FacesUtils.addInfoMessage("Se elimino sin problemas..");
//    }
//    

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public List<SelectItem> getListaGerenciaItems() {
        return listaGerenciaItems;
    }

    public void setListaGerenciaItems(List<SelectItem> listaGerenciaItems) {
        this.listaGerenciaItems = listaGerenciaItems;
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

    public List<GerenciaFormato> getListaRelaciones() {
        return listaRelaciones;
    }

    public void setListaRelaciones(List<GerenciaFormato> listaRelaciones) {
        this.listaRelaciones = listaRelaciones;
    }

//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
    public GerenciaFormato getGerenciaFormato() {
        return gerenciaFormato;
    }

    public void setGerenciaFormato(GerenciaFormato gerenciaFormato) {
        this.gerenciaFormato = gerenciaFormato;
    }

    public OperacionesEnum getOperacionesEnum() {
        return operacionesEnum;
    }

    public void setOperacionesEnum(OperacionesEnum operacionesEnum) {
        this.operacionesEnum = operacionesEnum;
    }

}
