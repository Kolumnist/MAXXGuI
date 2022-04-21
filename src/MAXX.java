import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * @version 1 20.12.2021
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 */

public class MAXX implements MouseListener {

    public static boolean repeat = true; // create a boolean variable
    public static String s;  // create a String named s
    public static int selected = 0; //0-3 First to Last Player

    public static Player[] players = {new Player(2, 2, 'W'), new Player(5, 5, 'B'),
                                      new Player(5, 2, 'R'), new Player(2, 5, 'Y')};

    public static MainMenu mm = new MainMenu(players); // gameboard object

    public void mouseClicked(MouseEvent e) {
        if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() + 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getY() + 1)
        {
            players[selected].northWest();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
            players[selected].onPlayerMoves((Field) e.getComponent(), (Field) e.getComponent(), selected);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() - 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getY() + 1)
        {
            players[selected].southWest();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
            players[selected].onPlayerMoves((Field) e.getComponent(), (Field) e.getComponent(), selected);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() - 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getY() - 1)
        {
            players[selected].southEast();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
            players[selected].onPlayerMoves((Field) e.getComponent(), (Field) e.getComponent(), selected);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() + 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getY() - 1)
        {
            players[selected].northEast();///
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            players[selected].onPlayerMoves((Field) e.getComponent(), (Field) e.getComponent(), selected);///
        }
        else if (players[selected].special()) //checks if the special action is allowed
        {
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            players[selected].onPlayerMoves((Field) e.getComponent(), (Field) e.getComponent(), selected);///
        }
        else//When the player gives something that he cant do
            System.out.println("Das darf deine Figur nicht!");
    }

    public static void main(String[] args) throws Exception {

        gb.createBoard(players);
        gb.drawBoard();

        while (repeat) {
            for (int i = 0; i < playerNumber && repeat; i++) {
                phase(players[i]);
            }
            for (Player value : players) {
                System.out.println("Spieler: " + value.toString() + " hat " + value.player_value + " Punkte.");
            }
        }
    }

}

    // this method organizate the whole game history: With this method a player can walk/ he can stop the game or the method prints the victory of an player
    public static void phase(Player p) throws InterruptedException
    {
        s = readString("Spieler" + p.toString() + " gebe einen Befehl für deinen Zug ein.");
    }

    /*if (p.player_value.intValue() >= (84 / playerNumber)) {
        System.out.println("Herzlichen Glückwunsch der" + p.toString() + "  Spieler hat mit " + p.player_value.doubleValue() + " Punkten gewonnen!");
        repeat = false;
        }
    //System.out.print("Dieses Programm schließt sich jetzt auf euer Geheiß hin");
    */

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
