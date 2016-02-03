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
@Table(name = "TURNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findById", query = "SELECT t FROM Turno t WHERE t.id = :id"),
    @NamedQuery(name = "Turno.findByNombre", query = "SELECT t FROM Turno t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Turno.findByEliminado", query = "SELECT t FROM Turno t WHERE t.eliminado = :eliminado")})
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(mappedBy = "idTurno")
    private Collection<DocenteMateria> docenteMateriaCollection;
    @OneToMany(mappedBy = "idTurno")
    private Collection<InscripcionAlumno> inscripcionAlumnoCollection;

    public Turno() {
    }

    public Turno(Integer id) {
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

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<DocenteMateria> getDocenteMateriaCollection() {
        return docenteMateriaCollection;
    }

    public void setDocenteMateriaCollection(Collection<DocenteMateria> docenteMateriaCollection) {
        this.docenteMateriaCollection = docenteMateriaCollection;
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
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Turno[ id=" + id + " ]";
    }
    
}
