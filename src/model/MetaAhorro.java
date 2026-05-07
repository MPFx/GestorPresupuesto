package model;

/**
 * Interfaz que define el comportamiento para metas de ahorro.
 * Las clases que implementen esta interfaz podran calcular
 * el progreso hacia una meta financiera.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see MetaAhorroImpl
 */
public interface MetaAhorro {
    
    /**
     * Calcula el progreso actual hacia la meta.
     * 
     * @return Progreso como porcentaje (0-100)
     */
    double calcularProgreso();
    
    /**
     * Obtiene la meta total a alcanzar.
     * 
     * @return Monto de la meta
     */
    double getMeta();
    
    /**
     * Obtiene el monto actual ahorrado.
     * 
     * @return Monto ahorrado
     */
    double getAhorrado();
    
}//fin de la interfaz