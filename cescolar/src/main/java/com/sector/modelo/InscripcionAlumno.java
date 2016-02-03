/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "INSCRIPCION_ALUMNO")

        @SequenceGenerator(sequenceName = "GENERAL_ID",
        name = "gen_seq", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InscripcionAlumno.findAll", query = "SELECT i FROM InscripcionAlumno i"),
    @NamedQuery(name = "InscripcionAlumno.findById", query = "SELECT i FROM InscripcionAlumno i WHERE i.id = :id"),
    @NamedQuery(name = "InscripcionAlumno.findByPromedio", query = "SELECT i FROM InscripcionAlumno i WHERE i.promedio = :promedio"),
    @NamedQuery(name = "InscripcionAlumno.findByObservaciones", query = "SELECT i FROM InscripcionAlumno i WHERE i.observaciones = :observaciones"),
    @NamedQuery(name = "InscripcionAlumno.findByEliminado", query = "SELECT i FROM InscripcionAlumno i WHERE i.eliminado = :eliminado")})
public class InscripcionAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @GeneratedValue(generator = "gen_seq", strategy = GenerationType.SEQUENCE)
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PROMEDIO")
    private BigDecimal promedio;
    @Size(max = 1200)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(mappedBy = "idIncripcionAlumno")
    private Collection<AsignacionMateriaAlumno> asignacionMateriaAlumnoCollection;
    @JoinColumn(name = "ID_TURNO", referencedColumnName = "ID")
    @ManyToOne
    private Turno idTurno;
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID")
    @ManyToOne
    private Grupo idGrupo;
    @JoinColumn(name = "ID_GRADO", referencedColumnName = "ID")
    @ManyToOne
    private Grado idGrado;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID")
    @ManyToOne
    private Curso idCurso;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID")
    @ManyToOne
    private Alumno idAlumno;

    public InscripcionAlumno() {
    }

    public InscripcionAlumno(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
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

    @XmlTransient
    public Collection<AsignacionMateriaAlumno> getAsignacionMateriaAlumnoCollection() {
        return asignacionMateriaAlumnoCollection;
    }

    public void setAsignacionMateriaAlumnoCollection(Collection<AsignacionMateriaAlumno> asignacionMateriaAlumnoCollection) {
        this.asignacionMateriaAlumnoCollection = asignacionMateriaAlumnoCollection;
    }

    public Turno getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Turno idTurno) {
        this.idTurno = idTurno;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
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
        if (!(object instanceof InscripcionAlumno)) {
            return false;
        }
        InscripcionAlumno other = (InscripcionAlumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.InscripcionAlumno[ id=" + id + " ]";
    }
    
}
