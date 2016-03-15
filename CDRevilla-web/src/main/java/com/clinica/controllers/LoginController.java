/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controllers;

import com.clinica.entidades.Personal;
import com.clinica.fachadas.PersonalFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Raul
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

  /**
   * Creates a new instance of LoginController
   */
  

  private Personal personal;
  
  //@Inject
  //private transient Logger logger;
  
  @EJB
  private com.clinica.fachadas.PersonalFacadeLocal ejbFacade;
  
  private String joder;

  public String validar() {
    
    Personal p =null;
    p = ejbFacade.validar(personal.getUsuario(), personal.getClave());
    if (p != null) {
      setPersonal(p);
      setJoder("joder..");
      
      RequestContext.getCurrentInstance().update("growl");
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",personal.getNombres()));
      
      return "main?faces-redirect=true"; // Pagina a Redireccionar
    } else {
      RequestContext.getCurrentInstance().update("growl");
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Usuario o contraseña es invalido"));
      return "";
      
      //return "main?faces-redirect=true"; // Pagina a Redireccionar
    }

  }
  
  /**
     * Listen for logout button clicks on the #{loginController.logout} action
     * and navigates to login screen.
     */
    public void logout() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //logger.log(Level.INFO, "User ({0}) loging out #" , request.getUserPrincipal().getName());
        if (session != null) {
            session.invalidate();
        }
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login?faces-redirect=true");
    }
    public String logoutButton() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //logger.log(Level.INFO, "User ({0}) loging out #" , request.getUserPrincipal().getName());
        if (session != null) {
            session.invalidate();
        }
        return "/login?faces-redirect=true";
    }
  
  public LoginController() {
    personal = new Personal();
  }

  public Personal getPersonal() {
    return personal;
  }

  public void setPersonal(Personal personal) {
    this.personal = personal;
  }
  
  public PersonalFacadeLocal getEjbFacade() {
    return ejbFacade;
  }

  public void setEjbFacade(PersonalFacadeLocal ejbFacade) {
    this.ejbFacade = ejbFacade;
  }

  public String getJoder() {
    return joder;
  }

  public void setJoder(String joder) {
    this.joder = joder;
  }

}
