package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta que representa un reporte financiero.
 * Contiene los atributos y comportamientos comunes a todos los reportes
 * (Texto, Gráfico, PDF).
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ReporteTexto
 * @see ReporteGrafico
 * @see ReportePDF
 */
public abstract class Reporte {
    
    // ==================== ATRIBUTOS ====================
    
    /** Titulo del reporte. */
    protected String titulo;
    
    /** Fecha de generacion del reporte. */
    protected LocalDateTime fechaGeneracion;
    
    /**
     * Constructor para crear un reporte.
     * 
     * @param titulo Titulo del reporte
     */
    public Reporte(String titulo) {
        this.titulo = titulo;
        this.fechaGeneracion = LocalDateTime.now();
    }
    
    /**
     * Genera el contenido del reporte a partir del presupuesto.
     * Metodo abstracto implementado por las subclases.
     * 
     * @param presupuesto Presupuesto a reportar
     * @return Contenido del reporte
     */
    public abstract String generar(PresupuestoMensual presupuesto);
    
    /**
     * Obtiene el tipo de reporte.
     * Metodo abstracto implementado por las subclases.
     * 
     * @return Tipo de reporte
     */
    public abstract String getTipoReporte();
    
    // ==================== GETTERS ====================
    
    /** @return Titulo del reporte */
    public String getTitulo() {
        return titulo;
    }
    
    /** @return Fecha de generacion */
    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }
    
    /**
     * Devuelve informacion basica del reporte.
     * 
     * @return Cadena con titulo y fecha
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return getTipoReporte() + " - " + titulo + " - " + fechaGeneracion.format(formatter);
    }
    
}//fin de la clase