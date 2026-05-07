package model;

/**
 * Clase que representa una categoria de transaccion.
 * Permite clasificar ingresos y gastos por tipo (Comida, Transporte, etc.)
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Transaccion
 */
public class Categoria {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico de la categoria. */
    private int idCategoria;
    
    /** Nombre de la categoria. */
    private String nombre;
    
    /** Descripcion de la categoria. */
    private String descripcion;
    
    /** Limite mensual para esta categoria (solo gastos). */
    private double limiteMensual;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear una categoria.
     * 
     * @param nombre Nombre de la categoria
     * @param limiteMensual Limite mensual
     */
    public Categoria(String nombre, double limiteMensual) {
        this.idCategoria = contadorIds++;
        this.nombre = nombre;
        this.limiteMensual = limiteMensual;
        this.descripcion = "";
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador de la categoria */
    public int getIdCategoria() {
        return idCategoria;
    }
    
    /** @return Nombre de la categoria */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Descripcion de la categoria */
    public String getDescripcion() {
        return descripcion;
    }
    
    /** @return Limite mensual */
    public double getLimiteMensual() {
        return limiteMensual;
    }
    
    // ==================== SETTERS ====================
    
    /** @param descripcion Nueva descripcion */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /** @param limiteMensual Nuevo limite mensual */
    public void setLimiteMensual(double limiteMensual) {
        this.limiteMensual = limiteMensual;
    }
    
    /**
     * Devuelve una representacion textual de la categoria.
     * 
     * @return Nombre de la categoria
     */
    @Override
    public String toString() {
        return nombre + " (Limite: $" + limiteMensual + ")";
    }
    
}//fin de la clase