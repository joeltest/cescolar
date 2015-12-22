/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.validador;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ihsa
 * TRABAJANDO CON ESTA CLASE
 */
@FacesValidator("combo.requerido")
public class ComboRequerido implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
          //--for input
        /*if (value.toString().equals("")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",
                    value + " es requerido"));
        }*/

        //-for combo : usar una literal
        int v = Integer.valueOf(value.toString());
        if (value == null || v == -1) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo requerido",
                    ""));
        }
    }

   
}
