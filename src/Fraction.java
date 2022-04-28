/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import java.math.BigInteger;

public class Fraction extends Number implements Comparable {

	BigInteger numerator; //Zähler
	BigInteger denominator; //Nenner

	// Constructor
	public Fraction (BigInteger pNumerator, BigInteger pDenominator ){
		//Screening if the denominator is 0
		switch(pDenominator.signum()) {
		case 0:
			denominator = BigInteger.ZERO;
			break;
		case -1:
			numerator = pNumerator.negate();
			denominator = pDenominator.negate();
		break;
		case 1:
			numerator = pNumerator;
			denominator = pDenominator;
			break;
		}
	}

	//Converts fractions into an Integer
	@Override
	public int intValue() {
		return numerator.divide(denominator).intValue();
	}

	//Converts fractions into a Long
	@Override
	public long longValue() {
		return numerator.divide(denominator).longValue();
	}

	//Converts fractions into a Float
	@Override
	public float floatValue() {
		return numerator.floatValue() / denominator.floatValue();
	}

	//Converts fractions into a Double
	@Override
	public double doubleValue() {
		return numerator.doubleValue() / denominator.doubleValue();
	}


	//Adds two fractions together
	public Fraction add (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newDenomiator = this.denominator.multiply(pNumber.denominator);
		newNumerator = this.numerator.multiply(pNumber.denominator).add(pNumber.numerator.multiply(this.denominator));


		Fraction sum = new Fraction(newNumerator, newDenomiator);
		return sum.shorten();
	}

	//Subtracts two fractions from each other
	public Fraction subtract (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newDenomiator = this.denominator.multiply(pNumber.denominator);
		newNumerator = this.numerator.multiply(pNumber.denominator).subtract(pNumber.numerator.multiply(this.denominator));

		Fraction product = new Fraction(newNumerator, newDenomiator);
		return product.shorten();
	}

	//Multiplies two fractions together
	public Fraction multiply (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newNumerator = this.numerator.multiply(pNumber.numerator);
		newDenomiator = this.denominator.multiply(pNumber.denominator);

		Fraction product = new Fraction(newNumerator, newDenomiator);
		return product.shorten();
	}

	//Divides two fractions from each other
	public Fraction divide (Fraction pNumber) {
		BigInteger newNumerator;
		BigInteger newDenomiator;

		newNumerator = this.numerator.multiply(pNumber.denominator);
		newDenomiator = this.denominator.multiply(pNumber.numerator);

		Fraction quotient = new Fraction(newNumerator, newDenomiator);
		return quotient.shorten();
	}


	//Converts the fraction into a String
	public String toString() {
		if(this.denominator.equals(BigInteger.ONE)) {
			return "" + this.numerator;
		}else {
			return "" + this.numerator + "/" + this.denominator;
		}
	}

	//Shortens the given fraction
	public Fraction shorten () {
		BigInteger ggT;
		//Determines the gcd of the given fraction
		ggT = this.numerator.gcd(this.denominator);
		//Uses the gcd to divide the given fraction
		Fraction sum = new Fraction(numerator.divide(ggT), denominator.divide(ggT));
		return sum;
	}

	//Checks if the given fraction has a 1 as the denominator
	public boolean isInteger() {
		if(this.denominator.equals(BigInteger.ONE)) {
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
			re = this.numerator.multiply(other.denominator).compareTo(other.numerator.multiply(this.denominator));
		}
		return re;
	}

}
