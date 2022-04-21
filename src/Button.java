import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements ActionListener {

    public Button(String value)
    {
        this.setBackground(Color.RED);
        this.setName(value);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        //Überprüfe ob der Spieler dorthin darf
        //nehme dann den Wert addiere ihn zum Spieler und bewege den Spieler auf das neue Feld
        //Verändere dann den alten Platz des Spielers zu einem Leeren Feld

        switch (direction)
        {
            case "NW":
                NorthWest();
                player_value.add(this.getName());
                break;
            case "NO":
                NorthEast();
                player_value.add(this.getName());
                break;
            case "SW":
                SouthWest();
                player_value.add(this.getName());
                break;
            case "SO":
                SouthEast();
                player_value.add(this.getName());
                break;
            case "SPECIAL":
                Special();
                System.out.println("\n\n\n\n\n\n\n\n\n");
                player_value.add(this.getName());
                break;
            default:
                System.out.println("\n  Das darf deine Figur nicht!");
                //When the player gives something that he cant do
        }
    }
}
