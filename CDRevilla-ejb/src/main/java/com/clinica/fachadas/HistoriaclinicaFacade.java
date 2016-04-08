/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Historiaclinica;
import com.clinica.entidades.Paciente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Raul
 */
@Stateless
public class HistoriaclinicaFacade extends AbstractFacade<Historiaclinica> implements HistoriaclinicaFacadeLocal {
  @PersistenceContext(unitName = "com.clinica_CDRevilla-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public HistoriaclinicaFacade() {
    super(Historiaclinica.class);
  }
  
  @Override
  public Historiaclinica findByPaciente(Paciente paciente) {
    Historiaclinica historia;

    try {
      Query q = getEntityManager().createNamedQuery("Historiaclinica.findByPaciente");
      q.setParameter("idPaciente", paciente);
      historia = (Historiaclinica) q.getSingleResult();
    } catch (NoResultException e) {
      historia = null;
    }
    return historia;
  }
}
