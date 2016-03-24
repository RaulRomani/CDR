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
import com.clinica.entidades.Servicio;
import com.clinica.entidades.Servicioventa;
import com.clinica.entidades.ServicioventaPK;
import com.clinica.entidades.Venta;
import com.clinica.entidades.util.Carrito;
import com.clinica.entidades.util.CarritoItem;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.log4j.Logger;

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
  
  @EJB
  private com.clinica.fachadas.PacienteFacadeLocal ejbFacadePaciente;
  @EJB
  private com.clinica.fachadas.ServicioFacadeLocal ejbFacadeServicio;
  @EJB
  private com.clinica.fachadas.VentaFacadeLocal ejbFacadeVenta;
  
  
  
 
  @Inject 
  private LoginController personal;
  
  private List<Paciente> pacienteList;
  private Paciente pacienteSelected=null;
  
  private List<Servicio> servicioList;
  private Servicio servicioSelected=null;
  
  private Servicioventa servicioVenta;
  private Cuota cuota;

  private Carrito carrito ;
  
  final static Logger logger = Log4jConfig.getLogger(Ventas.class.getName());
  
  
  @PostConstruct
  void init() {
    carrito = new Carrito();
    servicioVenta = new Servicioventa();
    cuota = new Cuota();
  }
  
  
  
  public void agregarServicio(){
    BigDecimal precio = servicioSelected.getPrecio();
    Integer cantidad =  1;
    BigDecimal importe = precio.multiply(new BigDecimal(cantidad));
    
    CarritoItem item = new CarritoItem();
    item.setIdServicio(servicioSelected.getIdServicio());
    item.setNombreServicio(servicioSelected.getNombre());
    item.setPrecioServicio(precio);
    item.setCantidad(cantidad);
    item.setImporte(importe);
    if(servicioVenta.getDiente() != null)
      item.setDiente(servicioVenta.getDiente());
    
    
    carrito.add(item);
    servicioVenta.setDiente("");
    logger.info("Servicio agregado cantidad: "+ carrito.getItems().size());
  }
  
  public void grabarVentaContado(){
    
    ejbFacadeVenta.grabarVentaContado(carrito,pacienteSelected,personal.getPersonal());
    
    JsfUtil.addSuccessMessage("La venta al contado se realizo correctamente.");
    logger.info("SE AGREGO UNA VENTA Y SU DETALLE");
    
  }
  
  public void grabarVentaCuotas(){
    
    ejbFacadeVenta.grabarVentaCuotas(carrito,pacienteSelected,personal.getPersonal(),cuota);
    
    JsfUtil.addSuccessMessage("La venta en cuotas se realizo correctamente.");
    logger.info("SE AGREGO UNA VENTA Y SU DETALLE");
    
  }
  
  public void calcularImporte(){
    
    logger.info("Inicial: " + cuota.getInicial());
    logger.info("Total de cuotas: " + cuota.getTotalcuotas());
    BigDecimal total = carrito.getTotal().subtract( cuota.getInicial());
    if(cuota.getTotalcuotas() == 0){
       cuota.setTotalcuotas(1);
    }
    BigDecimal importe = total.divide( new BigDecimal(cuota.getTotalcuotas()),2, RoundingMode.HALF_UP);
    cuota.setImporte(importe);
    logger.info("IMPORTE CALCULADO");
  }
  
  
  public List<Paciente> getPacienteList() {
    if (pacienteList == null) {
      pacienteList = ejbFacadePaciente.findAll();
    }
    return pacienteList;
  }

  public Paciente getPacienteSelected() {
    return pacienteSelected;
  }

  public void setPacienteSelected(Paciente pacienteSelected) {
    this.pacienteSelected = pacienteSelected;
  }
  
  public Carrito getCarrito() {
    return carrito;
  }

  public List<Servicio> getServicioList() {
    if (servicioList == null) {
      servicioList = ejbFacadeServicio.findAll();
    }
    return servicioList;
  }

  public Servicio getServicioSelected() {
    return servicioSelected;
  }

  public void setServicioSelected(Servicio servicioSelected) {
    this.servicioSelected = servicioSelected;
  }
  
  public LoginController getPersonal() {
    return personal;
  }

  public void setPersonal(LoginController personal) {
    this.personal = personal;
  }

  public Servicioventa getServicioVenta() {
    return servicioVenta;
  }

  public void setServicioVenta(Servicioventa servicioVenta) {
    this.servicioVenta = servicioVenta;
  }

  public Cuota getCuota() {
    return cuota;
  }

  public void setCuota(Cuota cuota) {
    this.cuota = cuota;
  }

  
}
