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
@SequenceGenerator(sequenceName = "GEN_AVISO_SUCURSAL_ID",
        name = "aviso_Seq", allocationSize = 1)
@Table(name = "AVISO_SUCURSAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvisoSucursal.findAllBySucursal", 
            query = "SELECT g FROM AvisoSucursal g WHERE g.eliminado='False' AND g.sucursal.id = :idSucursal AND g.fecha BETWEEN :fecha_inicio AND :fecha_fin")
})
public class AvisoSucursal implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "aviso_Seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    
    @Size(max = 250)
    @Column(name = "TITULO")
    private String titulo;
    
    @Size(max = 3000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @JoinColumn(name = "SUCURSAL", referencedColumnName = "ID")
    @ManyToOne
    private Sucursal sucursal;
    
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha; 
    
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora; 
    
    public AvisoSucursal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

}
