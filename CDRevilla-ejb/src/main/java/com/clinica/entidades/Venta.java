/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
  @NamedQuery(name = "Venta.findByIdVenta", query = "SELECT v FROM Venta v WHERE v.idVenta = :idVenta"),
  @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total"),
  @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
  @NamedQuery(name = "Venta.findByFormapago", query = "SELECT v FROM Venta v WHERE v.formapago = :formapago"),
  @NamedQuery(name = "Venta.findByComprobante", query = "SELECT v FROM Venta v WHERE v.comprobante = :comprobante"),
  @NamedQuery(name = "Venta.findByformaPagoPaciente", query = "SELECT v FROM Venta v WHERE v.formapago = :formapago AND v.idPaciente = :idPaciente")})
public class Venta implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "IdVenta")
  private Integer idVenta;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "total")
  private BigDecimal total;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fecha;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "formapago")
  private String formapago;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "comprobante")
  private String comprobante;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
  private List<Cuota> cuotaList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
  private List<Servicioventa> servicioventaList;
  @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
  @ManyToOne(optional = false)
  private Paciente idPaciente;
  @JoinColumn(name = "IdPersonal", referencedColumnName = "IdPersonal")
  @ManyToOne(optional = false)
  private Personal idPersonal;

  public Venta() {
  }

  public Venta(Integer idVenta) {
    this.idVenta = idVenta;
  }

  public Venta(Integer idVenta, BigDecimal total, Date fecha, String formapago, String comprobante) {
    this.idVenta = idVenta;
    this.total = total;
    this.fecha = fecha;
    this.formapago = formapago;
    this.comprobante = comprobante;
  }

  public Integer getIdVenta() {
    return idVenta;
  }

  public void setIdVenta(Integer idVenta) {
    this.idVenta = idVenta;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getFormapago() {
    return formapago;
  }

  public void setFormapago(String formapago) {
    this.formapago = formapago;
  }

  public String getComprobante() {
    return comprobante;
  }

  public void setComprobante(String comprobante) {
    this.comprobante = comprobante;
  }

  @XmlTransient
  public List<Cuota> getCuotaList() {
    return cuotaList;
  }

  public void setCuotaList(List<Cuota> cuotaList) {
    this.cuotaList = cuotaList;
  }

  @XmlTransient
  public List<Servicioventa> getServicioventaList() {
    return servicioventaList;
  }

  public void setServicioventaList(List<Servicioventa> servicioventaList) {
    this.servicioventaList = servicioventaList;
  }

  public Paciente getIdPaciente() {
    return idPaciente;
  }

  public void setIdPaciente(Paciente idPaciente) {
    this.idPaciente = idPaciente;
  }

  public Personal getIdPersonal() {
    return idPersonal;
  }

  public void setIdPersonal(Personal idPersonal) {
    this.idPersonal = idPersonal;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idVenta != null ? idVenta.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Venta)) {
      return false;
    }
    Venta other = (Venta) object;
    if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.Venta[ idVenta=" + idVenta + " ]";
  }
  
}
