package proyectofinal;

// LINEA CAMBIADA

public class Calculator {

	public int sum(int n1, int n2) {
		return n1 + n2;
	}

	public boolean isAnAdult(int age) {
		if (age >= 18)
			return true;
		else
			return false;
	}

	public void allowOnlyAdults(int age) {
		if (age < 18)
			throw new RuntimeException("Es menor de edad");
	}

}
