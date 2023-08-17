/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_gian;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author gfran
 */
public class PYGian {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Productora productora = new Productora();
        
        //Agregando usuarios de ejemplo
        Usuario usuario1 = new Usuario("Admin", "Apellidos1", "admin", "password1", true, "correo1@example.com");
        Usuario usuario2 = new Usuario("Cliente", "Apellidos2", "cliente", "password2", true, "correo2@example.com");
        
        //Agregando eventos ejemplo
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Evento 1
        Evento evento1 = new Evento("San José", "Avenida Central 123", 1, LocalDate.now(), true);

        // Evento 2
        LocalDate fecha2 = LocalDate.now().plusDays(1);
        String fechaStr2 = fecha2.format(dateFormatter);
        Evento evento2 = new Evento("Alajuela", "Calle 2, Parque Central", 2, fecha2, true);

        // Evento 3
        LocalDate fecha3 = LocalDate.now().plusDays(2);
        String fechaStr3 = fecha3.format(dateFormatter);
        Evento evento3 = new Evento("Heredia", "Plaza de la Cultura", 1, fecha3, true);

        // Evento 4
        //LocalDate fecha4 = LocalDate.now().plusDays(3);
        //String fechaStr4 = fecha4.format(dateFormatter);
        //Evento evento4 = new Evento("Cartago", "Ruinas de Cartago", 2, fecha4, true);

        // Evento 5
        //LocalDate fecha5 = LocalDate.now().plusDays(4);
        //String fechaStr5 = fecha5.format(dateFormatter);
        //Evento evento5 = new Evento("Liberia", "Parque Nacional, Guanacaste", 1, fecha5, true);
        
        productora.agregarUsuario(usuario1);
        productora.agregarUsuario(usuario2);
        productora.agregarEvento(evento1);
        productora.agregarEvento(evento2);
        productora.agregarEvento(evento3);
        //productora.agregarEvento(evento4);
        //productora.agregarEvento(evento5);
        
        boolean salir = false;
        //Esto es la funcion del menu principal donde se van a tener las 3 opciones a escoger del proyecto, si desea agregar usuarios, editar eventos o reservaciones
        while (!salir) {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Bienvenido a Producciones Palace\n" +
                    "Selecciona una opción:\n" +
                    "1. Usuarios\n" +
                    "2. Eventos\n" +
                    "3. Reservaciones\n" +
                    "4. Salir"
            ));

            switch (opcion) {
                case 1:
                    productora.iniciarUsuarios(productora);
                    break;
                case 2:
                    productora.iniciarEventos(productora);
                    break;
                case 3:
                    productora.iniciarFactura(productora);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    } 
    
}

