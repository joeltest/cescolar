/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.modelo.AvisoSucursal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;

/**
 *
 * @author ihsa
 */
@Stateless
public class AvisoSucursalFacade extends AbstractFacade<AvisoSucursal> implements AvisoSucursalFacadeLocal {

    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvisoSucursalFacade() {
        super(AvisoSucursal.class);
    }

    @Override
    public List<AvisoSucursal> findAllPorSucursal(int idSucursal) {

        Date hoy = new Date();

        final DateTime dateTime = new DateTime(hoy);

        Date fechaInicio = dateTime.minusDays(30).toDate();

        List<AvisoSucursal> lista
                = getEntityManager().createNamedQuery("AvisoSucursal.findAllBySucursal")
                .setParameter("idSucursal", idSucursal)
                .setParameter("fecha_inicio", fechaInicio)
                .setParameter("fecha_fin", hoy)
                .getResultList();
        return lista;
    }

}
