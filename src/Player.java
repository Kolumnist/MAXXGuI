/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 30.04.2022
 */

import java.io.Serializable;
import java.math.BigInteger;

public class Player implements Serializable {

    public Fraction player_value;//Value is 0 in the beginning and who has 42 first wins
    public Field player_field;//The field the player is on atm

    private final char name; //for the playing field

    /*Constructor with name as parameter(B or W etc.)*/
    public Player(char name)
    {
        this.name = name;
        player_value = new Fraction(new BigInteger("0"), new BigInteger("1"));
    }

    /*gives only the name of the player as string*/
    public String toString()
    {
        return name+"";
    }

    /*Player gets moved from his Field to the "released" Field if the Field is "available"*/
    public boolean move(Field field) {
        if (field.freeField) {

            //REGULAR Available Fields
            if  (   player_field.getPositionX() == field.getPositionX() + 1 && player_field.getPositionY() == field.getPositionY() + 1 ||
                    player_field.getPositionX() == field.getPositionX() + 1 && player_field.getPositionY() == field.getPositionY() - 1 ||
                    player_field.getPositionX() == field.getPositionX() - 1 && player_field.getPositionY() == field.getPositionY() - 1 ||
                    player_field.getPositionX() == field.getPositionX() - 1 && player_field.getPositionY() == field.getPositionY() + 1  )
            {
                player_value = player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
                field.onPlayerMoves(player_field, this);
                return true;
            }
            //SPECIAL Available Fields
            else if ((player_field.getPositionX() == field.getPositionX() + 1 && player_field.getPositionY() == field.getPositionY() && name == 'R')/*third player*/
                    || (player_field.getPositionX() == field.getPositionX() - 1 && player_field.getPositionY() == field.getPositionY() && name == 'Y')/*fourth player*/
                    || (player_field.getPositionY() == field.getPositionY() + 1 && player_field.getPositionX() == field.getPositionX() && name == 'W')/*first player*/
                    || (player_field.getPositionY() == field.getPositionY() - 1 && player_field.getPositionX() == field.getPositionX() && name == 'B'))/*second player*/
            {
                player_value = player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
                field.onPlayerMoves(player_field, this);//player gets moved to the next field and the next player gets selected
                return true;
            }
            else
            {
                System.out.println("Das darf deine Figur nicht!");
                return false;
            }
        }
        System.out.println("Du darfst nicht auf anderen Spielern stehen!");
        return false;
    }
}