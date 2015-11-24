package juegor2d2;

/**
 * Clase CJugador
 * Esta clase representa al usuario que maneja a Arturito. Contiene un atributo "_nombre", que representa el _nombre del usuario.
 * 
 * @author Gian Franco Vitola
 * @version 1.00, 01/10/2015
 */
public class CJugador 
{
    String _nombre; //representa el nombre del usuario.
    
    public CJugador(String nombre) 
    {
        this._nombre = nombre;
    }

    public String getNombre() 
    {
        return _nombre;
    }

    public void setNombre(String nombre) 
    {
        this._nombre = nombre;
    }
}
