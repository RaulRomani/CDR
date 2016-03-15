/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Servicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface ServicioFacadeLocal {

  void create(Servicio servicio);

  void edit(Servicio servicio);

  void remove(Servicio servicio);

  Servicio find(Object id);

  List<Servicio> findAll();

  List<Servicio> findRange(int[] range);

  int count();
  
}
