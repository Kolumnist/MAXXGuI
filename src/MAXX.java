/**
 * @version 1 20.12.2021
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MAXX implements MouseListener {

    public static int selected = 0; //0-3 First to Last Player
    public static Player[] players;
    public static MainMenu mm; // gameboard object

    public void mouseReleased(MouseEvent e) {

        if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].northWest();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].southWest();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected );
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].southEast();
            players[selected].player_value = players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1
                && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].northEast();///
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected);///
        }
        /*else if (players[selected].special() && ((Field) e.getComponent()).freeField)
        {
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            players[selected].onPlayerMoves((Field) e.getComponent(), (Field) e.getComponent(), selected);///
        }*/
        else//When the player gives something that he cant do
            System.out.println("Das darf deine Figur nicht!");
    }

    public static void main(String[] args)
    {
        players = new Player[]{new Player(2, 2, 'W'), new Player(5, 5, 'B'),
                new Player(5, 2, 'Y'), new Player(2, 5, 'R')};
        mm = new MainMenu(players);
    }
    /*if (p.player_value.intValue() >= (84 / playerNumber)) {
        System.out.println("Herzlichen Glückwunsch der" + p.toString() + "  Spieler hat mit " + p.player_value.doubleValue() + " Punkten gewonnen!");
        repeat = false;
        }

         for (Player value : players) {
                System.out.println("Spieler: " + value.toString() + " hat " + value.player_value + " Punkte.");
            }

         ("Spieler" + p.toString() + " gebe einen Befehl für deinen Zug ein.");
    //System.out.print("Dieses Programm schließt sich jetzt auf euer Geheiß hin");
    */

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
