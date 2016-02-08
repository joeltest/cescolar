package com.sesion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sector.modelo.Alumno;
import com.sector.modelo.Docente;
import com.sector.modelo.Usuario;
import com.sector.servicios.AlumnoFacadeLocal;
import com.sector.servicios.DocenteFacadeLocal;
import com.sector.servicios.UsuarioFacadeLocal;
import com.sector.utils.MensajeUtils;
import com.sector.utils.UtilsRedirect;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author jorodriguez
 */
@Named(value = "sesion")
@SessionScoped
public class Sesion implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioService;
    @EJB
    private DocenteFacadeLocal docenteService;
    @EJB
    private AlumnoFacadeLocal alumnoService;

    private Docente docente;
    private Alumno alumno;
    private Usuario usuarioSesion;
    private String usuarioTemp;
    private String claveTemp;

    private int tipoLogin = -1;
    private boolean enSesion;

    public Sesion() {

    }

    public void login(ActionEvent event) {
        this.docente = null;
        this.alumno = null;
        this.usuarioSesion = null;

        if (this.tipoLogin != -1) {
            System.out.println("Login " + usuarioTemp + " " + claveTemp);
            switch (getTipoLogin()) {
                case 1: //administrador
                    this.usuarioSesion = usuarioService.login(usuarioTemp, claveTemp);
                    if (getUsuarioSesion() == null) {
                        MensajeUtils.addErrorMessage("Acceso denegado", "el usuario o la clave son incorrectos...");
                        
                    } else {
                        System.out.println(" usuario " + usuarioSesion.getNombre());
                        MensajeUtils.addInfoMessage("Bienvenido", "...");
                        enSesion = true;
                    }
                break;
                case 2: // docente
                    this.docente = docenteService.login(usuarioTemp, claveTemp);
                    if (docente == null) {
                        MensajeUtils.addErrorMessage("Acceso denegado", " su usuario o clave de docente son incorrectos...");
                    } else {
                        System.out.println(" docenteo " + docente.getNombre());
                        MensajeUtils.addInfoMessage("Bienvenido", "...");
                        enSesion = true;
                    }
                break;
                case 3: // alumno
                    System.out.println("ALumno "+usuarioTemp+"      "+claveTemp);
                    this.alumno = alumnoService.login(usuarioTemp, claveTemp);

                    if (alumno == null) {
                        MensajeUtils.addErrorMessage("Acceso denegado", " su usuario o clave de alumno son incorrectos...");
                    } else {
                        System.out.println(" alumno " + docente.getNombre());
                        MensajeUtils.addInfoMessage("Bienvenido", "...");
                        enSesion = true;
                    }
                break;
            }
        }else{
             MensajeUtils.addInfoMessage("Error", "Seleccione un tipo de login");
        }

    }

    public void redireccionarPrincipal(ActionEvent event) {
        UtilsRedirect.redireccionarContexto("/Prepa");
    }

    /**
     * @return the usuarioSesion
     */
    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    /**
     * @param usuarioSesion the usuarioSesion to set
     */
    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public void cerrarSesion(ActionEvent event) {
        this.setUsuarioSesion(null);
        
        this.docente=null;
        this.alumno=null;
        this.claveTemp = "";
        this.usuarioTemp = "";
        this.enSesion = false;
        redireccionarPrincipal(event);
    }

    /**
     * @return the usuarioTemp
     */
    public String getUsuarioTemp() {
        return usuarioTemp;
    }

    /**
     * @param usuarioTemp the usuarioTemp to set
     */
    public void setUsuarioTemp(String usuarioTemp) {
        this.usuarioTemp = usuarioTemp;
    }

    /**
     * @return the claveTemp
     */
    public String getClaveTemp() {
        return claveTemp;
    }

    /**
     * @param claveTemp the claveTemp to set
     */
    public void setClaveTemp(String claveTemp) {
        this.claveTemp = claveTemp;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public int getTipoLogin() {
        return tipoLogin;
    }

    public void setTipoLogin(int tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

    public boolean isEnSesion() {
        return enSesion;
    }

    public void setEnSesion(boolean enSesion) {
        this.enSesion = enSesion;
    }
}
