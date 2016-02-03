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
@Table(name = "PLAN_GRADO_MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanGradoMateria.findAll", query = "SELECT p FROM PlanGradoMateria p"),
    @NamedQuery(name = "PlanGradoMateria.findById", query = "SELECT p FROM PlanGradoMateria p WHERE p.id = :id"),
    @NamedQuery(name = "PlanGradoMateria.findByObservaciones", query = "SELECT p FROM PlanGradoMateria p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "PlanGradoMateria.findByEliminado", query = "SELECT p FROM PlanGradoMateria p WHERE p.eliminado = :eliminado")})
public class PlanGradoMateria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;

    public PlanGradoMateria() {
    }

    public PlanGradoMateria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanGradoMateria)) {
            return false;
        }
        PlanGradoMateria other = (PlanGradoMateria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.PlanGradoMateria[ id=" + id + " ]";
    }
    
}
