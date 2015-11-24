package juegor2d2;

/**
 * Clase CCasillas
 * Esta clase representa todo lo que pueden contener las casillas del tablero. Por esta razon, hereda los atributos "_simbolo" y "_coordenadas" a multiples sub-clases, identificando eficientemente
 que objeto hay en cada espacio de la matriz, y donde esta ubicado el mismo.
 * 
 * @author Gian Franco Vitola
 * @version 1.00, 25/09/2015
 */
public class CCasillas
{
    protected char _simbolo; //representa el simbolo del objeto que se encuentre en la matriz
    protected CPosicion _coordenadas; //guarda las coordenadas de donde se encuentra el objeto actualmente en la matriz

    public CCasillas(char simbolo, CPosicion coordenadas) 
    {
        this._simbolo = simbolo;
        this._coordenadas = coordenadas;
    }
    
    public char getSimbolo() {
        return _simbolo;
    }

    public void setSimbolo(char simbolo) {
        this._simbolo = simbolo;
    }
    
    public CPosicion getCoordenadas() {
        return _coordenadas;
    }

    public void setCoordenadas(CPosicion coordenadas) {
        this._coordenadas = coordenadas;
    }
}
