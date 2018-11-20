
public class Experimentos {
public static void main (String[]args) {
	String coordenada = "F2";
	int coordY = (int) (coordenada.charAt(0)) - 65;
	int coordX = coordenada.charAt(1) -49+2;
	
	System.out.println(coordY);
	System.out.println(coordX);
}
}
