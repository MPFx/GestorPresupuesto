package model;

import java.time.LocalDateTime;

/**
 * Clase que representa un Gasto (dinero que sale).
 * Hereda de Transaccion e indica si el gasto fue necesario.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Transaccion
 */
public class Gasto extends Transaccion {
    
    // ==================== ATRIBUTOS ====================
    
    /** Indica si el gasto fue necesario o fue un lujo. */
    private boolean esNecesario;
    
    /**
     * Constructor para crear un gasto.
     * 
     * @param descripcion Descripcion del gasto
     * @param monto Monto del gasto
     * @param fecha Fecha del gasto
     * @param categoria Categoria del gasto
     * @param esNecesario Si fue necesario
     */
    public Gasto(String descripcion, double monto, LocalDateTime fecha, 
                 Categoria categoria, boolean esNecesario) {
        super(descripcion, monto, fecha, categoria);
        this.esNecesario = esNecesario;
    }
    
    /**
     * Obtiene el tipo de transaccion.
     * 
     * @return "GASTO"
     */
    @Override
    public String getTipo() {
        return "GASTO";
    }
    
    // ==================== GETTERS ====================
    
    /** @return true si el gasto fue necesario */
    public boolean isEsNecesario() {
        return esNecesario;
    }
    
    /**
     * Devuelve una representacion textual del gasto.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return super.toString() + " | GASTO - Necesario: " + (esNecesario ? "Si" : "No");
    }
    
}//fin de la clase