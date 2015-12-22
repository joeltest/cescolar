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
@SequenceGenerator(sequenceName = "GEN_GERENCIA_ID", 
        name = "gerencia_Seq", allocationSize = 1)
@Table(name = "GERENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gerencia.findAll", query = "SELECT g FROM Gerencia g"),
    @NamedQuery(name = "Gerencia.findById", query = "SELECT g FROM Gerencia g WHERE g.id = :id"),
    @NamedQuery(name = "Gerencia.findByNombre", query = "SELECT g FROM Gerencia g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "Gerencia.findByEliminado", query = "SELECT g FROM Gerencia g WHERE g.eliminado = :eliminado")})
public class Gerencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "gerencia_Seq", strategy = GenerationType.SEQUENCE)
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
    @JoinColumn(name = "USUARIO_GERENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usuarioGerente;
    @JoinColumn(name = "SUCURSAL", referencedColumnName = "ID")
    @ManyToOne
    private Sucursal sucursal;
    @OneToMany(mappedBy = "gerenciaAprueba")
    private Collection<Formato> formatoCollection;
    @OneToMany(mappedBy = "gerencia")
    private Collection<Adjunto> adjuntoCollection;

    public Gerencia() {
    }

    public Gerencia(Integer id) {
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

    public Usuario getUsuarioGerente() {
        return usuarioGerente;
    }

    public void setUsuarioGerente(Usuario usuarioGerente) {
        this.usuarioGerente = usuarioGerente;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @XmlTransient
    public Collection<Formato> getFormatoCollection() {
        return formatoCollection;
    }

    public void setFormatoCollection(Collection<Formato> formatoCollection) {
        this.formatoCollection = formatoCollection;
    }

    @XmlTransient
    public Collection<Adjunto> getAdjuntoCollection() {
        return adjuntoCollection;
    }

    public void setAdjuntoCollection(Collection<Adjunto> adjuntoCollection) {
        this.adjuntoCollection = adjuntoCollection;
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
        if (!(object instanceof Gerencia)) {
            return false;
        }
        Gerencia other = (Gerencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Gerencia[ id=" + id + " ]";
    }
    
}
