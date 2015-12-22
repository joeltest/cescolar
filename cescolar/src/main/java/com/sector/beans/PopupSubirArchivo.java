/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.beans;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jorodriguez
 */
@ManagedBean
public class PopupSubirArchivo {

    public void openPupupSubirArchivo() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("popupSubirArchivo", options, null);
    }

    public void openPupupSubirArchivoCustomized() {
        System.out.println("HOOSOS");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");

        RequestContext.getCurrentInstance().openDialog("popupSubirArchivo", options, null);
    }

}
