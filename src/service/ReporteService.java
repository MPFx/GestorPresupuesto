package service;

import model.PresupuestoMensual;
import model.Reporte;
import model.ReporteTexto;
import model.ReporteGrafico;
import model.ReportePDF;

/**
 * Clase de servicio que gestiona la generacion de reportes.
 * Permite crear diferentes tipos de reportes a partir del presupuesto.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Reporte
 * @see PresupuestoMensual
 */
public class ReporteService {
    
    /**
     * Genera un reporte del tipo especificado.
     * 
     * @param presupuesto Presupuesto a reportar
     * @param tipo Tipo de reporte (1: Texto, 2: Grafico, 3: PDF)
     */
    public void generarReporte(PresupuestoMensual presupuesto, int tipo) {
        Reporte reporte = null;
        
        switch (tipo) {
            case 1 -> reporte = new ReporteTexto("Presupuesto " + presupuesto.getMes() + "/" + presupuesto.getAnio());
            case 2 -> reporte = new ReporteGrafico("Presupuesto " + presupuesto.getMes() + "/" + presupuesto.getAnio());
            case 3 -> reporte = new ReportePDF("Presupuesto " + presupuesto.getMes() + "/" + presupuesto.getAnio());
            default -> {
                System.out.println("Tipo de reporte invalido");
                return;
            }
        }
        
        System.out.println(reporte.generar(presupuesto));
        System.out.println("\n✅ Reporte generado exitosamente!");
    }
    
    /**
     * Exporta el reporte a un archivo (simulado).
     * 
     * @param contenido Contenido del reporte
     * @param nombreArchivo Nombre del archivo
     */
    public void exportarReporte(String contenido, String nombreArchivo) {
        System.out.println("\n📁 Exportando reporte a: " + nombreArchivo);
        System.out.println("✅ Reporte exportado exitosamente!");
    }
    
}//fin de la clase