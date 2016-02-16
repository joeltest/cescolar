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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findById", query = "SELECT m FROM Materia m WHERE m.id = :id"),
    @NamedQuery(name = "Materia.findByEliminado", query = "SELECT m FROM Materia m WHERE m.eliminado = :eliminado"),
    @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre")})
public class Materia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "GRADO", referencedColumnName = "ID")
    @ManyToOne
    private Grado grado;
    @OneToMany(mappedBy = "idMateria")
    private Collection<DocenteMateria> docenteMateriaCollection;
    @OneToMany(mappedBy = "idMateria")
    private Collection<AsignacionMateriaAlumno> asignacionMateriaAlumnoCollection;
    
    @JoinColumn(name = "ID_TURNO", referencedColumnName = "ID")
    @ManyToOne
    private Turno idTurno;
  
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID")
    @ManyToOne
    private Turno idCurso;
  
    @JoinColumn(name = "ID_DOCENTE", referencedColumnName = "ID")
    @ManyToOne
    private Docente idDocente;

    public Materia() {
    }

    public Materia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    @XmlTransient
    public Collection<DocenteMateria> getDocenteMateriaCollection() {
        return docenteMateriaCollection;
    }

    public void setDocenteMateriaCollection(Collection<DocenteMateria> docenteMateriaCollection) {
        this.docenteMateriaCollection = docenteMateriaCollection;
    }

    @XmlTransient
    public Collection<AsignacionMateriaAlumno> getAsignacionMateriaAlumnoCollection() {
        return asignacionMateriaAlumnoCollection;
    }

    public void setAsignacionMateriaAlumnoCollection(Collection<AsignacionMateriaAlumno> asignacionMateriaAlumnoCollection) {
        this.asignacionMateriaAlumnoCollection = asignacionMateriaAlumnoCollection;
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
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Materia[ id=" + id + " ]";
    }

    public Turno getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Turno idTurno) {
        this.idTurno = idTurno;
    }

    public Turno getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Turno idCurso) {
        this.idCurso = idCurso;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }
    
}
