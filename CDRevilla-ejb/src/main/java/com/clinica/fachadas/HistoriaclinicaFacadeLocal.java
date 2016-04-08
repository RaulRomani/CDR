/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Historiaclinica;
import com.clinica.entidades.Paciente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface HistoriaclinicaFacadeLocal {

  void create(Historiaclinica historiaclinica);

  void edit(Historiaclinica historiaclinica);

  void remove(Historiaclinica historiaclinica);

  Historiaclinica find(Object id);
  
  public Historiaclinica findByPaciente(Paciente paciente);

  List<Historiaclinica> findAll();

  List<Historiaclinica> findRange(int[] range);

  int count();
  
}
