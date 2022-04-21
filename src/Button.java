import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements ActionListener {

    private String value = "";
    private int bx_pos = 0, by_pos = 0;

    public Button(String value, int bx_pos, int by_pos)
    {
        this.bx_pos = bx_pos;
        this.by_pos = by_pos;
        this.setBackground(Color.RED);
        this.setName(value);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Überprüfe ob der Spieler dorthin darf
        //nehme dann den Wert addiere ihn zum Spieler und bewege den Spieler auf das neue Feld
        //Verändere dann den alten Platz des Spielers zu einem Leeren Feld

        Player player = new Player(5, 5, 'M');

        if(player.getPX_pos() == bx_pos+1 && player.getPY_pos() == by_pos+1)
        {
            player.northWest();
        }
        else if(player.getPX_pos() == bx_pos-1 && player.getPY_pos() == by_pos+1)
        {
            player.southWest();
        }
        else if(player.getPX_pos() == bx_pos-1 && player.getPY_pos() == by_pos-1)
        {
            player.northEast();
        }
        else if()

        switch (e.getActionCommand())
        {
            case direction[0]:
                northWest();
                player_value.add(this.getName());
                break;
            case direction[1]:
                northEast();
                player_value.add(this.getName());
                break;
            case direction[2]:
                southWest();
                player_value.add(this.getName());
                break;
            case direction[3]:
                southEast();
                player_value.add(this.getName());
                break;
            case direction[4]:
                special();
                System.out.println("\n\n\n\n\n\n\n\n\n");
                player_value.add(this.getName());
                break;
            default:
                System.out.println("\n  Das darf deine Figur nicht!");
                //When the player gives something that he cant do
        }
        redraw(); //drawboard wird aufgerufen um das Feld zu erfrischen
    }
}
