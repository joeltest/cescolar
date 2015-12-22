/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.servicios;

import com.sector.constantes.TipoUsuarioEnum;
import com.sector.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jorodriguez
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "sArcvhivoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario login(String correo, String clave, Integer idSucursal) {

        Usuario usuario = em.createNamedQuery("Usuario.login",Usuario.class)
                .setParameter("correo", correo)
                .setParameter("clave", clave)
                .getSingleResult();
        
        return usuario;
    }

    @Override
    public List<Usuario> listaGerentes(int idSucursal) {
        
        return em.createQuery("SELECT u FROM Usuario u WHERE u.eliminado = 'False' AND u.tipoUsuario.id = "+TipoUsuarioEnum.GERENTE.getId())
                .getResultList();
        
                
    }

}
