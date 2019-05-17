package pruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Programame {

	public static void main(String args[]) {

	}

	public List<String> problemaA(List<String> entrada) {
		List<String> salida = new ArrayList<>();
		
		if (entrada.size() < 1) {
			salida.add("ERROR");
			return salida;
		}

		int operaciones = Integer.parseInt(entrada.get(0));

		for (int i = 1; i <= operaciones; i++) {
			int inicio = 0;
			int fin = entrada.get(i).indexOf(" ", 0);
			String caracter[] = new String[3];
			int indice = 0;

			while (fin != -1) {
				caracter[indice] = (entrada.get(i).substring(inicio, fin));
				inicio = fin + 1;
				fin = inicio;
				fin = entrada.get(i).indexOf(" ", fin);
				indice++;
			}

			caracter[indice] = entrada.get(i).substring(inicio, entrada.get(i).length());
			comprobarOperador(caracter, salida);
		}
		return salida;
	}

	public static List<String> problemaB(List<String> entrada) {
		ArrayList<String> salida = new ArrayList<>();

		if (entrada.size() < 1) {
			salida.add("ERROR");
			return salida;
		}

		int palabras = Integer.parseInt(entrada.get(0));

		for (int i = 0; i < palabras; i++) {
			if (esCorrectoElOrdenDeLasVocales(entrada.get(i + 1))) {
				salida.add(i, "OK");
			} else {
				salida.add(i, "ERROR");
			}
		}
		System.out.println(salida);
		return salida;
	}

	public List<String> problemaC(List<String> entrada) {
		ArrayList salida = null;
		return salida;
	}

	public List<String> problemaD(List<String> entrada) {
		ArrayList salida = null;
		return salida;
	}

	public List<String> problemaE(List<String> entrada) {
		ArrayList salida = null;
		return salida;
	}

	// metodos problemaA

	public void comprobarOperador(String[] caracter, List<String> salida) {
		if (caracter[1].equals("+")) {
			salida.add(Integer.toString((Integer.parseInt(caracter[0]) + Integer.parseInt(caracter[2]))));
		} else if (caracter[1].equals("-")) {
			salida.add(Integer.toString((Integer.parseInt(caracter[0]) - Integer.parseInt(caracter[2]))));
		} else if (caracter[1].equals("*")) {
			salida.add(Integer.toString((Integer.parseInt(caracter[0]) * Integer.parseInt(caracter[2]))));
		} else if (caracter[1].equals("/")) {
			comprobarSiHayUn0EnLaDivision(caracter, salida);
		}
	}

	public void comprobarSiHayUn0EnLaDivision(String[] caracter, List<String> salida) {
		if (caracter[0].equals("0") || caracter[2].equals("0")) {
			salida.add("ERROR");
		} else {
			salida.add(Integer.toString((Integer.parseInt(caracter[0]) / Integer.parseInt(caracter[2]))));
		}
	}


	// metodos problemaB

	public static boolean esCorrectoElOrdenDeLasVocales(String comprobada) {
		String[] splitComprobada = comprobada.split("");
		splitComprobada = pasarAMayusculas(splitComprobada);
		List<String> listaComprobada = Arrays.asList(splitComprobada);
		List<String> listaComprobadaEditable = new ArrayList<>();
		listaComprobadaEditable.addAll(listaComprobada);
		if (esLaPosicionCorrecta(listaComprobadaEditable)) {
			return true;
		} else {
			return false;
		}
	}

	private static String[] pasarAMayusculas(String[] pasada) {
		for (int i = 0; i < pasada.length; i++) {
			pasada[i] = pasada[i].toUpperCase();
		}
		return pasada;
	}

	private static boolean esLaPosicionCorrecta(List<String> listaComprobada) {
		int vocalComprobada = 0;
		String[] arrayVocales = new String[] { "A", "E", "I", "O", "U" };

		switch (listaComprobada.get(0)) {
		case "E":
			vocalComprobada = 1;
			break;
		case "I":
			vocalComprobada = 2;
			break;
		case "O":
			vocalComprobada = 3;
			break;
		case "U":
			vocalComprobada = 4;
			break;
		}

		for (int i = 0; i < listaComprobada.size(); i++) {
			if (!listaComprobada.get(i).equals(arrayVocales[vocalComprobada])
					&& "AEIOUaeiou".indexOf(listaComprobada.get(i)) != -1) {
				vocalComprobada++;
				if (!listaComprobada.get(i).equals(arrayVocales[vocalComprobada])
						&& "AEIOUaeiou".indexOf(listaComprobada.get(i)) != -1) {
					return false;
				}
			}
		}

		return true;
	}

	public static String posicionar(String letra, String[] ordenReal) {
		for (int i = 0; i < 5; i++) {
			if (ordenReal[i] != null) {
				ordenReal[i] = letra;
				return ordenReal[i];
			}
		}
		return null;
	}
}
