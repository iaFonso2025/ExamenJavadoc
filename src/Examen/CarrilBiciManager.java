package Examen;
/**
 * Nos encontramos ante una clae en la cual nos permite crear recorridos de ciclismo, nos permite:
 * Añadir tramos
 * Actualizar el estado de un tramo
 * Cambiar el estado de un tramo
 * Consultar el estado de un tramo
 * Generar un informe
 * Obtener tramos
 * Obtener la longitud del tramo
 * 
 * @author alfonso
 * @version 1.0
 * 
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CarrilBiciManager {

	/**
	 * Creamos los atributos:
	 *tramos -> Mapa(String, Double)
	 *estadoTramos -> Mapa(String, String
	 */
    private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
    private final Map<String, String> estadoTramos; // nombre del tramo -> estado

    /**
     * Creación del contructor por defecto
     */
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }

    /**
     * Este método recibe dos parámetros 'nombre' y 'longitud' de los cualos, crea un tramo con
     * esos atributos
     * @param nombre Recibe una cadena de caracteres y la utiliza como nombre del tramo
     * @param longitud Recibe un númeroo decimal (Double) y la utiliza como longitud del tramo
     * 
     * @exception IllegalArgumentException Excepción que se genera al dejar el nombre o la longitud vacios
     * los cuales capturamos
     */
    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }

    /**
     * Este método recibe el nombre de un tramo el cual comprueba si existe, en caso afirmativo
     * cambia el estado del tramo al que recibe el método, en caso negativo lanza una excepción
     * 
     * @param nombre Recibe una cadena de caracteres y la utiliza para identificar si existe un tramo
     * con ese nombre
     * @param nuevoEstado Recibe una cadena de caracteres que utiliza para actualizar el estado
     * 
     * @exception NoSuchElementException Excepción que se genera al introducir un tramo inexistente
     * la cual capturamos 
     */
    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }
    /**
     * Este método recibe dos cadenas de caracteres, y llama al método anterior 'actualizarEstado' y le
     * la variable 'nombre' y 'estado
     * @param nombre Recibe una cadena de caracteres y la utiliza para identificar si existe un tramo
     * con ese nombre
     * @param estado Recibe una cadena de caracteres que utiliza para Cambiar el estado
     */
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }

    /**
     * Este método nos devuelve el estado del tramo que le pasamos, en caso de no encontrar ningun
     * tramo con ese nombre, dara una excepción
     * 
     * @param nombre Recibe una cadena de caracteres la cual utiliza para identificar el tramo
     * @return Devuelve un String 'estadoTramos'
     */
    public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }

    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }

    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
}
