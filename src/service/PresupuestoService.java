package service;

import model.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase de servicio que gestiona las operaciones del presupuesto personal.
 * Permite agregar ingresos, gastos, metas de ahorro y mostrar resumenes.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see PresupuestoMensual
 * @see Ingreso
 * @see Gasto
 * @see MetaAhorroImpl
 */
public class PresupuestoService {
    
    // ==================== ATRIBUTOS ====================
    
    private PresupuestoMensual presupuesto;
    private ReporteService reporteService;
    
    /**
     * Constructor del servicio de presupuesto.
     * 
     * @param anio Año del presupuesto
     * @param mes Mes del presupuesto
     */
    public PresupuestoService(int anio, int mes) {
        this.presupuesto = new PresupuestoMensual(anio, mes);
        this.reporteService = new ReporteService();
    }
    
    /**
     * Agrega un ingreso al presupuesto.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void agregarIngreso(Scanner scanner) {
        System.out.println("\n=== AGREGAR INGRESO ===");
        
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Monto: $");
        double monto = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Fuente (Salario, Regalo, Venta, etc.): ");
        String fuente = scanner.nextLine();
        
        System.out.print("Categoria: ");
        String nombreCategoria = scanner.nextLine();
        Categoria categoria = new Categoria(nombreCategoria, 0);
        
        System.out.print("Año-Mes-Dia (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDateTime fecha = LocalDateTime.parse(fechaStr + "T00:00:00");
        
        Ingreso ingreso = new Ingreso(descripcion, monto, fecha, categoria, fuente);
        presupuesto.agregarIngreso(ingreso);
        
        System.out.println("✅ Ingreso agregado exitosamente!");
    }
    
    /**
     * Agrega un gasto al presupuesto.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void agregarGasto(Scanner scanner) {
        System.out.println("\n=== AGREGAR GASTO ===");
        
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Monto: $");
        double monto = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Categoria: ");
        String nombreCategoria = scanner.nextLine();
        System.out.print("Limite mensual para esta categoria: $");
        double limite = Double.parseDouble(scanner.nextLine());
        Categoria categoria = new Categoria(nombreCategoria, limite);
        
        System.out.print("¿Fue necesario? (S/N): ");
        boolean necesario = scanner.nextLine().equalsIgnoreCase("S");
        
        System.out.print("Año-Mes-Dia (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDateTime fecha = LocalDateTime.parse(fechaStr + "T00:00:00");
        
        Gasto gasto = new Gasto(descripcion, monto, fecha, categoria, necesario);
        presupuesto.agregarGasto(gasto);
        
        System.out.println("✅ Gasto agregado exitosamente!");
    }
    
    /**
     * Agrega una meta de ahorro.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void agregarMetaAhorro(Scanner scanner) {
        System.out.println("\n=== AGREGAR META DE AHORRO ===");
        
        System.out.print("Nombre de la meta (Vacaciones, Carro, etc.): ");
        String nombre = scanner.nextLine();
        
        System.out.print("Monto total a ahorrar: $");
        double meta = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Fecha limite (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDateTime fechaLimite = LocalDateTime.parse(fechaStr + "T00:00:00");
        
        System.out.print("Monto inicial ahorrado: $");
        double ahorrado = Double.parseDouble(scanner.nextLine());
        
        MetaAhorroImpl metaAhorro = new MetaAhorroImpl(nombre, meta, fechaLimite);
        metaAhorro.agregarAhorro(ahorrado);
        
        presupuesto.agregarMetaAhorro(metaAhorro);
        System.out.println("✅ Meta de ahorro agregada exitosamente!");
    }
    
    /**
     * Agrega dinero a una meta de ahorro existente.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void agregarAhorroMeta(Scanner scanner) {
        if (presupuesto.getMetasAhorro().isEmpty()) {
            System.out.println("No hay metas de ahorro registradas");
            return;
        }
        
        System.out.println("\n=== AGREGAR AHORRO A META ===");
        
        for (int i = 0; i < presupuesto.getMetasAhorro().size(); i++) {
            System.out.println((i + 1) + ". " + presupuesto.getMetasAhorro().get(i));
        }
        
        System.out.print("Seleccione meta: ");
        int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (seleccion >= 0 && seleccion < presupuesto.getMetasAhorro().size()) {
            System.out.print("Monto a agregar: $");
            double monto = Double.parseDouble(scanner.nextLine());
            
            MetaAhorroImpl meta = (MetaAhorroImpl) presupuesto.getMetasAhorro().get(seleccion);
            meta.agregarAhorro(monto);
            
            System.out.println("✅ Ahorro agregado!");
            System.out.println("Progreso actual: " + String.format("%.1f", meta.calcularProgreso()) + "%");
        } else {
            System.out.println("Seleccion invalida");
        }
    }
    
    /**
     * Muestra el resumen del presupuesto.
     */
    public void mostrarResumen() {
        System.out.println("\n=== RESUMEN DEL PRESUPUESTO ===");
        System.out.println(presupuesto);
        System.out.println("-".repeat(40));
        
        Map<String, Double> excedidos = presupuesto.verificarLimites();
        if (!excedidos.isEmpty()) {
            System.out.println("\n⚠️ ALERTAS DE LIMITES:");
            for (Map.Entry<String, Double> entry : excedidos.entrySet()) {
                System.out.printf("  %s: Excedido por $%.2f%n", entry.getKey(), entry.getValue());
            }
        }
        
        if (presupuesto.getBalance() < 0) {
            System.out.println("\n⚠️ ADVERTENCIA: Balance negativo!");
            System.out.println("   Recomendacion: Revisa tus gastos o busca aumentar ingresos");
        } else if (presupuesto.getBalance() > 0) {
            double ahorroPotencial = presupuesto.getBalance() * 0.5;
            System.out.printf("\n✅ Buen trabajo! Puedes ahorrar hasta $%.2f este mes%n", ahorroPotencial);
        }
        
        System.out.println("\n📊 GASTOS POR CATEGORIA:");
        Map<String, Double> gastos = presupuesto.getGastosPorCategoria();
        for (Map.Entry<String, Double> entry : gastos.entrySet()) {
            System.out.printf("  %-15s: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Muestra las metas de ahorro.
     */
    public void mostrarMetasAhorro() {
        System.out.println("\n=== METAS DE AHORRO ===");
        if (presupuesto.getMetasAhorro().isEmpty()) {
            System.out.println("No hay metas de ahorro registradas");
            return;
        }
        
        for (MetaAhorro meta : presupuesto.getMetasAhorro()) {
            System.out.println(meta);
        }
    }
    
    /**
     * Genera reportes del presupuesto.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void generarReporte(Scanner scanner) {
        System.out.println("\n=== GENERAR REPORTE ===");
        System.out.println("1. Reporte de texto");
        System.out.println("2. Reporte grafico");
        System.out.println("3. Reporte PDF (simulado)");
        System.out.print("Seleccione tipo de reporte: ");
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        reporteService.generarReporte(presupuesto, opcion);
    }
    
    /**
     * Obtiene el presupuesto actual.
     * 
     * @return Presupuesto mensual
     */
    public PresupuestoMensual getPresupuesto() {
        return presupuesto;
    }
    
}//fin de la clase