/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import com.sector.dto.ElementoDto;
import com.sector.dto.FolderDto;
import com.sector.modelo.Usuario;
import com.sector.servicios.UsuarioFacadeLocal;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author jorodriguez Falta Crear 4 carpetas de sistema cada vez que se cree un
 * usuario
 *
 * - Compartir archivos - Compartir carpetas - Arrreglar Bajar archivo - Crear
 * administracion de usuarios solo con el rol de Administrador - * Dar de alta
 * usuarios - * Crear raiz con las carpetas de sistema - * 1. Documentos, 2.
 * Imagenes, 3. Importantes 4. Compartido conmigo
 *
 *
 */
@ManagedBean
@ViewScoped
public class ArbolBean implements Serializable {

//    @ManagedProperty(value = "#{sesion}")
//    private Sesion sesion;
//
//    @EJB
//    private ElementoFacadeLocal elementoService;
//    @EJB
//    private ParametroFacadeLocal parametroService;
//    @EJB
//    private ComparteConFacadeLocal compartirService;
//
//    @EJB
//    private UsuarioFacadeLocal usuarioService;
//
//    private TreeNode root;
//
//    private TreeNode elementoNode;
//    private TreeNode carpetaNode;
//
//    private List<ElementoDto> listaElemento;
//    private ElementoDto elementoSeleccionado;
//    private ElementoDto carpetaSeleccionada;
//
//    private Parametro parametroRaiz;
//    private Elemento elemento;
//
//    private FolderDto folderDto;
//    private String nombreFolder;
//    private String comentario;
//    private String nombreUsuarioCompartir;
//
//    private int idUsuarioCompartir;
//
//    private List<Usuario> listaUsuarioCompartir;
//    private List<SelectItem> listaUsuarioCompartirItems;
//    private List<ComparteCon> listaUsuarioCompartidos;
//
//    private StreamedContent file;
//    //private final String destination = "C:\\tmp\\";
////    private final String destination = "/local/";
//
//    @PostConstruct
//    public void init() {
//        parametroRaiz = parametroService.findRaizActiva();
//        System.out.println("Guardar en raiz " + parametroRaiz.getNombre() + " raiz : " + parametroRaiz.getValor());
//        if (parametroRaiz == null || parametroRaiz.getValor().equals("")) {
//            FacesUtils.addErrorMessage("Error al cargar la raiz...");
//        } else {
//            crearInicio();
//        }
//    }
//
//    public void crearInicio() {
//        System.out.println("crear iniciio");
//        System.out.println("crear " + sesion.getUsuarioSesion().toString());
//        ElementoDto raiz = elementoService.traerElementoRaiz(sesion.getUsuarioSesion().getId());
//
//        //asignar la raiz a la carpeta seleccionada
//        carpetaSeleccionada = raiz;
//
//        //crear arbol       
//        root = new DefaultTreeNode(raiz, null);
//
//        agregarNodos(carpetaSeleccionada.getId(), root);
//
//        //si existieron carpetas en el arbol seleccionar la primer
//        if (listaElemento != null && !listaElemento.isEmpty()) {
//            this.carpetaSeleccionada = listaElemento.get(0);
//        }
//
//    }
//
////    public void onNodeSelected(NodeSelectEvent event) {
////        System.out.println("onNodeSelected");
////        //ElementoDto dtoSeleccionado = (ElementoDto) event.getTreeNode().getData();
////        elementoSeleccionado = (ElementoDto) event.getTreeNode().getData();
////        TreeNode nodoActivo = event.getTreeNode();
////        if (elementoSeleccionado.getEsCarpeta().equals("True")) {
////            TreeNode nuevoNodo = agregarNodos(elementoSeleccionado.getId(), nodoActivo);
////            setSelectedCarpetaNode(nodoActivo);
////            setCarpetaSeleccionada(elementoSeleccionado);
//////            nodoActivo.setExpanded(true);
////        } else {
////            System.out.println("Visualizar archivo...");
////
////        }
////    }
//    public void vizualizarContenidoCarpetaPadre(NodeSelectEvent event) {
//        System.out.println("onNodeSelected");
//        elementoSeleccionado = (ElementoDto) event.getTreeNode().getData();
////        TreeNode nodoActivo = event.getTreeNode();
//
//        cargarContenido();
//    }
//
//    private void cargarContenido() {
//        if (elementoSeleccionado.getEsCarpeta().equals("True")) {
//            System.out.println("Cargar contenido carpeta " + elementoSeleccionado.getNombre());
//
//            carpetaSeleccionada = elementoSeleccionado;
//            listaElemento = elementoService.traerElementosDeElementoPadre(carpetaSeleccionada.getId());
//        } else {
//            prepararDonwload();
//            cargarCompartidos();
//            System.out.println("Visualizar elemento.." + elementoSeleccionado.getNombre());
//
//        }
//    }
//
//    public void verDetalleArchivo(ActionEvent event) {
//        System.out.println("CArgar detale.." + elementoSeleccionado.toString());
//
//    }
//
//    public void cargarElementoParaDetalle(ElementoDto elemento) {
//        System.out.println("Cargar " + elemento.getNombre());
//        this.elementoSeleccionado = elemento;
//        cargarContenido();
////        return "compartirArchivo.xhtml?faces-redirect=true";
//    }
//
//    public void verCarpeta(ActionEvent event) {
//        System.out.println("CArgar detalle carpeta..");
//
//    }
//
//    private TreeNode agregarNodos(int idElementoPadre, TreeNode nodeToAdd) {
//        TreeNode nodo = null;
//        setListaElemento(elementoService.traerElementosDeElementoPadre(idElementoPadre));
//        if (nodeToAdd.getChildCount() == 0) {
//            if (getListaElemento() != null) {
//                for (ElementoDto dto : getListaElemento()) {
//                    System.out.println("sub " + dto.getNombre());
//                    nodo = new DefaultTreeNode(dto, nodeToAdd);
//                    nodeToAdd.getChildren().add(nodo);
//                    nodeToAdd.setExpanded(true);
//                }
//            } else {
//                System.out.println("no existen mas elementos para " + idElementoPadre);
//            }
//        }
//        return nodo;
//    }
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
//    public void prepararCrearCarpeta(ActionEvent event) {
//        System.out.println("prepararCrearCarpeta");
//        this.folderDto = new FolderDto();
//    }
//
//    public void crearFolder(ActionEvent event) {
//        System.out.println("crearFolder");
////        String nombreDirectorio = carpetaSeleccionada.getRuta()+folderDto.getNombre()+"\\";
//        String rutaCompleta = carpetaSeleccionada.getRuta() + nombreFolder;
//        System.out.println("Nombre del nuevo directorio " + rutaCompleta);
//        File directorio = new File(rutaCompleta);
//        if (directorio.mkdir()) {
//            Elemento folder = new Elemento();
//            folder.setNombre(nombreFolder);
//            folder.setTipoArchivo("Carpeta");
//            folder.setPeso(String.valueOf("0"));
//            folder.setEsCarpeta("True");
//            folder.setEliminado("False");
//            folder.setFechaGenero(new Date());
//            folder.setHoraGenero(new Date());
//            folder.setUsuario(sesion.getUsuarioSesion());
//            folder.setElementoPadre(new Elemento(carpetaSeleccionada.getId()));
//            folder.setSistema("False");
//            folder.setRuta(rutaCompleta + parametroRaiz.getSeparador());
//            this.elementoService.create(folder);
//
//            elementoSeleccionado = carpetaSeleccionada;
//            cargarContenido();
//            nombreFolder = "";
//            System.out.println("Se creo sin problemas el folder");
//            FacesUtils.addInfoMessage("Se creo sin problemas el folder..");
//        }
//
//    }
//
//    public void prepararDonwload() {       
//               
//        InputStream stream = ((ServletContext) FacesContext
//                .getCurrentInstance()
//                .getExternalContext()
//                .getContext())
//                .getResourceAsStream(elementoSeleccionado.getRuta());
//
//        file = new DefaultStreamedContent(stream,
//                elementoSeleccionado.getTipoArchivo(),
//                elementoSeleccionado.getNombre());
//
//    }
//
//    public StreamedContent getFile() {
//        return file;
//    }
//
//    public void uploadAttachment(FileUploadEvent event) {
//        System.out.println("uploadAttachment para la carpeta" + elementoSeleccionado.getNombre());
//        try {
//            UploadedFile file = event.getFile();
//            elemento = new Elemento();
//            elemento.setNombre(file.getFileName());
//            elemento.setTipoArchivo(file.getContentType());
//            elemento.setPeso(String.valueOf(file.getSize()));
//            elemento.setEsCarpeta("False");
//            elemento.setEliminado("False");
//            elemento.setFechaGenero(new Date());
//            elemento.setHoraGenero(new Date());
//            elemento.setUsuario(sesion.getUsuarioSesion());
//            elemento.setElementoPadre(new Elemento(elementoSeleccionado.getId()));
//            //elemento.setRuta(parametroRaiz.getValor() + event.getFile().getFileName());
//            elemento.setRuta(carpetaSeleccionada.getRuta() + event.getFile().getFileName());
//
//            elementoService.create(elemento);
//
//            System.out.println("Elemento " + elemento.toString());
//
//            //copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
//            copyFile(elemento.getRuta(), event.getFile().getInputstream());
//
////            getCarpetaNode().getChildren().removeAll(getCarpetaNode().getChildren());
////            TreeNode nuevoNodo = agregarNodos(carpetaSeleccionada.getId(), getCarpetaNode());
//            cargarContenido();
//
//            System.out.println("Se copio");
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("Archivo cargado: %s ", file.getFileName()),
//                    ""));
//        } catch (IOException ex) {
//            Logger.getLogger(ArchivoBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public void copyFile(String rutaCompleta, InputStream in) {
//        try {
//            // write the inputStream to a FileOutputStream
//            //OutputStream out = new FileOutputStream(new File(parametroRaiz.getValor() + fileName));
//            OutputStream out = new FileOutputStream(new File(rutaCompleta));
//
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = in.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//
//            in.close();
//            out.flush();
//            out.close();
//
//            System.out.println("New file created!");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
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

}
