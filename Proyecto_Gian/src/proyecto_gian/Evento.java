/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_gian;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Evento {
    private String ciudad;
    private String direccion;
    private Integer categoria;
    private LocalDate fecha;
    private int[] asientosDisponibles = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
    private int[] asientosOcupados = {};
    private boolean estado;
    
    // Constructor
    public Evento(String ciudad, String direccion, Integer categoria, LocalDate fecha,Boolean estado) {
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.categoria = categoria;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters
    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getCategoria() {
        return categoria;
    }
    
    public Boolean getEstado() {
        return estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int[] getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public int[] getAsientosOcupados() {
        return asientosOcupados;
    }
    
    static boolean contains(int[] arr, int element) {
        for (int value : arr) {
            if (value == element) {
                return true;
            }
        }
        return false;
    }
    
    public void addOcupados(ArrayList<Integer> nuevosAsientos) {
        ArrayList<Integer> asientosOcupadosList = new ArrayList<>();
        ArrayList<Integer> asientosDisponiblesList = new ArrayList<>();

        for (int asiento : asientosDisponibles) {
            asientosDisponiblesList.add(asiento);
        }

        for (int asiento : nuevosAsientos) {
            if (contains(asientosDisponibles, asiento)) {
                asientosOcupadosList.add(asiento);
                asientosDisponiblesList.remove(Integer.valueOf(asiento));
            }
        }

        asientosOcupados = new int[asientosOcupadosList.size()];
        for (int i = 0; i < asientosOcupadosList.size(); i++) {
            asientosOcupados[i] = asientosOcupadosList.get(i);
        }

        asientosDisponibles = new int[asientosDisponiblesList.size()];
        for (int i = 0; i < asientosDisponiblesList.size(); i++) {
            asientosDisponibles[i] = asientosDisponiblesList.get(i);
        }
    }

    // Setters
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setAsientosOcupados(int[] asientosOcupados) {
        this.asientosOcupados = asientosOcupados;
    }
    
    public String getInfoEvento () {
        String info = "Ciudad: " + ciudad + "\n" +
                          "Dirección: " + direccion + "\n" +
                          "Categoría: " + categoria + "\n" +
                          "Fecha: " + fecha + "\n" +
                          "Asientos disponibles: " + asientosDisponibles.length + "\n" +
                          "Asientos ocupados: " + asientosOcupados.length + "\n";
        return info;
    }

    void setDescripcion(String nuevaDescripcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
