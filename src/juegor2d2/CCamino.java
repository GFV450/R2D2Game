package juegor2d2;

/**
 * Clase CCamino
 * Esta clase representa las casillas del tablero que no contienen paredes, beepers, o al mismo Arturito. Esta es una sub-clase de Casillas.
 * 
 * @author Gian Franco Vitola
 * @version 1.00, 29/09/2015
 */
public class CCamino extends CCasillas
{
    public CCamino(char simbolo, CPosicion coordenadas)
    {
        super(simbolo, coordenadas);
    }
    
}
