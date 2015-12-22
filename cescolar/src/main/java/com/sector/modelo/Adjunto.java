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
@Table(name = "ADJUNTO")
@SequenceGenerator(sequenceName = "GEN_ADJUNTO_ID", 
        name = "adjunto_Seq", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adjunto.findAll", query = "SELECT a FROM Adjunto a"),
    @NamedQuery(name = "Adjunto.findById", query = "SELECT a FROM Adjunto a WHERE a.id = :id"),
    @NamedQuery(name = "Adjunto.findByNombre", query = "SELECT a FROM Adjunto a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Adjunto.findByTipoArchivo", query = "SELECT a FROM Adjunto a WHERE a.tipoArchivo = :tipoArchivo"),
    @NamedQuery(name = "Adjunto.findByFechaGenero", query = "SELECT a FROM Adjunto a WHERE a.fechaGenero = :fechaGenero"),
    @NamedQuery(name = "Adjunto.findByHoraGenero", query = "SELECT a FROM Adjunto a WHERE a.horaGenero = :horaGenero"),
    @NamedQuery(name = "Adjunto.findByRuta", query = "SELECT a FROM Adjunto a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "Adjunto.findByEliminado", query = "SELECT a FROM Adjunto a WHERE a.eliminado = :eliminado"),
    @NamedQuery(name = "Adjunto.findByExtension", query = "SELECT a FROM Adjunto a WHERE a.extension = :extension"),
    @NamedQuery(name = "Adjunto.findBySistema", query = "SELECT a FROM Adjunto a WHERE a.sistema = :sistema"),
    @NamedQuery(name = "Adjunto.findByEsRepositorio", query = "SELECT a FROM Adjunto a WHERE a.esRepositorio = :esRepositorio")})
public class Adjunto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "adjunto_Seq", strategy = GenerationType.SEQUENCE)
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 2048)
    @Column(name = "TIPO_ARCHIVO")
    private String tipoArchivo;
    @Column(name = "FECHA_GENERO")
    @Temporal(TemporalType.DATE)
    private Date fechaGenero;
    @Column(name = "HORA_GENERO")
    @Temporal(TemporalType.TIME)
    private Date horaGenero;
    @Size(max = 600)
    @Column(name = "RUTA")
    private String ruta;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Size(max = 10)
    @Column(name = "EXTENSION")
    private String extension;
    @Size(max = 5)
    @Column(name = "SISTEMA")
    private String sistema;
    @Size(max = 5)
    @Column(name = "ES_REPOSITORIO")
    private String esRepositorio;
    @OneToMany(mappedBy = "adjunto")
    private Collection<Formato> formatoCollection;
    @JoinColumn(name = "GENERO", referencedColumnName = "ID")
    @ManyToOne
    private Usuario genero;
    @JoinColumn(name = "GERENCIA", referencedColumnName = "ID")
    @ManyToOne
    private Gerencia gerencia;

    public Adjunto() {
    }

    public Adjunto(Integer id) {
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

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Date getFechaGenero() {
        return fechaGenero;
    }

    public void setFechaGenero(Date fechaGenero) {
        this.fechaGenero = fechaGenero;
    }

    public Date getHoraGenero() {
        return horaGenero;
    }

    public void setHoraGenero(Date horaGenero) {
        this.horaGenero = horaGenero;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getEsRepositorio() {
        return esRepositorio;
    }

    public void setEsRepositorio(String esRepositorio) {
        this.esRepositorio = esRepositorio;
    }

    @XmlTransient
    public Collection<Formato> getFormatoCollection() {
        return formatoCollection;
    }

    public void setFormatoCollection(Collection<Formato> formatoCollection) {
        this.formatoCollection = formatoCollection;
    }

    public Usuario getGenero() {
        return genero;
    }

    public void setGenero(Usuario genero) {
        this.genero = genero;
    }

    public Gerencia getGerencia() {
        return gerencia;
    }

    public void setGerencia(Gerencia gerencia) {
        this.gerencia = gerencia;
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
        if (!(object instanceof Adjunto)) {
            return false;
        }
        Adjunto other = (Adjunto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Adjunto[ id=" + id + " ]";
    }
    
}
