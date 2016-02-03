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
@Table(name = "DOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findById", query = "SELECT d FROM Docente d WHERE d.id = :id"),
    @NamedQuery(name = "Docente.findByNombre", query = "SELECT d FROM Docente d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Docente.findByApellidos", query = "SELECT d FROM Docente d WHERE d.apellidos = :apellidos"),
    @NamedQuery(name = "Docente.findByDomicilio", query = "SELECT d FROM Docente d WHERE d.domicilio = :domicilio"),
    @NamedQuery(name = "Docente.findByEstudios", query = "SELECT d FROM Docente d WHERE d.estudios = :estudios"),
    @NamedQuery(name = "Docente.findByEliminado", query = "SELECT d FROM Docente d WHERE d.eliminado = :eliminado"),
    @NamedQuery(name = "Docente.findByUsuario", query = "SELECT d FROM Docente d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "Docente.findByClave", query = "SELECT d FROM Docente d WHERE d.clave = :clave")})
public class Docente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 500)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Size(max = 300)
    @Column(name = "ESTUDIOS")
    private String estudios;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Size(max = 40)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 30)
    @Column(name = "CLAVE")
    private String clave;
    @OneToMany(mappedBy = "idDocente")
    private Collection<DocenteMateria> docenteMateriaCollection;

    public Docente() {
    }

    public Docente(Integer id) {
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public Collection<DocenteMateria> getDocenteMateriaCollection() {
        return docenteMateriaCollection;
    }

    public void setDocenteMateriaCollection(Collection<DocenteMateria> docenteMateriaCollection) {
        this.docenteMateriaCollection = docenteMateriaCollection;
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
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Docente[ id=" + id + " ]";
    }
    
}
