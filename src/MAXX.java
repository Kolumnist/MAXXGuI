/**
 * @version 1 20.12.2021
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 */
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MAXX {

    public static int selected = 0; //0-3 First to Last Player
    public static Player[] players;
    public static MainMenu mm; // main menu object

    public int playerCount;

    public MAXX(int playerCount)
    {
        this.playerCount = playerCount;
    }



    public static void main(String[] args)
    {
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
    */

    /*
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

     */

}

