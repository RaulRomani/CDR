/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controllers;

import com.clinica.entidades.Paciente;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Raul
 */
@Named(value = "cobros")
@ViewScoped
public class Cobros implements Serializable{

  /**
   * Creates a new instance of Cobros
   */
  private static final long serialVersionUID = -2564031884483676327L;  
  
  public Cobros() {
  }
  
  private List<Paciente> pacientesFiltrados ;
  private Paciente selected = new Paciente();
  
  @EJB
  private com.clinica.fachadas.PacienteFacadeLocal ejbFacade;
  private List<Paciente> items = null;

  public List<Paciente> getPacientesFiltrados() {
    return pacientesFiltrados;
  }

  public void setPacientesFiltrados(List<Paciente> pacientesFiltrados) {
    this.pacientesFiltrados = pacientesFiltrados;
  }
  
  public List<Paciente> getItems() {
    if (items == null) {
      items = ejbFacade.findAll();
    }
    return items;
  }

  public Paciente getSelected() {
    return selected;
  }

  public void setSelected(Paciente selected) {
    this.selected = selected;
  }
  
  


  
  
}
