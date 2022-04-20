package MAXX;

/*This is a class for fractions which inherits form Number.
*
* @version 1 22.11.2021
* @author Michel Jouaux, Collin Hoss, Lara Mangi
*/
import java.math.*;

public class Fraction extends Number implements Comparable {

	BigInteger zaehler;
	BigInteger nenner;

	// Constructor
	public Fraction (BigInteger pZaehler, BigInteger pNenner ){
		//Screening if the denominator is 0
		switch(pNenner.signum()) {
		case 0:
			nenner = BigInteger.ZERO;
			break;
		case -1:
			zaehler = pZaehler.negate();
			nenner = pNenner.negate();
		break;
		case 1:
			zaehler = pZaehler;
			nenner = pNenner;
			break;
		}
	}

	//Converts fractions into an Integer
	@Override
	public int intValue() {
		return zaehler.divide(nenner).intValue();
	}

	//Converts fractions into a Long
	@Override
	public long longValue() {
		return zaehler.divide(nenner).longValue();
	}

	//Converts fractions into a Float
	@Override
	public float floatValue() {
		return zaehler.divide(nenner).floatValue();
	}

	//Converts fractions into a Double
	@Override
	public double doubleValue() {
		return zaehler.divide(nenner).doubleValue();
	}


	//Adds two fractions together
	public Fraction add (Fraction pNumber) {
		BigInteger mZaehler;
		BigInteger mNenner;

		mNenner = this.nenner.multiply(pNumber.nenner);
		mZaehler = this.zaehler.multiply(pNumber.nenner).add(pNumber.zaehler.multiply(this.nenner));


		Fraction sum = new Fraction(mZaehler, mNenner);
		return sum.shorten();
	}

	//Subtracts two fractions from each other
	public Fraction subtract (Fraction pNumber) {
		BigInteger mZaehler;
		BigInteger mNenner;

		mNenner = this.nenner.multiply(pNumber.nenner);
		mZaehler = this.zaehler.multiply(mNenner).subtract(pNumber.zaehler.multiply(mNenner));

		Fraction product = new Fraction(mZaehler, mNenner);
		return product.shorten();
	}

	//Multiplies two fractions together
	public Fraction multiply (Fraction pNumber) {
		BigInteger mZaehler;
		BigInteger mNenner;

		mZaehler = this.zaehler.multiply(pNumber.zaehler);
		mNenner = this.nenner.multiply(pNumber.nenner);

		Fraction product = new Fraction(mZaehler, mNenner);
		return product.shorten();
	}

	//Divides two fractions from each other
	public Fraction divide (Fraction pNumber) {
		BigInteger mZaehler;
		BigInteger mNenner;

		mZaehler = this.zaehler.multiply(pNumber.nenner);
		mNenner = this.nenner.multiply(pNumber.zaehler);

		Fraction quotient = new Fraction(mZaehler, mNenner);
		return quotient.shorten();
	}


	//Converts the fraction into a String
	public String toString() {
		if(this.nenner.equals(BigInteger.ONE)) {
			return "" + this.zaehler;
		}else {
			return "" + this.zaehler + "/" + this.nenner;
		}
	}

	//Shortens the given fraction
	public Fraction shorten () {
		BigInteger ggT;
		//Determines the gcd of the given fraction
		ggT = this.zaehler.gcd(this.nenner);
		//Uses the gcd to divide the given fraction
		Fraction sum = new Fraction(zaehler.divide(ggT), nenner.divide(ggT));
		return sum;
	}

	//Checks if the given fraction has a 1 as the denominator
	public boolean isInteger() {
		if(this.nenner.equals(BigInteger.ONE)) {
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
			re = this.zaehler.multiply(other.nenner).compareTo(other.zaehler.multiply(this.nenner));
		}
		return re;
	}

}
