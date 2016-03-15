/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "enfermedad")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Enfermedad.findAll", query = "SELECT e FROM Enfermedad e"),
  @NamedQuery(name = "Enfermedad.findByIdEnfermedad", query = "SELECT e FROM Enfermedad e WHERE e.idEnfermedad = :idEnfermedad"),
  @NamedQuery(name = "Enfermedad.findByDatosInformante", query = "SELECT e FROM Enfermedad e WHERE e.datosInformante = :datosInformante"),
  @NamedQuery(name = "Enfermedad.findByMotivoConsulta", query = "SELECT e FROM Enfermedad e WHERE e.motivoConsulta = :motivoConsulta"),
  @NamedQuery(name = "Enfermedad.findByTiempoEnfermedad", query = "SELECT e FROM Enfermedad e WHERE e.tiempoEnfermedad = :tiempoEnfermedad"),
  @NamedQuery(name = "Enfermedad.findBySignosSintomas", query = "SELECT e FROM Enfermedad e WHERE e.signosSintomas = :signosSintomas"),
  @NamedQuery(name = "Enfermedad.findByRelatoCronologico", query = "SELECT e FROM Enfermedad e WHERE e.relatoCronologico = :relatoCronologico"),
  @NamedQuery(name = "Enfermedad.findByFuncionesBiologiacas", query = "SELECT e FROM Enfermedad e WHERE e.funcionesBiologiacas = :funcionesBiologiacas")})
public class Enfermedad implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "IdEnfermedad")
  private Integer idEnfermedad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "DatosInformante")
  private String datosInformante;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "MotivoConsulta")
  private String motivoConsulta;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "TiempoEnfermedad")
  private String tiempoEnfermedad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "SignosSintomas")
  private String signosSintomas;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "RelatoCronologico")
  private String relatoCronologico;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "FuncionesBiologiacas")
  private String funcionesBiologiacas;
  @JoinColumn(name = "IdHistoriaClinica", referencedColumnName = "IdHistoriaClinica")
  @ManyToOne(optional = false)
  private Historiaclinica idHistoriaClinica;

  public Enfermedad() {
  }

  public Enfermedad(Integer idEnfermedad) {
    this.idEnfermedad = idEnfermedad;
  }

  public Enfermedad(Integer idEnfermedad, String datosInformante, String motivoConsulta, String tiempoEnfermedad, String signosSintomas, String relatoCronologico, String funcionesBiologiacas) {
    this.idEnfermedad = idEnfermedad;
    this.datosInformante = datosInformante;
    this.motivoConsulta = motivoConsulta;
    this.tiempoEnfermedad = tiempoEnfermedad;
    this.signosSintomas = signosSintomas;
    this.relatoCronologico = relatoCronologico;
    this.funcionesBiologiacas = funcionesBiologiacas;
  }

  public Integer getIdEnfermedad() {
    return idEnfermedad;
  }

  public void setIdEnfermedad(Integer idEnfermedad) {
    this.idEnfermedad = idEnfermedad;
  }

  public String getDatosInformante() {
    return datosInformante;
  }

  public void setDatosInformante(String datosInformante) {
    this.datosInformante = datosInformante;
  }

  public String getMotivoConsulta() {
    return motivoConsulta;
  }

  public void setMotivoConsulta(String motivoConsulta) {
    this.motivoConsulta = motivoConsulta;
  }

  public String getTiempoEnfermedad() {
    return tiempoEnfermedad;
  }

  public void setTiempoEnfermedad(String tiempoEnfermedad) {
    this.tiempoEnfermedad = tiempoEnfermedad;
  }

  public String getSignosSintomas() {
    return signosSintomas;
  }

  public void setSignosSintomas(String signosSintomas) {
    this.signosSintomas = signosSintomas;
  }

  public String getRelatoCronologico() {
    return relatoCronologico;
  }

  public void setRelatoCronologico(String relatoCronologico) {
    this.relatoCronologico = relatoCronologico;
  }

  public String getFuncionesBiologiacas() {
    return funcionesBiologiacas;
  }

  public void setFuncionesBiologiacas(String funcionesBiologiacas) {
    this.funcionesBiologiacas = funcionesBiologiacas;
  }

  public Historiaclinica getIdHistoriaClinica() {
    return idHistoriaClinica;
  }

  public void setIdHistoriaClinica(Historiaclinica idHistoriaClinica) {
    this.idHistoriaClinica = idHistoriaClinica;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idEnfermedad != null ? idEnfermedad.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Enfermedad)) {
      return false;
    }
    Enfermedad other = (Enfermedad) object;
    if ((this.idEnfermedad == null && other.idEnfermedad != null) || (this.idEnfermedad != null && !this.idEnfermedad.equals(other.idEnfermedad))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.Enfermedad[ idEnfermedad=" + idEnfermedad + " ]";
  }
  
}
