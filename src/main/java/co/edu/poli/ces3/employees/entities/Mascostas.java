package co.edu.poli.ces3.employees.entities;

public class Mascostas {

    private int ID;
    private String nombre;
    private String descripcion;
    private String raza;
    private boolean vacunado;
    private boolean esteril;
    private int disponible;
    private int edad;
    private String foto;

    public Mascostas(int ID, String nombre, String descripcion, String raza, boolean vacunado, boolean esteril, int disponible, int edad, String foto) {
        this.ID = ID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.raza = raza;
        this.vacunado = vacunado;
        this.esteril = esteril;
        this.disponible = disponible;
        this.edad = edad;
        this.foto = foto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isVacunado() {
        return vacunado;
    }

    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }

    public boolean isEsteril() {
        return esteril;
    }

    public void setEsteril(boolean esteril) {
        this.esteril = esteril;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
