/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_gian;

/**
 *
 * @author gfran
 */
public class Usuario {
    private String nombre;
    private String apellidos;
    private String usuario;
    private String password;
    private boolean estado;
    private String correo;

    public Usuario(String nombre, String apellidos, String usuario, String password, boolean estado, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
        this.correo = correo;
    }

    // Getter y Setter para Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para Apellidos
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    // Getter y Setter para Usuario
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Getter y Setter para Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter y Setter para Estado
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Getter y Setter para Correo
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
               "Apellidos: " + apellidos + "\n" +
               "Usuario: " + usuario + "\n" +
               "Estado: " + (estado ? "Activo" : "Inactivo") + "\n" +
               "Correo: " + correo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario usuarioObj = (Usuario) obj;
        return usuario.equals(usuarioObj.usuario);
    }
}