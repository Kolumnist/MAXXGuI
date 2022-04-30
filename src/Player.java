/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
import java.io.Serializable;
import java.math.BigInteger;

public class Player implements Serializable {

    public Fraction player_value;//Value is 0 in the beginning and who has 42 first wins
    public Field player_field;//The field the player is on atm
    private final char name; //for the playing field

    public Player(char name)//Constructor for Player, it needs a position and a name(B or W etc.)
    {
        this.name = name;
        player_value = new Fraction(new BigInteger("0"), new BigInteger("1"));
    }

    public String toString()//gives only the name of the player as string
    {
        return name + "";
    }

    public boolean move(Field field) {
        if (field.freeField) {
            //NORTH WEST FIELD
            if (player_field.getPosX() == field.getPosX() + 1 && player_field.getPosY() == field.getPosY() + 1) {
                player_value = player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
                field.onPlayerMoves(player_field, this);
                return true;
            }
            //SOUTH WEST FIELD
            else if (player_field.getPosX() == field.getPosX() + 1 && player_field.getPosY() == field.getPosY() - 1) {
                player_value = player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
                field.onPlayerMoves(player_field, this);
                return true;
            }
            //SOUTH EAST FIELD
            else if (player_field.getPosX() == field.getPosX() - 1 && player_field.getPosY() == field.getPosY() - 1) {
                player_value = player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
                field.onPlayerMoves(player_field, this);
                return true;
            }
            //NORTH EAST FIELD
            else if (player_field.getPosX() == field.getPosX() - 1 && player_field.getPosY() == field.getPosY() + 1) {
                player_value = player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
                field.onPlayerMoves(player_field, this);
                return true;
            }
            //SPECIAL MOVE
            else if ((player_field.getPosX() == field.getPosX() + 1 && player_field.getPosY() == field.getPosY() && name == 'R')/*third player*/
                    || (player_field.getPosX() == field.getPosX() - 1 && player_field.getPosY() == field.getPosY() && name == 'Y')/*fourth player*/
                    || (player_field.getPosY() == field.getPosY() + 1 && player_field.getPosX() == field.getPosX() && name == 'W')/*first player*/
                    || (player_field.getPosY() == field.getPosY() - 1 && player_field.getPosX() == field.getPosX() && name == 'B'))/*second player*/ {
                player_value = player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
                field.onPlayerMoves(player_field, this);//player gets moved to the next field and the next player gets selected
                return true;
            } else//When the player gives something that he cant do
            {
                System.out.println("Das darf deine Figur nicht!");
                return false;
            }
        }
        System.out.println("Du darfst nicht auf anderen Spielern stehen!");
        return false;
    }
}