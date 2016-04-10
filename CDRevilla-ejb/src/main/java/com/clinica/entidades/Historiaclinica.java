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
@Table(name = "historiaclinica")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Historiaclinica.findAll", query = "SELECT h FROM Historiaclinica h"),
  @NamedQuery(name = "Historiaclinica.findByIdHistoriaClinica", query = "SELECT h FROM Historiaclinica h WHERE h.idHistoriaClinica = :idHistoriaClinica"),
  @NamedQuery(name = "Historiaclinica.findByFechaAtencion", query = "SELECT h FROM Historiaclinica h WHERE h.fechaAtencion = :fechaAtencion"),
  @NamedQuery(name = "Historiaclinica.findByAntecedentesFamiliares", query = "SELECT h FROM Historiaclinica h WHERE h.antecedentesFamiliares = :antecedentesFamiliares"),
  @NamedQuery(name = "Historiaclinica.findByAntecedentesPersonales", query = "SELECT h FROM Historiaclinica h WHERE h.antecedentesPersonales = :antecedentesPersonales"),
  @NamedQuery(name = "Historiaclinica.findByDiagnosticoPresuntivo", query = "SELECT h FROM Historiaclinica h WHERE h.diagnosticoPresuntivo = :diagnosticoPresuntivo"),
  @NamedQuery(name = "Historiaclinica.findByDiagnosticoDefinitivo", query = "SELECT h FROM Historiaclinica h WHERE h.diagnosticoDefinitivo = :diagnosticoDefinitivo"),
  @NamedQuery(name = "Historiaclinica.findByPlanTrabajo", query = "SELECT h FROM Historiaclinica h WHERE h.planTrabajo = :planTrabajo"),
  @NamedQuery(name = "Historiaclinica.findByPronostico", query = "SELECT h FROM Historiaclinica h WHERE h.pronostico = :pronostico"),
  @NamedQuery(name = "Historiaclinica.findByTratamientoRecomendacion", query = "SELECT h FROM Historiaclinica h WHERE h.tratamientoRecomendacion = :tratamientoRecomendacion"),
  @NamedQuery(name = "Historiaclinica.findByAltaPaciente", query = "SELECT h FROM Historiaclinica h WHERE h.altaPaciente = :altaPaciente"),
  @NamedQuery(name = "Historiaclinica.findByPaciente", query = "SELECT h FROM Historiaclinica h WHERE h.idPaciente = :idPaciente")})
public class Historiaclinica implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "IdHistoriaClinica")
  private Integer idHistoriaClinica;
  @Basic(optional = false)
  @NotNull
  @Column(name = "FechaAtencion")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaAtencion;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "AntecedentesFamiliares")
  private String antecedentesFamiliares;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "AntecedentesPersonales")
  private String antecedentesPersonales;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "DiagnosticoPresuntivo")
  private String diagnosticoPresuntivo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "DiagnosticoDefinitivo")
  private String diagnosticoDefinitivo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "PlanTrabajo")
  private String planTrabajo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "Pronostico")
  private String pronostico;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 300)
  @Column(name = "TratamientoRecomendacion")
  private String tratamientoRecomendacion;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 300)
  @Column(name = "AltaPaciente")
  private String altaPaciente;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHistoriaClinica")
  private List<Enfermedad> enfermedadList;
  @JoinColumn(name = "IdPaciente", referencedColumnName = "idPaciente")
  @ManyToOne(optional = false)
  private Paciente idPaciente;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHistoriaClinica")
  private List<Exploracionfisica> exploracionfisicaList;

  public Historiaclinica() {
  }

  public Historiaclinica(Integer idHistoriaClinica) {
    this.idHistoriaClinica = idHistoriaClinica;
  }

  public Historiaclinica(Integer idHistoriaClinica, Date fechaAtencion, String antecedentesFamiliares, String antecedentesPersonales, String diagnosticoPresuntivo, String diagnosticoDefinitivo, String planTrabajo, String pronostico, String tratamientoRecomendacion, String altaPaciente) {
    this.idHistoriaClinica = idHistoriaClinica;
    this.fechaAtencion = fechaAtencion;
    this.antecedentesFamiliares = antecedentesFamiliares;
    this.antecedentesPersonales = antecedentesPersonales;
    this.diagnosticoPresuntivo = diagnosticoPresuntivo;
    this.diagnosticoDefinitivo = diagnosticoDefinitivo;
    this.planTrabajo = planTrabajo;
    this.pronostico = pronostico;
    this.tratamientoRecomendacion = tratamientoRecomendacion;
    this.altaPaciente = altaPaciente;
  }

  public Integer getIdHistoriaClinica() {
    return idHistoriaClinica;
  }

  public void setIdHistoriaClinica(Integer idHistoriaClinica) {
    this.idHistoriaClinica = idHistoriaClinica;
  }

  public Date getFechaAtencion() {
    return fechaAtencion;
  }

  public void setFechaAtencion(Date fechaAtencion) {
    this.fechaAtencion = fechaAtencion;
  }

  public String getAntecedentesFamiliares() {
    return antecedentesFamiliares;
  }

  public void setAntecedentesFamiliares(String antecedentesFamiliares) {
    this.antecedentesFamiliares = antecedentesFamiliares;
  }

  public String getAntecedentesPersonales() {
    return antecedentesPersonales;
  }

  public void setAntecedentesPersonales(String antecedentesPersonales) {
    this.antecedentesPersonales = antecedentesPersonales;
  }

  public String getDiagnosticoPresuntivo() {
    return diagnosticoPresuntivo;
  }

  public void setDiagnosticoPresuntivo(String diagnosticoPresuntivo) {
    this.diagnosticoPresuntivo = diagnosticoPresuntivo;
  }

  public String getDiagnosticoDefinitivo() {
    return diagnosticoDefinitivo;
  }

  public void setDiagnosticoDefinitivo(String diagnosticoDefinitivo) {
    this.diagnosticoDefinitivo = diagnosticoDefinitivo;
  }

  public String getPlanTrabajo() {
    return planTrabajo;
  }

  public void setPlanTrabajo(String planTrabajo) {
    this.planTrabajo = planTrabajo;
  }

  public String getPronostico() {
    return pronostico;
  }

  public void setPronostico(String pronostico) {
    this.pronostico = pronostico;
  }

  public String getTratamientoRecomendacion() {
    return tratamientoRecomendacion;
  }

  public void setTratamientoRecomendacion(String tratamientoRecomendacion) {
    this.tratamientoRecomendacion = tratamientoRecomendacion;
  }

  public String getAltaPaciente() {
    return altaPaciente;
  }

  public void setAltaPaciente(String altaPaciente) {
    this.altaPaciente = altaPaciente;
  }

  @XmlTransient
  public List<Enfermedad> getEnfermedadList() {
    return enfermedadList;
  }

  public void setEnfermedadList(List<Enfermedad> enfermedadList) {
    this.enfermedadList = enfermedadList;
  }

  public Paciente getIdPaciente() {
    return idPaciente;
  }

  public void setIdPaciente(Paciente idPaciente) {
    this.idPaciente = idPaciente;
  }

  @XmlTransient
  public List<Exploracionfisica> getExploracionfisicaList() {
    return exploracionfisicaList;
  }

  public void setExploracionfisicaList(List<Exploracionfisica> exploracionfisicaList) {
    this.exploracionfisicaList = exploracionfisicaList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idHistoriaClinica != null ? idHistoriaClinica.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Historiaclinica)) {
      return false;
    }
    Historiaclinica other = (Historiaclinica) object;
    if ((this.idHistoriaClinica == null && other.idHistoriaClinica != null) || (this.idHistoriaClinica != null && !this.idHistoriaClinica.equals(other.idHistoriaClinica))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.Historiaclinica[ idHistoriaClinica=" + idHistoriaClinica + " ]";
  }
  
}
