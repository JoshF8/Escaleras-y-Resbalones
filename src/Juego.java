import java.util.Scanner;
import java.math.*;
public class Juego {
	
	static Scanner teclado = new Scanner(System.in);
	//Datos jugador
	static String[][] signosJugadores = {{"Jugador 1", "#"},{"Jugador 2", "@"},{"Jugador 3", "%"},{"Jugador 4", "&"}};
	static String[][] textoJugadores = new String[2][1];
	static int[][] datosNumerosDeJugadores = new int[2][3];
	static int Jugadores = 2, subidas = 1, bajadas = 1;
	//Datos juego
	static String[][] tablero = new String[5][5];
	static int turno = 1;
	static boolean activo = false;
	static String letrasSub[] = {"a","b","c","d","e","f","g","h","i","j"};
	static int[][] PosEspeciales;
	
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
		limpiarTablero();
		for(int i = 0; i < Jugadores; i++){
			datosNumerosDeJugadores[i][0] = tablero.length-1;
			datosNumerosDeJugadores[i][1] = 0;
			datosNumerosDeJugadores[i][2] = i;
		}
		PosEspeciales = new int[subidas + bajadas + 1][5];
		PosEspeciales[0][0] = tablero.length - 1;
		PosEspeciales[0][1] = 0;
		PosEspeciales[0][2] = 0;
		PosEspeciales[0][3] = tablero.length - 1;
		PosEspeciales[0][4] = 0;
		tablero[PosEspeciales[0][0]][PosEspeciales[0][1]] = "$";
		tablero[PosEspeciales[0][2]][PosEspeciales[0][3]] = "$";
		posicionarSubBaj();
		posicionarSignos();
	}
	
	static void limpiarTablero(){
		for(int i = 0; i < tablero.length; i++){
			for(int j = 0; j < tablero.length; j++){
				tablero[i][j] = " ";
			}
		}
	}
	
	static void posicionarSubBaj(){
		
		for(int i = 0; i < subidas; i++){
			do{
				PosEspeciales[i + 1][0] = Randomizar(1, tablero.length - 1);
				PosEspeciales[i + 1][1] = Randomizar(0, tablero.length);
			}while(!(tablero[PosEspeciales[i + 1][0]][PosEspeciales[i + 1][1]].equals(" ")));
			tablero[PosEspeciales[i + 1][0]][PosEspeciales[i + 1][1]] = letrasSub[i];
			do{
				PosEspeciales[i + 1][2] = Randomizar(0, tablero.length - 1);
				PosEspeciales[i + 1][3] = Randomizar(0, tablero.length);
			}while(!(tablero[PosEspeciales[i + 1][2]][PosEspeciales[i + 1][3]].equals(" ")) || (PosEspeciales[i + 1][0] == PosEspeciales[i + 1][2]));
			tablero[PosEspeciales[i + 1][2]][PosEspeciales[i + 1][3]] = letrasSub[i];
			PosEspeciales[i + 1][4] = 1;
		}
		for(int i = 0; i < bajadas; i++){
			do{
				PosEspeciales[i + 1 + subidas][0] = Randomizar(1, tablero.length - 1);
				PosEspeciales[i + 1 + subidas][1] = Randomizar(0, tablero.length);
			}while(!(tablero[PosEspeciales[i + 1][0]][PosEspeciales[i + 1 + subidas][1]].equals(" ")));
			tablero[PosEspeciales[i + 1 + subidas][0]][PosEspeciales[i + 1 + subidas][1]] = letrasSub[i];
			do{
				PosEspeciales[i + 1 + subidas][2] = Randomizar(0, tablero.length - 1);
				PosEspeciales[i + 1 + subidas][3] = Randomizar(0, tablero.length);
			}while(!(tablero[PosEspeciales[i + 1][2]][PosEspeciales[i + 1 + subidas][3]].equals(" ")) || (PosEspeciales[i + 1 + subidas][0] == PosEspeciales[i + 1 + subidas][2]));
			tablero[PosEspeciales[i + 1 + subidas][2]][PosEspeciales[i + 1 + subidas][3]] = letrasSub[i];
			PosEspeciales[i + 1 + subidas][4] = 2;
		}
	}
	
	static int Randomizar(int min, int max){
		return (int)(Math.random() * max) + min;
	}
	
	static void posicionarSignos(){
		limpiarTablero();
		String signo = "";
		for(int i = 0; i < PosEspeciales.length; i++){
			switch(PosEspeciales[i][4]){
				case 0:
					signo = "$";
					break;
				case 1:
					signo = letrasSub[i - 1];
					break;
				case 2:
					signo = String.valueOf(i - subidas);
			}
			tablero[PosEspeciales[i][0]][PosEspeciales[i][1]] = signo;
			tablero[PosEspeciales[i][2]][PosEspeciales[i][3]] = signo;
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
		System.out.println("==== Menu Juego ====");
		System.out.println("Turno del jugador " + (datosNumerosDeJugadores[turno - 1][2] + 1));
		System.out.println("1. Lanzar dado");
		System.out.println("2. Regresar al menu");
		int opcion = teclado.nextInt();
		switch(opcion){
			case 1:
					moverJugador((int) (Math.random() * 6) + 1);
					turno = (turno + 1) > Jugadores ? 1: turno + 1; 
					imprimirTablero();
				break;
			case 2:
				menu();
				break;
			default:
				System.out.println("No es una opcion valida.");
				imprimirTablero();
		}
	}
	
	static void moverJugador(int dado){
		System.out.println("En el dado salio: " + dado);
		for(int i = 1; i < (dado + 1); i++){
			if(((datosNumerosDeJugadores[turno - 1][1] == 0) && datosNumerosDeJugadores[turno - 1][0]%2 != 0)||((datosNumerosDeJugadores[turno - 1][1] == (tablero.length-1)) && datosNumerosDeJugadores[turno - 1][0]%2 == 0)){
				datosNumerosDeJugadores[turno - 1][0]--;
			}else{
				datosNumerosDeJugadores[turno - 1][1] += Math.pow(-1, datosNumerosDeJugadores[turno - 1][0]);
			}
			if((datosNumerosDeJugadores[turno -1][0] == 0) && (datosNumerosDeJugadores[turno - 1][1] == tablero.length - 1)){
				System.out.println("Ha ganado el jugador no. " + turno);
				posicionarSignos();
				return;
			}
		}
		if(!(tablero[datosNumerosDeJugadores[turno - 1][0]][datosNumerosDeJugadores[turno - 1][1]].equals(" "))){
			for(int j = 0; j < subidas; j++){
				if(tablero[datosNumerosDeJugadores[turno - 1][0]][datosNumerosDeJugadores[turno - 1][1]].equals(letrasSub[j])){
						if(PosEspeciales[j + 1][0] < PosEspeciales[j + 1][2]){
							datosNumerosDeJugadores[turno - 1][0] = PosEspeciales[j + 1][0];
							datosNumerosDeJugadores[turno - 1][1] = PosEspeciales[j + 1][1];
						}
					
						if(PosEspeciales[j + 1][0] > PosEspeciales[j + 1][2]){
							datosNumerosDeJugadores[turno - 1][0] = PosEspeciales[j + 1][2];
							datosNumerosDeJugadores[turno - 1][1] = PosEspeciales[j + 1][3];
						}
					
				}
			}
			for(int j = 0; j < bajadas; j++){
				if(tablero[datosNumerosDeJugadores[turno - 1][0]][datosNumerosDeJugadores[turno - 1][1]].equals(String.valueOf(j +1))){
						if(PosEspeciales[j + 1 + subidas][0] < PosEspeciales[j + 1 + subidas][2]){
							datosNumerosDeJugadores[turno - 1][0] = PosEspeciales[j + 1 + subidas][2];
							datosNumerosDeJugadores[turno - 1][1] = PosEspeciales[j + 1 + subidas][3];
						}
						if(PosEspeciales[j + 1 + subidas][0] > PosEspeciales[j + 1 + subidas][2]){
							datosNumerosDeJugadores[turno - 1][0] = PosEspeciales[j + 1 + subidas][0];
							datosNumerosDeJugadores[turno - 1][1] = PosEspeciales[j + 1 + subidas][1];
						}
				}
			}
		}
		posicionarSignos();
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
				NumJugadores();
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
			}while(numero > 4);
		}else{
			do{
				numero = teclado.nextInt();
				if(numero > 10){
					System.out.println("Dado el tamaño del tablero 10 subidas es el maximo, ingrese un nuevo numero.");
				}else{
					subidas = numero;
				}
			}while(numero > 11);
		}
		System.out.println("Ingrese la cantidad de bajadas: ");
		if(tablero.length < 11){
			do{
				numero = teclado.nextInt();
				if(numero > 3){
					System.out.println("Dado el tamaño del tablero 3 bajadas es el maximo, ingrese un nuevo numero.");
				}else{
					bajadas = numero;
				}
			}while(numero > 4);
		}else{
			do{
				numero = teclado.nextInt();
				if(numero > 10){
					System.out.println("Dado el tamaño del tablero 10 bajadas es el maximo, ingrese un nuevo numero.");
				}else{
					bajadas = numero;
				}
			}while(numero > 11);
		}
		Configuracion();
	}

	static void NumJugadores(){
		System.out.println("Ingrese el numero de jugadores (1 minimo y 4 maximo)");
	}
}
