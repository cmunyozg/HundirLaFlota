import java.util.Scanner;

public class Hundir {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		String nombre;
		char[][] tableroJugador = new char[11][11];
		char[][] tableroDisparosJugador = new char[11][11];
		char[][] tableroPC = new char[11][11];
		char[][] tableroDisparosPC = new char[11][11];
		int[] barcos = { 4, 3, 2 }; // Número de barcos y tamaño
		System.out.print("Introduce tu nombre: ");
		nombre = entrada.next();

		inicializarTablero(tableroJugador);
		inicializarTablero(tableroDisparosJugador);
		inicializarTablero(tableroPC);
		inicializarTablero(tableroDisparosPC);

		tableroPC[4][4] = 'B';
		tableroPC[4][5] = 'B';
		tableroJugador[3][2] = 'B';
		tableroJugador[3][3] = 'B';
		tableroJugador[5][2] = 'B';
		tableroJugador[5][3] = 'B';
		colocarBarcosJugador(tableroJugador, tableroDisparosJugador, barcos);
		visualizarTablero(tableroPC, tableroDisparosPC);
		System.out.println();
		visualizarTablero(tableroJugador, tableroDisparosJugador);
		disparoJugador(tableroJugador, tableroDisparosJugador, tableroPC);
		disparoPC(tableroPC, tableroDisparosPC, tableroJugador);

	}

	// Este metodo coloca los barcos pasados como vector dentro del tablero del
	// Jugador. REVISAR
	public static void colocarBarcosJugador(char[][] tableroJugador, char[][] tableroDisparosJugador, int[] barcos) {
		Scanner entrada = new Scanner(System.in);
		int coordLetra;
		int coordNumero;
		String coordenada;
		String orientacion;
	

		for (int j = 0; j < barcos.length; j++) {
			System.out.println("Introduce las coordenadas para colocar el " + (j + 1) + "º barco  (longitud "
					+ barcos[j] + "). Ej. [A0]");
			coordenada = entrada.next();
			coordLetra = (int) (coordenada.charAt(0)) - 65;
			coordNumero = coordenada.charAt(1) - 49 + 2;
			System.out.println("¿Posición horizontal o vertical? [H/V]");
			orientacion = entrada.next();

			switch (orientacion) {
			case "H":
	
					(colision == false) tableroJugador[coordLetra][i] = 'B';
				
				break;
			case "V": 
				for (int i = coordLetra; i <= barcos[j]; i++) { // menor o igual o no?
					if (tableroJugador[i][coordNumero] == 'B') { 
						colision = true; 
						System.out.println("***Introduce una posición vacía");
					
					}
					if (colision == false) tableroJugador[i][coordNumero] = 'B';
				}
			}
			visualizarTablero(tableroJugador, tableroDisparosJugador);
		}

	}

	public static boolean colision(char[][] tableroJugador, int coordLetra, int coordNumero, int longitud,
			String orientacion) {
		switch (orientacion) {
		case "H":
			for (int i = coordNumero; i <= longitud; i++) {
				if (tableroJugador[coordLetra][i] == 'B') {
					return true;
				} else return false;
			}
			break;
		case "V":
			for (int i = coordLetra; i <= longitud; i++) { // menor o igual?
				if (tableroJugador[i][coordNumero] == 'B') {
					return true;
				} else return false;
			}
			break;
		default: return true;
		}
	}

	// Método que implementa el disparo del jugador.
	public static void disparoJugador(char[][] tableroJugador, char[][] tableroDisparosJugador, char[][] tableroPC) {
		Scanner entrada = new Scanner(System.in);
		boolean disparo = false;
//		String y;
		int coordY;
		int coordX;
		String coordenada;

//		System.out.println("Introduce la coordenada Y [A-J]");
//		y = entrada.next();
//		System.out.println("Introduce la coordenada X [0-9]");
//		coordX = entrada.nextInt() + 1;
//		int coordY = y.charAt(0) - 65;

		System.out.println("Introduce la coordenada del disparo. Ej. [A1]");
		coordenada = entrada.next();
		coordY = (int) (coordenada.charAt(0)) - 65;
		coordX = coordenada.charAt(1) - 49 + 2;

		if (tableroPC[coordY][coordX] != '~') {
			tableroDisparosJugador[coordY][coordX] = 'T';
			disparo = true;
			// borrarPantalla();
			visualizarTablero(tableroJugador, tableroDisparosJugador);
		} else if (tableroPC[coordY][coordX] == '~') {
			tableroDisparosJugador[coordY][coordX] = '*';
			// borrarPantalla();
			visualizarTablero(tableroJugador, tableroDisparosJugador);
		}

		if (disparo == true)
			disparoJugador(tableroJugador, tableroDisparosJugador, tableroPC);

	}

	// Método que implementa el disparo del PC. COMPROBAR (mirar otro metodo que
	// almacene las coordenadas ya introducidas).
	public static void disparoPC(char[][] tableroPC, char[][] tableroDisparosPC, char[][] tableroJugador) {
		boolean disparo = false;
		int coordY;
		int coordX;

		do {
			coordY = (int) (Math.random() * ((9 - 0 + 1) + 0));
			coordX = (int) (Math.random() * (9 - 0 + 1) + 0);

		} while (tableroDisparosPC[coordY][coordX] != '~');

		if (tableroJugador[coordY][coordX] != '~') {
			tableroDisparosPC[coordY][coordX] = 'T';
			tableroJugador[coordY][coordX] = 'T';
			disparo = true;
			// borrarPantalla();
			visualizarTablero(tableroPC, tableroDisparosPC);
		} else if (tableroJugador[coordY][coordX] == '~') {
			tableroDisparosPC[coordY][coordX] = '*';
			tableroJugador[coordY][coordX] = '*';
			// borrarPantalla();
			visualizarTablero(tableroPC, tableroDisparosPC);
		}

		if (disparo == true)
			disparoJugador(tableroPC, tableroDisparosPC, tableroJugador);

	}

	// Método que inicia los tableros desde cero.
	public static void inicializarTablero(char[][] tablero) {
		char letra = 'A';
		char numero = '0';
		tablero[tablero.length - 1][0] = ' ';
		for (int fila = 0; fila < tablero.length - 1; fila++) {
			tablero[fila][0] = letra;
			letra++;
		}
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 1; columna < tablero[0].length; columna++) {
				tablero[fila][columna] = '~';

			}
		}
		for (int columna = 1; columna < tablero[0].length; columna++) {
			tablero[10][columna] = numero;
			numero++;
		}
	}

	// Método que visualiza los tableros en pantalla.
	public static void visualizarTablero(char[][] tablero, char[][] tableroDisparos) {
		System.out.println("    Tablero Jugador" + "\t\t" + "   Tablero Disparos");
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 0; columna < tablero[0].length; columna++) {
				System.out.print(tablero[fila][columna] + " ");

			}
			System.out.print("\t\t");
			for (int columna = 0; columna < tableroDisparos[0].length; columna++) {

				System.out.print(tableroDisparos[fila][columna] + " ");

			}
			System.out.println();

		}
		System.out.println();
	}

	// Método que borra la terminal.
	public static void borrarPantalla() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
}
