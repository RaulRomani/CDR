/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controllers;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Raul
 */
@Named(value = "ventas")
@ViewScoped
public class Ventas implements Serializable{

  /**
   * Creates a new instance of Ventas
   */
  public Ventas() {
//    FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
//    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//    Personal p =(Personal) session.getAttribute("personal");
//    idPersonal =p.getIdPersonal();
    
  }
 
  @Inject 
  private LoginController personal;
  
  private Integer idPersonal;
  
  
  

  public Integer getIdPersonal() {
    if(personal.getPersonal() == null)
      return 0;
    else
      return personal.getPersonal().getIdPersonal();
  }

  public void setIdPersonal(Integer idPersonal) {
    this.idPersonal = idPersonal;
  }

  public LoginController getPersonal() {
    return personal;
  }

  public void setPersonal(LoginController personal) {
    this.personal = personal;
  }
  
  
  
}
