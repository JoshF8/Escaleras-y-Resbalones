import java.util.Scanner;
public class Juego {
	
	static Scanner teclado = new Scanner(System.in);
	//Datos jugador
	static String[][] signosJugadores = {{"Jugador 1", "#"},{"Jugador 2", "@"},{"Jugador 3", "%"},{"Jugador 4", "&"}};
	static String[][] textoJugadores = new String[2][1];
	static int[][] datosNumerosDeJugadores = new int[1][3];
	static int Jugadores = 1, subidas = 1, bajadas = 1;
	//Datos juego
	static String[][] tablero = new String[5][5];
	static int turno = 0;
	static boolean activo = false;
	
	public static void main(String[] args) {
		inicioJuego();
		menu();
	}

	static void menu(){
		System.out.println("==== Menu Principal ====");
		System.out.println("1. Iniciar juego");
		System.out.println("2. Regresar al juego");
		System.out.println("3. Configuracion");
		System.out.println("4. Salir");
		//System.out.println(tablero.length);
		int opcion = teclado.nextInt();
		switch(opcion){
			case 1:
				inicioJuego();
				imprimirTablero();
				break;
			case 2:
				imprimirTablero();
				break;
			case 3:
				Configuracion();
				break;
			default:
				System.out.println("Esa opcion no es valida.");
				menu();
				break;
		}
	}
	
	static void inicioJuego(){
		for(int i = 0; i < tablero.length; i++){
			for(int j = 0; j < tablero.length; j++){
				tablero[i][j] = " ";
			}
		}
		for(int i = 0; i < Jugadores; i++){
			datosNumerosDeJugadores[i][0] = tablero.length-1;
			datosNumerosDeJugadores[i][1] = 0;
			//cambiar luego esto
			datosNumerosDeJugadores[i][2] = 2;
		}
		for(int i = 0; i < Jugadores; i++){
			tablero[datosNumerosDeJugadores[i][0]][datosNumerosDeJugadores[i][1]] = signosJugadores[datosNumerosDeJugadores[i][2]][1];
		}
	}
	static void imprimirTablero(){
		for(int i = 0; i < tablero.length; i++){
			System.out.print("|");
			for(int j = 0; j < tablero.length; j++){
				System.out.print(" " + tablero[i][j] + " |");
			}
			System.out.println("");
		}
	}
	
	static void Configuracion(){
		System.out.println("==== Menu Configuracion ====");
		System.out.println("1. Dimension del tablero");
		System.out.println("2. Cantidad de subidas y bajadas");
		System.out.println("3. Ingresar jugadores");
		System.out.println("4. Regresar");
		int opcion = teclado.nextInt();
		switch(opcion){
			case 1:
				opcionDimension();
				break;
			case 2:
				subidasYBajadas();
				break;
			case 3:
				Configuracion();
				break;
			case 4:
				menu();
				break;
			default:
				System.out.println("Esa opcion no es valida.");
				Configuracion();
				break;
		}
	}
	
	static void opcionDimension(){
		System.out.println("==== Menu Dimensiones del tablero ====");
		System.out.println("1. Pequeño");
		System.out.println("2. Grande");
		System.out.println("3. Regresar");
		int opcion = teclado.nextInt();
		switch(opcion){
			case 1:
				tablero = new String[5][5];
				inicioJuego();
				System.out.println("El tablero ahora es pequeño.");
				opcionDimension();
				break;
			case 2:
				tablero = new String[11][11];
				inicioJuego();
				System.out.println("El tablero ahora es grande.");
				opcionDimension();
				break;
			case 3:
				Configuracion();
				break;
			default:
				System.out.println("Esa opcion no es valida.");
				opcionDimension();
				break;
		}
	}
	
	static void subidasYBajadas(){
		int numero;
		System.out.println("Ingrese la cantidad de subidas: ");
		if(tablero.length < 11){
			do{
				numero = teclado.nextInt();
				if(numero > 3){
					System.out.println("Dado el tamaño del tablero 3 subidas es el maximo, ingrese un nuevo numero.");
				}else{
					subidas = numero;
				}
			}while(numero < 4);
		}else{
			do{
				numero = teclado.nextInt();
				if(numero > 10){
					System.out.println("Dado el tamaño del tablero 10 subidas es el maximo, ingrese un nuevo numero.");
				}else{
					subidas = numero;
				}
			}while(numero < 11);
		}
		System.out.println("Ingrese la cantidad de bajadas: ");
		if(tablero.length < 11){
			do{
				numero = teclado.nextInt();
				if(numero > 3){
					System.out.println("Dado el tamaño del tablero 3 bajadas es el maximo, ingrese un nuevo numero.");
				}else{
					subidas = numero;
				}
			}while(numero < 4);
		}else{
			do{
				numero = teclado.nextInt();
				if(numero > 10){
					System.out.println("Dado el tamaño del tablero 10 bajadas es el maximo, ingrese un nuevo numero.");
				}else{
					subidas = numero;
				}
			}while(numero < 11);
		}
		Configuracion();
	}

}
