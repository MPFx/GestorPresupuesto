package model;

import java.util.Map;

/**
 * Clase que genera un reporte en formato grafico (ASCII).
 * Hereda de Reporte y produce una representacion grafica en consola.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Reporte
 * @see PresupuestoMensual
 */
public class ReporteGrafico extends Reporte {
    
    /**
     * Constructor para crear un reporte grafico.
     * 
     * @param titulo Titulo del reporte
     */
    public ReporteGrafico(String titulo) {
        super(titulo);
    }
    
    /**
     * Genera el reporte en formato grafico ASCII.
     * 
     * @param presupuesto Presupuesto a reportar
     * @return Reporte con graficos
     */
    @Override
    public String generar(PresupuestoMensual presupuesto) {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("\n").append("=".repeat(50)).append("\n");
        reporte.append("REPORTE GRAFICO - ").append(titulo).append("\n");
        reporte.append("=".repeat(50)).append("\n");
        
        double totalIngresos = presupuesto.getTotalIngresos();
        double totalGastos = presupuesto.getTotalGastos();
        double balance = presupuesto.getBalance();
        
        reporte.append("\n📊 RESUMEN GENERAL 📊\n");
        reporte.append(String.format("💰 Ingresos: $%.2f%n", totalIngresos));
        reporte.append(String.format("💸 Gastos:   $%.2f%n", totalGastos));
        reporte.append(String.format("⚖️ Balance:  $%.2f%n", balance));
        
        if (balance > 0) {
            reporte.append("✅ Balance POSITIVO - Sigue asi!\n");
        } else if (balance < 0) {
            reporte.append("⚠️ Balance NEGATIVO - Reduce gastos\n");
        } else {
            reporte.append("📌 Balance NEUTRO\n");
        }
        
        reporte.append("\n📊 DISTRIBUCION DE GASTOS 📊\n");
        Map<String, Double> gastos = presupuesto.getGastosPorCategoria();
        double maxGasto = gastos.values().stream().max(Double::compare).orElse(1.0);
        
        for (Map.Entry<String, Double> entry : gastos.entrySet()) {
            int barras = (int)((entry.getValue() / maxGasto) * 20);
            reporte.append(String.format("%-12s: $%-8.2f %s%n", 
                entry.getKey(), entry.getValue(), "█".repeat(barras)));
        }
        
        reporte.append("\n📊 BALANCE POR CATEGORIA 📊\n");
        Map<String, Double> ingresosCat = presupuesto.getIngresosPorCategoria();
        
        for (Map.Entry<String, Double> entry : ingresosCat.entrySet()) {
            String categoria = entry.getKey();
            double ingresos = entry.getValue();
            double gastosCat = gastos.getOrDefault(categoria, 0.0);
            double diff = ingresos - gastosCat;
            
            String simbolo = diff >= 0 ? "✅" : "❌";
            reporte.append(String.format("%s %-12s: +$%-8.2f -$%-8.2f = $%.2f%n", 
                simbolo, categoria, ingresos, gastosCat, diff));
        }
        
        reporte.append("\n🎯 METAS DE AHORRO 🎯\n");
        for (MetaAhorro meta : presupuesto.getMetasAhorro()) {
            double progreso = meta.calcularProgreso();
            int barras = (int)(progreso / 5);
            reporte.append(String.format("  %-15s: [%-20s] %.1f%%%n", 
                ((MetaAhorroImpl)meta).getNombre(), "█".repeat(barras), progreso));
        }
        
        reporte.append("=".repeat(50)).append("\n");
        
        return reporte.toString();
    }
    
    /**
     * Obtiene el tipo de reporte.
     * 
     * @return "GRAFICO"
     */
    @Override
    public String getTipoReporte() {
        return "GRAFICO";
    }
    
}//fin de la clase