import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

/*
 *
 * @version 1 20.12.2021
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 */
public class MAXX implements MouseListener {

    public static Scanner sc = new Scanner(System.in); // create a scanner, so people can enter a text by the keyboard
    public static GameBoard gb = new GameBoard(8, 8); // gameboard object

    public static int selected = 0; //0-3 First to Last Player
    public static int playerNumber = 2;  // create a player number -> at the beginning there are 2 players selected
    public static Player white;
    public static Player black;
    public static Player red;
    public static Player yellow;
    public static Player[] players = new Player[playerNumber];

    public static boolean repeat = true; // create a boolean variable
    public static String s;  // create a String named s


    public void mouseClicked(MouseEvent e) {
        if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getY() + 1)
        {
            players[selected].northWest();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getY() + 1)
        {
            players[selected].southWest();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getY() - 1)
        {
            players[selected].southEast();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
        }
        else if (players[selected].getX_pos() == ((Field) e.getComponent()).getX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getY() - 1)
        {
            players[selected].northEast();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
        }
        else if ()
        {
            players[selected].special();
            players[selected].player_value.add(((Field) e.getComponent()).fieldValue);
        }
        else
            System.out.println("\n  Das darf deine Figur nicht!");
        //When the player gives something that he cant do

        onPlayerMoves((Field) e.getComponent(), (Field) e.getComponent());
    }

    // This method shows the instruction and create a gameboard with 2/ 3 or 4 Players
    public static void main(String[] args) throws Exception {

        switch (playerNumber) {
            case 2:
                white = new Player(2, 2, 'W');
                black = new Player(5, 5, 'B');
                players[0] = white;
                players[1] = black;
                break;
            case 3:
                white = new Player(2, 2, 'W');
                black = new Player(5, 5, 'B');
                red = new Player(5, 2, 'R');
                players[0] = white;
                players[1] = black;
                players[2] = red;
                break;
            case 4:
                white = new Player(2, 2, 'W');
                black = new Player(5, 5, 'B');
                red = new Player(5, 2, 'R');
                yellow = new Player(2, 5, 'Y');
                players[0] = white;
                players[1] = black;
                players[2] = red;
                players[3] = yellow;
                break;
        }

        sc.nextLine();
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
    public static void phase(Player p) throws InterruptedException {
        s = readString("Spieler" + p.toString() + " gebe einen Befehl für deinen Zug ein.");

        if (s.toUpperCase().trim().equals("STOP") == true) {// Programm closes here
            stop();
        } else {
            repeat = p.walk(s);
            while (!repeat) {
                s = readString("Spieler" + p.toString() + " bitte gebe einen richtigen Befehl ein!");
                repeat = p.walk(s);
            }

            gb.setPlayer(p);
            gb.drawBoard();

            if (p.player_value.intValue() >= (84 / playerNumber)) {
                System.out.println("Herzlichen Glückwunsch der" + p.toString() + "  Spieler hat mit "
                        + p.player_value.doubleValue() + " Punkten gewonnen!");
                repeat = false;
            }
        }
    }

    // this method stops the programm
    public static void stop() throws InterruptedException {
        repeat = false;
        System.out.print("Dieses Programm schließt sich jetzt auf euer Geheiß hin");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(800);
            System.out.print(".");
        }
        System.out.println("\n");
    }

    // this method set an player number
    public static void setPlayerNumber(int pNumber) {
        playerNumber = pNumber;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
