import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventHandler implements MouseListener {


    /** I need a Listener for each Player! and a Listener for the MainMenu!
     */

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Überprüfe ob der Spieler dorthin darf
        //nehme dann den Wert addiere ihn zum Spieler und bewege den Spieler auf das neue Feld
        //Verändere dann den alten Platz des Spielers zu einem Leeren Feld

        Player player = new Player(5, 5, 'M');
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        Player player = new Player(5, 5, 'M');

        player.onPlayerMoves((Field)e.getComponent(), (Field)e.getComponent());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}


    @Override
    public void mouseExited(MouseEvent e) {}
}
