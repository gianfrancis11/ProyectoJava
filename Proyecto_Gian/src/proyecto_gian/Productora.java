/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_gian;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;

public class Productora {
    private LinkedList<Usuario> usuarios;
    private LinkedList<Evento> eventos;
    private boolean estaLogeado;
    private String usuarioLogeado;
    
// Costructor de la clase productora
    public Productora() {
        this.usuarios = new LinkedList<>();
        this.eventos = new LinkedList<>();
        this.estaLogeado = false;      
        this.usuarioLogeado = "";  
    }
// Funcion para agregar los usuarios a la lista 
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    // Funcion para agregar los eventos a la lista 
    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }
// Funcion en la cual se verifica soi el usuario esta logeado
    public void logear(String usuario){
        this.estaLogeado = true;
        this.usuarioLogeado = usuario;
    }
    // Comparativo para saber si el usuario existe en la lista 
    public Usuario consultarUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return u;
            }
        }
        return null; // Si no se encuentra el usuario
    }
    // Disponibilidad de los asientos disponibles en el evento
    public Boolean consultarNombreUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false; // Si no se encuentra el usuario
    }
    // Funcion para realizar la reservacion del espacio
    public String getEspaciosEvento(int eventoSeleccionado){
        int[] espacios = eventos.get(eventoSeleccionado).getAsientosDisponibles();

        String resultado = "";
        for (int i = 0; i < espacios.length; i++) {
            resultado += espacios[i];

            if ((i + 1) % 5 == 0 || i == espacios.length - 1) {
                resultado += "\n";
            } else {
                resultado += ",";
            }
        }
        return resultado;
    }
    // Funcion en la cual el arreglo se verifica si tiene o no elementos almacenados
    static boolean contains(int[] arr, int element) {
        for (int value : arr) {
            if (value == element) {
                return true;
            }
        }
        return false;
    }
    // Reservaciones de los espacios 
    public String reservarEspacios(int eventoSeleccionado, String seleccionEspacios) {
        String[] arregloAsientos = seleccionEspacios.split(",");
        Evento evento = eventos.get(eventoSeleccionado);
        int[] asientosDisponibles = eventos.get(eventoSeleccionado).getAsientosDisponibles();
        ArrayList<Integer> asientosOcupados = new ArrayList<>();

        for (String asientoStr : arregloAsientos) {
            try {
                int asiento = Integer.parseInt(asientoStr);
                if (asiento >= 1 && asiento <= asientosDisponibles.length) {
                    asientosOcupados.add(asiento);
                } else {
                    System.out.println("El asiento " + asiento + " está fuera del rango de asientos disponibles.");
                    return "El asiento " + asiento + " está fuera del rango de asientos disponibles.";
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor '" + asientoStr + "' no es un número válido.");
                return "El valor '" + asientoStr + "' no es un número válido.";
            }
        }

        // Verificar si algún asiento no existe en asientosDisponibles
        for (int asiento : asientosOcupados) {
            if (!contains(asientosDisponibles, asiento)) {
                System.out.println("El asiento " + asiento + " no está disponible.");
                return "El asiento " + asiento + " no está disponible.";
            }
        }

        evento.addOcupados(asientosOcupados);
        int netoFactura = 3850*arregloAsientos.length;
        int impuestoFactura = (netoFactura*13)/100;
        int totalFactura = netoFactura+impuestoFactura;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();   
        JOptionPane.showMessageDialog(null,"Cliente: "+usuarioLogeado+"\n"
        +"Fecha de compra: "+dtf.format(now)+"\n"
        +"Cantidad de boletos reservados: "+asientosOcupados.size()+"\n"
        +"Costo por entradas: "+netoFactura+"\n"
        +"I.V.A: "+impuestoFactura+"\n"
        +"Costo total: "+totalFactura+"\n");
        
        return "Se han reservado con éxito los espacios.";
    }
    // Funcion para mostrar los usarios ya almacenados
    public void mostrarNombresUsuarios() {
        System.out.println("Nombres de usuarios guardados:");
        for (Usuario u : usuarios) {
            System.out.println(u.getUsuario());
        }
    }
    
    public void mostrarEventos() {
        System.out.println("Eventos guardados:");
        for (Evento e : eventos) {
            if(e.getEstado()){
                System.out.println(e.getInfoEvento());  
            }
        }
    }
    // Funcion Eventos que se encuentran activos
    public String getStringEventos() {
        String resultado = "";
        int counter = 1;
        for (Evento e : eventos) {
            if(e.getEstado()){
                resultado += "Evento "+ counter + ":\n" + e.getInfoEvento() + "\n";
                counter ++;
            }
        }
        return resultado;
    }
// Funcion para editar los detalles
    public void editarEvento(LocalDate fechaEvento, String nuevaDireccion, String nuevaCiudad) {
        for (Evento e : eventos) {
            if (e.getFecha().equals(fechaEvento)) {
                e.setDireccion(nuevaDireccion);
                e.setCiudad(nuevaCiudad);
                JOptionPane.showMessageDialog(null, "Evento editado con éxito.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Evento no encontrado."); 
    }
    // Funcion para quitar un evento
    public void inactivarEvento(LocalDate fechaEvento) {
        for (Evento e : eventos) {
            if (e.getFecha().equals(fechaEvento)) {
                e.setEstado(false);
                JOptionPane.showMessageDialog(null, "Evento inactivado con éxito.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Evento no encontrado."); 
    }
    //Función para gestionar eventos (agregar, editar, inactivar, mostrar)
    public void iniciarEventos(Productora productora) {
            try{
                String opcion = JOptionPane.showInputDialog(
                    "Seleccione una opción:\n" +
                    "1. Agregar nuevo evento\n" +
                    "2. Editar evento existente\n" +
                    "3. Inactivar eventos\n" +
                    "4. Mostrar eventos\n" +
                    "5. Salir"
                );

                switch (opcion) {
                    case "1":
                        String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad del evento:");
                        String direccion = JOptionPane.showInputDialog("Ingrese la direccion del evento:");                        
                        String fechaInput = JOptionPane.showInputDialog("Ingrese la fecha del evento (DD/MM/YYYY):");
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate fecha = null;
                        try {
                            fecha = LocalDate.parse(fechaInput, dateFormat);
                            fechaInput = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (DateTimeParseException e) {
                            JOptionPane.showMessageDialog(null, "Fecha inválida. Ingrese la fecha en formato DD/MM/YYYY.");
                            break;
                        }
                        
                        String categoria = JOptionPane.showInputDialog("Este evento es para mayores de edad? (Si,No)");
                        
                        categoria = categoria.toLowerCase();
                        Integer categoriaFinal;
                        if (categoria.equals("si")) {
                            categoriaFinal = 1;
                        }else if (categoria.equals("no")){
                            categoriaFinal = 0;
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese 'Si' o 'No'.");
                            break;
                        }
                        
                        
                        Evento nuevoEvento = new Evento(ciudad, direccion, categoriaFinal, fecha, true);
                        productora.agregarEvento(nuevoEvento);
                        JOptionPane.showMessageDialog(null, "Evento agregado con éxito.");
                        break;

                    case "2":
                        String fechaEditar = JOptionPane.showInputDialog("Ingrese la fecha del evento a editar (DD/MM/YYYY):");
                        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate fechaEvento = null;
                        try {
                            fechaEvento = LocalDate.parse(fechaEditar, dateFormat2);
                        } catch (DateTimeParseException e) {
                            JOptionPane.showMessageDialog(null, "Fecha inválida. Ingrese la fecha en formato DD/MM/YYYY.");
                            break;
                        }
                        String nuevaCiudad = JOptionPane.showInputDialog("Ingrese la nueva ciudad del evento:");
                        String nuevaDireccion = JOptionPane.showInputDialog("Ingrese la nueva direccion del evento:");
                        productora.editarEvento(fechaEvento, nuevaDireccion, nuevaCiudad);
                        break;

                    case "3":
                        String fechaInactivar = JOptionPane.showInputDialog("Ingrese la fecha del evento a editar (DD/MM/YYYY):");
                        DateTimeFormatter dateFormat3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate fechaEvento3 = null;
                        try {
                            fechaEvento3 = LocalDate.parse(fechaInactivar, dateFormat3);
                        } catch (DateTimeParseException e) {
                            JOptionPane.showMessageDialog(null, "Fecha inválida. Ingrese la fecha en formato DD/MM/YYYY.");
                            break;
                        }
                        productora.inactivarEvento(fechaEvento3);
                        break;
                    case "4":
                        productora.mostrarEventos();
                        break;
                        
                    case "5":
                        //seguirEjecutando = false;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, intente de nuevo.");
                        break;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, intente de nuevo.");
            }
        
    }
// Función para gestionar usuarios (agregar, consultar, iniciar sesión)
    public static void iniciarUsuarios(Productora productora) {
            try{
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Selecciona una opción:\n" +
                    "1. Agregar usuario\n" +
                    "2. Consultar usuario\n" +
                    "3. Login\n" +
                    "4. Salir"
                ));
                switch (opcion) {
                    case 1:
                        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
                        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos:");
                        String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
                        String password = JOptionPane.showInputDialog("Ingrese la contraseña:");
                        String estadoString = JOptionPane.showInputDialog("Ingrese el estado (Activo o Inactivo):");
                        boolean estado = estadoString.equalsIgnoreCase("Activo");
                        String correo = JOptionPane.showInputDialog("Ingrese el correo:");

                        Usuario nuevoUsuario = new Usuario(nombre, apellidos, usuario, password, estado, correo);
                        productora.agregarUsuario(nuevoUsuario);
                        JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.");
                        break;

                    case 2:
                        String usuarioConsultar = JOptionPane.showInputDialog("Ingrese el nombre de usuario a consultar:");
                        Usuario usuarioEncontrado = productora.consultarUsuario(usuarioConsultar);
                        if (usuarioEncontrado != null) {
                            JOptionPane.showMessageDialog(null,
                                    "Usuario encontrado:\n" +
                                    "Nombre: " + usuarioEncontrado.getNombre() + "\n" +
                                    "Apellidos: " + usuarioEncontrado.getApellidos() + "\n" +
                                    "Usuario: " + usuarioEncontrado.getUsuario() + "\n" +
                                    "Estado: " + (usuarioEncontrado.isEstado() ? "Activo" : "Inactivo") + "\n" +
                                    "Correo: " + usuarioEncontrado.getCorreo()
                            );
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                        }
                        break;

                    case 3:
                        String usuarioIngresado = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
                        Boolean consultaUsuario = productora.consultarNombreUsuario(usuarioIngresado);
                        if(consultaUsuario){
                            Usuario datosUsuario = productora.consultarUsuario(usuarioIngresado);
                            String contraIngresada = JOptionPane.showInputDialog("Ingrese la contrasena de este usuario:");
                            System.out.println(datosUsuario.getPassword());
                            System.out.println(contraIngresada);
                            System.out.println(contraIngresada.equals(datosUsuario.getPassword()));
                            if(contraIngresada.equals(datosUsuario.getPassword())) {
                                productora.logear(usuarioIngresado);
                                JOptionPane.showMessageDialog(null, "Ha ingresado con exito");
                            }else{
                            JOptionPane.showMessageDialog(null, "Contrasena incorrecta");
                            break;
                        }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Este nombre de usuario no existe, ingrese otro");
                            break;
                        }
                    case 4:
                        //seguirEjecutando = false;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, intente de nuevo.");
                        break;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, intente de nuevo.");
            }
        
    }
    // Función para gestionar la reserva de espacios para un evento
    public static void iniciarFactura(Productora productora) {
        if (productora.estaLogeado){
            String opcionSeleccionada = JOptionPane.showInputDialog(productora.getStringEventos()+ "\nIngrese el numero del evento a reservar");
            try {
                int seleccionEntero = Integer.parseInt(opcionSeleccionada)-1;
                System.out.println("El valor es:" + seleccionEntero);
                String seleccionEspacios = JOptionPane.showInputDialog(productora.getEspaciosEvento(seleccionEntero)+ "\nIngrese los numeros de los asientos a seleccionar. Separados por [,]");
                
                JOptionPane.showMessageDialog(null, productora.reservarEspacios(seleccionEntero, seleccionEspacios));
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un numero.");
            }catch (java.lang.IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una opcion valida.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor, ingrese a la plataforma.");
        }
    }
}
