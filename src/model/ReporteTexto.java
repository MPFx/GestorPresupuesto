package model;

import java.util.Map;

/**
 * Clase que genera un reporte en formato texto.
 * Hereda de Reporte y produce una representacion textual del presupuesto.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Reporte
 * @see PresupuestoMensual
 */
public class ReporteTexto extends Reporte {
    
    /**
     * Constructor para crear un reporte de texto.
     * 
     * @param titulo Titulo del reporte
     */
    public ReporteTexto(String titulo) {
        super(titulo);
    }
    
    /**
     * Genera el reporte en formato texto.
     * 
     * @param presupuesto Presupuesto a reportar
     * @return Reporte formateado
     */
    @Override
    public String generar(PresupuestoMensual presupuesto) {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("\n").append("=".repeat(50)).append("\n");
        reporte.append("REPORTE DE PRESUPUESTO - ").append(titulo).append("\n");
        reporte.append("=".repeat(50)).append("\n");
        reporte.append(presupuesto).append("\n");
        reporte.append("-".repeat(50)).append("\n");
        
        reporte.append("\n--- INGRESOS POR CATEGORIA ---\n");
        Map<String, Double> ingresos = presupuesto.getIngresosPorCategoria();
        for (Map.Entry<String, Double> entry : ingresos.entrySet()) {
            reporte.append(String.format("  %-15s: $%.2f%n", entry.getKey(), entry.getValue()));
        }
        
        reporte.append("\n--- GASTOS POR CATEGORIA ---\n");
        Map<String, Double> gastos = presupuesto.getGastosPorCategoria();
        for (Map.Entry<String, Double> entry : gastos.entrySet()) {
            reporte.append(String.format("  %-15s: $%.2f%n", entry.getKey(), entry.getValue()));
        }
        
        reporte.append("\n--- ALERTAS DE LIMITES ---\n");
        Map<String, Double> excedidos = presupuesto.verificarLimites();
        if (excedidos.isEmpty()) {
            reporte.append("  No hay categorias que excedan su limite\n");
        } else {
            for (Map.Entry<String, Double> entry : excedidos.entrySet()) {
                reporte.append(String.format("  %-15s: Excedido por $%.2f%n", 
                    entry.getKey(), entry.getValue()));
            }
        }
        
        reporte.append("\n--- METAS DE AHORRO ---\n");
        for (MetaAhorro meta : presupuesto.getMetasAhorro()) {
            reporte.append("  ").append(meta).append("\n");
        }
        
        reporte.append("=".repeat(50)).append("\n");
        reporte.append("Reporte generado: ").append(fechaGeneracion).append("\n");
        
        return reporte.toString();
    }
    
    /**
     * Obtiene el tipo de reporte.
     * 
     * @return "TEXTO"
     */
    @Override
    public String getTipoReporte() {
        return "TEXTO";
    }
    
}//fin de la clase