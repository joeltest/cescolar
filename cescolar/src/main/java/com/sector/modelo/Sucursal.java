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
@Table(name = "SUCURSAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s"),
    @NamedQuery(name = "Sucursal.findById", query = "SELECT s FROM Sucursal s WHERE s.id = :id"),
    @NamedQuery(name = "Sucursal.findByNombre", query = "SELECT s FROM Sucursal s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sucursal.findByEstado", query = "SELECT s FROM Sucursal s WHERE s.estado = :estado"),
    @NamedQuery(name = "Sucursal.findByCiudad", query = "SELECT s FROM Sucursal s WHERE s.ciudad = :ciudad"),
    @NamedQuery(name = "Sucursal.findByCalle", query = "SELECT s FROM Sucursal s WHERE s.calle = :calle"),
    @NamedQuery(name = "Sucursal.findByColonia", query = "SELECT s FROM Sucursal s WHERE s.colonia = :colonia"),
    @NamedQuery(name = "Sucursal.findByNumeroTelefono", query = "SELECT s FROM Sucursal s WHERE s.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Sucursal.findByEncargado", query = "SELECT s FROM Sucursal s WHERE s.encargado = :encargado"),
    @NamedQuery(name = "Sucursal.findByEliminado", query = "SELECT s FROM Sucursal s WHERE s.eliminado = :eliminado")})
public class Sucursal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 50)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 50)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Size(max = 50)
    @Column(name = "CALLE")
    private String calle;
    @Size(max = 50)
    @Column(name = "COLONIA")
    private String colonia;
    @Size(max = 15)
    @Column(name = "NUMERO_TELEFONO")
    private String numeroTelefono;
    @Size(max = 50)
    @Column(name = "ENCARGADO")
    private String encargado;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(mappedBy = "sucursal")
    private Collection<Gerencia> gerenciaCollection;
    @OneToMany(mappedBy = "sucursal")
    private Collection<Usuario> usuarioCollection;

    public Sucursal() {
    }

    public Sucursal(Integer id) {
        this.id = id;
    }

    public Sucursal(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Gerencia> getGerenciaCollection() {
        return gerenciaCollection;
    }

    public void setGerenciaCollection(Collection<Gerencia> gerenciaCollection) {
        this.gerenciaCollection = gerenciaCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
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
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Sucursal[ id=" + id + " ]";
    }
    
}
