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
    public final byte player_ID;//for identification of the characters: 1 = white , 2 = black , 3 = red, 4 = yellow
    public Field players_field;//The field the player is on atm

    private char name; //for the playing field
    private static byte identifier = 1; // helps with the identification of a created character

    private int x_pos = 0, y_pos = 0; //position of the character
    private int x_v, y_v; //vector movement variables

    public Player(int x_pos, int y_pos, char name)//Constructor for Player, it needs a position and a name(B or W etc.)
    {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.name = name;

        player_ID = identifier++;//sets character to identifier and then counts the identifier up
        if(identifier == 5)
        {
            identifier = 1;
        }
        player_value = new Fraction(new BigInteger("0"), new BigInteger("1"));
    }

    public String toString()//gives only the name of the player as string
    {
        return name+"";
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void special() //Move Action that is special for every player
    {
        switch(player_ID)
        {
            case(1):/*first Player*/
                x_v = 0; y_v = -1;
                x_pos += x_v; y_pos += y_v;
                break;
            case(2):/*second Player*/
                x_v = 0; y_v = 1;
                x_pos += x_v; y_pos += y_v;
                break;
            case(3):/*third Player*/
                x_v = -1; y_v = 0;
                x_pos += x_v; y_pos += y_v;
                break;
            case(4):/*fourth Player*/
                x_v = 1; y_v = 0;
                x_pos += x_v; y_pos += y_v;
                break;
            default:
                x_v = 0; y_v = 0;
                x_pos += x_v; y_pos += y_v;
        }
    }

    public void northWest() /*Northwest movement*/
    {
        x_v = -1; y_v = -1;
        x_pos += x_v; y_pos += y_v;
    }
    public void northEast() /*Northeast movement*/
    {
        x_v = 1; y_v = -1;
        x_pos += x_v; y_pos += y_v;
    }
    public void southWest() /*Southwest movement*/
    {
        x_v = -1; y_v = 1;
        x_pos += x_v; y_pos += y_v;
    }
    public void southEast() /*Southeast movement*/
    {
        x_v = 1; y_v = 1;
        x_pos += x_v; y_pos += y_v;
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

        System.out.println(this + " X: " + x_pos + " Y: " + y_pos);

        switch(playerCount)//checks if, depending on the playerCount, the selected value is at its max or not
        {
            case 2: selected = ((selected+1) % 2)-1; break;
            case 3: selected = ((selected+1) % 3)-1; break;
            case 4: selected = ((selected+1) % 4)-1; break;
            default: selected = -1; break;
        }
        //Put this in the Button thing with a boolean instead of the two parameters

        //where the player is now
        this.players_field = after;
        this.players_field.setPlayerOnField(this);

        return ++selected;
    }
}