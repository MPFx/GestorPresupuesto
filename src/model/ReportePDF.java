package model;

import java.util.Map;

/**
 * Clase que simula la generacion de un reporte en formato PDF.
 * Hereda de Reporte y produce una representacion simulada de PDF.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Reporte
 * @see PresupuestoMensual
 */
public class ReportePDF extends Reporte {
    
    /**
     * Constructor para crear un reporte PDF simulado.
     * 
     * @param titulo Titulo del reporte
     */
    public ReportePDF(String titulo) {
        super(titulo);
    }
    
    /**
     * Genera un reporte simulado en formato PDF.
     * 
     * @param presupuesto Presupuesto a reportar
     * @return Contenido simulado del PDF
     */
    @Override
    public String generar(PresupuestoMensual presupuesto) {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("\n").append("=".repeat(60)).append("\n");
        reporte.append("                    REPORTE PDF SIMULADO\n");
        reporte.append("=".repeat(60)).append("\n");
        reporte.append("Titulo: ").append(titulo).append("\n");
        reporte.append("Fecha generacion: ").append(fechaGeneracion).append("\n");
        reporte.append("=".repeat(60)).append("\n\n");
        
        reporte.append("RESUMEN FINANCIERO\n");
        reporte.append("-".repeat(40)).append("\n");
        reporte.append(String.format("Total Ingresos: $%.2f%n", presupuesto.getTotalIngresos()));
        reporte.append(String.format("Total Gastos:   $%.2f%n", presupuesto.getTotalGastos()));
        reporte.append(String.format("Balance:        $%.2f%n", presupuesto.getBalance()));
        
        reporte.append("\nDETALLE DE GASTOS POR CATEGORIA\n");
        reporte.append("-".repeat(40)).append("\n");
        Map<String, Double> gastos = presupuesto.getGastosPorCategoria();
        for (Map.Entry<String, Double> entry : gastos.entrySet()) {
            reporte.append(String.format("%-15s: $%.2f%n", entry.getKey(), entry.getValue()));
        }
        
        reporte.append("\nALERTAS DE LIMITES\n");
        reporte.append("-".repeat(40)).append("\n");
        Map<String, Double> excedidos = presupuesto.verificarLimites();
        if (excedidos.isEmpty()) {
            reporte.append("Todas las categorias dentro del limite\n");
        } else {
            for (Map.Entry<String, Double> entry : excedidos.entrySet()) {
                reporte.append(String.format("%s: Excedido por $%.2f%n", 
                    entry.getKey(), entry.getValue()));
            }
        }
        
        reporte.append("\nMETAS DE AHORRO\n");
        reporte.append("-".repeat(40)).append("\n");
        for (MetaAhorro meta : presupuesto.getMetasAhorro()) {
            reporte.append(meta).append("\n");
        }
        
        reporte.append("\n").append("=".repeat(60)).append("\n");
        reporte.append("Este es un PDF SIMULADO - Para un PDF real, se necesitaria\n");
        reporte.append("una biblioteca como iText o Apache PDFBox\n");
        reporte.append("=".repeat(60)).append("\n");
        
        return reporte.toString();
    }
    
    /**
     * Obtiene el tipo de reporte.
     * 
     * @return "PDF"
     */
    @Override
    public String getTipoReporte() {
        return "PDF";
    }
    
}//fin de la clase