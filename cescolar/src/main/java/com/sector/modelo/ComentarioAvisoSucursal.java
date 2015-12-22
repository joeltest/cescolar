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
@SequenceGenerator(sequenceName = "GEN_RESPUESTA_AVISO_SUCURSAL_ID",
        name = "respuestaAviso_Seq", allocationSize = 1)
@Table(name = "COMENTARIO_AVISO_SUCURSAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentarioAvisoSucursal.findAllByAviso", 
            query = "SELECT g FROM ComentarioAvisoSucursal g WHERE g.eliminado='False' AND g.avisoSucursal.id = :idAviso")
})
public class ComentarioAvisoSucursal implements Serializable {

    private static long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "respuestaAviso_Seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    
    @Size(max = 250)
    @Column(name = "TITULO")
    private String titulo;
    
    @Size(max = 3000)
    @Column(name = "COMENTARIO")
    private String comentario;
    
    @Size(max = 5)
    @Column(name = "ELIMINADO")
    private String eliminado;
    
    @JoinColumn(name = "AVISO_SUCURSAL", referencedColumnName = "ID")
    @ManyToOne
    private AvisoSucursal avisoSucursal;
    
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha; 
    
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora; 
    
    public ComentarioAvisoSucursal() {
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public AvisoSucursal getAvisoSucursal() {
        return avisoSucursal;
    }

    public void setAvisoSucursal(AvisoSucursal avisoSucursal) {
        this.avisoSucursal = avisoSucursal;
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
