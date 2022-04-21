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
    private int postionX, postionY;
    private BigInteger numerator, denominator;
    private Fraction fieldValue = new Fraction(new BigInteger("0"), new BigInteger("1"));
    private boolean notInteger = false;
<<<<<<< Updated upstream
    private EventHandler button;

    public Field(){
        button = new EventHandler(createValue());
        button.setSize(10, 10);
=======
    //private JButton field;

    public Field(){
        setName(createValue());
        setSize(10, 10);
        setVisible(true);
>>>>>>> Stashed changes
    }

    public void setPlayerOnField(Player pPlayer){
        switch (pPlayer.toString()){
            case "w":
<<<<<<< Updated upstream
                button = new EventHandler("w");
                playerColor = Color.WHITE;
                break;
            case "b":
                button = new EventHandler("b");
                playerColor = Color.BLACK;
                break;
            case "r":
                button = new EventHandler("r");
                playerColor = Color.RED;
                break;
            case "y":
                button = new EventHandler("y");
                playerColor = Color.YELLOW;
=======
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
>>>>>>> Stashed changes
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
