/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controllers.process;

import com.clinica.controllers.util.JsfUtil;
import com.clinica.controllers.util.Log4jConfig;
import com.clinica.entidades.Personal;
import com.clinica.fachadas.PersonalFacadeLocal;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Raul
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

  @EJB
  private com.clinica.fachadas.PersonalFacadeLocal ejbFacade;

  private Personal personal;
  private UploadedFile file;
  private StreamedContent image;
  private String pathImage;

  final static Logger logger = Log4jConfig.getLogger(LoginController.class.getName());

  @PostConstruct
  private void init() {
    personal = new Personal();
    file = null;
    String projectStage = FacesContext.getCurrentInstance().getApplication().getProjectStage().toString();
    if (projectStage.equals("Production")) {
      pathImage = ResourceBundle.getBundle("/deploy").getString("productionPeresonalPhotoPath");
    } else if (projectStage.equals("Development")) {
      pathImage = ResourceBundle.getBundle("/deploy").getString("developmentPersonalPhotoPath");
    }
    logger.info("Project stage : " + projectStage);
  }

  public void checkSession() {

    FacesContext context = FacesContext.getCurrentInstance();

    Personal p = (Personal) context.getExternalContext().getSessionMap().get("user");
    if (p == null) {

      logger.info("seguridad");

      context.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login?faces-redirect=true");
      JsfUtil.addErrorMessage("Primero inicie sesión");

    } else {
      logger.info("Sesion de usuario existente!");
      //FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");

    }

  }

  public String validar() {

    Personal p = null;
    p = ejbFacade.validar(personal.getUsuario(), personal.getClave());
    if (p != null) {
      personal = p;

      //creamos una sesion jsf usuario
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", p);

//      RequestContext.getCurrentInstance().update("growl");
//      FacesContext context = FacesContext.getCurrentInstance();
//      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",personal.getNombres()));
      JsfUtil.addSuccessMessage("Bienvenido " + p.getNombres());

      return "main?faces-redirect=true"; // Pagina a Redireccionar
    } else {
      RequestContext.getCurrentInstance().update("growl");
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña es invalido"));

      JsfUtil.addErrorMessage("Error sin growl");
      return "";

      //return "main?faces-redirect=true"; // Pagina a Redireccionar
    }

  }

  /**
   * Listen for logout button clicks on the #{loginController.logout} action and
   * navigates to login screen.
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

  public void update() throws FileNotFoundException {
    if (file != null){
      uploadFoto();
      logger.info("Upload foto OK");
    }
    persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonalUpdated"));
    //load photo  - Si fuera Ajax
//      FileInputStream stream = new FileInputStream(pathImage + personal.getIdPersonal()+ ".jpg");
//      image = new DefaultStreamedContent(stream, "image/jpg");
//      logger.info("Upload photo OK");
  }

  private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
    if (personal != null) {
      try {
        if (persistAction != JsfUtil.PersistAction.DELETE) {
          ejbFacade.edit(personal);
          logger.info("EDIT loginController OK");
        } else {
          ejbFacade.remove(personal);
        }
        JsfUtil.addSuccessMessage(successMessage);
      } catch (EJBException ex) {
        String msg = "";
        Throwable cause = ex.getCause();
        if (cause != null) {
          msg = cause.getLocalizedMessage();
        }
        if (msg.length() > 0) {
          JsfUtil.addErrorMessage(msg);
        } else {
          JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
      } catch (Exception ex) {
        java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      }
    }
  }

  public StreamedContent getImage() throws FileNotFoundException {
    if (personal.getIdPersonal() != null) {
      FileInputStream stream = new FileInputStream(pathImage + personal.getIdPersonal() + ".jpg");
      image = new DefaultStreamedContent(stream, "image/jpg");
    }
    return image;
  }

  public void uploadFoto() {

    try {

//      String fileName = file.getFileName();
      String fileName = personal.getIdPersonal()+ ".jpg";
      if (personal.getIdPersonal()== null) //Si se va a crear
      {
        image = new DefaultStreamedContent(new ByteArrayInputStream(file.getContents()), "image/png");
      }
//      file.get
      InputStream in = file.getInputstream();
      String destination = pathImage;

      // write the inputStream to a FileOutputStream
      OutputStream out = new FileOutputStream(new File(destination + fileName));

      int read = 0;
      byte[] bytes = new byte[1024];

      while ((read = in.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }

      in.close();
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Personal getPersonal() {
    return personal;
  }

  public void setPersonal(Personal personal) {
    this.personal = personal;
  }

  public String nombreApellidoPersonal() {
    return personal.getNombres() + " " + personal.getApellidos();
  }

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }
  

}
