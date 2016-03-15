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
@Table(name = "exploracionfisica")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Exploracionfisica.findAll", query = "SELECT e FROM Exploracionfisica e"),
  @NamedQuery(name = "Exploracionfisica.findByIdExploracionFisica", query = "SELECT e FROM Exploracionfisica e WHERE e.idExploracionFisica = :idExploracionFisica"),
  @NamedQuery(name = "Exploracionfisica.findBySignosVitales", query = "SELECT e FROM Exploracionfisica e WHERE e.signosVitales = :signosVitales"),
  @NamedQuery(name = "Exploracionfisica.findByPulso", query = "SELECT e FROM Exploracionfisica e WHERE e.pulso = :pulso"),
  @NamedQuery(name = "Exploracionfisica.findByTemperatura", query = "SELECT e FROM Exploracionfisica e WHERE e.temperatura = :temperatura"),
  @NamedQuery(name = "Exploracionfisica.findByFc", query = "SELECT e FROM Exploracionfisica e WHERE e.fc = :fc"),
  @NamedQuery(name = "Exploracionfisica.findByFrecuenciaRespiratoria", query = "SELECT e FROM Exploracionfisica e WHERE e.frecuenciaRespiratoria = :frecuenciaRespiratoria"),
  @NamedQuery(name = "Exploracionfisica.findByExamenClinicoGeneral", query = "SELECT e FROM Exploracionfisica e WHERE e.examenClinicoGeneral = :examenClinicoGeneral"),
  @NamedQuery(name = "Exploracionfisica.findByOdontoestomatologico", query = "SELECT e FROM Exploracionfisica e WHERE e.odontoestomatologico = :odontoestomatologico")})
public class Exploracionfisica implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "IdExploracionFisica")
  private Integer idExploracionFisica;
  @Basic(optional = false)
  @NotNull
  @Column(name = "SignosVitales")
  private int signosVitales;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Pulso")
  private int pulso;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Temperatura")
  private int temperatura;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "FC")
  private String fc;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "FrecuenciaRespiratoria")
  private String frecuenciaRespiratoria;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "ExamenClinicoGeneral")
  private String examenClinicoGeneral;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "Odontoestomatologico")
  private String odontoestomatologico;
  @JoinColumn(name = "IdHistoriaClinica", referencedColumnName = "IdHistoriaClinica")
  @ManyToOne(optional = false)
  private Historiaclinica idHistoriaClinica;

  public Exploracionfisica() {
  }

  public Exploracionfisica(Integer idExploracionFisica) {
    this.idExploracionFisica = idExploracionFisica;
  }

  public Exploracionfisica(Integer idExploracionFisica, int signosVitales, int pulso, int temperatura, String fc, String frecuenciaRespiratoria, String examenClinicoGeneral, String odontoestomatologico) {
    this.idExploracionFisica = idExploracionFisica;
    this.signosVitales = signosVitales;
    this.pulso = pulso;
    this.temperatura = temperatura;
    this.fc = fc;
    this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    this.examenClinicoGeneral = examenClinicoGeneral;
    this.odontoestomatologico = odontoestomatologico;
  }

  public Integer getIdExploracionFisica() {
    return idExploracionFisica;
  }

  public void setIdExploracionFisica(Integer idExploracionFisica) {
    this.idExploracionFisica = idExploracionFisica;
  }

  public int getSignosVitales() {
    return signosVitales;
  }

  public void setSignosVitales(int signosVitales) {
    this.signosVitales = signosVitales;
  }

  public int getPulso() {
    return pulso;
  }

  public void setPulso(int pulso) {
    this.pulso = pulso;
  }

  public int getTemperatura() {
    return temperatura;
  }

  public void setTemperatura(int temperatura) {
    this.temperatura = temperatura;
  }

  public String getFc() {
    return fc;
  }

  public void setFc(String fc) {
    this.fc = fc;
  }

  public String getFrecuenciaRespiratoria() {
    return frecuenciaRespiratoria;
  }

  public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
    this.frecuenciaRespiratoria = frecuenciaRespiratoria;
  }

  public String getExamenClinicoGeneral() {
    return examenClinicoGeneral;
  }

  public void setExamenClinicoGeneral(String examenClinicoGeneral) {
    this.examenClinicoGeneral = examenClinicoGeneral;
  }

  public String getOdontoestomatologico() {
    return odontoestomatologico;
  }

  public void setOdontoestomatologico(String odontoestomatologico) {
    this.odontoestomatologico = odontoestomatologico;
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
    hash += (idExploracionFisica != null ? idExploracionFisica.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Exploracionfisica)) {
      return false;
    }
    Exploracionfisica other = (Exploracionfisica) object;
    if ((this.idExploracionFisica == null && other.idExploracionFisica != null) || (this.idExploracionFisica != null && !this.idExploracionFisica.equals(other.idExploracionFisica))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.clinica.entidades.Exploracionfisica[ idExploracionFisica=" + idExploracionFisica + " ]";
  }
  
}
