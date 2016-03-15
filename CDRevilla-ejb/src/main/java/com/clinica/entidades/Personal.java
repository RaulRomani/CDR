/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
  @NamedQuery(name = "Personal.findByIdPersonal", query = "SELECT p FROM Personal p WHERE p.idPersonal = :idPersonal"),
  @NamedQuery(name = "Personal.findByNombres", query = "SELECT p FROM Personal p WHERE p.nombres = :nombres"),
  @NamedQuery(name = "Personal.findByApellidos", query = "SELECT p FROM Personal p WHERE p.apellidos = :apellidos"),
  @NamedQuery(name = "Personal.findByEspecialidad", query = "SELECT p FROM Personal p WHERE p.especialidad = :especialidad"),
  @NamedQuery(name = "Personal.findByUsuario", query = "SELECT p FROM Personal p WHERE p.usuario = :usuario"),
  @NamedQuery(name = "Personal.findByClave", query = "SELECT p FROM Personal p WHERE p.clave = :clave"),
  @NamedQuery(name = "validar", query = "SELECT p FROM Personal p WHERE p.clave = :clave and p.usuario = :usuario")})

public class Personal implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "IdPersonal")
  private Integer idPersonal;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "Nombres")
  private String nombres;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "Apellidos")
  private String apellidos;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "Especialidad")
  private String especialidad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "usuario")
  private String usuario;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "clave")
  private String clave;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonal")
  private List<Venta> ventaList;

  public Personal() {
  }

  public Personal(Integer idPersonal) {
    this.idPersonal = idPersonal;
  }

  public Personal(Integer idPersonal, String nombres, String apellidos, String especialidad, String usuario, String clave) {
    this.idPersonal = idPersonal;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.especialidad = especialidad;
    this.usuario = usuario;
    this.clave = clave;
  }

  public Integer getIdPersonal() {
    return idPersonal;
  }

  public void setIdPersonal(Integer idPersonal) {
    this.idPersonal = idPersonal;
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(String especialidad) {
    this.especialidad = especialidad;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  @XmlTransient
  public List<Venta> getVentaList() {
    return ventaList;
  }

  public void setVentaList(List<Venta> ventaList) {
    this.ventaList = ventaList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idPersonal != null ? idPersonal.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Personal)) {
      return false;
    }
    Personal other = (Personal) object;
    if ((this.idPersonal == null && other.idPersonal != null) || (this.idPersonal != null && !this.idPersonal.equals(other.idPersonal))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.Personal[ idPersonal=" + idPersonal + " ]";
  }
  
}
