/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.modelo;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorodriguez
 */
@Entity
@Table(name = "FORMATO")
@SequenceGenerator(sequenceName = "GEN_FORMATO_ID", name = "formato_Seq", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formato.findAll", query = "SELECT f FROM Formato f"),
    @NamedQuery(name = "Formato.findById", query = "SELECT f FROM Formato f WHERE f.id = :id")
    })
public class Formato implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "formato_Seq", strategy = GenerationType.SEQUENCE)
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_GENERO")
    @Temporal(TemporalType.DATE)
    private Date fechaGenero;
    @Column(name = "HORA_GENERO")
    @Temporal(TemporalType.TIME)
    private Date horaGenero;
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Size(max = 5)
    @Column(name = "ENVIADO_APROBACION")
    private String enviadoAprobacion;
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "HORA_ENVIO")
    @Temporal(TemporalType.TIME)
    private Date horaEnvio;

    @Size(max = 5)
    @Column(name = "FECHA_CANCELACION")
    private String fechaCancelacion;
    
    @Size(max = 2500)
    @Column(name = "COMENTARIOS")
    private String comentarios;
    
    @Size(max = 5)
    @Column(name = "HORA_CANCELACION")
    private String horaCancelacion;
    @Size(max = 2048)
    @Column(name = "MOTIVO_CANCELACION")
    private String motivoCancelacion;
    @Size(max = 2048)
    @Column(name = "MOTIVO_DEVUELTO")
    private String motivoDevuelto;
    @JoinColumn(name = "USUARIO_APRUEBA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usuarioAprueba;
    @JoinColumn(name = "USUARIO_GENERO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usuarioGenero;
    @JoinColumn(name = "GERENCIA_APRUEBA", referencedColumnName = "ID")
    @ManyToOne
    private Gerencia gerenciaAprueba;
    @JoinColumn(name = "ADJUNTO", referencedColumnName = "ID")
    @ManyToOne
    private Adjunto adjunto;

    @JoinColumn(name = "ESTATUS", referencedColumnName = "ID")
    @ManyToOne
    private Estatus estatus;
    
    public Formato() {
    }

    public Formato(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getEnviadoAprobacion() {
        return enviadoAprobacion;
    }

    public void setEnviadoAprobacion(String enviadoAprobacion) {
        this.enviadoAprobacion = enviadoAprobacion;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(Date horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(String fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getHoraCancelacion() {
        return horaCancelacion;
    }

    public void setHoraCancelacion(String horaCancelacion) {
        this.horaCancelacion = horaCancelacion;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getMotivoDevuelto() {
        return motivoDevuelto;
    }

    public void setMotivoDevuelto(String motivoDevuelto) {
        this.motivoDevuelto = motivoDevuelto;
    }

    public Usuario getUsuarioAprueba() {
        return usuarioAprueba;
    }

    public void setUsuarioAprueba(Usuario usuarioAprueba) {
        this.usuarioAprueba = usuarioAprueba;
    }

    public Usuario getUsuarioGenero() {
        return usuarioGenero;
    }

    public void setUsuarioGenero(Usuario usuarioGenero) {
        this.usuarioGenero = usuarioGenero;
    }

    public Gerencia getGerenciaAprueba() {
        return gerenciaAprueba;
    }

    public void setGerenciaAprueba(Gerencia gerenciaAprueba) {
        this.gerenciaAprueba = gerenciaAprueba;
    }

    public Adjunto getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Adjunto adjunto) {
        this.adjunto = adjunto;
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
        if (!(object instanceof Formato)) {
            return false;
        }
        Formato other = (Formato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sector.modelo.Formato[ id=" + id + " ]";
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
}
