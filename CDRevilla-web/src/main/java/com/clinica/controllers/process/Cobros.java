/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controllers.process;

import com.clinica.controllers.util.JsfUtil;
import com.clinica.controllers.util.Log4jConfig;
import com.clinica.entidades.Cuota;
import com.clinica.entidades.Paciente;
import com.clinica.entidades.Venta;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Raul
 */
@Named(value = "cobros")
@SessionScoped
public class Cobros implements Serializable{

  @EJB
  private com.clinica.fachadas.PacienteFacadeLocal ejbFacadeCliente;
  
  @EJB
  private com.clinica.fachadas.VentaFacadeLocal ejbFacadeVenta;
  
  @EJB
  private com.clinica.fachadas.CuotaFacadeLocal ejbFacadeCuota;
  
  private List<Paciente> pacienteList=null;
  private List<Venta> ventaList=null;
  
  private Paciente pacienteSelected;
  private Venta ventaSelected;
  private Cuota cuotaSelected;
  private Integer cuotas;
  
  final static org.apache.log4j.Logger logger = Log4jConfig.getLogger(Cobros.class.getName());
  
  @PostConstruct
  void init() {
    pacienteSelected = new Paciente();
    ventaSelected = new Venta();
    cuotaSelected = new Cuota();
    cuotas = 1;
    logger.info("INIT COBROS");
  }
  

  public List<Paciente> getPacienteList() {
    if (pacienteList == null) {
      pacienteList = ejbFacadeCliente.findAll();
    }
    return pacienteList;
  }
  
  public List<Venta> getVentaList() {
    if (ventaList == null) {
      ventaList = ejbFacadeVenta.findByFormaPagoPaciente(pacienteSelected,"CUOTAS");
      logger.info("getVentas, paciente="+ pacienteSelected.getNombre());
      logger.info("getVentas, idPaciente="+ pacienteSelected.getIdPaciente());
    }
    logger.info("Get Ventas CUOTAS :" + ventaList.size());
    return ventaList;
  }
  
  public void loadCuota(){
    cuotaSelected = ventaSelected.getCuotaList().get(0);
  }
  public void loadCuotas(){
    ventaList = ejbFacadeVenta.findByFormaPagoPaciente(pacienteSelected,"CUOTAS");
    
    ventaSelected = null;
    cuotaSelected = null;
  }
  
  public void Save(){
    
    cuotaSelected.setCuotaspagado(cuotaSelected.getCuotaspagado() + cuotas);
    persist(JsfUtil.PersistAction.CREATE, "Guardado correctamente");
    if (!JsfUtil.isValidationFailed()) { //Si la transaccion tuvo exito.
      
    }
  }
  
  private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
    if (cuotaSelected != null) {
      try {
        if (persistAction != JsfUtil.PersistAction.DELETE) {
          ejbFacadeCuota.edit(cuotaSelected);
        } else {
          ejbFacadeCuota.remove(cuotaSelected);
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
  
  public void loadPaciente(Paciente paciente){
    pacienteSelected = paciente;
  }

  public Paciente getPacienteSelected() {
    return pacienteSelected;
  }

  public void setPacienteSelected(Paciente pacienteSelected) {
    this.pacienteSelected = pacienteSelected;
  }

  public Venta getVentaSelected() {
    return ventaSelected;
  }

  public void setVentaSelected(Venta ventaSelected) {
    this.ventaSelected = ventaSelected;
  }

  public Cuota getCuotaSelected() {
    return cuotaSelected;
  }

  public void setCuotaSelected(Cuota cuotaSelected) {
    this.cuotaSelected = cuotaSelected;
  }

  public Integer getCuotas() {
    return cuotas;
  }

  public void setCuotas(Integer cuotas) {
    this.cuotas = cuotas;
  }
  
  

  


  
}
