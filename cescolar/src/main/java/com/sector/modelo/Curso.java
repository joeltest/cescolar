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
@Table(name = "CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id"),
    @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Curso.findByEliminado", query = "SELECT c FROM Curso c WHERE c.eliminado = :eliminado")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(mappedBy = "idCurso")
    private Collection<InscripcionAlumno> inscripcionAlumnoCollection;
    @JoinColumn(name = "TIPO_CURSO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoCurso tipoCursoId;
    @JoinColumn(name = "ANIO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Anio anioId;

    public Curso() {
    }

    public Curso(Integer id) {
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
    public Collection<InscripcionAlumno> getInscripcionAlumnoCollection() {
        return inscripcionAlumnoCollection;
    }

    public void setInscripcionAlumnoCollection(Collection<InscripcionAlumno> inscripcionAlumnoCollection) {
        this.inscripcionAlumnoCollection = inscripcionAlumnoCollection;
    }

    public TipoCurso getTipoCursoId() {
        return tipoCursoId;
    }

    public void setTipoCursoId(TipoCurso tipoCursoId) {
        this.tipoCursoId = tipoCursoId;
    }

    public Anio getAnioId() {
        return anioId;
    }

    public void setAnioId(Anio anioId) {
        this.anioId = anioId;
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Curso[ id=" + id + " ]";
    }
    
}
