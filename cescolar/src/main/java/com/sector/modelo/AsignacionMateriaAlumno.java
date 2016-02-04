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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "ASIGNACION_MATERIA_ALUMNO")
//@SequenceGenerator(sequenceName = "GENERAL_ID",
//        name = "gen_seq", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionMateriaAlumno.findAll", query = "SELECT a FROM AsignacionMateriaAlumno a"),
    @NamedQuery(name = "AsignacionMateriaAlumno.findById", query = "SELECT a FROM AsignacionMateriaAlumno a WHERE a.id = :id"),
    @NamedQuery(name = "AsignacionMateriaAlumno.findByRepite", query = "SELECT a FROM AsignacionMateriaAlumno a WHERE a.repite = :repite"),
    @NamedQuery(name = "AsignacionMateriaAlumno.findByObservaciones", query = "SELECT a FROM AsignacionMateriaAlumno a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "AsignacionMateriaAlumno.findByEliminado", query = "SELECT a FROM AsignacionMateriaAlumno a WHERE a.eliminado = :eliminado")})
public class AsignacionMateriaAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "gen_seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    
    @Size(max = 5)
    @Column(name = "REPITE")
    private String repite;
    @Size(max = 300)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "ID_INCRIPCION_ALUMNO", referencedColumnName = "ID")
    @ManyToOne
    private InscripcionAlumno idIncripcionAlumno;
    @OneToMany(mappedBy = "asignacionMateriaAlumno")
    private Collection<Calificacion> calificacionCollection;

    public AsignacionMateriaAlumno() {
    }

    public AsignacionMateriaAlumno(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepite() {
        return repite;
    }

    public void setRepite(String repite) {
        this.repite = repite;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public InscripcionAlumno getIdIncripcionAlumno() {
        return idIncripcionAlumno;
    }

    public void setIdIncripcionAlumno(InscripcionAlumno idIncripcionAlumno) {
        this.idIncripcionAlumno = idIncripcionAlumno;
    }

    @XmlTransient
    public Collection<Calificacion> getCalificacionCollection() {
        return calificacionCollection;
    }

    public void setCalificacionCollection(Collection<Calificacion> calificacionCollection) {
        this.calificacionCollection = calificacionCollection;
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
        if (!(object instanceof AsignacionMateriaAlumno)) {
            return false;
        }
        AsignacionMateriaAlumno other = (AsignacionMateriaAlumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.AsignacionMateriaAlumno[ id=" + id + " ]";
    }
    
}
