/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Exploracionfisica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface ExploracionfisicaFacadeLocal {

  void create(Exploracionfisica exploracionfisica);

  void edit(Exploracionfisica exploracionfisica);

  void remove(Exploracionfisica exploracionfisica);

  Exploracionfisica find(Object id);

  List<Exploracionfisica> findAll();

  List<Exploracionfisica> findRange(int[] range);

  int count();
  
}
