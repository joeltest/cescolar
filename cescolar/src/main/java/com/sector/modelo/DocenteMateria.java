/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorodriguez
 */
@Entity
@Table(name = "DOCENTE_MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocenteMateria.findAll", query = "SELECT d FROM DocenteMateria d"),
    @NamedQuery(name = "DocenteMateria.findById", query = "SELECT d FROM DocenteMateria d WHERE d.id = :id"),
    @NamedQuery(name = "DocenteMateria.findByEliminado", query = "SELECT d FROM DocenteMateria d WHERE d.eliminado = :eliminado")})
public class DocenteMateria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @JoinColumn(name = "ID_TURNO", referencedColumnName = "ID")
    @ManyToOne
    private Turno idTurno;
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "ID_DOCENTE", referencedColumnName = "ID")
    @ManyToOne
    private Docente idDocente;

    public DocenteMateria() {
    }

    public DocenteMateria(Integer id) {
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

    public Turno getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Turno idTurno) {
        this.idTurno = idTurno;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
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
        if (!(object instanceof DocenteMateria)) {
            return false;
        }
        DocenteMateria other = (DocenteMateria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.DocenteMateria[ id=" + id + " ]";
    }
    
}
