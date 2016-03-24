/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.fachadas;

import com.clinica.entidades.Cuota;
import com.clinica.entidades.Paciente;
import com.clinica.entidades.Personal;
import com.clinica.entidades.Servicio;
import com.clinica.entidades.Servicioventa;
import com.clinica.entidades.ServicioventaPK;
import com.clinica.entidades.Venta;
import com.clinica.entidades.util.Carrito;
import com.clinica.entidades.util.CarritoItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Raul
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> implements VentaFacadeLocal {

  @PersistenceContext(unitName = "com.clinica_CDRevilla-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public VentaFacade() {
    super(Venta.class);
  }

  @Override
  public void grabarVentaContado(Carrito carrito, Paciente paciente, Personal personal) {

    Venta venta = new Venta();
    venta.setComprobante("BOLETA");
    venta.setFormapago("CONTADO");
    venta.setFecha(new Date());
    venta.setTotal(carrito.getTotal());
    venta.setIdPaciente(paciente);
    venta.setIdPersonal(personal);

    try {
//    utx.begin();

      em.persist(venta);
      em.flush();
      List<Servicioventa> servicioVentaList = new ArrayList<>();
      Servicioventa servicioVenta;

      for (CarritoItem i : carrito.getItems()) {
        servicioVenta = new Servicioventa();
        servicioVenta.setCantidad(i.getCantidad());
        servicioVenta.setDiente(i.getDiente());
        servicioVenta.setImporte(i.getImporte());
        servicioVenta.setServicio(new Servicio(i.getIdServicio()));
        servicioVenta.setVenta(venta); //para persistencia 1 a muchos
        servicioVenta.setServicioventaPK(new ServicioventaPK(venta.getIdVenta(), i.getIdServicio()));

        servicioVentaList.add(servicioVenta);

      }
      venta.setServicioventaList(servicioVentaList);
      em.persist(venta);

    //save jpa many to one
//    ejbFacadeVenta.create(venta);
//    utx.commit();
    } catch (Exception e) {
      Logger.getLogger(VentaFacade.class.getName()).log(Level.SEVERE, null, "EROOR GUARDAR VENTA AL CONTADO: " + e.toString());
//      try {
//        utx.rollback();
//      } catch (IllegalStateException ex) {
//        Logger.getLogger(VentaFacade.class.getName()).log(Level.SEVERE, null, ex);
//      } catch (SecurityException ex) {
//        Logger.getLogger(VentaFacade.class.getName()).log(Level.SEVERE, null, ex);
//      } catch (SystemException ex) {
//        Logger.getLogger(VentaFacade.class.getName()).log(Level.SEVERE, null, ex);
//      }
    }

  }

  @Override
  public void grabarVentaCuotas(Carrito carrito, Paciente paciente, Personal personal, Cuota cuota) {

    Venta venta = new Venta();
    venta.setComprobante("BOLETA");
    venta.setFormapago("CONTADO");
    venta.setFecha(new Date());
    venta.setTotal(carrito.getTotal());
    venta.setIdPaciente(paciente);
    venta.setIdPersonal(personal);

    try {

      em.persist(venta);
      em.flush();
      List<Servicioventa> servicioVentaList = new ArrayList<>();
      Servicioventa servicioVenta;

      for (CarritoItem i : carrito.getItems()) {
        servicioVenta = new Servicioventa();
        servicioVenta.setCantidad(i.getCantidad());
        servicioVenta.setDiente(i.getDiente());
        servicioVenta.setImporte(i.getImporte());
        servicioVenta.setServicio(new Servicio(i.getIdServicio()));
        servicioVenta.setVenta(venta); //para persistencia 1 a muchos
        servicioVenta.setServicioventaPK(new ServicioventaPK(venta.getIdVenta(), i.getIdServicio()));

        servicioVentaList.add(servicioVenta);

      }
      
      venta.setServicioventaList(servicioVentaList);
      
      //Agregar cuota
      List<Cuota> cuotaList = new ArrayList<>();
      cuota.setFecha(venta.getFecha());
      cuota.setCuotaspagado(0);
      cuota.setIdVenta(venta);
      cuotaList.add(cuota);
      venta.setCuotaList(cuotaList);
      System.out.println("Se agregó la cuota");
      
      
      em.persist(venta);
      System.out.println("Se guardó la venta en cuotas");

    } catch (Exception e) {
      System.out.println(e.toString());
    }

//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
