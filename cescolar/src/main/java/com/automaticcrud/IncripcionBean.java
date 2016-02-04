/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automaticcrud;

import com.automaticcrud.generic.FacadeLocal;
import com.sector.modelo.Alumno;
import com.sector.modelo.AsignacionMateriaAlumno;
import com.sector.modelo.Curso;
import com.sector.modelo.Grado;
import com.sector.modelo.Grupo;
import com.sector.modelo.InscripcionAlumno;
import com.sector.modelo.Materia;
import com.sector.modelo.Sucursal;
import com.sector.modelo.Turno;
import com.sector.servicios.AlumnoFacadeLocal;
import com.sector.servicios.AsignacionMateriaAlumnoFacadeLocal;
import com.sector.servicios.CursoFacadeLocal;
import com.sector.servicios.GradoFacadeLocal;
import com.sector.servicios.GrupoFacadeLocal;
import com.sector.servicios.InscripcionAlumnoFacadeLocal;
import com.sector.servicios.MateriaFacadeLocal;
import com.sector.servicios.SucursalFacadeLocal;
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
import javax.faces.view.ViewScoped;

/**
 *
 * @author ihsa
 */
@ManagedBean
@SessionScoped
public class IncripcionBean extends BaseCrud<InscripcionAlumno> {

    @EJB
    private InscripcionAlumnoFacadeLocal servicio;

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

    List<Alumno> listaAlumno;
    private List<Materia> listaMateria;
    private List<AsignacionMateriaAlumno> listaAsignacionMateria;

    private Alumno alumno;
//    private InscripcionAlumno inscripcionAlumno;

    private String numeroControl;

    private List<SelectItem> listaAlumnosItems;
    private List<SelectItem> listaCursoItems;
    private List<SelectItem> listaGradoItems;
    private List<SelectItem> listaGrupoItems;
    private List<SelectItem> listaTurnoItems;

    private int idAlumnoSeleccionado;
    private int idCursoSeleccionado;
    private int idGradoSeleccionado;
    private int idGrupoSeleccionado;
    private int idTurnoSeleccionado;

    public IncripcionBean() {
        super(InscripcionAlumno.class);
    }

    @PostConstruct
    public void init() {
        try {
            preprarNuevoRegistro();
            llenarCursoItem();
        } catch (InstantiationException ex) {
            Logger.getLogger(IncripcionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IncripcionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminacion(ActionEvent event) {
        prepararEliminacion(event);
        getSelected().setEliminado("True");
        persistir();
        this.setSelected(null);
        this.alumno = null;
        FacesUtils.addInfoMessage("Se elimino el registro..");
    }

    public void buscarPorSeleccion(ActionEvent event) {
        System.out.println("Buscar por seleccion");

    }

    public void buscarAlumnoPorNumeroControl(ActionEvent eventi) {
        this.setAlumno(alummnoService.findNumeroControl(getNumeroControl()));
        if (alumno != null) {
            this.setSelected(servicio.buscarInscripcionAlumno(this.alumno.getId(), idCursoSeleccionado));

            setSelected(inscripcionAlummnoService.buscarInscripcionAlumno(alumno.getId(), idCursoSeleccionado));
            //cargar las asignaturas 
            if (getSelected() != null) {
                idGradoSeleccionado = getSelected().getIdGrado().getId();
                idGrupoSeleccionado = getSelected().getIdGrupo().getId();
                idTurnoSeleccionado = getSelected().getIdTurno().getId();
                idAlumnoSeleccionado = alumno.getId();
                this.listaAsignacionMateria = asignacionMateriAlumnoMateriaService.findAllPorInscripcionAlumno(getSelected().getId());
            }
        } else {
            FacesUtils.addWarnMessage("No se encontro el numero de control escrito..");
        }
    }

    public void prepararNuevaInscripcion(ActionEvent ev) throws InstantiationException, IllegalAccessException {

        preprarNuevoRegistro();

        llenarGradoItem();
        llenarGrupoItem();
        llenarTurnoItem();

        //llenar las materias por grado 
    }
    
    
    public void eliminarInscripcion(ActionEvent e){
        
        getSelected().setEliminado("True");
        
        servicio.edit(getSelected());
    }

    public void preparaModificacionInscripcion(ActionEvent e) {
        prepararModificacion();
        llenarGradoItem();
        llenarGrupoItem();
        llenarTurnoItem();

    }

    public void modificarInscripcion(ActionEvent e) {
        getSelected().setIdCurso(new Curso(idCursoSeleccionado));
        getSelected().setIdGrado(new Grado(idGradoSeleccionado));
        getSelected().setIdGrupo(new Grupo(idGrupoSeleccionado));
        getSelected().setIdTurno(new Turno(idTurnoSeleccionado));

        persistir();

        for (AsignacionMateriaAlumno asignacion : listaAsignacionMateria) {

            System.out.println("Modificar Asignacion de materia ");
            asignacionMateriAlumnoMateriaService.edit(asignacion);
        }

    }

    public void valueChangeGrado(ValueChangeEvent chan) {
        idGradoSeleccionado = (Integer) chan.getNewValue();

        if (getOPERACION() == CrudActions.MODIFICAR) {
            
            this.listaAsignacionMateria = asignacionMateriAlumnoMateriaService.findAllPorInscripcionAlumno(getSelected().getId());
        
        } else {
            //obtener el plan de materias para el grado seleccionado
            listaMateria = materiaService.findAllByGrado(idGradoSeleccionado);
            listaAsignacionMateria = new ArrayList<>();

            for (Materia m : listaMateria) {
                AsignacionMateriaAlumno asignacion = new AsignacionMateriaAlumno();
                asignacion.setIdMateria(m);
                asignacion.setRepite("False");
                asignacion.setEliminado("False");
                listaAsignacionMateria.add(asignacion);
            }
        }
    }

    public void guardarInscripcion(ActionEvent e) {

        if (getOPERACION() == CrudActions.MODIFICAR) {
            modificarInscripcion(e);
        } else {

            getSelected().setIdAlumno(alumno);
            getSelected().setIdCurso(new Curso(idCursoSeleccionado));
            getSelected().setIdGrado(new Grado(idGradoSeleccionado));
            getSelected().setIdGrupo(new Grupo(idGrupoSeleccionado));
            getSelected().setIdTurno(new Turno(idTurnoSeleccionado));

            persistir();

            for (AsignacionMateriaAlumno asignacion : listaAsignacionMateria) {
//            AsignacionMateriaAlumno asignacion = new AsignacionMateriaAlumno();
                asignacion.setIdIncripcionAlumno(getSelected());
                asignacion.setRepite("False");
                asignacion.setEliminado("False");
                System.out.println("Asignacion de materia ");
                asignacionMateriAlumnoMateriaService.create(asignacion);
            }
        }
    }

    public void toggleRepite(AsignacionMateriaAlumno asig) {
        if (asig.getRepite().equals("True")) {
            asig.setRepite("False");
        } else {
            asig.setRepite("True");
        }

    }

    //obtener combo de alumnos
    public void llenarAlumnosItem() {

        List<Alumno> listaAlumno = this.getAlummnoService().findAll();

        if (listaAlumno != null && !listaAlumno.isEmpty()) {

            listaAlumnosItems = new ArrayList<>();

            for (Alumno obj : listaAlumno) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                getListaAlumnosItems().add(item);
            }
        }
    }

    //obtener combo de Curso
    public void llenarCursoItem() {

        List<Curso> listaCurso = this.getCursoService().findAll();

        if (listaCurso != null && !listaCurso.isEmpty()) {

            listaCursoItems = new ArrayList<>();

            for (Curso obj : listaCurso) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                getListaCursoItems().add(item);
            }
        }
    }

    //obtener combo de Grado
    public void llenarGradoItem() {

        List<Grado> listaGrado = this.getGradoService().findAll();

        if (listaGrado != null && !listaGrado.isEmpty()) {

            listaGradoItems = new ArrayList<>();

            for (Grado obj : listaGrado) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                getListaGradoItems().add(item);
            }
        }
    }

    //obtener combo de grupo
    public void llenarGrupoItem() {

        List<Grupo> listaGrupo = this.getGrupoService().findAll();

        if (listaGrupo != null && !listaGrupo.isEmpty()) {

            listaGrupoItems = new ArrayList<>();

            for (Grupo obj : listaGrupo) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                getListaGrupoItems().add(item);
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
    protected FacadeLocal<InscripcionAlumno> getService() {
        return getServicio();
    }

    public InscripcionAlumnoFacadeLocal getServicio() {
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

    public List<SelectItem> getListaAlumnosItems() {
        return listaAlumnosItems;
    }

    public List<SelectItem> getListaCursoItems() {
        return listaCursoItems;
    }

    public List<SelectItem> getListaGradoItems() {
        return listaGradoItems;
    }

    public List<SelectItem> getListaGrupoItems() {
        return listaGrupoItems;
    }

    public List<SelectItem> getListaTurnoItems() {
        return listaTurnoItems;
    }

    public int getIdAlumnoSeleccionado() {
        return idAlumnoSeleccionado;
    }

    public int getIdCursoSeleccionado() {
        return idCursoSeleccionado;
    }

    public int getIdGradoSeleccionado() {
        return idGradoSeleccionado;
    }

    public int getIdGrupoSeleccionado() {
        return idGrupoSeleccionado;
    }

    public int getIdTurnoSeleccionado() {
        return idTurnoSeleccionado;
    }

    public void setIdAlumnoSeleccionado(int idAlumnoSeleccionado) {
        this.idAlumnoSeleccionado = idAlumnoSeleccionado;
    }

    public void setIdCursoSeleccionado(int idCursoSeleccionado) {
        this.idCursoSeleccionado = idCursoSeleccionado;
    }

    public void setIdGradoSeleccionado(int idGradoSeleccionado) {
        this.idGradoSeleccionado = idGradoSeleccionado;
    }

    public void setIdGrupoSeleccionado(int idGrupoSeleccionado) {
        this.idGrupoSeleccionado = idGrupoSeleccionado;
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public List<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public List<AsignacionMateriaAlumno> getListaAsignacionMateria() {
        return listaAsignacionMateria;
    }

    public void setListaAsignacionMateria(List<AsignacionMateriaAlumno> listaAsignacionMateria) {
        this.listaAsignacionMateria = listaAsignacionMateria;
    }

//    public InscripcionAlumno getInscripcionAlumno() {
//        return inscripcionAlumno;
//    }
//
//    public void setInscripcionAlumno(InscripcionAlumno inscripcionAlumno) {
//        this.inscripcionAlumno = inscripcionAlumno;
//    }
}
