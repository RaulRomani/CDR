/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.test;
import com.clinica.fachadas.PersonalFacadeLocal;
import javax.ejb.EJB;

/**
 *
 * @author Raul
 */
public class Personal {
  
  @EJB
  private PersonalFacadeLocal ejbFacade;
  
  
  
  public static  void main(String[] args) {
    Personal p;
    //p = ejbFacade.validar("admin", "admin");
    
    //joder();
    
  }
  
  public void joder(){
    //ejbFacade.validar("sad", "sad");
  }
}
