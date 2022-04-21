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
    public boolean freeField = true;

    //constructor to create a field with a fraction
    public Field(int pPositionX, int pPositionY) {
        String s = createValue();
        setText(s);
        setName(s);
        positionX = pPositionX;
        positionY = pPositionY;
        setBackground(Color.CYAN);
        setFont(new Font("Serif",Font.PLAIN,14));
    }

    //constructor to create a field with a player
    public Field(int pPositionX, int pPositionY, Player pPlayer){
        setPlayerOnField(pPlayer);
        positionX = pPositionX;
        positionY = pPositionY;
        setFont(new Font("Serif",Font.PLAIN,14));
    }

    //sets a player on a button
    public void setPlayerOnField(Player pPlayer){
        switch (pPlayer.toString().trim()){
            case "W":
                setText("W");
                setName("W");
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
                freeField = false;
                break;
            case "B":
                setText("B");
                setName("B");
                setBackground(Color.BLACK);
                setForeground(Color.WHITE);
                freeField = false;
                break;
            case "R":
                setText("R");
                setName("R");
                setBackground(Color.RED);
                setForeground(Color.BLACK);
                freeField = false;
                break;
            case "Y":
                setText("Y");
                setName("Y");
                setBackground(Color.YELLOW);
                setForeground(Color.BLACK);
                freeField = false;
                break;
        }
    }

    //creates a random fraction
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
                if(!fieldValue.isInteger()) {
                    notInteger = true;
                }
            }
        }
        return fieldValue.toString();
    }

    //region Setter & Getter
    public void setPositionX(int pPositionX){
        positionX = pPositionX;
    }

    public void setPositionY(int pPositionY){
        positionY = pPositionY;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }
    //endregion
}
