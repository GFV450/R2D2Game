package juegor2d2;

/**
 * Clase CJuegoArturito
 * Esta clase ejecuta el juego. Contiene los atributos "_m" tipo Mundo, y "_j" tipo Jugador para ejecutar el juego.
 * 
 * @author Gian Franco Vitola
 * @version 2.00, 26/09/2015
 */
public class CJuegoArturito 
{
    private static CMundo _m;
    private static CJugador _j;
    
    public static void main(String[] args) 
    {
        _j = new CJugador(" ");
        _m = new CMundo();
        
        _m.ejecutar(_j);
    }
}
