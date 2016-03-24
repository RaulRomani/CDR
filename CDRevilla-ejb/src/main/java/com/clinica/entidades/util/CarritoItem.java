/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.entidades.util;

import java.math.BigDecimal;

/**
 *
 * @author Raul
 */
public class CarritoItem {
  
  //Datos de Servicio y de ServicioVenta
  
  private Integer idServicio;
  private String nombreServicio;
  private BigDecimal precioServicio;
  private Integer cantidad;
  private BigDecimal importe;
  private String diente;

  public Integer getIdServicio() {
    return idServicio;
  }

  public void setIdServicio(Integer idServicio) {
    this.idServicio = idServicio;
  }

  public String getDiente() {
    return diente;
  }

  public void setDiente(String diente) {
    this.diente = diente;
  }

  

  public String getNombreServicio() {
    return nombreServicio;
  }

  public void setNombreServicio(String nombreServicio) {
    this.nombreServicio = nombreServicio;
  }

  public BigDecimal getPrecioServicio() {
    return precioServicio;
  }

  public void setPrecioServicio(BigDecimal precioServicio) {
    this.precioServicio = precioServicio;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public BigDecimal getImporte() {
    return importe;
  }

  public void setImporte(BigDecimal importe) {
    this.importe = importe;
  }
  
  
  
  
  
}
