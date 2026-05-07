package ui;

import service.PresupuestoService;
import java.util.Scanner;

/**
 * Clase que implementa la interfaz de usuario por consola para el Gestor de Presupuesto.
 * Gestiona la interaccion con el usuario, muestra los menus y procesa las opciones.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see PresupuestoService
 */
public class MenuConsola {
    
    // ==================== ATRIBUTOS ====================
    
    private Scanner scanner;
    private PresupuestoService presupuestoService;
    private int anio;
    private int mes;
    
    /**
     * Constructor del menu de consola.
     * Inicializa el scanner y el servicio de presupuesto.
     */
    public MenuConsola() {
        this.scanner = new Scanner(System.in);
        configurarMes();
        this.presupuestoService = new PresupuestoService(anio, mes);
    }
    
    /**
     * Configura el mes y año del presupuesto.
     */
    private void configurarMes() {
        System.out.println("\n=== CONFIGURAR PRESUPUESTO ===");
        System.out.print("Año: ");
        anio = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Mes (1-12): ");
        mes = Integer.parseInt(scanner.nextLine());
        
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        System.out.println("Presupuesto configurado para: " + meses[mes - 1] + " " + anio);
    }
    
    /**
     * Inicia el bucle principal del menu.
     */
    public void iniciar() {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1 -> presupuestoService.agregarIngreso(scanner);
                case 2 -> presupuestoService.agregarGasto(scanner);
                case 3 -> presupuestoService.agregarMetaAhorro(scanner);
                case 4 -> presupuestoService.agregarAhorroMeta(scanner);
                case 5 -> presupuestoService.mostrarResumen();
                case 6 -> presupuestoService.mostrarMetasAhorro();
                case 7 -> presupuestoService.generarReporte(scanner);
                case 8 -> {
                    System.out.println("\n¡Gracias por usar el Gestor de Presupuesto!");
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                }
                default -> System.out.println("Opcion no valida");
            }
            
            if (!salir) {
                pausa();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menu principal del sistema.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("     GESTOR DE PRESUPUESTO PERSONAL");
        System.out.println("========================================");
        System.out.println("1. Agregar ingreso");
        System.out.println("2. Agregar gasto");
        System.out.println("3. Agregar meta de ahorro");
        System.out.println("4. Agregar ahorro a meta");
        System.out.println("5. Ver resumen");
        System.out.println("6. Ver metas de ahorro");
        System.out.println("7. Generar reporte");
        System.out.println("8. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }
    
    /**
     * Lee y valida la opcion ingresada por el usuario.
     * 
     * @return Numero entero de la opcion seleccionada, o 0 si no es valida
     */
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Pausa la ejecucion hasta que el usuario presione Enter.
     */
    private void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
}//fin de la clase