/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class Field extends JButton implements Serializable {
    private Random rnd = new Random();
    private int positionX, positionY;
    private BigInteger numerator, denominator;
    private final BigInteger comp = new BigInteger("9");
    public Fraction fieldValue = new Fraction(new BigInteger("0"), new BigInteger("1"));
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
        positionX = pPositionY;
        positionY = pPositionY;
        setFont(new Font("Serif",Font.PLAIN,14));
    }

    //sets a player on a button
    public void setPlayerOnField(Player pPlayer){
        System.out.println(this.fieldValue + " X: " + positionX + " Y: " + positionY);
        switch (pPlayer.toString()){
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
        do{
            numerator = BigInteger.valueOf(rnd.nextInt(10, 1000));
            denominator = BigInteger.valueOf(rnd.nextInt(10, 1000));

            fieldValue = new Fraction(numerator, denominator);
            fieldValue = fieldValue.shorten();

            //Testing if the denominator and numerator are greater than 9 and if the fraction is an integer
        }while(((fieldValue.denominator.compareTo(comp) != 1) || (fieldValue.numerator.compareTo(comp) != 1)) || (fieldValue.isInteger()));

        return fieldValue.toString();
    }

    //region Setter & Getter
    public void setPosX(int pPositionX){
        positionX = pPositionX;
    }
    public void setPosY(int pPositionY){
        positionY = pPositionY;
    }
    public int getPosX(){
        return positionX;
    }
    public int getPosY(){
        return positionY;
    }
    //endregion
}
