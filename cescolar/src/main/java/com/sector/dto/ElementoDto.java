/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jorodriguez
 */
public class ElementoDto implements Serializable, Comparable<ElementoDto> {

    private Integer id;
    private String nombre;
    private String tipoArchivo;
    private String peso;
    private String esCarpeta;
    private String extension;
    private String ruta;
    private String eliminado;
    private Date fechaGenero;
    private Date horaGenero;
    //private Usuario usuario;
    //private ElementoId elementoPadre;
    private Integer idElementoPadre;

    @Override
    public int compareTo(ElementoDto o) {
        return this.getNombre().compareTo(o.getNombre());
    }
    //Eclipse Generated hashCode and equals

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.peso);
        hash = 59 * hash + Objects.hashCode(this.esCarpeta);
        hash = 59 * hash + Objects.hashCode(this.extension);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ElementoDto other = (ElementoDto) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.esCarpeta, other.esCarpeta)) {
            return false;
        }
        if (!Objects.equals(this.extension, other.extension)) {
            return false;
        }
        return true;
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEsCarpeta() {
        return esCarpeta;
    }

    public void setEsCarpeta(String esCarpeta) {
        this.esCarpeta = esCarpeta;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
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

    public Integer getIdElementoPadre() {
        return idElementoPadre;
    }

    public void setIdElementoPadre(Integer idElementoPadre) {
        this.idElementoPadre = idElementoPadre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
