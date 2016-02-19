/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automaticcrud;

import com.automaticcrud.generic.FacadeLocal;
import com.sector.modelo.Alumno;
import com.sector.modelo.AsignacionMateriaAlumno;
import com.sector.modelo.Calificacion;
import com.sector.modelo.Curso;
import com.sector.modelo.Docente;
import com.sector.modelo.Grado;
import com.sector.modelo.Grupo;
import com.sector.modelo.InscripcionAlumno;
import com.sector.modelo.Materia;
import com.sector.modelo.Turno;
import com.sector.servicios.AlumnoFacadeLocal;
import com.sector.servicios.AsignacionMateriaAlumnoFacadeLocal;
import com.sector.servicios.CalificacionFacadeLocal;
import com.sector.servicios.CursoFacadeLocal;
import com.sector.servicios.DocenteFacadeLocal;
import com.sector.servicios.GradoFacadeLocal;
import com.sector.servicios.GrupoFacadeLocal;
import com.sector.servicios.InscripcionAlumnoFacadeLocal;
import com.sector.servicios.MateriaFacadeLocal;
import com.sector.servicios.TurnoFacadeLocal;
import com.sector.utils.FacesUtils;
import com.sesion.Sesion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class CapturaCalificacionBean extends BaseCrud<Calificacion> {

    @ManagedProperty(value = "#{sesion}")
    private Sesion sesion;

    @EJB
    private CalificacionFacadeLocal servicio;

    @EJB
    private AlumnoFacadeLocal alummnoService;

    @EJB
    private CursoFacadeLocal cursoService;
    @EJB
    private InscripcionAlumnoFacadeLocal inscripcionService;

    @EJB
    private GradoFacadeLocal gradoService;

    @EJB
    private GrupoFacadeLocal grupoService;

    @EJB
    private TurnoFacadeLocal turnoService;

    @EJB
    private MateriaFacadeLocal materiaService;

    @EJB
    private DocenteFacadeLocal docenteService;
    @EJB
    private AsignacionMateriaAlumnoFacadeLocal asignacionMateriaAlumnoService;
    @EJB
    private CalificacionFacadeLocal calificacionService;

    private InscripcionAlumno inscripcionAlumno;
    
    
    List<Alumno> listaAlumno;
    private List<Materia> listaMateria;
    private List<AsignacionMateriaAlumno> listaAsignacioMateriaAlumno;
    private List<Calificacion> listaCalificacion;

    private String numeroControl;
    private Docente docente;
    private AsignacionMateriaAlumno asignacionSeleccionada;
    private List<SelectItem> listaMateriaItems;
    private List<SelectItem> listaGradoItems;
    private List<SelectItem> listaTurnoItems;
    private List<SelectItem> listaCursoItems;
    private List<SelectItem> listaGrupoItems;
    private List<SelectItem> listaUnidadesItems;

    private int idMateriaSeleccionado;
    private int idGrupoSeleccionado;
    private int idGradoSeleccionado;
    private int idTurnoSeleccionado;
    private int idCursoSeleccionado;

    private int unidad;
    private BigDecimal calificacionBigDecimal;

    public CapturaCalificacionBean() {
        super(Calificacion.class);
    }

    @PostConstruct
    public void init() {
        inicializar();
    }

    public void inicializar() {
        try {
            preprarNuevoRegistro();
            cargarListaMateriasPorGrado();
            idMateriaSeleccionado = -1;
            setIdGrupoSeleccionado(-1);
            setIdGradoSeleccionado(-1);
            setIdTurnoSeleccionado(-1);
            setIdCursoSeleccionado(-1);
            llenarTurnoItem();
            llenarGradoItem();

            llenarCursoItem();
            llenarGrupoItem();
            llenarMateriasItem();
        } catch (InstantiationException ex) {
            Logger.getLogger(CapturaCalificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CapturaCalificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void buscarCardex(ActionEvent e) {
        System.out.println("Buscar cardez");
        
        final AsignacionMateriaAlumno i = asignacionMateriaAlumnoService.findByInscripcionAlumno(sesion.getAlumno().getId(),idMateriaSeleccionado,idCursoSeleccionado,idGradoSeleccionado,idGrupoSeleccionado,idTurnoSeleccionado);
        
//        listaAsignacioMateriaAlumno = calificacionService.listaCalificacionPorAsignacion(i.getIdIncripcionAlumno().getId());
    }

    public void prepararNuevaCalificacion(ActionEvent ev) {
        try {
            int idAsignacion = Integer.parseInt(FacesUtils.getRequestParameter("idAsignacion"));
            this.asignacionSeleccionada = asignacionMateriaAlumnoService.find(idAsignacion);

            System.out.println("idAsignacion ========" + idAsignacion);
            llenarUnidades();
            this.setCalificacionBigDecimal(new BigDecimal(BigInteger.ZERO));
            preprarNuevoRegistro();
        } catch (InstantiationException ex) {
            Logger.getLogger(CapturaCalificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CapturaCalificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarCalificacion(ActionEvent ev) {

        getSelected().setUnidad(unidad);

        getSelected().setCalificacion(getCalificacionBigDecimal());

        getSelected().setAsignacionMateriaAlumno(this.asignacionSeleccionada);

        getSelected().setEliminado("False");

        persistir();

        cargarLista(ev);
    }

    private void llenarUnidades() {
        if (this.listaUnidadesItems == null) {
            this.listaUnidadesItems = new ArrayList<>();
            for (int i = 1; i < 5; i++) {
                listaUnidadesItems.add(new SelectItem(i, String.valueOf(i)));
            }
        }
    }

    public void eliminacion(ActionEvent event) {
        prepararEliminacion(event);
        getSelected().setEliminado("True");
        persistir();
        this.setSelected(null);
        FacesUtils.addInfoMessage("Se elimino el registro..");
    }

    public void cargarLista(ActionEvent event) {
        System.out.println("Buscar por seleccion");

        this.listaAsignacioMateriaAlumno
                = asignacionMateriaAlumnoService.findAllPorInscripcionAlumno(idMateriaSeleccionado,
                        idCursoSeleccionado,
                        idGradoSeleccionado,
                        idGrupoSeleccionado,
                        idTurnoSeleccionado);
    }

    public List<Calificacion> getCalificacionPorMateria(int idAsignacionMateriAlumno) {
        return calificacionService.listaCalificacionPorAsignacion(idAsignacionMateriAlumno);

    }

    public void cargarListaMateriasPorGrado() {

        listaMateria = materiaService.findAllByDocente(sesion.getDocente().getId());

    }

    public void eliminarInscripcion(ActionEvent e) {

        getSelected().setEliminado("True");

        servicio.edit(getSelected());
    }

    public void valueChangeGrado(ValueChangeEvent change) {

//        setIdGrado((int) (Integer) (change.getNewValue()));
        cargarListaMateriasPorGrado();

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

    public void llenarGradoItem() {

        List<Grado> listaGrado = gradoService.findAll();

        if (listaGrado != null && !listaGrado.isEmpty()) {

            listaGradoItems = new ArrayList<>();

            for (Grado obj : listaGrado) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                listaGradoItems.add(item);
            }
        }
    }

    public void llenarGrupoItem() {

        List<Grupo> listaGrupo = grupoService.findAll();

        if (listaGrupo != null && !listaGrupo.isEmpty()) {

            listaGrupoItems = new ArrayList<>();

            for (Grupo obj : listaGrupo) {

                SelectItem item = new SelectItem();

                item.setValue(obj.getId());

                item.setLabel(obj.getNombre());

                listaGrupoItems.add(item);
            }
        }
    }

    //obtener combo de Curso
    public void llenarCursoItem() {
        System.out.println("llenarCursoItem");
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

    
    public void buscarCardexAlumno(ActionEvent e){
      this.inscripcionAlumno = inscripcionService.buscarInscripcionAlumno(sesion.getAlumno().getId(),idCursoSeleccionado);
      //cargar listas de materas
      this.listaAsignacioMateriaAlumno = asignacionMateriaAlumnoService.findAllPorInscripcionAlumno(inscripcionAlumno.getId());
      
      
    }
    
    
    @Override
    protected FacadeLocal<Calificacion> getService() {
        return servicio;
    }

    public CalificacionFacadeLocal getServicio() {
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

    public List<SelectItem> getListaMateriaItems() {
        return listaMateriaItems;
    }

    public void setListaMateriaItems(List<SelectItem> listaMateriaItems) {
        this.listaMateriaItems = listaMateriaItems;
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

    public List<SelectItem> getListaGradoItems() {
        return listaGradoItems;
    }

    public void setListaGradoItems(List<SelectItem> listaGradoItems) {
        this.listaGradoItems = listaGradoItems;
    }

    public List<SelectItem> getListaCursoItems() {
        return listaCursoItems;
    }

    public void setListaCursoItems(List<SelectItem> listaCursoItems) {
        this.listaCursoItems = listaCursoItems;
    }

    public List<SelectItem> getListaGrupoItems() {
        return listaGrupoItems;
    }

    public void setListaGrupoItems(List<SelectItem> listaGrupoItems) {
        this.listaGrupoItems = listaGrupoItems;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public int getIdGrupoSeleccionado() {
        return idGrupoSeleccionado;
    }

    public void setIdGrupoSeleccionado(int idGrupoSeleccionado) {
        this.idGrupoSeleccionado = idGrupoSeleccionado;
    }

    public int getIdGradoSeleccionado() {
        return idGradoSeleccionado;
    }

    public void setIdGradoSeleccionado(int idGradoSeleccionado) {
        this.idGradoSeleccionado = idGradoSeleccionado;
    }

    public int getIdTurnoSeleccionado() {
        return idTurnoSeleccionado;
    }

    public void setIdTurnoSeleccionado(int idTurnoSeleccionado) {
        this.idTurnoSeleccionado = idTurnoSeleccionado;
    }

    public int getIdCursoSeleccionado() {
        return idCursoSeleccionado;
    }

    public void setIdCursoSeleccionado(int idCursoSeleccionado) {
        this.idCursoSeleccionado = idCursoSeleccionado;
    }

    public List<AsignacionMateriaAlumno> getListaAsignacioMateriaAlumno() {
        return listaAsignacioMateriaAlumno;
    }

    public void setListaAsignacioMateriaAlumno(List<AsignacionMateriaAlumno> listaAsignacioMateriaAlumno) {
        this.listaAsignacioMateriaAlumno = listaAsignacioMateriaAlumno;
    }

    public List<SelectItem> getListaUnidadesItems() {
        return listaUnidadesItems;
    }

    public void setListaUnidadesItems(List<SelectItem> listaUnidadesItems) {
        this.listaUnidadesItems = listaUnidadesItems;
    }

    public AsignacionMateriaAlumno getAsignacionSeleccionada() {
        return asignacionSeleccionada;
    }

    public void setAsignacionSeleccionada(AsignacionMateriaAlumno asignacionSeleccionada) {
        this.asignacionSeleccionada = asignacionSeleccionada;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public BigDecimal getCalificacionBigDecimal() {
        return calificacionBigDecimal;
    }

    public void setCalificacionBigDecimal(BigDecimal calificacionBigDecimal) {
        this.calificacionBigDecimal = calificacionBigDecimal;
    }

    public List<Calificacion> getListaCalificacion() {
        return listaCalificacion;
    }

    public void setListaCalificacion(List<Calificacion> listaCalificacion) {
        this.listaCalificacion = listaCalificacion;
    }

    public InscripcionAlumno getInscripcionAlumno() {
        return inscripcionAlumno;
    }

    public void setInscripcionAlumno(InscripcionAlumno inscripcionAlumno) {
        this.inscripcionAlumno = inscripcionAlumno;
    }

}
