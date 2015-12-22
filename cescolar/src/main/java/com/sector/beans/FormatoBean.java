/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.modelo.Adjunto;
import com.sector.modelo.Estatus;
import com.sector.modelo.Formato;
import com.sector.modelo.Gerencia;
import com.sector.servicios.FormatoFacadeLocal;
import com.sector.servicios.GerenciaFacadeLocal;
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
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class FormatoBean implements Serializable {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private FormatoFacadeLocal formatoService;
    @EJB
    private GerenciaFacadeLocal gerenciaService;

    private List<Formato> listaFormato;
    private Formato elementoSeleccionado;

    private List<SelectItem> listaGerenciaItems;

    private Integer idGerenciaSeleccionado;
    private Adjunto adjunto;

//    private StreamedContent file;
    private final String destination = "C:\\tmp\\";
////    private final String destination = "/local/";

    @PostConstruct
    public void init() {
        //traer tab pendientes
        cargarLista();
    }

    private void cargarLista() {
        setListaFormato(formatoService.obteberListaFormatos(EstatusEnum.PENDIENTE.getId(), getSesion().getUsuarioSesion().getId()));
    }

    public void preprarNuevo(ActionEvent event) {
        System.out.println("Preprar nuevo registro");

        elementoSeleccionado = new Formato();

        //cargar gerencias
        cargarGerenciasCombo();

    }

    public void guardarRegistroFormato(ActionEvent event) {

        Gerencia gerencia = gerenciaService.find(idGerenciaSeleccionado);
        elementoSeleccionado.setFechaGenero(new Date());
        elementoSeleccionado.setHoraGenero(new Date());
        elementoSeleccionado.setEliminado("False");
        elementoSeleccionado.setEstatus(new Estatus(EstatusEnum.PENDIENTE.getId()));
        elementoSeleccionado.setGerenciaAprueba(gerencia);
        elementoSeleccionado.setUsuarioAprueba(gerencia.getUsuarioGerente());
        elementoSeleccionado.setUsuarioGenero(sesion.getUsuarioSesion());
        formatoService.create(elementoSeleccionado);
        System.out.println("guardado");
        cargarLista();
        FacesUtils.addInfoMessage("Se registró exitosamente el formato...");
    }

    public void cargarGerenciasCombo() {
        List<Gerencia> lista = gerenciaService.findAll();

        listaGerenciaItems = new ArrayList<>();
        for (Gerencia u : lista) {
            listaGerenciaItems.add(new SelectItem(u.getId(), u.getNombre()));
        }
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
        String rutaCompleta = destination + "\\" + elementoSeleccionado.getId();
        System.out.println("Nombre del nuevo directorio " + rutaCompleta);
        File directorio = new File(rutaCompleta);
        directorio.mkdir();
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
//            adjunto.setPeso(String.valueOf(file.getSize()));
            adjunto.setEliminado("False");
            adjunto.setFechaGenero(new Date());
            adjunto.setHoraGenero(new Date());
            adjunto.setEsRepositorio("False");
            adjunto.setGenero(sesion.getUsuarioSesion());
            adjunto.setSistema("False");

            String rutaCarpeta = crearFolder();

            adjunto.setRuta(rutaCarpeta + "\\" + file.getFileName());
            copyFile(adjunto.getRuta(), event.getFile().getInputstream());
            FacesUtils.addInfoMessage("Se adjunto el formato...");
            System.out.println("Se copio");
        } catch (IOException ex) {
            System.out.println("Excepcion al subir archivo" + ex.getMessage());
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
//    /**
//     * @return the sesion
//     */
//    public Sesion getSesion() {
//        return sesion;
//    }
//
//    public TreeNode getCarpetaNode() {
//        return carpetaNode;
//    }
//
//    public void setCarpetaNode(TreeNode carpetaNode) {
//        this.carpetaNode = carpetaNode;
//    }
//
//    /**
//     * @param sesion the sesion to set
//     */
//    public void setSesion(Sesion sesion) {
//        this.sesion = sesion;
//    }
//
//    public TreeNode getRoot() {
//        return root;
//    }
//
//    public void setRoot(TreeNode root) {
//        this.root = root;
//    }
//
//    public List<ElementoDto> getListaElemento() {
//        return listaElemento;
//    }
//
//    public void setListaElemento(List<ElementoDto> listaElemento) {
//        this.listaElemento = listaElemento;
//    }
//
//    public ElementoDto getElementoSeleccionado() {
//        return elementoSeleccionado;
//    }
//
//    public void setElementoSeleccionado(ElementoDto elementoSeleccionado) {
//        this.elementoSeleccionado = elementoSeleccionado;
//    }
//
//    public ElementoDto getCarpetaSeleccionada() {
//        return carpetaSeleccionada;
//    }
//
//    public void setCarpetaSeleccionada(ElementoDto carpetaSeleccionada) {
//        this.carpetaSeleccionada = carpetaSeleccionada;
//    }
//
//    public TreeNode getCarpetaActiva() {
//        return getCarpetaNode();
//    }
//
//    public void setCarpetaActiva(TreeNode selectedCarpetaNode) {
//        this.setCarpetaNode(selectedCarpetaNode);
//    }
//
//    public TreeNode getNodoSeleccionado() {
//        return elementoNode;
//    }
//
//    public void setNodoSeleccionado(TreeNode nodoSeleccionado) {
//        this.elementoNode = nodoSeleccionado;
//    }
//
//    public FolderDto getFolderDto() {
//        return folderDto;
//    }
//
//    public void setFolderDto(FolderDto folderDto) {
//        this.folderDto = folderDto;
//    }
//
//    public String getNombreFolder() {
//        return nombreFolder;
//    }
//
//    public void setNombreFolder(String nombreFolder) {
//        this.nombreFolder = nombreFolder;
//    }
//
//    public String getNombreUsuarioCompartir() {
//        return nombreUsuarioCompartir;
//    }
//
//    public void setNombreUsuarioCompartir(String nombreUsuarioCompartir) {
//        this.nombreUsuarioCompartir = nombreUsuarioCompartir;
//    }
//
//    public List<Usuario> getListaUsuarioCompartir() {
//        return listaUsuarioCompartir;
//    }
//
//    public void setListaUsuarioCompartir(List<Usuario> listaUsuarioCompartir) {
//        this.listaUsuarioCompartir = listaUsuarioCompartir;
//    }
//
//    public String getComentario() {
//        return comentario;
//    }
//
//    public void setComentario(String comentario) {
//        this.comentario = comentario;
//    }
//
//    public int getIdUsuarioCompartir() {
//        return idUsuarioCompartir;
//    }
//
//    public void setIdUsuarioCompartir(int idUsuarioCompartir) {
//        this.idUsuarioCompartir = idUsuarioCompartir;
//    }
//
//    public List<ComparteCon> getListaUsuarioCompartidos() {
//        return listaUsuarioCompartidos;
//    }
//
//    public void setListaUsuarioCompartidos(List<ComparteCon> listaUsuarioCompartidos) {
//        this.listaUsuarioCompartidos = listaUsuarioCompartidos;
//    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public List<Formato> getListaFormato() {
        return listaFormato;
    }

    public void setListaFormato(List<Formato> listaFormato) {
        this.listaFormato = listaFormato;
    }

    public Formato getElementoSeleccionado() {
        return elementoSeleccionado;
    }

    public void setElementoSeleccionado(Formato elementoSeleccionado) {
        this.elementoSeleccionado = elementoSeleccionado;
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

}
