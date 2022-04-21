import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {

    private Fraction value;

    public EventHandler(Fraction value, int bx_pos, int by_pos)
    {
        this.value = value;
    }

    public EventHandler(Player player, int bx_pos, int by_pos)
    {
        this.value = value;
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
            player.player_value.add(this.value);
        }
        else if(player.getPX_pos() == bx_pos-1 && player.getPY_pos() == by_pos+1)
        {
            player.southWest();
            player.player_value.add(this.value);
        }
        else if(player.getPX_pos() == bx_pos-1 && player.getPY_pos() == by_pos-1)
        {
            player.southEast();
            player.player_value.add(this.value);
        }
        else if(player.getPX_pos() == bx_pos+1 && player.getPY_pos() == by_pos-1)
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

        redraw();//drawboard wird aufgerufen um das Feld zu erfrischen
    }
}
