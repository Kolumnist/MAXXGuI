/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 1 21.04.2022
 */

import java.awt.*;
import java.math.BigInteger;
import java.util.Random;

public class Field {
    private Random rnd = new Random();
    private BigInteger numerator, denominator;
    private Fraction fieldValue = new Fraction(new BigInteger("0"), new BigInteger("1"));
    private Color playerColor;
    private boolean notInteger = false;
    private Button button;

    public Field(){
        button = new Button(createValue());
        button.setSize(10, 10);
    }

    public void setPlayerOnField(Player pPlayer){
        switch (pPlayer.toString()){
            case "w":
                button = new Button("w");
                playerColor = Color.WHITE;
                break;
            case "b":
                button = new Button("b");
                playerColor = Color.BLACK;
                break;
            case "r":
                button = new Button("r");
                playerColor = Color.RED;
                break;
            case "y":
                button = new Button("y");
                playerColor = Color.YELLOW;
                break;
        }
    }

    public String createValue(){
        while (!notInteger){
            numerator = BigInteger.valueOf(rnd.nextInt(10, 1000));
            denominator = BigInteger.valueOf(rnd.nextInt(10, 1000));

            fieldValue = new Fraction(numerator, denominator);
            fieldValue = fieldValue.shorten();

            BigInteger comp = new BigInteger("9");
            //Testing if the denominator and numerator are greater then 9
            if(fieldValue.denominator.compareTo(comp) == 1 && fieldValue.numerator.compareTo(comp) == 1) {
                //Testing if the fraction is an integer
                if(fieldValue.isInteger() == false) {
                    notInteger = true;
                }
            }
        }
        return fieldValue.toString();
    }
    
    public Fraction getFieldValue(){
        return fieldValue;
    }

    public void setFieldValue(Fraction pValue){
        fieldValue = pValue;
    }
}
