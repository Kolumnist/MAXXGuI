import java.math.BigInteger;
import java.util.Random;

public class Field {
    private Random rnd = new Random();
    private BigInteger numerator, denominator;
    private Fraction fraction = new Fraction(new BigInteger("0"), new BigInteger("1"));
    private boolean notInteger = false;

    public Field(){

    }

    public String createValue(){
        while (!notInteger){
            numerator = BigInteger.valueOf(rnd.nextInt(10, 1000));
            denominator = BigInteger.valueOf(rnd.nextInt(10, 1000));

            fraction = new Fraction(numerator, denominator);
            fraction = fraction.shorten();

            BigInteger comp = new BigInteger("9");
            //Testing if the denominator and numerator are greater then 9
            if(fraction.denominator.compareTo(comp) == 1 && fraction.numerator.compareTo(comp) == 1) {
                //Testing if the fraction is an integer
                if(fraction.isInteger() == false) {
                    notInteger = true;
                }
            }
        }
        return fraction.toString();
    }
}
