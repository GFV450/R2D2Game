package juegor2d2;

/**
 * Clase CPosicion
 * Esta clase esta diseñada para ser contenida como un objeto instanciado dentro de la clase Casillas. De esta manera, se tendra referencia de la ubicacion de cada objeto con respecto a la
 * matriz, con la utilizacion de los metodos "Get". A su vez, se podra modificar la ubicacion de los objetos si es necesario con los metodos "Set".
 * 
 * @author Gian Franco Vitola
 * @version 1.00, 25/09/2015
 */
public class CPosicion
{
    private int _filas; //representa la ubicacion del objeto en relacion a las filas del tablero
    private int _columnas; //representa la ubicacion del objeto en relacion a las columnas del tablero
    
    public CPosicion(int x, int y)
    {
        _filas = x;
        _columnas = y;
    }
    
    public int getFilas()
    {
        return _filas;
    }
    
    public int getColumnas()
    {
        return _columnas;
    }
    
    public void setFilas(int filas) 
    {
        this._filas = filas;
    }

    public void setColumnas(int columnas) 
    {
        this._columnas = columnas;
    }
}