package juegor2d2;

/**
 * Clase CBeepers
 * Esta clase representa los beepers con los que Arturito podra interactuar en el camino, recogiendolos de las casillas donde se encuentren, o dejandolos en 
 * las casillas que Arturito desee. Esta es una sub-clase de Casillas.
 * 
 * @author Gian Franco Vitola
 * @version 2.00, 26/09/2015
 */
public class CBeepers extends CCasillas
{
    public CBeepers(char simbolo, CPosicion coordenadas)
    {
        super(simbolo, coordenadas);
    }
    
}