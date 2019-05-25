package pruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Programame {
    
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

    public List<String> problemaB(List<String> entrada) {
        ArrayList<String> salida = new ArrayList<>();

        if (entrada.size() < 1) {
            salida.add("ERROR");
            return salida;
        }

        int palabras = Integer.parseInt(entrada.get(0));

        for (int i = 0; i < palabras; i++) {
            if (tieneVocales(entrada.get(i + 1)) && esCorrectoElOrdenDeLasVocales(entrada.get(i + 1))) {
                salida.add(i, "OK");
            } else if(!esCorrectoElOrdenDeLasVocales(entrada.get(i + 1)) || !tieneVocales(entrada.get(i + 1))){
                salida.add(i, "ERROR");
            }
        }
        
        for (int i = 0, j = salida.size() - 1; i < j; i++) {
            salida.add(i, salida.remove(j));
	}

        return salida;
    }

    public List<String> problemaC(List<String> entrada) {
        ArrayList<String> salida = new ArrayList<>();
        ArrayList<Integer> entradaInts = new ArrayList<>();
        ArrayList<Integer> numerosPrimosQueEmpiezanPor100 = new ArrayList<>();
        int acumulador = 0;

        if (entrada.size() < 1) {
            salida.add("ERROR");
            return salida;
        }

        ponerNumerosPrimosEnLista(numerosPrimosQueEmpiezanPor100);
        pasarEntradaAIntegers(entrada, entradaInts);
        ponerNumerosPrimosQueEmpiezanPor1EnLaSalida(entradaInts, numerosPrimosQueEmpiezanPor100, salida);

        return salida;
    }

    public List<String> problemaD(List<String> entrada) {
        boolean over = false;
        ArrayList salida = new ArrayList();
        
        if (!entrada.isEmpty() && (comprobarProbD(entrada))) {
            int totalCasos = Integer.parseInt(entrada.get(0));
            int casoActual = 0;
            int datoActual = 1;
            while (casoActual < totalCasos) {
                int habitaciones = Integer.parseInt(entrada.get(datoActual));
                datoActual++;
                int conexionesEntreHabitaciones = Integer.parseInt(entrada.get(datoActual));
                datoActual++;
                List<String> aux = new ArrayList<String>();

                for (int i = 0; i < conexionesEntreHabitaciones; i++) {
                    String c[] = entrada.get(datoActual).split(" ");
                    for (int j = 0; j < c.length; j++) {
                        aux.add(c[j]);
                    }
                    datoActual++;
                }

                int habitacionesConectadas[][] = new int[conexionesEntreHabitaciones][2];
                for (int i = 0, x = 0; i < habitacionesConectadas.length; i++) {
                    for (int j = 0; j < habitacionesConectadas[i].length; j++) {
                        habitacionesConectadas[i][j] = Integer.parseInt(aux.get(x));
                        x++;
                    }
                }
                
                List<Integer> habsConec = new ArrayList<Integer>();
                for (int i = 0; i < habitacionesConectadas.length; i++) {
                    for (int j = 0; j < habitacionesConectadas[i].length; j++) {
                        habsConec.add(habitacionesConectadas[i][j]);
                    }
                }
                for (int i = 0; i < habitacionesConectadas.length; i++) {
                    for (int j = 0; j < habitacionesConectadas[i].length; j++) {
                    }
                }

                String pAux[] = entrada.get(datoActual).split(",");
                List<Integer> pasos = new ArrayList<Integer>();
                
                for (int i = 0; i < pAux.length; i++) {
                    pasos.add(Integer.parseInt(pAux[i]));
                }
                
                datoActual++;

                if (comprobarGameOver(habitacionesConectadas, pasos, salida) == 1) {
                    over = true;
                }

                if (!over) {
                    comprobarPerdidoVictoria(habitaciones, pasos, salida);
                }

                over = false;
                casoActual++;
            }
        }

        return salida;

    }

    public List<String> problemaE(List<String> entrada) {
        ArrayList<String> salida = new ArrayList<>();

        if (entrada.size() < 1) {
            salida.add("ERROR");
            return salida;
        }

        int n_Operaciones = Integer.parseInt(entrada.get(0));

        for (int i = 1, i2 = 1; i <= n_Operaciones; i++) {
            salida.add("Caso " + i + ":");
            int oxigeno = Integer.parseInt(entrada.get(i2));
            int n_Naves = Integer.parseInt(entrada.get(i2 + 1));
            int valores[][] = new int[n_Naves][2];
            ponerValores(n_Naves, valores, entrada, i2);

            ponerValotesAscendente(valores);

            ponerValoresDescendente(valores, oxigeno);

            ponerValoresEnLaSalida(valores, salida);
            
            i2 += n_Naves + 2;
        }
        return salida;
    }

 // Metodos para los problemas
    
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
    public boolean esCorrectoElOrdenDeLasVocales(String comprobada) {
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
    
    public boolean tieneVocales(String comprobada){
        String[] splitComprobada = comprobada.split("");
        splitComprobada = pasarAMayusculas(splitComprobada);
        List<String> listaComprobada = Arrays.asList(splitComprobada);
        List<String> listaComprobadaEditable = new ArrayList<>();
        listaComprobadaEditable.addAll(listaComprobada);
        
        for(int i = 0; i < listaComprobadaEditable.size(); i++){
            if(listaComprobadaEditable.get(i).equals("A") || listaComprobadaEditable.get(i).equals("E") || listaComprobadaEditable.get(i).equals("I") || listaComprobadaEditable.get(i).equals("O") || listaComprobadaEditable.get(i).equals("U")){
                return true;
            }
        }
        return false;
    }

    public String[] pasarAMayusculas(String[] pasada) {
        for (int i = 0; i < pasada.length; i++) {
            pasada[i] = pasada[i].toUpperCase();
        }
        return pasada;
    }

    public boolean esLaPosicionCorrecta(List<String> listaComprobada) {
        int vocalComprobada = 0;
        String[] arrayVocales = new String[]{"A", "E", "I", "O", "U"};

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

    public String posicionar(String letra, String[] ordenReal) {
        for (int i = 0; i < 5; i++) {
            if (ordenReal[i] != null) {
                ordenReal[i] = letra;
                return ordenReal[i];
            }
        }
        return null;
    }

    // metodos problemaC
    public void pasarEntradaAIntegers(List<String> entrada, ArrayList<Integer> entradaInts) throws NumberFormatException {
        for (int i = 0; i < entrada.size(); i++) {
            entradaInts.add(Integer.parseInt(entrada.get(i)));
        }
        entradaInts.remove(0);
    }

    public boolean esPrimo(int numero) {
        int contadorDeRestosQueSon0 = 0;

        for (int i = 1; i <= numero; i++) {
            if (numero % i == 0) {
                contadorDeRestosQueSon0++;
            }
        }

        if (contadorDeRestosQueSon0 == 2 || numero == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void ponerNumerosPrimosQueEmpiezanPor1EnLaSalida(ArrayList<Integer> entradaInts, ArrayList<Integer> numerosPrimosQueEmpiezanPor100, ArrayList<String> salida) {
        for (int i = 0; i < entradaInts.size(); i++) {
            int contadorPrimos = 0;
            for (int j = 0; j < numerosPrimosQueEmpiezanPor100.size(); j++) {
                if (numerosPrimosQueEmpiezanPor100.get(j) <= entradaInts.get(i) && ((numerosPrimosQueEmpiezanPor100.get(j) >= 10 && numerosPrimosQueEmpiezanPor100.get(j) < 20) || (numerosPrimosQueEmpiezanPor100.get(j) >= 100 && numerosPrimosQueEmpiezanPor100.get(j) < 200))) {
                    contadorPrimos++;
                }
            }
            salida.add(Integer.toString(contadorPrimos));
        }
    }

    public void ponerNumerosPrimosEnLista(ArrayList<Integer> numerosPrimosQueEmpiezanPor100) {
        for (int i = 0; i < 1000; i++) {
            if (esPrimo(i)) {
                numerosPrimosQueEmpiezanPor100.add(i);
            }
        }
    }

    // metodos problemaD
    public boolean comprobarProbD(List<String> entrada) {
        int totalCasos = Integer.parseInt(entrada.get(0));
        int condiciones = 0;

        if ((totalCasos >= 1 && totalCasos <= 100) && (entrada.size() >= (totalCasos * 4) + 1)) {
            int datoActual = 1;
            condiciones++;
            if (Integer.parseInt(entrada.get(datoActual)) >= 2 && Integer.parseInt(entrada.get(datoActual)) <= 40) {
                int habs = Integer.parseInt(entrada.get(datoActual));
                datoActual++;
                condiciones++;
                if (Integer.parseInt(entrada.get(datoActual)) >= 1 && Integer.parseInt(entrada.get(datoActual)) <= 20
                        && Integer.parseInt(entrada.get(datoActual)) >= habs - 1) {
                    condiciones++;

                    for (int i = 0; i < Integer.parseInt(entrada.get(datoActual - 1)); i++) {
                        datoActual++;
                        if (entrada.get(datoActual).contains(" ")
                                && comprobarConexHabs(entrada.get(datoActual), habs)) {
                            condiciones++;
                        }
                    }
                    datoActual++;

                    if (entrada.get(datoActual) != null && comprobarPasos(entrada.get(datoActual), habs)) {
                        condiciones++;
                    }
                }
            }
        }

        if (condiciones == 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarPasos(String string, int habs) {
        String aux[] = string.split(",");
        int aux2[] = new int[aux.length];
        int coincide = 0;

        for (int i = 0; i < aux.length; i++) {
            aux2[i] = Integer.parseInt(aux[i]);
            for (int j = 1; j <= habs; j++) {
                if (aux2[i] == j) {
                    coincide++;
                }
            }
        }

        if (coincide == aux.length) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarConexHabs(String string, int habs) {
        int coincide = 0;
        String aux[] = string.split(" ");
        int aux2[] = new int[aux.length];

        for (int i = 0; i < aux.length; i++) {
            aux2[i] = Integer.parseInt(aux[i]);
            for (int j = 1; j <= habs; j++) {
                if (aux2[i] == j) {
                    coincide++;
                }
            }
        }

        if (coincide == string.length() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void comprobarPerdidoVictoria(int habitacionesTotal, List<Integer> pasos, ArrayList salida) {
        int ultimaHab = 0;
        for (int i = 0; i < pasos.size(); i++) {
            if (pasos.get(i) > ultimaHab) {
                ultimaHab = pasos.get(i);
            }
        }
        if (ultimaHab == habitacionesTotal) {
            salida.add("VICTORIA");
        } else {
            salida.add("PERDIDO");
        }
    }

    public int comprobarGameOver(int[][] habitacionesConectadas, List<Integer> pasos, ArrayList salida) {
        int hayConexion = 0;
        int habitacionesPasadas[] = new int[pasos.size()];
        for (int i = 0; i < pasos.size(); i++) {
            habitacionesPasadas[i] = pasos.get(i);
        }

        if (habitacionesPasadas.length - 1 == 0) { // Si solo hay 1 paso
            int hab1 = 1;
            int hab2 = habitacionesPasadas[0];
            for (int j = 0; j < habitacionesConectadas.length; j++) {
                if ((habitacionesConectadas[j][0] == hab1 && habitacionesConectadas[j][1] == hab2)
                        || (habitacionesConectadas[j][1] == hab1 && habitacionesConectadas[j][0] == hab2)) {
                    hayConexion++;
                }
            }
        } else if (habitacionesPasadas.length - 1 > 0) {
            for (int i = 0; i < habitacionesPasadas.length; i++) {
                if (i == 0) {
                    int hab1 = 1;
                    int hab2 = habitacionesPasadas[i];
                    for (int j = 0; j < habitacionesConectadas.length; j++) {
                        if ((habitacionesConectadas[j][0] == hab1 && habitacionesConectadas[j][1] == hab2)
                                || (habitacionesConectadas[j][1] == hab1 && habitacionesConectadas[j][0] == hab2)) {

                            hayConexion++;
                        }
                    }
                } else {
                    int hab1 = habitacionesPasadas[i - 1];
                    int hab2 = habitacionesPasadas[i];
                    for (int j = 0; j < habitacionesConectadas.length; j++) {
                        if ((habitacionesConectadas[j][0] == hab1 && habitacionesConectadas[j][1] == hab2)
                                || (habitacionesConectadas[j][1] == hab1 && habitacionesConectadas[j][0] == hab2)) {
                            hayConexion++;
                        }
                    }
                }
            }
        }

        if (hayConexion == habitacionesPasadas.length) {
            return 0;
        } else {
            salida.add("GAMEOVER");
            return 1;
        }
    }
    
    // metodos problemaE
    
    public void ponerValores(int n_Naves, int[][] valores, List<String> entrada, int i2) throws NumberFormatException {
        for (int j = 0; j < n_Naves; j++) {
            valores[j][0] = Integer
                    .parseInt(entrada.get(j + i2 + 2).substring(0, entrada.get(j + i2 + 2).indexOf(" ")));
            valores[j][1] = Integer.parseInt(entrada.get(j + i2 + 2)
                    .substring(entrada.get(j + i2 + 2).indexOf(" ") + 1, entrada.get(j + i2 + 2).length()));
        }
    }
    
    public void ponerValoresEnLaSalida(int[][] valores, ArrayList<String> salida) {
        for (int j = 0; j < valores.length; j++) {
            salida.add(valores[j][0] + " " + valores[j][1]);
        }
    }
    
    public void ponerValotesAscendente(int[][] valores) {
        for (int x = 0; x < (valores.length - 1); x++) {
            for (int j = x + 1; j < valores.length; j++) {
                if (valores[x][1] > valores[j][1]) {
                    int variableauxiliar1 = valores[x][1];
                    int variableauxiliar0 = valores[x][0];
                    valores[x][1] = valores[j][1];
                    valores[x][0] = valores[j][0];
                    valores[j][1] = variableauxiliar1;
                    valores[j][0] = variableauxiliar0;
                }
            }
        }
    }
    
    public void ponerValoresDescendente(int[][] valores, int oxigeno) {
        for (int x = valores.length - 1; x > 0; x--) {
            for (int j = x - 1; j > -1; j--) {
                if (valores[x][0] >= oxigeno && valores[x][0] > valores[j][0]) {
                    int variableauxiliar1 = valores[x][1];
                    int variableauxiliar0 = valores[x][0];
                    valores[x][1] = valores[j][1];
                    valores[x][0] = valores[j][0];
                    valores[j][1] = variableauxiliar1;
                    valores[j][0] = variableauxiliar0;
                }
            }
        }
    }
}
