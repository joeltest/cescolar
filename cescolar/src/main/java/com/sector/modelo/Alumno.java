/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jorodriguez
 */
@Entity
@Table(name = "ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findById", query = "SELECT a FROM Alumno a WHERE a.id = :id"),
    @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alumno.findByApellidoPaterno", query = "SELECT a FROM Alumno a WHERE a.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "Alumno.findByApellidoMaterno", query = "SELECT a FROM Alumno a WHERE a.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "Alumno.findByAnoNacimiento", query = "SELECT a FROM Alumno a WHERE a.anoNacimiento = :anoNacimiento"),
    @NamedQuery(name = "Alumno.findByMesNacimiento", query = "SELECT a FROM Alumno a WHERE a.mesNacimiento = :mesNacimiento"),
    @NamedQuery(name = "Alumno.findByDiaNacimiento", query = "SELECT a FROM Alumno a WHERE a.diaNacimiento = :diaNacimiento"),
    @NamedQuery(name = "Alumno.findByEstado", query = "SELECT a FROM Alumno a WHERE a.estado = :estado"),
    @NamedQuery(name = "Alumno.findByCiudad", query = "SELECT a FROM Alumno a WHERE a.ciudad = :ciudad"),
    @NamedQuery(name = "Alumno.findByColonia", query = "SELECT a FROM Alumno a WHERE a.colonia = :colonia"),
    @NamedQuery(name = "Alumno.findByCalle", query = "SELECT a FROM Alumno a WHERE a.calle = :calle"),
    @NamedQuery(name = "Alumno.findBySemestreActual", query = "SELECT a FROM Alumno a WHERE a.semestreActual = :semestreActual"),
    @NamedQuery(name = "Alumno.findByGenero", query = "SELECT a FROM Alumno a WHERE a.genero = :genero"),
    @NamedQuery(name = "Alumno.findByEliminado", query = "SELECT a FROM Alumno a WHERE a.eliminado = :eliminado"),
    @NamedQuery(name = "Alumno.findByNumeroControl", query = "SELECT a FROM Alumno a WHERE a.numeroControl = :numeroControl"),
    @NamedQuery(name = "Alumno.findByClave", query = "SELECT a FROM Alumno a WHERE a.clave = :clave")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(generator = "gen_seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @Size(max = 100)
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Column(name = "ANO_NACIMIENTO")
    private String anoNacimiento;
    @Column(name = "MES_NACIMIENTO")
    private String mesNacimiento;
    @Column(name = "DIA_NACIMIENTO")
    private String diaNacimiento;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "COLONIA")
    private String colonia;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "SEMESTRE_ACTUAL")
    private Integer semestreActual;
    @Column(name = "GENERO")
    private Integer genero;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Size(max = 20)
    @Column(name = "NUMERO_CONTROL")
    private String numeroControl;
    @Size(max = 50)
    @Column(name = "CLAVE")
    private String clave;
    @OneToMany(mappedBy = "idAlumno")
    private Collection<InscripcionAlumno> inscripcionAlumnoCollection;

    public Alumno() {
    }

    public Alumno(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }



    public Integer getSemestreActual() {
        return semestreActual;
    }

    public void setSemestreActual(Integer semestreActual) {
        this.semestreActual = semestreActual;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public Collection<InscripcionAlumno> getInscripcionAlumnoCollection() {
        return inscripcionAlumnoCollection;
    }

    public void setInscripcionAlumnoCollection(Collection<InscripcionAlumno> inscripcionAlumnoCollection) {
        this.inscripcionAlumnoCollection = inscripcionAlumnoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Alumno[ id=" + id + " ]";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(String anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public String getMesNacimiento() {
        return mesNacimiento;
    }

    public void setMesNacimiento(String mesNacimiento) {
        this.mesNacimiento = mesNacimiento;
    }

    public String getDiaNacimiento() {
        return diaNacimiento;
    }

    public void setDiaNacimiento(String diaNacimiento) {
        this.diaNacimiento = diaNacimiento;
    }
    
}
