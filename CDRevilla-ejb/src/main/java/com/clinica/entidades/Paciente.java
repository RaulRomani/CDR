/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.entidades;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
  @NamedQuery(name = "Paciente.findByIdPaciente", query = "SELECT p FROM Paciente p WHERE p.idPaciente = :idPaciente"),
  @NamedQuery(name = "Paciente.findByNombre", query = "SELECT p FROM Paciente p WHERE p.nombre = :nombre"),
  @NamedQuery(name = "Paciente.findByApelllidos", query = "SELECT p FROM Paciente p WHERE p.apelllidos = :apelllidos"),
  @NamedQuery(name = "Paciente.findByDni", query = "SELECT p FROM Paciente p WHERE p.dni = :dni"),
  @NamedQuery(name = "Paciente.findByDireccion", query = "SELECT p FROM Paciente p WHERE p.direccion = :direccion"),
  @NamedQuery(name = "Paciente.findByCelular", query = "SELECT p FROM Paciente p WHERE p.celular = :celular"),
  @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
  @NamedQuery(name = "Paciente.findByEdad", query = "SELECT p FROM Paciente p WHERE p.edad = :edad"),
  @NamedQuery(name = "Paciente.findByLugarNacimiento", query = "SELECT p FROM Paciente p WHERE p.lugarNacimiento = :lugarNacimiento"),
  @NamedQuery(name = "Paciente.findByRaza", query = "SELECT p FROM Paciente p WHERE p.raza = :raza"),
  @NamedQuery(name = "Paciente.findByGradoInstruccion", query = "SELECT p FROM Paciente p WHERE p.gradoInstruccion = :gradoInstruccion"),
  @NamedQuery(name = "Paciente.findByOcupaci\u00f3n", query = "SELECT p FROM Paciente p WHERE p.ocupaci\u00f3n = :ocupaci\u00f3n"),
  @NamedQuery(name = "Paciente.findByReligion", query = "SELECT p FROM Paciente p WHERE p.religion = :religion"),
  @NamedQuery(name = "Paciente.findByEstadoCivil", query = "SELECT p FROM Paciente p WHERE p.estadoCivil = :estadoCivil"),
  @NamedQuery(name = "Paciente.findByFechaApertura", query = "SELECT p FROM Paciente p WHERE p.fechaApertura = :fechaApertura")})
public class Paciente implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idPaciente")
  private Integer idPaciente;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "Nombre")
  private String nombre;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "Apelllidos")
  private String apelllidos;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 8)
  @Column(name = "DNI")
  private String dni;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "Direccion")
  private String direccion;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "Celular")
  private String celular;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "Sexo")
  private String sexo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "Edad")
  private String edad;
  @Size(max = 100)
  @Column(name = "LugarNacimiento")
  private String lugarNacimiento;
  @Size(max = 200)
  @Column(name = "Raza")
  private String raza;
  @Size(max = 200)
  @Column(name = "GradoInstruccion")
  private String gradoInstruccion;
  @Size(max = 50)
  @Column(name = "Ocupaci\u00f3n")
  private String ocupación;
  @Size(max = 50)
  @Column(name = "Religion")
  private String religion;
  @Size(max = 50)
  @Column(name = "EstadoCivil")
  private String estadoCivil;
  @Column(name = "FechaApertura")
  @Temporal(TemporalType.DATE)
  private Date fechaApertura;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
  private List<Historiaclinica> historiaclinicaList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
  private List<Venta> ventaList;

  public Paciente() {
  }

  public Paciente(Integer idPaciente) {
    this.idPaciente = idPaciente;
  }

  public Paciente(Integer idPaciente, String nombre, String apelllidos, String dni, String direccion, String celular, String sexo, String edad) {
    this.idPaciente = idPaciente;
    this.nombre = nombre;
    this.apelllidos = apelllidos;
    this.dni = dni;
    this.direccion = direccion;
    this.celular = celular;
    this.sexo = sexo;
    this.edad = edad;
  }

  public Integer getIdPaciente() {
    return idPaciente;
  }

  public void setIdPaciente(Integer idPaciente) {
    this.idPaciente = idPaciente;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApelllidos() {
    return apelllidos;
  }

  public void setApelllidos(String apelllidos) {
    this.apelllidos = apelllidos;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
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

  public String getLugarNacimiento() {
    return lugarNacimiento;
  }

  public void setLugarNacimiento(String lugarNacimiento) {
    this.lugarNacimiento = lugarNacimiento;
  }

  public String getRaza() {
    return raza;
  }

  public void setRaza(String raza) {
    this.raza = raza;
  }

  public String getGradoInstruccion() {
    return gradoInstruccion;
  }

  public void setGradoInstruccion(String gradoInstruccion) {
    this.gradoInstruccion = gradoInstruccion;
  }

  public String getOcupación() {
    return ocupación;
  }

  public void setOcupación(String ocupación) {
    this.ocupación = ocupación;
  }

  public String getReligion() {
    return religion;
  }

  public void setReligion(String religion) {
    this.religion = religion;
  }

  public String getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(String estadoCivil) {
    this.estadoCivil = estadoCivil;
  }

  public Date getFechaApertura() {
    return fechaApertura;
  }

  public void setFechaApertura(Date fechaApertura) {
    this.fechaApertura = fechaApertura;
  }

  @XmlTransient
  public List<Historiaclinica> getHistoriaclinicaList() {
    return historiaclinicaList;
  }

  public void setHistoriaclinicaList(List<Historiaclinica> historiaclinicaList) {
    this.historiaclinicaList = historiaclinicaList;
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
    hash += (idPaciente != null ? idPaciente.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Paciente)) {
      return false;
    }
    Paciente other = (Paciente) object;
    if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.Paciente[ idPaciente=" + idPaciente + " ]";
  }
  
}
