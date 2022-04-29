/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
import java.awt.*;
import java.io.Serializable;
import java.math.BigInteger;

public class Player implements Serializable {

    public Fraction player_value;//Value is 0 in the beginning and who has 42 first wins
    public Field player_field;//The field the player is on atm
    public Field[] free_fields = new Field[5];

    private final char name; //for the playing field

    public Player(char name)//Constructor for Player, it needs a position and a name(B or W etc.)
    {
        this.name = name;
        player_value = new Fraction(new BigInteger("0"), new BigInteger("1"));
    }

    public String toString()//gives only the name of the player as string
    {
        return name+"";
    }

    public int move(Field field, int selected, int playerCount)
    {
        if (field != this.player_field)
        {
            this.player_value = this.player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
            selected = this.onPlayerMoves(this.player_field, field, selected, playerCount);
        }
        //SPECIAL MOVE
        else if (((this.player_field.getPositionX() == field.getPositionX() + 1 && this.player_field.getPositionY() == field.getPositionY() && selected == 2)/*third player*/
                || (this.player_field.getPositionX() == field.getPositionX() - 1 && this.player_field.getPositionY() == field.getPositionY() && selected == 3)/*fourth player*/
                || (this.player_field.getPositionY() == field.getPositionY() + 1 && this.player_field.getPositionX() == field.getPositionX() && selected == 0)/*first player*/
                || (this.player_field.getPositionY() == field.getPositionY() - 1 && this.player_field.getPositionX() == field.getPositionX() && selected == 1))/*second player*/
                && field.freeField)
        {
            this.player_value = this.player_value.add(field.fieldValue);//adds the fieldvalue to the playervalue
            selected = this.onPlayerMoves(this.player_field, field, selected, playerCount);//player gets moved to the next field and the next player gets selected
        } else//When the player gives something that he cant do
            System.out.println("Das darf deine Figur nicht!");

        return selected;
    }


    /*It happens when the player is moving from one field to another and handles this action accordingly*/
    public int onPlayerMoves(Field before, Field after, int selected, int playerCount)
    {
        //Where the player was
        before.freeField = true;
        before.setBackground(Color.cyan);
        before.setText("x");
        before.setName("x");
        before.fieldValue = new Fraction(new BigInteger("0"), new BigInteger("1"));

        //System.out.println(this + " X: " + x_pos + " Y: " + y_pos + " VALUE: " + player_value.intValue());

        //Put this in the Button thing with a boolean instead of the two parameters

        //where the player is now
        this.player_field = after;
        this.player_field.setPlayerOnField(this);

        switch(playerCount)//checks if, depending on the playerCount, the selected value is at its max or not
        {
            case 2: selected = ((selected+1) % 2)-1; break;
            case 3: selected = ((selected+1) % 3)-1; break;
            case 4: selected = ((selected+1) % 4)-1; break;
            default: selected = -1; break;
        }
        return ++selected;
    }

}