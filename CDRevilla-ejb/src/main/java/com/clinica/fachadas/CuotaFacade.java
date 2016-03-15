/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Cuota;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Raul
 */
@Stateless
public class CuotaFacade extends AbstractFacade<Cuota> implements CuotaFacadeLocal {
  @PersistenceContext(unitName = "com.clinica_CDRevilla-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CuotaFacade() {
    super(Cuota.class);
  }
  
}
