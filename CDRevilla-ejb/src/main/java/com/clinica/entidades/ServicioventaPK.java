/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Raul
 */
@Embeddable
public class ServicioventaPK implements Serializable {
  @Basic(optional = false)
  @NotNull
  @Column(name = "IdVenta")
  private int idVenta;
  @Basic(optional = false)
  @NotNull
  @Column(name = "idServicio")
  private int idServicio;

  public ServicioventaPK() {
  }

  public ServicioventaPK(int idVenta, int idServicio) {
    this.idVenta = idVenta;
    this.idServicio = idServicio;
  }

  public int getIdVenta() {
    return idVenta;
  }

  public void setIdVenta(int idVenta) {
    this.idVenta = idVenta;
  }

  public int getIdServicio() {
    return idServicio;
  }

  public void setIdServicio(int idServicio) {
    this.idServicio = idServicio;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (int) idVenta;
    hash += (int) idServicio;
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ServicioventaPK)) {
      return false;
    }
    ServicioventaPK other = (ServicioventaPK) object;
    if (this.idVenta != other.idVenta) {
      return false;
    }
    if (this.idServicio != other.idServicio) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.ServicioventaPK[ idVenta=" + idVenta + ", idServicio=" + idServicio + " ]";
  }
  
}
