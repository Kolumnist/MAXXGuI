import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventHandler implements PlayerEvent, MouseListener {
    @Override
    public void onButtonClick(Player player, Field field)
    {
        if(player.getX_pos() == field.getX()+1 && player.getY_pos() == field.getY()+1)
        {
            player.northWest();
            player.player_value.add(field.fieldValue);
        }
        else if(player.getX_pos() == field.getX()-1 && player.getY_pos() == field.getY()+1)
        {
            player.southWest();
            player.player_value.add(field.fieldValue);
        }
        else if(player.getX_pos() == field.getX()-1 && player.getY_pos() == field.getY()-1)
        {
            player.southEast();
            player.player_value.add(field.fieldValue);
        }
        else if(player.getX_pos() == field.getX()+1 && player.getY_pos() == field.getY()-1)
        {
            player.northEast();
            player.player_value.add(field.fieldValue);
        }
        else if()
        {
            player.special();
            player.player_value.add(field.fieldValue);
        }
        else
            System.out.println("\n  Das darf deine Figur nicht!");
        //When the player gives something that he cant do
    }

    @Override
    public void onPlayerMoves(Field before, Field after)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        onPlayerMoves((Field)e.getComponent(), (Field)e.getComponent());

        if(player.getX_pos() == ((Field)e.getComponent()).getX()+1 && player.getY_pos() == field.getY()+1)
        {
            player.northWest();
            player.player_value.add(field.fieldValue);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    /** I need a Listener for each Player! and a Listener for the MainMenu!
     */
}
