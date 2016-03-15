/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "servicioventa")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Servicioventa.findAll", query = "SELECT s FROM Servicioventa s"),
  @NamedQuery(name = "Servicioventa.findByIdVenta", query = "SELECT s FROM Servicioventa s WHERE s.servicioventaPK.idVenta = :idVenta"),
  @NamedQuery(name = "Servicioventa.findByIdServicio", query = "SELECT s FROM Servicioventa s WHERE s.servicioventaPK.idServicio = :idServicio"),
  @NamedQuery(name = "Servicioventa.findByCantidad", query = "SELECT s FROM Servicioventa s WHERE s.cantidad = :cantidad"),
  @NamedQuery(name = "Servicioventa.findByImporte", query = "SELECT s FROM Servicioventa s WHERE s.importe = :importe")})
public class Servicioventa implements Serializable {
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected ServicioventaPK servicioventaPK;
  @Basic(optional = false)
  @NotNull
  @Column(name = "cantidad")
  private int cantidad;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "importe")
  private BigDecimal importe;
  @JoinColumn(name = "idServicio", referencedColumnName = "idServicio", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Servicio servicio;
  @JoinColumn(name = "IdVenta", referencedColumnName = "IdVenta", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Venta venta;

  public Servicioventa() {
  }

  public Servicioventa(ServicioventaPK servicioventaPK) {
    this.servicioventaPK = servicioventaPK;
  }

  public Servicioventa(ServicioventaPK servicioventaPK, int cantidad, BigDecimal importe) {
    this.servicioventaPK = servicioventaPK;
    this.cantidad = cantidad;
    this.importe = importe;
  }

  public Servicioventa(int idVenta, int idServicio) {
    this.servicioventaPK = new ServicioventaPK(idVenta, idServicio);
  }

  public ServicioventaPK getServicioventaPK() {
    return servicioventaPK;
  }

  public void setServicioventaPK(ServicioventaPK servicioventaPK) {
    this.servicioventaPK = servicioventaPK;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public BigDecimal getImporte() {
    return importe;
  }

  public void setImporte(BigDecimal importe) {
    this.importe = importe;
  }

  public Servicio getServicio() {
    return servicio;
  }

  public void setServicio(Servicio servicio) {
    this.servicio = servicio;
  }

  public Venta getVenta() {
    return venta;
  }

  public void setVenta(Venta venta) {
    this.venta = venta;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (servicioventaPK != null ? servicioventaPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Servicioventa)) {
      return false;
    }
    Servicioventa other = (Servicioventa) object;
    if ((this.servicioventaPK == null && other.servicioventaPK != null) || (this.servicioventaPK != null && !this.servicioventaPK.equals(other.servicioventaPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.Servicioventa[ servicioventaPK=" + servicioventaPK + " ]";
  }
  
}
