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
@Table(name = "GERENCIA_FORMATO")
@SequenceGenerator(sequenceName = "GEN_GERENCIA_FORMATO_ID", 
        name = "gerenciaFormato_Seq", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GerenciaFormato.findAll", query = "SELECT f FROM GerenciaFormato f"),
    @NamedQuery(name = "GerenciaFormato.findById", query = "SELECT f FROM GerenciaFormato f WHERE f.id = :id")
    })
public class GerenciaFormato implements Serializable {
    
    private static long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(generator = "gerenciaFormato_Seq", strategy = GenerationType.SEQUENCE)
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "GENERO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario genero;
        
    @Column(name = "FECHA_GENERO")
    @Temporal(TemporalType.DATE)
    private Date fechaGenero;   
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
       
    @Size(max = 2054)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @JoinColumn(name = "GERENCIA", referencedColumnName = "ID")
    @ManyToOne
    private Gerencia gerencia;
    @JoinColumn(name = "ADJUNTO", referencedColumnName = "ID")
    @ManyToOne
    private Adjunto adjunto;

  
    public GerenciaFormato() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getGenero() {
        return genero;
    }

    public void setGenero(Usuario genero) {
        this.genero = genero;
    }

    public Date getFechaGenero() {
        return fechaGenero;
    }

    public void setFechaGenero(Date fechaGenero) {
        this.fechaGenero = fechaGenero;
    }


    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Gerencia getGerencia() {
        return gerencia;
    }

    public void setGerencia(Gerencia gerencia) {
        this.gerencia = gerencia;
    }

    public Adjunto getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Adjunto adjunto) {
        this.adjunto = adjunto;
    }



    
}
