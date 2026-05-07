package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa el presupuesto mensual de una persona.
 * Contiene ingresos, gastos y metas de ahorro para un mes especifico.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Ingreso
 * @see Gasto
 * @see MetaAhorro
 */
public class PresupuestoMensual {
    
    // ==================== ATRIBUTOS ====================
    
    /** Año del presupuesto. */
    private int anio;
    
    /** Mes del presupuesto (1-12). */
    private int mes;
    
    /** Lista de ingresos del mes. */
    private List<Ingreso> ingresos;
    
    /** Lista de gastos del mes. */
    private List<Gasto> gastos;
    
    /** Lista de metas de ahorro. */
    private List<MetaAhorro> metasAhorro;
    
    /**
     * Constructor para crear un presupuesto mensual.
     * 
     * @param anio Año del presupuesto
     * @param mes Mes del presupuesto (1-12)
     */
    public PresupuestoMensual(int anio, int mes) {
        this.anio = anio;
        this.mes = mes;
        this.ingresos = new ArrayList<>();
        this.gastos = new ArrayList<>();
        this.metasAhorro = new ArrayList<>();
    }
    
    /**
     * Agrega un ingreso al presupuesto.
     * 
     * @param ingreso Ingreso a agregar
     */
    public void agregarIngreso(Ingreso ingreso) {
        ingresos.add(ingreso);
    }
    
    /**
     * Agrega un gasto al presupuesto.
     * 
     * @param gasto Gasto a agregar
     */
    public void agregarGasto(Gasto gasto) {
        gastos.add(gasto);
    }
    
    /**
     * Agrega una meta de ahorro.
     * 
     * @param meta Meta de ahorro
     */
    public void agregarMetaAhorro(MetaAhorro meta) {
        metasAhorro.add(meta);
    }
    
    /**
     * Calcula el total de ingresos del mes.
     * 
     * @return Suma de todos los ingresos
     */
    public double getTotalIngresos() {
        return ingresos.stream().mapToDouble(Ingreso::getMonto).sum();
    }
    
    /**
     * Calcula el total de gastos del mes.
     * 
     * @return Suma de todos los gastos
     */
    public double getTotalGastos() {
        return gastos.stream().mapToDouble(Gasto::getMonto).sum();
    }
    
    /**
     * Calcula el balance del mes (ingresos - gastos).
     * 
     * @return Balance del mes
     */
    public double getBalance() {
        return getTotalIngresos() - getTotalGastos();
    }
    
    /**
     * Obtiene los gastos agrupados por categoria.
     * 
     * @return Mapa con categoria y total gastado
     */
    public Map<String, Double> getGastosPorCategoria() {
        Map<String, Double> gastosPorCategoria = new HashMap<>();
        
        for (Gasto g : gastos) {
            String categoria = g.getCategoria().getNombre();
            gastosPorCategoria.put(categoria, 
                gastosPorCategoria.getOrDefault(categoria, 0.0) + g.getMonto());
        }
        
        return gastosPorCategoria;
    }
    
    /**
     * Obtiene los ingresos agrupados por categoria.
     * 
     * @return Mapa con categoria y total ingresado
     */
    public Map<String, Double> getIngresosPorCategoria() {
        Map<String, Double> ingresosPorCategoria = new HashMap<>();
        
        for (Ingreso i : ingresos) {
            String categoria = i.getCategoria().getNombre();
            ingresosPorCategoria.put(categoria, 
                ingresosPorCategoria.getOrDefault(categoria, 0.0) + i.getMonto());
        }
        
        return ingresosPorCategoria;
    }
    
    /**
     * Verifica si alguna categoria ha excedido su limite.
     * 
     * @return Lista de categorias que excedieron el limite
     */
    public Map<String, Double> verificarLimites() {
        Map<String, Double> excedidos = new HashMap<>();
        Map<String, Double> gastosPorCategoria = getGastosPorCategoria();
        
        for (Gasto g : gastos) {
            String categoria = g.getCategoria().getNombre();
            double limite = g.getCategoria().getLimiteMensual();
            double gastado = gastosPorCategoria.getOrDefault(categoria, 0.0);
            
            if (gastado > limite) {
                excedidos.put(categoria, gastado - limite);
            }
        }
        
        return excedidos;
    }
    
    // ==================== GETTERS ====================
    
    /** @return Año del presupuesto */
    public int getAnio() {
        return anio;
    }
    
    /** @return Mes del presupuesto */
    public int getMes() {
        return mes;
    }
    
    /** @return Lista de ingresos */
    public List<Ingreso> getIngresos() {
        return ingresos;
    }
    
    /** @return Lista de gastos */
    public List<Gasto> getGastos() {
        return gastos;
    }
    
    /** @return Lista de metas de ahorro */
    public List<MetaAhorro> getMetasAhorro() {
        return metasAhorro;
    }
    
    /**
     * Devuelve una representacion textual del presupuesto.
     * 
     * @return Cadena con resumen
     */
    @Override
    public String toString() {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return "Presupuesto " + meses[mes - 1] + " " + anio + 
               " - Ingresos: $" + getTotalIngresos() + 
               " - Gastos: $" + getTotalGastos() +
               " - Balance: $" + getBalance();
    }
    
}//fin de la clase