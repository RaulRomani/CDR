/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controllers.process;

import com.clinica.controllers.PacienteController;
import com.clinica.controllers.util.JsfUtil;
import com.clinica.controllers.util.Log4jConfig;
import com.clinica.entidades.Enfermedad;
import com.clinica.entidades.Personal;
import com.clinica.entidades.Exploracionfisica;
import com.clinica.entidades.Historiaclinica;
import com.clinica.entidades.Paciente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Raul
 */
@Named(value = "historia")
@SessionScoped
public class Historia implements Serializable {

  @EJB
  private com.clinica.fachadas.PacienteFacadeLocal ejbFacadePaciente;

  @EJB
  private com.clinica.fachadas.HistoriaclinicaFacadeLocal ejbFacadeHistoria;

  private UploadedFile file;

  Historiaclinica historia;
  Paciente pacienteSelected;
  List<Paciente> pacientes;

  Enfermedad enfermedad;
  List<Enfermedad> enfermedades;
  Exploracionfisica exploracion;
  List<Exploracionfisica> exploraciones;

  private StreamedContent image;
  private String pathImage;

  final static org.apache.log4j.Logger logger = Log4jConfig.getLogger(PacienteController.class.getName());

  @PostConstruct
  void init() {
    enfermedad = new Enfermedad();
    exploracion = new Exploracionfisica();
    historia = new Historiaclinica();
    pacienteSelected = new Paciente();

    String projectStage = FacesContext.getCurrentInstance().getApplication().getProjectStage().toString();
    if (projectStage.equals("Production")) {
      pathImage = ResourceBundle.getBundle("/deploy").getString("productionPicturesPath");
    } else if (projectStage.equals("Development")) {
      pathImage = ResourceBundle.getBundle("/deploy").getString("developmentPicturesPath");
    }
    logger.info("Project stage : " + projectStage);
  }

  public void grabarHistoriaClinica() {

    historia.setEnfermedadList(enfermedades);
    historia.setExploracionfisicaList(exploraciones);
    historia.setFechaAtencion(new Date());
    historia.setIdExploracionFisica(21);
    historia.setIdEnfermedad(23);
    
    historia.setIdPaciente(pacienteSelected);

    for (Enfermedad e : enfermedades) {
      e.setIdHistoriaClinica(historia);
    }

    for (Exploracionfisica ex : exploraciones) {
      ex.setIdHistoriaClinica(historia);
    }

    persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HistoriaclinicaCreated"));
    if (!JsfUtil.isValidationFailed()) { //Si la transaccion tuvo exito.
//      enfermedades = null;    // Invalidate list of items to trigger re-query.
//      exploraciones = null;
//      historia = new Historiaclinica();
//      pacienteSelected = new Paciente();
    }
  }

  private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
    if (historia != null) {
      try {
        if (persistAction != JsfUtil.PersistAction.DELETE) {
          ejbFacadeHistoria.edit(historia);
        } else {
          ejbFacadeHistoria.remove(historia);
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
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      }
    }
  }

  public void addEnfermedad() {
    if (enfermedades == null) {
      enfermedades = new ArrayList<>();
    }
    enfermedades.add(enfermedad);
    enfermedad = new Enfermedad();
  }

  public void addExploracionFisica() {
    if (exploraciones == null) {
      exploraciones = new ArrayList<>();
    }
    exploraciones.add(exploracion);
    exploracion = new Exploracionfisica();
  }

  public void cargarHistoriaPaciente() {
    
    enfermedades = null;
    exploraciones = null;

    historia = ejbFacadeHistoria.findByPaciente(pacienteSelected);

    if (historia != null) { //El paciente tiene historia clínica
      enfermedades = historia.getEnfermedadList();
      exploraciones = historia.getExploracionfisicaList();

    } else {
      historia = new Historiaclinica();
    }
    if (enfermedades == null) {
      enfermedades = new ArrayList<>();
      logger.info("Enfermedades == null");
    }
    if (exploraciones == null) {
      exploraciones = new ArrayList<>();
    }
  }

  public void upload() {

    FacesMessage msg = new FacesMessage("Exito! ", file.getFileName() + " fue subido.");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    // Do what you want with the file        
    try {
      copyFile(file.getFileName(), file.getInputstream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void copyFile(String fileName, InputStream in) {
    try {

      String destination = "D:\\tmp\\";

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

      System.out.println("New file created!");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Paciente> getPacientes() {
    if (pacientes == null) {
      pacientes = ejbFacadePaciente.findAll();
    }
    return pacientes;
  }

  public void setPacientes(List<Paciente> pacientes) {
    this.pacientes = pacientes;
  }

  public Enfermedad getEnfermedad() {
    return enfermedad;
  }

  public void setEnfermedad(Enfermedad enfermedad) {
    this.enfermedad = enfermedad;
  }

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

  public List<Enfermedad> getEnfermedades() {
    return enfermedades;
  }

  public void setEnfermedades(List<Enfermedad> enfermedades) {
    this.enfermedades = enfermedades;
  }

  public Exploracionfisica getExploracion() {
    return exploracion;
  }

  public void setExploracion(Exploracionfisica exploracion) {
    this.exploracion = exploracion;
  }

  public List<Exploracionfisica> getExploraciones() {
    return exploraciones;
  }

  public void setExploraciones(List<Exploracionfisica> exploraciones) {
    this.exploraciones = exploraciones;
  }

  public Paciente getPacienteSelected() {
    return pacienteSelected;
  }

  public void setPacienteSelected(Paciente pacienteSelected) {
    this.pacienteSelected = pacienteSelected;
  }

  public Historiaclinica getHistoria() {
    return historia;
  }

  public void setHistoria(Historiaclinica historia) {
    this.historia = historia;
  }

  public StreamedContent getImage() throws FileNotFoundException {
    if (pacienteSelected.getIdPaciente() != null) {
      FileInputStream stream = new FileInputStream(pathImage + pacienteSelected.getIdPaciente() + ".jpg");
      image = new DefaultStreamedContent(stream, "image/jpg");
    }
    return image;
  }

}
