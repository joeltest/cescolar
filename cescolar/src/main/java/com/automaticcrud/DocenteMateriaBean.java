/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automaticcrud;

import com.automaticcrud.generic.FacadeLocal;
import com.sector.modelo.Alumno;
import com.sector.modelo.Docente;
import com.sector.modelo.DocenteMateria;
import com.sector.modelo.Materia;
import com.sector.modelo.Turno;
import com.sector.servicios.AlumnoFacadeLocal;
import com.sector.servicios.AsignacionMateriaAlumnoFacadeLocal;
import com.sector.servicios.CursoFacadeLocal;
import com.sector.servicios.DocenteFacadeLocal;
import com.sector.servicios.DocenteMateriaFacadeLocal;
import com.sector.servicios.GradoFacadeLocal;
import com.sector.servicios.GrupoFacadeLocal;
import com.sector.servicios.InscripcionAlumnoFacadeLocal;
import com.sector.servicios.MateriaFacadeLocal;
import com.sector.servicios.TurnoFacadeLocal;
import com.sector.utils.FacesUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author ihsa
 */
@ManagedBean
@SessionScoped
public class DocenteMateriaBean extends BaseCrud<DocenteMateria> {

    @EJB
    private DocenteMateriaFacadeLocal servicio;

    @EJB
    private AlumnoFacadeLocal alummnoService;

    @EJB
    private InscripcionAlumnoFacadeLocal inscripcionAlummnoService;

    @EJB
    private CursoFacadeLocal cursoService;

    @EJB
    private GradoFacadeLocal gradoService;

    @EJB
    private GrupoFacadeLocal grupoService;

    @EJB
    private TurnoFacadeLocal turnoService;

    @EJB
    private MateriaFacadeLocal materiaService;

    @EJB
    private AsignacionMateriaAlumnoFacadeLocal asignacionMateriAlumnoMateriaService;

    @EJB
    private DocenteFacadeLocal docenteService;

    List<Alumno> listaAlumno;
    private List<Materia> listaMateria;
    private List<DocenteMateria> listaDocenteMateria;

    private String numeroControl;
    private Docente docente;
    private Materia materia;

    private List<SelectItem> listaDocentesItems;
    private List<SelectItem> listaTurnoItems;
    private List<SelectItem> listaMateriaItems;

    private int idTurnoSeleccionado;
    private int idDocentreSeleccionado;
    private int idMateriaSeleccionado;

    public DocenteMateriaBean() {
        super(DocenteMateria.class);
    }

    @PostConstruct
    public void init() {
        try {
            preprarNuevoRegistro();
            idDocentreSeleccionado = -1;
            idTurnoSeleccionado = -1;
            idMateriaSeleccionado = -1;
            llenarTurnoItem();
            llenarDocentesItem();

        } catch (InstantiationException ex) {
            Logger.getLogger(DocenteMateriaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DocenteMateriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminacion(ActionEvent event) {
        prepararEliminacion(event);
        getSelected().setEliminado("True");
        persistir();
        this.setSelected(null);
        FacesUtils.addInfoMessage("Se elimino el registro..");
    }

    public void buscarPorSeleccion(ActionEvent event) {
        System.out.println("Buscar por seleccion");
        docente = docenteService.find(idDocentreSeleccionado);

    }

    public void cargarListaDocenteMateria() {

        if (idDocentreSeleccionado != -1 && idTurnoSeleccionado != -1) {
            this.listaDocenteMateria = servicio.findAllPorTurno(idDocentreSeleccionado, idTurnoSeleccionado);
        }
    }

    public void prepararNuevaAsignacion(ActionEvent ev) throws InstantiationException, IllegalAccessException {

        preprarNuevoRegistro();
        idMateriaSeleccionado = -1;
        llenarMateriasItem();

    }

    public void eliminarInscripcion(ActionEvent e) {

        getSelected().setEliminado("True");

        servicio.edit(getSelected());
    }

    public void preparaModificacionInscripcion(ActionEvent e) {
        prepararModificacion();
        llenarTurnoItem();

    }

    public void guardarAsignacion(ActionEvent e) {
        getSelected().setIdDocente(docente);
        getSelected().setIdMateria(materia);
        getSelected().setIdTurno(new Turno(idTurnoSeleccionado));
        getSelected().setEliminado("False");
        persistir();
         cargarListaDocenteMateria();
    }

    public void valueChangeDocente(ValueChangeEvent change) {
        idDocentreSeleccionado = (Integer) (change.getNewValue());

        docente = docenteService.find(idDocentreSeleccionado);

        cargarListaDocenteMateria();

    }

    public void valueChangeTurno(ValueChangeEvent change) {

        idTurnoSeleccionado = (Integer) (change.getNewValue());

        cargarListaDocenteMateria();

    }

    public void valueChangeMateria(ValueChangeEvent change) {

        idMateriaSeleccionado = (Integer) (change.getNewValue());

        this.materia = materiaService.find(idMateriaSeleccionado);

    }

    public void llenarDocentesItem() {

        List<Docente> listaDocente = docenteService.findAll();

        if (listaDocente != null && !listaDocente.isEmpty()) {

            this.listaDocentesItems = new ArrayList<>();

            for (Docente obj : listaDocente) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                getListaDocentesItems().add(item);
            }
        }
    }

    public void llenarMateriasItem() {

        List<Materia> lista = materiaService.findAll();

        if (lista != null && !lista.isEmpty()) {

            listaMateriaItems = new ArrayList<>();

            for (Materia obj : lista) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                listaMateriaItems.add(item);
            }
        }
    }

    //obtener combo de Turno
    public void llenarTurnoItem() {

        List<Turno> listaTurno = this.getTurnoService().findAll();

        if (listaTurno != null && !listaTurno.isEmpty()) {

            listaTurnoItems = new ArrayList<>();

            for (Turno obj : listaTurno) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                getListaTurnoItems().add(item);
            }
        }
    }

    @Override
    protected FacadeLocal<DocenteMateria> getService() {
        return servicio;
    }

    public DocenteMateriaFacadeLocal getServicio() {
        return servicio;
    }

    public AlumnoFacadeLocal getAlummnoService() {
        return alummnoService;
    }

    public CursoFacadeLocal getCursoService() {
        return cursoService;
    }

    public GradoFacadeLocal getGradoService() {
        return gradoService;
    }

    public GrupoFacadeLocal getGrupoService() {
        return grupoService;
    }

    public TurnoFacadeLocal getTurnoService() {
        return turnoService;
    }

    public List<SelectItem> getListaTurnoItems() {
        return listaTurnoItems;
    }

    public int getIdTurnoSeleccionado() {
        return idTurnoSeleccionado;
    }

    public void setIdTurnoSeleccionado(int idTurnoSeleccionado) {
        this.idTurnoSeleccionado = idTurnoSeleccionado;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public List<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public int getIdDocentreSeleccionado() {
        return idDocentreSeleccionado;
    }

    public void setIdDocentreSeleccionado(int idDocentreSeleccionado) {
        this.idDocentreSeleccionado = idDocentreSeleccionado;
    }

    public List<DocenteMateria> getListaDocenteMateria() {
        return listaDocenteMateria;
    }

    public void setListaDocenteMateria(List<DocenteMateria> listaDocenteMateria) {
        this.listaDocenteMateria = listaDocenteMateria;
    }

    public List<SelectItem> getListaMateriaItems() {
        return listaMateriaItems;
    }

    public void setListaMateriaItems(List<SelectItem> listaMateriaItems) {
        this.listaMateriaItems = listaMateriaItems;
    }

    public List<SelectItem> getListaDocentesItems() {
        return listaDocentesItems;
    }

    public void setListaDocentesItems(List<SelectItem> listaDocentesItems) {
        this.listaDocentesItems = listaDocentesItems;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getIdMateriaSeleccionado() {
        return idMateriaSeleccionado;
    }

    public void setIdMateriaSeleccionado(int idMateriaSeleccionado) {
        this.idMateriaSeleccionado = idMateriaSeleccionado;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
