/**
 * @version 1 20.12.2021
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 */
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MAXX implements MouseListener {

    public static int selected = 0; //0-3 First to Last Player
    public static Player[] players;
    public static MainMenu mm; // main menu object

    public int playerCount;

    public MAXX(int playerCount)
    {
        this.playerCount = playerCount;
    }

    public void mouseReleased(MouseEvent e) {
        //NORTH WEST MOVE
        if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].northWest();///
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);///
        }
        //SOUTH WEST MOVE
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].southWest();///
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);///
        }
        //SOUTH EAST MOVE
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].southEast();///
            players[selected].player_value = players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);///
        }
        //NORTH EAST MOVE
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].northEast();///
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);///
        }
        //SPECIAL MOVE
        else if (( (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() && selected == 2)/*third player*/
                || (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() && selected == 3)/*fourth player*/
                || (players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1 && players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() && selected == 0)/*first player*/
                || (players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1 && players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() && selected == 1))/*second player*/
                && ((Field) e.getComponent()).freeField)
        {
            players[selected].special();///
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);///
            selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);///
        }
        else//When the player gives something that he cant do
            System.out.println("Das darf deine Figur nicht!");

        if (players[selected].player_value.intValue() == 4 / playerCount) {
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            JTextArea win = new JTextArea("Herzlichen Glückwunsch der " + players[selected].toString()
                    + "  Spieler hat mit " + players[selected].player_value.doubleValue() + " Punkten gewonnen!\n");
            panel.add(win);
            f.setSize(300, 300);
            f.add(panel);
            f.setVisible(true);
        }
    }

    public static void main(String[] args)
    {
        players = new Player[]{new Player(2, 2, 'W'), new Player(5, 5, 'B'),
                new Player(5, 2, 'R'), new Player(2, 5, 'Y')};
        mm = new MainMenu(players);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
