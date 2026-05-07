package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta que representa una transaccion financiera.
 * Contiene los atributos y comportamientos comunes a ingresos y gastos.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Ingreso
 * @see Gasto
 */
public abstract class Transaccion {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico de la transaccion. */
    protected int idTransaccion;
    
    /** Descripcion de la transaccion. */
    protected String descripcion;
    
    /** Monto de la transaccion. */
    protected double monto;
    
    /** Fecha y hora de la transaccion. */
    protected LocalDateTime fecha;
    
    /** Categoria de la transaccion. */
    protected Categoria categoria;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear una transaccion.
     * 
     * @param descripcion Descripcion de la transaccion
     * @param monto Monto de la transaccion
     * @param fecha Fecha de la transaccion
     * @param categoria Categoria de la transaccion
     */
    public Transaccion(String descripcion, double monto, LocalDateTime fecha, Categoria categoria) {
        this.idTransaccion = contadorIds++;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
    }
    
    /**
     * Obtiene el tipo de transaccion (Ingreso o Gasto).
     * Metodo abstracto implementado por las subclases.
     * 
     * @return Tipo de transaccion
     */
    public abstract String getTipo();
    
    // ==================== GETTERS ====================
    
    /** @return Identificador de la transaccion */
    public int getIdTransaccion() {
        return idTransaccion;
    }
    
    /** @return Descripcion de la transaccion */
    public String getDescripcion() {
        return descripcion;
    }
    
    /** @return Monto de la transaccion */
    public double getMonto() {
        return monto;
    }
    
    /** @return Fecha de la transaccion */
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    /** @return Categoria de la transaccion */
    public Categoria getCategoria() {
        return categoria;
    }
    
    /**
     * Devuelve una representacion textual de la transaccion.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "[" + idTransaccion + "] " + descripcion + " - $" + monto + 
               " (" + categoria.getNombre() + ") - " + fecha.format(formatter);
    }
    
}//fin de la clase