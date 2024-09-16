package org.example;
import org.example.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = emf.createEntityManager();
        System.out.println("en marcha Alberto");

        try {

            em.getTransaction().begin();




            Factura factura1 = Factura.builder().
                    fecha("14/04/2017").
                    numero(4).
                    total(20).
                    build();

            Factura factura2 = Factura.builder().
                    fecha("27/02/2017").
                    numero(5).
                    total(126).
                    build();

            List<Factura> facturas = new ArrayList<Factura>();
            facturas.add(factura1);
            facturas.add(factura2);

            Domicilio domicilio1 = Domicilio.builder().
                    nombreCalle("Viamonte").
                    numero(87).
                    build();

            Cliente cliente1 = Cliente.builder().
                    nombre("Alejandro").
                    apellido("Perez").
                    dni(33455298).
                    build();

            factura1.setCliente(cliente1);
            factura2.setCliente(cliente1);
            cliente1.setDomicilio(domicilio1);
            cliente1.setFacturas(facturas);

            DetalleFactura detalle1 = DetalleFactura.builder().
                    cantidad(34).
                    subtotal(12).
                    build();

            DetalleFactura detalle2 = DetalleFactura.builder().
                    cantidad(45).
                    subtotal(93).
                    build();

            List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
            detalles.add(detalle1);
            detalles.add(detalle2);
            factura1.setDetalles(detalles);

            Articulo articulo1 = Articulo.builder().
                    cantidad(8).
                    denominacion("Cocina").
                    precio(23).
                    build();

            detalle1.setFactura(factura1);
            detalle2.setFactura(factura1);
            detalle1.setArticulo(articulo1);
            detalle2.setArticulo(articulo1);

            Categoria categoria1 = Categoria.builder().
                    denominacion("Lavanderia").
                    build();

            List<Articulo> articulos = new ArrayList<Articulo>();
            List<Categoria> categorias = new ArrayList<Categoria>();
            articulos.add(articulo1);
            categorias.add(categoria1);
            articulo1.setCategorias(categorias);
            categoria1.setArticulos(articulos);

            em.persist(factura1);

            //Encontrar la factura para actualizarla y ver el registro de auditoria en la clase Factura_Aud (hay que descomentar para ver su funcionamiento y comentar las lineas anteriores, y cambiar a "update" en el persistence.xml)
            /*Factura facturaEncontrada = em.find(Factura.class, 1L);
            facturaEncontrada.setNumero(65);
            em.merge(facturaEncontrada);*/

            //Encontrar la factura para eliminarla y ver el registro de auditoria en la clase Factura_Aud (hay que descomentar para ver su funcionamiento y comentar las lineas anteriores, y cambiar a "update" en el persistence.xml)
            /*Factura facturaEncontrada = em.find(Factura.class, 1L);
            em.remove(facturaEncontrada);*/


            em.flush();
            em.getTransaction().commit();


        }catch (Exception e){

            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }


      em.close();
        emf.close();
    }
}
