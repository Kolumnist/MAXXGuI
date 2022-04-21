/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 1 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.Random;

public class Field extends JButton {
    private Random rnd = new Random();
    private int positionX, positionY;
    private BigInteger numerator, denominator;
    public Fraction fieldValue = new Fraction(new BigInteger("0"), new BigInteger("1"));
    private boolean notInteger = false;

    public Field(int pPositionX, int pPositionY) {
        setName(createValue());
        pPositionX = pPositionX;
        positionY = pPositionY;
        setBackground(Color.CYAN);
        setFont(new Font("Serif",1,20));
    }

    public Field(int pPositionX, int pPositionY, Player pPlayer){
        setPlayerOnField(pPlayer);
        pPositionX = pPositionX;
        positionY = pPositionY;
        setBackground(Color.CYAN);
        setFont(new Font("Serif",1,20));
    }

    public void setPlayerOnField(Player pPlayer){
        switch (pPlayer.toString()){
            case "w":
                setName("w");
                setBackground(Color.WHITE);
                break;
            case "b":
                setName("b");
                setBackground(Color.BLACK);
                break;
            case "r":
                setName("r");
                setBackground(Color.RED);
                break;
            case "y":
                setName("y");
                setBackground(Color.YELLOW);
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
}
