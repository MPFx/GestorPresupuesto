package model;

import java.time.LocalDateTime;

/**
 * Clase que representa un Ingreso (dinero que entra).
 * Hereda de Transaccion e incluye la fuente del ingreso.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Transaccion
 */
public class Ingreso extends Transaccion {
    
    // ==================== ATRIBUTOS ====================
    
    /** Fuente del ingreso (Salario, Regalo, Venta, etc.). */
    private String fuente;
    
    /**
     * Constructor para crear un ingreso.
     * 
     * @param descripcion Descripcion del ingreso
     * @param monto Monto del ingreso
     * @param fecha Fecha del ingreso
     * @param categoria Categoria del ingreso
     * @param fuente Fuente del ingreso
     */
    public Ingreso(String descripcion, double monto, LocalDateTime fecha, 
                   Categoria categoria, String fuente) {
        super(descripcion, monto, fecha, categoria);
        this.fuente = fuente;
    }
    
    /**
     * Obtiene el tipo de transaccion.
     * 
     * @return "INGRESO"
     */
    @Override
    public String getTipo() {
        return "INGRESO";
    }
    
    // ==================== GETTERS ====================
    
    /** @return Fuente del ingreso */
    public String getFuente() {
        return fuente;
    }
    
    /**
     * Devuelve una representacion textual del ingreso.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return super.toString() + " | INGRESO - Fuente: " + fuente;
    }
    
}//fin de la clase