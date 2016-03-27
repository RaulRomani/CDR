/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controllers.process;

import com.clinica.entidades.Enfermedad;
import com.clinica.entidades.Personal;
import com.clinica.entidades.Exploracionfisica;
import com.clinica.entidades.Historiaclinica;
import com.clinica.entidades.Paciente;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Raul
 */
@Named(value = "historia")
@SessionScoped
public class Historia implements Serializable{

  
  @EJB
  private com.clinica.fachadas.PacienteFacadeLocal ejbFacadePaciente;

  private UploadedFile file;
  
  Historiaclinica historia ;
  Paciente paciente ;

  Enfermedad enfermedad ;
  List<Enfermedad> enfermedades ;
  Exploracionfisica exploracion ;
  List<Exploracionfisica> exploraciones;
  
  @PostConstruct
  void init(){
    enfermedad = new Enfermedad();
    exploracion = new Exploracionfisica();
    historia = new Historiaclinica();
    paciente = new Paciente();
  }
  
  public void grabarHistoriaClinica(){
    
    historia.setEnfermedadList(enfermedades);
    historia.setExploracionfisicaList(exploraciones);
    
    List<Historiaclinica> historiaList = new ArrayList<>();
    historiaList.add(historia);
    paciente.setHistoriaclinicaList(historiaList);

    ejbFacadePaciente.create(paciente);
    
  }

  
  public void addEnfermedad() {
    if (enfermedades == null)
      enfermedades = new ArrayList<>();
    enfermedades.add(enfermedad);
    enfermedad = new Enfermedad();
  }
  
  public void addExploracionFisica() {
    if (exploraciones == null)
      exploraciones = new ArrayList<>();
    exploraciones.add(exploracion);
    exploracion = new Exploracionfisica();
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

  public Paciente getPaciente() {
    return paciente;
  }

  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }

  public Historiaclinica getHistoria() {
    return historia;
  }

  public void setHistoria(Historiaclinica historia) {
    this.historia = historia;
  }
  
}
