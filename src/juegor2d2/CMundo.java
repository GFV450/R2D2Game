package juegor2d2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase CMundo
 * Esta clase representa el mundo de Arturito. Por esta razon, se encarga de los tableros y de todos los objetos que estos contiene. Por otra parte, contiene el
 * metodo a ser llamado desde el main para ejecutar el juego.
 * 
 * @author Gian Franco Vitola
 * @version 3.02, 11/10/2015
 */
public class CMundo 
{
    private CCasillas[][] _tableroInicial;
    private CCasillas[][] _tableroFinal;
    
    public static Scanner leer = new Scanner(System.in);
    
    /**
    * Metodo convertirPosicion
    * Recibe las coordenadas de los objetos que se encuentran en el archivo de texto (Paredes, Beepers y Arturito), y los 
    * convierte en un objeto de tipo Posicion, retornando el mismo al metodo del que haya sido convocado.
    * 
    * @param linea representa la linea de texto que contiene las coordenadas
    * @param x representa las filas de las coordenadas a determinar, contenidas en la variable "linea"
    * @param y representa las columnas de las coordenadas a determinar, contenidas en la variable "linea"
    * 
    * @return retorna un objeto de tipo Posicion con las coordenadas extraidas
    */
    public CPosicion convertirPosicion(int x, int y, String linea)
    {
        char numeroChar; //variable para tomar caracteres especificos de la linea (extraida del archivo de texto) con las coordenadas del objeto
        
        numeroChar = linea.charAt(x); //extrae el numero de la fila en la que se encuentra el objeto como caracter (char)
        x = Character.getNumericValue(numeroChar); //convierte el caracter extraido en un entero (int)
        numeroChar = linea.charAt(y); //extrae el numero de la columna en la que se encuentra el objeto como caracter (char)
        y = Character.getNumericValue(numeroChar); //convierte el caracter extraido en un entero (int)
        
        CPosicion aux = new CPosicion(x, y); //instancia un objeto Posicion con las coordenadas del objeto
        
        return aux; //devuelve las coordenadas del objeto
    }
    
    /**
    * Metodo llenarTableros
    * Los tableros son llenados con la informacion del archivo "mundos.txt". Se ubican objetos de tipo paredes, beepers y a 
    * Arturito, tanto en el tablero inicial como en el tablero final. Las casillas vacias contienen un objeto tipo camino.
    * 
    * @param artInicial parametro de tipo Arturito, con las coordenadas de Arturito en el tablero inicial.
    */
    public void llenarTableros(CArturito artInicial)
    {
        int i = 0; //variable para determinar la linea a ser extraida del archivo de texto 
        String linea; //guarda la informacion de la linea con las coordenadas del objeto
        CPosicion aux;
        
        _tableroInicial = new CCasillas[8][8];
        _tableroFinal = new CCasillas[8][8];
        
        try //bloque para leer el archivo de texto a traves de un FileReader y un BufferedReader
        {
            FileReader fr = new FileReader("mundos.txt"); //instancia un objeto FileReader usando el archivo "mundos.txt" como parametro
            BufferedReader br = new BufferedReader(fr); //instancia un objeto BufferedReader usando el objeto "fr" creado anteriormente como parametro
            
            //condicion para leer el archivo y guardar el contenido de la linea siendo leida en la variable "linea"
            while( (linea = br.readLine() ) != null)
            {    
                if(i < 12) //condicion para obtener las coordenadas de las paredes
                {
                    aux = convertirPosicion(6, 8, linea); //devuelve las coordenadas del objeto Paredes a instanciar
                    
                    CParedes par = new CParedes('#', aux); //instancia un objeto Paredes
                    
                    //ubica el objeto Paredes en la matriz inicial/final segun sus coordenadas
                    _tableroInicial[aux.getFilas()][aux.getColumnas()] = par;
                    _tableroFinal[aux.getFilas()][aux.getColumnas()] = par;
                }
                else if( ( (i > 12) && (i < 16) ) || ( (i > 16) && (i < 20) ) ) //condicion para obtener las coordenadas de los beepers
                {
                    aux = convertirPosicion(7, 9, linea); //devuelve las coordenadas del objeto Beepers a instanciar
                    
                    CBeepers beep = new CBeepers('B', aux); //instancia un objeto Beepers
                    
                    //ubica el objeto Beepers en el tablero inicial o en el tablero final (dependiendo de su ubicacion en el archivo de texto)
                    if( (i > 12) && (i < 16) )
                        _tableroInicial[aux.getFilas()][aux.getColumnas()] = beep;
                    else
                        _tableroFinal[aux.getFilas()][aux.getColumnas()] = beep;
                }
                else if(i == 20) //condicion para conseguir las coordenadas de Arturito en el tablero final
                {
                    aux = convertirPosicion(9, 11, linea); //devuelve las coordenadas del objeto Arturito a instanciar
                    
                    CArturito artFinal = new CArturito('A', aux, 0, 0); // instancia un objeto CArturito
                    
                    _tableroFinal[aux.getFilas()][aux.getColumnas()] = artFinal; //ubica el objeto Arturito en el tablero final
                }
                
                i++; //suma la variable "i" para extraer la linea de texto con las coordenadas del objeto adecuado
            }
            
            br.close(); //cierra el BufferedReader
        }
        catch(IOException e) //captura una excepcion en caso de ocurrir un error en el bloque "try"
        {
            System.out.println("Exception caught");
        }
        
        CCamino cam = new CCamino(' ', null); //instancia un objeto Camino
        
        //ciclos para recorrer las matrices "_tableroIicial" y "_tableroFinal"
        for(int x = 0 ; x < _tableroInicial.length ; x++)
        {
            for(int y = 0 ; y < _tableroInicial.length ; y++)
            {
                //comprueba que las coordenadas provistas no contengan nada para insertar un objeto Camino en el tablero inicial
                if(_tableroInicial[x][y] == null) 
                {
                    _tableroInicial[x][y] = cam;
                }
                
                //comprueba que las coordenadas provistas no contengan nada para insertar un objeto Camino en el tablero final
                if(_tableroFinal[x][y] == null)
                {    
                    _tableroFinal[x][y] = cam;
                }
            }
        }
        
        //Inserta el objeto Arturito recibido por pase de parametros en el tablero inicial
        _tableroInicial[artInicial._coordenadas.getFilas()][artInicial._coordenadas.getColumnas()] = artInicial;
    }
    
    /**
    * Metodo imprimirTableros
    * Metodo para imprimir los tableros iniciales y finales del juego.
    */
    public void imprimirTableros()
    {
        int x, y; //variables para recorrer las matrices "_tableroInicial" y "_tableroFinal"
        String ANSI_Reset = "\u001B[0m", ANSI_Blue = "\u001B[34m", ANSI_Red = "\u001B[31m"; //variables para resaltar los objetos en la matriz de distintos colores
        
        System.out.println("Tablero de Juego:");
        System.out.println("");
        
        //ciclos para recorrer la matriz "_tableroInicial"
        for (x = 0 ; x < _tableroInicial.length ; x++)
        {
            for (y = 0 ; y < _tableroInicial.length ; y++)
            {
                //condiciones para imprimir los diversos simbolos de los objetos en el tablero de distintos colores
                if(_tableroInicial[x][y]._simbolo == 'A')
                    System.out.print(ANSI_Blue + " [ " + _tableroInicial[x][y]._simbolo + " ]\t" + ANSI_Reset);
                else if(_tableroInicial[x][y]._simbolo == 'B')
                    System.out.print(ANSI_Red + " [ " + _tableroInicial[x][y]._simbolo + " ]\t" + ANSI_Reset);
                else
                    System.out.print(" [ " + _tableroInicial[x][y]._simbolo + " ]\t");
            }
            
            System.out.print("\n");
        }
        
        System.out.println("");
        System.out.println("Tablero Final:");
        System.out.println("");
        
        //ciclos para recorrer la matriz "_tableroInicial"
        for (x = 0 ; x < _tableroFinal.length ; x++)
        {
            for (y = 0 ; y < _tableroFinal.length ; y++)
            {
                //condiciones para imprimir los diversos simbolos de los objetos en el tablero de distintos colores
                if(_tableroFinal[x][y]._simbolo == 'A')
                    System.out.print(ANSI_Blue + " [ " + _tableroFinal[x][y]._simbolo + " ]\t" + ANSI_Reset);
                else if(_tableroFinal[x][y]._simbolo == 'B')
                    System.out.print(ANSI_Red + " [ " + _tableroFinal[x][y]._simbolo + " ]\t" + ANSI_Reset);
                else
                    System.out.print(" [ " + _tableroFinal[x][y]._simbolo + " ]\t");
            }
            
            System.out.print("\n");
        }
        
        System.out.println("");
    }
    
    /**
    * Metodo menu
    * Este metodo despliega el menu de las acciones que Arturito puede realizar, habilitando las opciones del juego para
    * el usuario.
    */
    public int menu()
    {
        int s; //variable que representa la opcion a ser elegida por el usuario
        
        //despliegue del menu
        System.out.println("Que desea hacer:");
        System.out.println("1 - Moverse");
        System.out.println("2 - Girar a la Derecha");
        System.out.println("3 - Girar a la izquierda");
        System.out.println("4 - Dejar Beeper");
        System.out.println("5 - Apagar");
        
        s = leer.nextInt(); //lectura de la variable "s" para elegir la opcion
        
        return s; //retorno de la opcion elegida
    }
    
    /**
    * Metodo compararTableros
    * Este metodo compara los tableros iniciales y finales, para determinar si el usuario gano o perdio el juego.
    * 
    * @return retorna un boolean para validar si el usuario gano o perdio el juego
    */
    public boolean compararTableros()
    {
        int x,y; //variables para recorrer las matrices "_tableroInicial" y "_tableroFinal"
        boolean igualar = true; //variable para concluir si ambos tableros son iguales
                
        //ciclos para recorrer las matrices "_tableroInicial" y "_tableroFinal"
        outerloop: for (x = 0 ; x < _tableroFinal.length ; x++) //define el ciclo como "outerloop"
        {
            for (y = 0 ; y < _tableroFinal.length ; y++)
            {
                //condicion para comparar las casillas de ambos tableros en las coordendas especificadas, y determinar si son iguales
                if(_tableroInicial[x][y]._simbolo == _tableroFinal[x][y]._simbolo)
                    igualar = true;
                else //esta condicion se cumple si se determina que alguna de las casillas no son iguales, saliendo del ciclo inmediatamente
                {
                    igualar = false; //las matrices no son iguales
                    break outerloop; //rompe el ciclo externo y deja de recorrer la matriz
                }
            }
        }
        
        return igualar;
    }
    
    /**
    * Metodo pausa
    * Este metodo no es necesario para el buen funcionamiento del programa. Solo esta hecho para causar un pequeno delay
    * entre ciertas acciones.
    */
    public void pausa()
    { 
        try //inicio de bloque try para pausar brevemente el juego
        {
            Thread.sleep(1000); //un segundo de delay
        }
        catch(Exception e) //captura una excepcion en caso de ocurrir un error en el bloque "try"
        {
            System.out.println("Exception caught");
        }
    }
    
    /**
    * Metodo ejecutar
    * Ejecuta el juego de Arturito.
    * 
    * @param play parametro de tipo Jugador, contendra el nombre del usuario para el juego.
    */
    public void ejecutar(CJugador play)
    {
        int x = 7, y = 0; //variables para determinar las coordenadas iniciales de Arturito
        int s = 0, move; //variables para elegie una opcion del menu y mover a Arturito, respectivamente
        CPosicion aux = null; //variable auxiliar para poder instanciar los objetos Arturito y Camino
        boolean fin = false; //variable para determinar si el juego ha terminado, y para validar si el usuario gano el juego
        
        CArturito art = new CArturito('A', aux, 0, 0); //instancia objeto CArturito a ser manipulado por el usuario
        CCamino cam = new CCamino(' ', aux); //instancia un objeto camino para ser usado cuando Arturito se mueva
        
        System.out.println("indique su nombre:");
        play.setNombre(leer.nextLine());
        
        System.out.println("Desea iniciar a R2D2 en la casilla predeterminada? (1 = Y ; 2 = N)");
        s = leer.nextInt();
        
        if(s == 2) //condicion para establecer las coordenadas de Arturito si no se empieza en la casilla predeterminada
        {
            System.out.println("Definir la coordenada 'X':");
            x =leer.nextInt();
            System.out.println("Definir la coordenada 'Y':");
            y = leer.nextInt();
        }
        
        art = art.inicio(x, y); //ubica el objeto Arturito a ser manipulado por el usuario en el tablero 
        pausa(); //pausa la ejecucion un segundo
        llenarTableros(art);
        
        while(fin == false) //ciclo para iniciar el juego hasta que Arturito se apague
        {
            
            imprimirTableros(); //imprime los tableros inicial y final cada vez que empiece un turno
            
            switch(art.getDireccion()) //imprime un mensaje con la direccion en la que Arturito se esta moviendo
            {
                case 0: System.out.println("Estas moviendote hacia arriba");
                        break;
                case 1: System.out.println("Estas moviendote hacia la derecha");
                        break;
                case 2: System.out.println("Estas moviendote hacia abajo");
                        break;
                case 3: System.out.println("Estas moviendote hacia la izquierda");
                        break;
            }
            System.out.println("");
            
            s = menu(); //despliega el menu y retorna la opcion elegida
        
            switch(s)
            {
                case 1: //mover
                {
                    System.out.println("Cuantos espacios desea moverse?");
                    move = leer.nextInt();
                
                    //sobreescribe el objeto Arturito en su ubicacion actual por un objeto Camino
                    _tableroInicial[art.getCoordenadas().getFilas()][art.getCoordenadas().getColumnas()] = cam;
                
                    art.mover(_tableroInicial, move, 0); //mueve a Arturito a la nueva casilla
                    pausa(); //pausa la ejecucion un segundo
                
                    //sobreescribe el objeto que hubiese en la nueva casilla donde se ubicara Arturito
                    _tableroInicial[art.getCoordenadas().getFilas()][art.getCoordenadas().getColumnas()] = art;
                    
                    break; //fin mover
                }
                case 2: art.girarDer(); //girar derecha
                        break;
                case 3: art.girarIzq(); //girar izquierda
                        break;
                case 4: //dejar beeper
                {
                    if(art.getBolsa() > 0) //se cumple si Arturito tiene Beepers en la bolsa
                    {
                        art.dejarBeeper(_tableroInicial); //deja el beeper en la casilla y mueve a Arturito a una casilla nueva
                    
                        //ubica a Arturito en el tablero, una vez haya dejado el beeper y se haya movido de casilla
                        _tableroInicial[art.getCoordenadas().getFilas()][art.getCoordenadas().getColumnas()] = art;
                    }
                    else
                        System.out.println("No tienes beepers en la bolsa!");
                    
                    break; //fin dejar beeper
                }
                case 5: fin = art.apagar(); //apagar
                        break;
            }
            
            pausa(); //pausa la ejecucion un segundo
        }
        
        fin = compararTableros(); //compara los tableros y devuelve un boolean que indica si son iguales o no
        
        //condicion para ganar o perder el juego.
        if(fin == true)
            System.out.println(play.getNombre() + " ha ganado el juego!");
        else
            System.out.println(play.getNombre() + " ha perdido el juego!");
    }
}