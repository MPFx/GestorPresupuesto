package principal;

import ui.MenuConsola;

/**
 * Clase principal que contiene el punto de entrada del Gestor de Presupuesto Personal.
 * Inicia la aplicacion y muestra el menu de consola al usuario.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see MenuConsola
 */
public class Main {
    
    /**
     * Metodo principal que inicia el gestor de presupuesto.
     * Muestra mensaje de bienvenida y crea una instancia del menu de consola.
     * 
     * @param args Argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BIENVENIDO AL GESTOR DE");
        System.out.println("     PRESUPUESTO PERSONAL");
        System.out.println("========================================");
        System.out.println("Controla tus finanzas, establece metas");
        System.out.println("y genera reportes de tus ingresos y gastos.");
        System.out.println("========================================\n");
        
        MenuConsola menu = new MenuConsola();
        menu.iniciar();
    }
    
}//fin de la clase