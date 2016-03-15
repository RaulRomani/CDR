/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Enfermedad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Raul
 */
@Stateless
public class EnfermedadFacade extends AbstractFacade<Enfermedad> implements EnfermedadFacadeLocal {
  @PersistenceContext(unitName = "com.clinica_CDRevilla-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public EnfermedadFacade() {
    super(Enfermedad.class);
  }
  
}
