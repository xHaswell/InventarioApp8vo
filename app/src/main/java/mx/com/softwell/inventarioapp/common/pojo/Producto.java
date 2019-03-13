package mx.com.softwell.inventarioapp.common.pojo;


import com.google.firebase.database.Exclude;

import java.util.Objects;

public class Producto {

    public static final String ID = "idProducto";
    public static final String NOMBRE = "nombre";
    public static final String CANTIDAD = "cantidad";
    public static final String FOTO_URL = "fotoUrl";

    @Exclude
    private String idProducto;
    private String nombre;
    private int cantidad;
    private String fotoUrl;

    public Producto() {
    }

    @Exclude
    public String getIdProducto() {
        return idProducto;
    }

    @Exclude
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }
}
