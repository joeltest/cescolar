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
@Table(name = "GRADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grado.findAll", query = "SELECT g FROM Grado g"),
    @NamedQuery(name = "Grado.findById", query = "SELECT g FROM Grado g WHERE g.id = :id"),
    @NamedQuery(name = "Grado.findByEliminado", query = "SELECT g FROM Grado g WHERE g.eliminado = :eliminado"),
    @NamedQuery(name = "Grado.findByNombre", query = "SELECT g FROM Grado g WHERE g.nombre = :nombre")})
public class Grado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Size(max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "grado")
    private Collection<Materia> materiaCollection;
    @OneToMany(mappedBy = "idGrado")
    private Collection<InscripcionAlumno> inscripcionAlumnoCollection;

    public Grado() {
    }

    public Grado(Integer id) {
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

    @XmlTransient
    public Collection<Materia> getMateriaCollection() {
        return materiaCollection;
    }

    public void setMateriaCollection(Collection<Materia> materiaCollection) {
        this.materiaCollection = materiaCollection;
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
        if (!(object instanceof Grado)) {
            return false;
        }
        Grado other = (Grado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Grado[ id=" + id + " ]";
    }
    
}
