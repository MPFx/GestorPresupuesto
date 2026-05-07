package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que implementa una meta de ahorro.
 * Permite establecer una meta financiera y hacer seguimiento del progreso.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see MetaAhorro
 */
public class MetaAhorroImpl implements MetaAhorro {
    
    // ==================== ATRIBUTOS ====================
    
    /** Nombre de la meta (Vacaciones, Carro, Casa, etc.). */
    private String nombre;
    
    /** Monto total de la meta. */
    private double meta;
    
    /** Monto actual ahorrado. */
    private double ahorrado;
    
    /** Fecha limite para cumplir la meta. */
    private LocalDateTime fechaLimite;
    
    /**
     * Constructor para crear una meta de ahorro.
     * 
     * @param nombre Nombre de la meta
     * @param meta Monto total a ahorrar
     * @param fechaLimite Fecha limite
     */
    public MetaAhorroImpl(String nombre, double meta, LocalDateTime fechaLimite) {
        this.nombre = nombre;
        this.meta = meta;
        this.fechaLimite = fechaLimite;
        this.ahorrado = 0;
    }
    
    /**
     * Agrega dinero al ahorro.
     * 
     * @param monto Cantidad a agregar
     */
    public void agregarAhorro(double monto) {
        this.ahorrado += monto;
    }
    
    /**
     * Calcula el progreso actual.
     * 
     * @return Porcentaje de progreso (0-100)
     */
    @Override
    public double calcularProgreso() {
        if (meta <= 0) return 0;
        return (ahorrado / meta) * 100;
    }
    
    // ==================== GETTERS ====================
    
    /** @return Nombre de la meta */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Meta total */
    @Override
    public double getMeta() {
        return meta;
    }
    
    /** @return Monto ahorrado */
    @Override
    public double getAhorrado() {
        return ahorrado;
    }
    
    /** @return Fecha limite */
    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }
    
    /**
     * Devuelve una representacion textual de la meta.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nombre + " - Meta: $" + meta + " - Ahorrado: $" + ahorrado + 
               " (" + String.format("%.1f", calcularProgreso()) + "%)" +
               " - Limite: " + fechaLimite.format(formatter);
    }
    
}//fin de la clase