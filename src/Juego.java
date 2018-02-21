import java.util.Scanner;
public class Juego {
	
	static Scanner teclado = new Scanner(System.in);
	//Datos jugador
	static String[][] signosJugadores = {{"Jugador 1", "#"},{"Jugador 2", "@"},{"Jugador 3", "%"},{"Jugador 4", "&"}};
	static String[][] textoJugadores = new String[2][1];
	static int[][] datosNumerosDeJugadores = new int[2][3];
	static int maxJugadores = 2;
	//Datos juego
	static String[][] tablero = new String[5][5];
	static int turno = 0;
	public static void main(String[] args) {
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
		tablero[tablero.length-1][tablero.length-1] = "#";
		imprimirTablero();
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

}
