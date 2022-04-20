/*This is a class for fractions which inherits form Number.
*
* @version 1 22.11.2021
* @author Michel Jouaux, Collin Hoss, Lara Mangi
*/
import java.math.*;

public class Fraction extends Number implements Comparable {

	BigInteger numerator; //Zähler
	BigInteger denomiator; //Nenner

	// Constructor
	public Fraction (BigInteger pNumerator, BigInteger pDenomiator ){
		//Screening if the denominator is 0
		switch(pDenomiator.signum()) {
		case 0:
			denomiator = BigInteger.ZERO;
			break;
		case -1:
			numerator = pNumerator.negate();
			denomiator = pDenomiator.negate();
		break;
		case 1:
			numerator = pNumerator;
			denomiator = pDenomiator;
			break;
		}
	}

	//Converts fractions into an Integer
	@Override
	public int intValue() {
		return numerator.divide(denomiator).intValue();
	}

	//Converts fractions into a Long
	@Override
	public long longValue() {
		return numerator.divide(denomiator).longValue();
	}

	//Converts fractions into a Float
	@Override
	public float floatValue() {
		return numerator.floatValue() / denomiator.floatValue();
	}

	//Converts fractions into a Double
	@Override
	public double doubleValue() {
		return numerator.doubleValue() / denomiator.doubleValue();
	}


	//Adds two fractions together
	public Fraction add (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newDenomiator = this.denomiator.multiply(pNumber.denomiator);
		newNumerator = this.numerator.multiply(pNumber.denomiator).add(pNumber.numerator.multiply(this.denomiator));


		Fraction sum = new Fraction(newNumerator, newDenomiator);
		return sum.shorten();
	}

	//Subtracts two fractions from each other
	public Fraction subtract (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newDenomiator = this.denomiator.multiply(pNumber.denomiator);
		newNumerator = this.numerator.multiply(pNumber.denomiator).subtract(pNumber.numerator.multiply(this.denomiator));

		Fraction product = new Fraction(newNumerator, newDenomiator);
		return product.shorten();
	}

	//Multiplies two fractions together
	public Fraction multiply (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newNumerator = this.numerator.multiply(pNumber.numerator);
		newDenomiator = this.denomiator.multiply(pNumber.denomiator);

		Fraction product = new Fraction(newNumerator, newDenomiator);
		return product.shorten();
	}

	//Divides two fractions from each other
	public Fraction divide (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newNumerator = this.numerator.multiply(pNumber.denomiator);
		newDenomiator = this.denomiator.multiply(pNumber.numerator);

		Fraction quotient = new Fraction(newNumerator, newDenomiator);
		return quotient.shorten();
	}


	//Converts the fraction into a String
	public String toString() {
		if(this.denomiator.equals(BigInteger.ONE)) {
			return "" + this.numerator;
		}else {
			return "" + this.numerator + "/" + this.denomiator;
		}
	}

	//Shortens the given fraction
	public Fraction shorten () {
		BigInteger ggT;
		//Determines the gcd of the given fraction
		ggT = this.numerator.gcd(this.denomiator);
		//Uses the gcd to divide the given fraction
		Fraction sum = new Fraction(numerator.divide(ggT), denomiator.divide(ggT));
		return sum;
	}

	//Checks if the given fraction has a 1 as the denominator
	public boolean isInteger() {
		if(this.denomiator.equals(BigInteger.ONE)) {
			return true;
		}else {
			return false;
		}
	}

	//Compares two fractions with each other and returns 0 if equal, 1 if greater, -1 if smaller
	@Override
	public int compareTo(Object o) {
		int re = 0;
		if(o instanceof Fraction) {
			Fraction other = (Fraction) o;
			re = this.numerator.multiply(other.denomiator).compareTo(other.numerator.multiply(this.denomiator));
		}
		return re;
	}

}
