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
  @NamedQuery(name = "Personal.findByDni", query = "SELECT p FROM Personal p WHERE p.dni = :dni"),
  @NamedQuery(name = "Personal.findByFoto", query = "SELECT p FROM Personal p WHERE p.foto = :foto"),
  @NamedQuery(name = "Personal.findByDireccion", query = "SELECT p FROM Personal p WHERE p.direccion = :direccion"),
  @NamedQuery(name = "Personal.findByLugarNacimiento", query = "SELECT p FROM Personal p WHERE p.lugarNacimiento = :lugarNacimiento"),
  @NamedQuery(name = "Personal.findByCelular", query = "SELECT p FROM Personal p WHERE p.celular = :celular"),
  @NamedQuery(name = "Personal.findBySexo", query = "SELECT p FROM Personal p WHERE p.sexo = :sexo"),
  @NamedQuery(name = "Personal.findByEdad", query = "SELECT p FROM Personal p WHERE p.edad = :edad"),
  @NamedQuery(name = "Personal.findByUsuario", query = "SELECT p FROM Personal p WHERE p.usuario = :usuario"),
  @NamedQuery(name = "Personal.findByClave", query = "SELECT p FROM Personal p WHERE p.clave = :clave"),
  @NamedQuery(name = "Personal.findByRol", query = "SELECT p FROM Personal p WHERE p.rol = :rol"),
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
  @Size(min = 1, max = 50)
  @Column(name = "Nombres")
  private String nombres;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "Apellidos")
  private String apellidos;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "Especialidad")
  private String especialidad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 8)
  @Column(name = "DNI")
  private String dni;
  @Size(max = 100)
  @Column(name = "Foto")
  private String foto;
  @Size(max = 100)
  @Column(name = "Direccion")
  private String direccion;
  @Size(max = 200)
  @Column(name = "LugarNacimiento")
  private String lugarNacimiento;
  @Size(max = 20)
  @Column(name = "Celular")
  private String celular;
  @Size(max = 10)
  @Column(name = "Sexo")
  private String sexo;
  @Size(max = 3)
  @Column(name = "Edad")
  private String edad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "usuario")
  private String usuario;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "clave")
  private String clave;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "rol")
  private String rol;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonal")
  private List<Venta> ventaList;

  public Personal() {
  }

  public Personal(Integer idPersonal) {
    this.idPersonal = idPersonal;
  }

  public Personal(Integer idPersonal, String nombres, String apellidos, String especialidad, String dni, String usuario, String clave, String rol) {
    this.idPersonal = idPersonal;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.especialidad = especialidad;
    this.dni = dni;
    this.usuario = usuario;
    this.clave = clave;
    this.rol = rol;
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

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getLugarNacimiento() {
    return lugarNacimiento;
  }

  public void setLugarNacimiento(String lugarNacimiento) {
    this.lugarNacimiento = lugarNacimiento;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getEdad() {
    return edad;
  }

  public void setEdad(String edad) {
    this.edad = edad;
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

  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
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
