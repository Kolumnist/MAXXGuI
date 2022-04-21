import java.math.BigInteger;

/*
*
* @version 1 20.12.2021
* @author Michel Jouaux, Collin Hoss, Lara Mangi
*/
public class Player implements PlayerEvent {

    public Fraction player_value;//Value is 0 in the beginning and who has 42 first wins
    public final byte player_ID;//for identification of the characters: 1 = white , 2 = black , 3 = red, 4 = yellow
    private char name; //for the playing field

    private int x_pos = 0, y_pos = 0; //position of the character
    private int x_v, y_v; //vector movement variables

    private static byte identifier = 1; // helps with the identification of a created character

    public Player(int px_pos, int py_pos, char name)//Constructor for Player, it needs a position and a name(black/white etc.)
    {
        this.x_pos = px_pos;
        this.y_pos = py_pos;
        this.name = name;

        player_ID = identifier++;//sets character to identifier and then counts the identifier up
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

    public void special() //Move Action that is special for every player
    {
        switch(player_ID%4)
        {
            case(1):/*first Player*/
                x_v = 0;
                y_v = 1;
                x_pos += x_v;
                y_pos += y_v;
                break;
            case(2):/*second Player*/
                x_v = 0;
                y_v = -1;
                x_pos += x_v;
                y_pos += y_v;
                break;
            case(3):/*third Player*/
                x_v = 1;
                y_v = 0;
                x_pos += x_v;
                y_pos += y_v;
                break;
            case(0):/*fourth Player*/
                x_v = -1;
                y_v = 0;
                x_pos += x_v;
                y_pos += y_v;
                break;
            default:
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

    @Override
    public void onButtonClick(Player player, Field field)
    {
        if(player.getX_pos() == field.getX()+1 && player.getY_pos() == by_pos+1)
        {
            player.northWest();
            player.player_value.add(this.value);
        }
        else if(player.getX_pos() == bx_pos-1 && player.getY_pos() == by_pos+1)
        {
            player.southWest();
            player.player_value.add(this.value);
        }
        else if(player.getX_pos() == bx_pos-1 && player.getY_pos() == by_pos-1)
        {
            player.southEast();
            player.player_value.add(this.value);
        }
        else if(player.getX_pos() == bx_pos+1 && player.getY_pos() == by_pos-1)
        {
            player.northEast();
            player.player_value.add(this.value);
        }
        else if()
        {
            player.special();
            player.player_value.add(this.value);
        }
        else
            System.out.println("\n  Das darf deine Figur nicht!");
        //When the player gives something that he cant do


    }

    @Override
    public void onPlayerMoves(Field before, Field after)
    {
        redraw();//drawboard wird aufgerufen um das Feld zu erfrischen
    }
}