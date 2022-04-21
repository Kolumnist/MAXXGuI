import java.math.BigInteger;

/*
*
* @version 1 20.12.2021
* @author Michel Jouaux, Collin Hoss, Lara Mangi
*/
public class Player {

    public Fraction player_value;//Value is 0 in the beginning and who has 42 first wins
    public final byte character;//for identification of the characters: 1 = white , 2 = black , 3 = red, 4 = yellow

    private int x_pos = 0, y_pos = 0; //position of the character
    private int x_v, y_v; //vector movement variables

    private static byte identifier = 1; // helps with the identification of a created character
    private char name; //for the playing field

    public Player(int x_pos, int y_pos, char name)//Constructor for Player, it needs a position and a name(black/white etc.)
    {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.name = name;

        character = identifier++;//sets character to identifier and then counts the identifier up
        player_value = new Fraction(new BigInteger("0"), new BigInteger("1"));
    }

    public String toString()//gives only the name of the player as string
    {
        return " " + name;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public int getX_v() {
        return x_v;
    }

    public int getY_v() {
        return y_v;
    }

    public void resetTurn()
    {
        x_pos -= x_v;
        y_pos -= y_v;
    }

    private void special() //Move Action that is special for every player
    {
        if (character%4 == 1 && x_pos != 7) {/*first Player*/
            x_v = 0;
            y_v = 1;
            x_pos += x_v;
            y_pos += y_v;
        } else if (character%4 == 2 && x_pos != 0) {/*second Player*/
            x_v = 0;
            y_v = -1;
            x_pos += x_v;
            y_pos += y_v;
        } else if (character%4 == 3 && y_pos != 7) {/*third Player*/
            x_v = 1;
            y_v = 0;
            x_pos += x_v;
            y_pos += y_v;
        } else if (character%4 == 0 && y_pos != 0) {/*fourth Player*/
            x_v = -1;
            y_v = 0;
            x_pos += x_v;
            y_pos += y_v;
        }
        else{
            x_v = 0;
            y_v = 0;
            x_pos += x_v;
            y_pos += y_v;
        }
    }

    public void northWest() /*Northwest movement*/
    {
        x_v = -1;
        y_v = -1;
        x_pos += x_v;
        y_pos += y_v;
    }

    public void northEast() /*Northeast movement*/
    {
        x_v = -1;
        y_v = 1;
        x_pos += x_v;
        y_pos += y_v;
    }

    public void southWest() /*Southwest movement*/
    {
        x_v = 1;
        y_v = -1;
        x_pos += x_v;
        y_pos += y_v;
    }

    public void southEast() /*Southeast movement*/
    {
        x_v = 1;
        y_v = 1;
        x_pos += x_v;
        y_pos += y_v;
    }
}