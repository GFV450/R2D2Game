package juegor2d2;
import java.util.Scanner;

/**
 * Clase CArturito
 * Esta clase representa el robot Arturito. Contiene los atributos "_direccion" y "_bolsa", que representan en que _direccion se 
 * mueve Arturito, y cuantos Beepers tiene Arturito, respectivamente. Esta clase es una sub-clase de Casillas.
 * 
 * @author Gian Franco Vitola
 * @version 3.02, 11/10/2015
 */
public class CArturito extends CCasillas
{
    private int _direccion; //Direccion de los movimientos de Arturito: 0 = arriba - 1 = derecha - 2 = abajo - 3 = izquierda.
    private int _bolsa;
    
    public static Scanner leer = new Scanner(System.in);
    
    public CArturito(char simbolo, CPosicion coordenadas, int direccion, int bolsa) 
    {
        super(simbolo, coordenadas);
        this._direccion = direccion;
        this._bolsa = bolsa;
    }
    
    /**
    * Metodo inicio
    * Arturito es encendido, instanciando un objeto de Arturito en las coordenadas especificadas
    *
    * @param x es un parametro int que representa las filas del tablero.
    * @param y es un parametro int que representa las columnas del tablero.
    *
    * @return retorna un objeto Arturito para ser ubicado en el tablero, con las coordenadas especificadas.
    */
    public CArturito inicio(int x, int y)
    {
        System.out.println("Iniciando...");
        
        _coordenadas = new CPosicion(x, y); //Instancia el objeto Posicion con las coordenadas de Arturito
        CArturito art = new CArturito('A', _coordenadas, 0, 0); //Instancia el objeto CArturito a ubicar en el tablero inicial
        
        return art;
    }
    
    /**
    * Metodo mover
    * Arturito se mueve a traves el tablero, validando que no choque contra una pared.
    *
    * @param tablero es un parametro Casillas que representa el tablero en el cual Arturito se esta moviendo.
    * @param move es un parametro int que representa cuantos espacios se va a mover Arturito.
    * @param fin es un parametro int para definir si Arturito debe avanzar una casilla mas o no
    */
    public void mover(CCasillas tablero[][], int move, int fin)
    {
        boolean choque = false; //boolean para validar si Arturito se movera una casilla o no
        
            if(move != fin) //condicion para evitar que se ejecute el metodo "chocar" una vez que Arturito haya terminado de moverse
                choque = chocar(tablero); //recibe el boolean "choque" que determinara si Arturito se movera una casilla o no
            
            if( (choque == false) && (move != fin) ) //condicion para avanzar una casilla si Arturito no choca
            {
                if(_direccion == 0)
                    _coordenadas.setFilas(_coordenadas.getFilas() - 1); //Arturito de mueve hacia arriba un espacio
                else if(_direccion == 1)
                    _coordenadas.setColumnas(_coordenadas.getColumnas() + 1); //Arturito se mueve hacia la derecha un espacio
                else if(_direccion == 2)
                    _coordenadas.setFilas(_coordenadas.getFilas() + 1); //Arturito se mueve hacia abajo un espacio
                else if(_direccion == 3)
                    _coordenadas.setColumnas(_coordenadas.getColumnas() - 1); //Arturito se mueve hacia la izquierda un espacio
                
                mover(tablero, move, fin + 1); //vuelve a ejecutar el metodo para avanzar otra casilla
            }
            else
            {
                System.out.println("Acelerando...");
                tomarBeeper(tablero); //metodo para que Arturito tome un beeper si se encuentran en la misma casilla.
            }
    }
    
    /**
    * Metodo chocar
    * Valida si Arturito choca con una pared al moverse en el tablero. 
    * 
    * @param tablero es un parametro Casillas que representa el tablero en el cual Arturito se esta moviendo.
    * 
    * @return retorna un Boolean en caso de que Arturito choque contra una pared, de manera que no siga tratando de avanzar 
    * casillas chocando multiples veces.
    */
    public boolean chocar(CCasillas tablero[][])
    {
        boolean choque = false; //boolean para validar si Arturito chocara contra una pared al moverse o no
        
        /* Condicion para validar si Arturito se saldra del tablero de juego al moverse en una direccion especifica. 
           e.g: si direccion = 0 (mover hacia arriba), y Arturito se encuentra en la casilla [0][0], al restar 1 en las filas
           se validara que Arturito ha salido de los limites de la matriz (debido a que las filas tendran un valor menor a cero)
        */
        if( ( (_direccion == 0) && ((_coordenadas.getFilas() - 1) < 0) )    || 
            ( (_direccion == 1) && ((_coordenadas.getColumnas() + 1) > 7) ) || 
            ( (_direccion == 2) && ((_coordenadas.getFilas() + 1) > 7) )    || 
            ( (_direccion == 3) && ((_coordenadas.getColumnas() - 1) < 0) ) )
        {
            System.out.println("Haz chocado con una pared!");
            choque = true;
        }
        /* Condicion para validar si Arturito chocara contra un objeto de tipo Paredes al moverse en una direccion especifica
           e.g: si direccion = 1 (mover hacia la derecha), y Arturito se encuentra en la casilla [2][0], se suma 1 en las 
           columnas para validar si en la casilla [2][1] hay una pared. 
        */
        else if( (_direccion == 0) && (tablero[_coordenadas.getFilas() - 1][_coordenadas.getColumnas()] instanceof CParedes) || 
                 (_direccion == 1) && (tablero[_coordenadas.getFilas()][_coordenadas.getColumnas() + 1] instanceof CParedes) ||
                 (_direccion == 2) && (tablero[_coordenadas.getFilas() + 1][_coordenadas.getColumnas()] instanceof CParedes) ||
                 (_direccion == 3) && (tablero[_coordenadas.getFilas()][_coordenadas.getColumnas() - 1] instanceof CParedes) )
        {
            System.out.println("Haz chocado con una pared!");
            choque = true;
        }
        
        return choque; //devuelve el boolean "choque" para determinar si Arturito chocara al moverse
    }
    
    /**
    * Metodo girarIzq
    * Modifica el atributo _direccion, rotando a Arturito hacia la izquierda.
    */
    public void girarIzq()
    {
        System.out.println("Girando...");
        
        //condicion para que al restar no convierta el valor de _direccion a -1, confinando los movimientos del 0 al 3
        if(_direccion == 0)
            _direccion = 3;
        else
            _direccion--;
    }
    
    /**
    * Metodo girarDer
    * Modifica el atributo _direccion, rotando a Arturito hacia la derecha.
    */
    public void girarDer()
    {
        System.out.println("Girando...");
        
        //condicion para que al sumar no convierta el valor de _direccion a 4, confinando los movimientos del 0 al 3
        if(_direccion == 3)
            _direccion = 0;
        else
            _direccion++;
    }
    
    /**
    * Metodo tomarBeeper
    * Arturito toma un Beeper cuando se encuentra en una casilla que lo contenga. 
    * 
    * @param tablero es un parametro Casillas que representa el tablero en el cual Arturito se esta moviendo.
    * @param s es un parametro int que representa la opcion del menu que ha sido seleccionada.
    */
    public void tomarBeeper(CCasillas tablero[][])
    {
        //condicion para validar si Arturito se encuentra en una casilla con un beeper.
        if(tablero[_coordenadas.getFilas()][_coordenadas.getColumnas()] instanceof CBeepers)
        {
            System.out.println("Has tomado un Beeper!");
            _bolsa = _bolsa + 1; //suma un beeper a la bolsa de Arturito
        }
    }
    
    /**
    * Metodo dejarBeeper
    * Arturito deja un Beeper en la casilla que se encuentra actualmente. A su vez, Arturito se mueve de la misma (debido a 
    * que no pueden haber dos objetos en una misma casilla).
    * 
    * @param tablero es un parametro Casillas que representa el tablero en el cual Arturito se esta moviendo.
    */
    public void dejarBeeper(CCasillas tablero[][])
    {
        int s = 0, move = 0;
        
        System.out.println("Moverse en direccion actual = 1 / Girar a la derecha = 2 / Girar a la izquierda = 3");
        s = leer.nextInt();
                        
        switch(s)
        {
            case 2: girarDer();
                    break;
            case 3: girarIzq();
                    break;
        }
                    
        System.out.println("Cuantos espacios desea moverse?");
        move = leer.nextInt();
            
        //instancia un objeto Posicion para la construccion del objeto Beepers que reemplazara a Arturito en la casilla actual
        CPosicion pos = new CPosicion(_coordenadas.getFilas(), _coordenadas.getColumnas() ); 
        CBeepers beep = new CBeepers('B', pos); //crea un beeper con las coordenadas actuales de Arturito
            
        tablero[beep.getCoordenadas().getFilas()][beep.getCoordenadas().getColumnas()] = beep; //coloca un beeper en la casilla donde se encuentra Arturito actualmente
        mover(tablero, move, 0); //mueve a Arturito a su nueva casilla.
            
        //condicion en caso de que Arturito choque contra una pared y no pueda moverse de casilla
        if(tablero[beep.getCoordenadas().getFilas()][beep.getCoordenadas().getColumnas()] == 
           tablero[_coordenadas.getFilas()][_coordenadas.getColumnas()])
        {
            System.out.println("Sigues en la misma casilla! Debes moverte para dejar el beeper!");
            dejarBeeper(tablero); //llamada recursiva en caso de no haberse movido de casilla
        }
        else
            _bolsa--; //resta un beeper a la bolsa de Arturito
    }
    
    /**
    * Metodo apagar
    * Arturito se apaga, terminando el juego.
    */
    public boolean apagar()
    {
        boolean fin = true;
        
        return fin; //devuelve el boolean "fin" para salir del ciclo que mantiene el juego funcionando
    }
    
    public int getDireccion() 
    {
        return _direccion;
    }

    public void setDireccion(int direccion) 
    {
        this._direccion = direccion;
    }

    public int getBolsa() 
    {
        return _bolsa;
    }

    public void setBolsa(int _bolsa) 
    {
        this._bolsa = _bolsa;
    }
    
    
}
