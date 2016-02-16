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
import java.util.Date;
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
@ViewScoped
public class AlumnoBean extends BaseCrud<Alumno> {

    @EJB
    private AlumnoFacadeLocal servicio;

    private List<Alumno> listaAlumno;
    private List<Materia> listaMateria;
    private List<AsignacionMateriaAlumno> listaAsignacionMateria;

    private String numeroControl;

    public AlumnoBean() {
        super(Alumno.class);
    }

    @PostConstruct
    public void init() {
        try {
            preprarNuevoRegistro();
            llenarLista();
        } catch (InstantiationException ex) {
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarLista() {
        listaAlumno = servicio.listaAlumnos();
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

    }

    public void prepararNueva(ActionEvent ev) throws InstantiationException, IllegalAccessException {

        preprarNuevoRegistro();

    }

    public void eliminar(ActionEvent e) {

        getSelected().setEliminado("True");

        servicio.edit(getSelected());
        servicio.remove(getSelected());
        llenarLista();
    }

    public void preparaModificacion(ActionEvent e) {
        prepararModificacion(e);

    }

    public void guardarAlumno(ActionEvent e) {
        getSelected().setEliminado("False");
//        getSelected().setGenero(new Date());
        
        guardar(e);
    }

    public void modificar(ActionEvent e) {

        persistir();
        llenarLista();
    }

    //obtener combo de Turno
//    public void llenarTurnoItem() {
//
//        List<Turno> listaTurno = this.getTurnoService().findAll();
//
//        if (listaTurno != null && !listaTurno.isEmpty()) {
//
//            listaTurnoItems = new ArrayList<>();
//
//            for (Turno obj : listaTurno) {
//
//                SelectItem item = new SelectItem();
//
//                item.setValue(obj.getId());
//
//                item.setLabel(obj.getNombre());
//
//                getListaTurnoItems().add(item);
//            }
//        }
//    }
    @Override
    protected FacadeLocal<Alumno> getService() {
        return servicio;
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

    public List<AsignacionMateriaAlumno> getListaAsignacionMateria() {
        return listaAsignacionMateria;
    }

    public void setListaAsignacionMateria(List<AsignacionMateriaAlumno> listaAsignacionMateria) {
        this.listaAsignacionMateria = listaAsignacionMateria;
    }

    public List<Alumno> getListaAlumno() {
        return listaAlumno;
    }

}
