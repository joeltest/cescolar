/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jorodriguez
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({  

@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
      @NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u "
                    + " WHERE u.correo = :correo AND u.clave = :clave "
                    + "  AND u.eliminado = 'False'"),
      
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByEliminado", query = "SELECT u FROM Usuario u WHERE u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByFechaGenero", query = "SELECT u FROM Usuario u WHERE u.fechaGenero = :fechaGenero")})
public class Usuario implements Serializable {
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
    @Size(max = 300)
    @Column(name = "APELLIDOS")    
    private String apellidos;
    @Size(max = 50)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 50)
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "FECHA_GENERO")
    @Temporal(TemporalType.DATE)
    private Date fechaGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioGerente")
    private Collection<Gerencia> gerenciaCollection;
    @JoinColumn(name = "TIPO_USUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;
    @JoinColumn(name = "SUCURSAL", referencedColumnName = "ID")
    @ManyToOne
    private Sucursal sucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<UsuarioRol> usuarioRolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAprueba")
    private Collection<Formato> formatoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioGenero")
    private Collection<Formato> formatoCollection1;
    @OneToMany(mappedBy = "genero")
    private Collection<Adjunto> adjuntoCollection;

    public Usuario() {
    }

    public Usuario(Integer id) {
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


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaGenero() {
        return fechaGenero;
    }

    public void setFechaGenero(Date fechaGenero) {
        this.fechaGenero = fechaGenero;
    }

    @XmlTransient
    public Collection<Gerencia> getGerenciaCollection() {
        return gerenciaCollection;
    }

    public void setGerenciaCollection(Collection<Gerencia> gerenciaCollection) {
        this.gerenciaCollection = gerenciaCollection;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @XmlTransient
    public Collection<UsuarioRol> getUsuarioRolCollection() {
        return usuarioRolCollection;
    }

    public void setUsuarioRolCollection(Collection<UsuarioRol> usuarioRolCollection) {
        this.usuarioRolCollection = usuarioRolCollection;
    }

    @XmlTransient
    public Collection<Formato> getFormatoCollection() {
        return formatoCollection;
    }

    public void setFormatoCollection(Collection<Formato> formatoCollection) {
        this.formatoCollection = formatoCollection;
    }

    @XmlTransient
    public Collection<Formato> getFormatoCollection1() {
        return formatoCollection1;
    }

    public void setFormatoCollection1(Collection<Formato> formatoCollection1) {
        this.formatoCollection1 = formatoCollection1;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Usuario[ id=" + id + " ]";
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
}
