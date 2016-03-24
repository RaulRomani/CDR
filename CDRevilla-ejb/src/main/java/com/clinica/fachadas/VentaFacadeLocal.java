/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Cuota;
import com.clinica.entidades.Paciente;
import com.clinica.entidades.Personal;
import com.clinica.entidades.Venta;
import com.clinica.entidades.util.Carrito;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface VentaFacadeLocal {

  void create(Venta venta);

  void edit(Venta venta);

  void remove(Venta venta);

  Venta find(Object id);

  List<Venta> findAll();

  List<Venta> findRange(int[] range);

  int count();
  
  void grabarVentaContado(Carrito carrito,Paciente paciente,Personal personal);
  
  void grabarVentaCuotas(Carrito carrito,Paciente paciente,Personal personal,Cuota cuota);
  
}
