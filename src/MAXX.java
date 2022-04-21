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
    public static int playerNumber = 2;  // create a player number -> at the beginn no players are selected so the number is 0
    public static boolean repeat = true; // create a boolean variable
    public static String s;  // create a String named s


    public void mouseClicked(MouseEvent e)
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

        onPlayerMoves((Field)e.getComponent(), (Field)e.getComponent());
    }

    // This method shows the instruction and create a gameboard with 2/ 3 or 4 Players
    public static void main(String[] args) throws Exception {
        System.out.println("Möchten Sie die Anleitung sehen?");
        System.out.println("Bitte geben Sie n/j für nein/ja ein!");
        s = sc.nextLine();
        System.out.println("\n");
        if (!((s.equalsIgnoreCase("n")) || (s.equalsIgnoreCase("j")))) {
            System.out.println("Sie dürfen nur 'n' oder 'j' eingeben! Bitte geben Sie Ihre Anweisung erneut ein: ");
            System.out.println("Möchten Sie die Anleitung sehen?");
            System.out.println("Bitte geben Sie n/j für nein/ja ein!");
            s = sc.nextLine();
            System.out.println("\n");
        }
        switch (s.toLowerCase()) {
            case "j":
                System.out.println(hello());
                System.out.println("Bitte drücken Sie enter um das Spiel zu starten! \n");
                sc.nextLine();
            case "n":

                try{
                    System.out.print("Wie viele Spieler?(2-4): ");
                    setPlayerNumber(sc.nextInt());
                    while (playerNumber > 4 || playerNumber < 2) {
                        System.out.print("Es kann nur 2-4 Spieler geben!");
                        setPlayerNumber(sc.nextInt());
                    }
                    System.out.println("\n");
                }catch(Exception e){
                    System.out.println("Es wurde keine Zahl eingegeben. Das Spiel startet jetzt automatisch mit 2 Spielern");
                }


                Player white;
                Player black;
                Player red;
                Player yellow;
                Player[] playerArr = new Player[playerNumber];

                switch (playerNumber) {
                    case 2:
                        white = new Player(2, 2, 'W');
                        black = new Player(5, 5, 'B');
                        playerArr[0] = white;
                        playerArr[1] = black;
                        break;
                    case 3:
                        white = new Player(2, 2, 'W');
                        black = new Player(5, 5, 'B');
                        red = new Player(5, 2, 'R');
                        playerArr[0] = white;
                        playerArr[1] = black;
                        playerArr[2] = red;
                        break;
                    case 4:
                        white = new Player(2, 2, 'W');
                        black = new Player(5, 5, 'B');
                        red = new Player(5, 2, 'R');
                        yellow = new Player(2, 5, 'Y');
                        playerArr[0] = white;
                        playerArr[1] = black;
                        playerArr[2] = red;
                        playerArr[3] = yellow;
                        break;
                }

                sc.nextLine();
                gb.createBoard(playerArr);
                gb.drawBoard();


                while (repeat) {
                    for (int i = 0; i < playerNumber && repeat; i++) {

                        phase(playerArr[i]);
                    }
                    for (Player value : playerArr) {
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

    // this method reads a String and shows it on the keyboard
    public static String readString(String s) {
        System.out.print(s);
        return sc.nextLine();
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

    // this method shows the instruction
    public static String hello() {
        System.out.println("" + "Hallo! Herzlich willkommen zu 'MAXX'!"
                + "\nIn unserem Spiel können vier Spieler abwechselnd miteinander spielen. Diese Spieler sind als 'black', 'white', 'red' und 'yellow' konfiguriert."
                + "\nAlle Spieler haben einen Spielstein, welchen sie auf dem Spielfeld hin und her bewegen können."
                + "\nAber Achtung!!"
                + "\nDer Spielstein kann nur in 4 bestimmte Richtungen bewegt werden. Nämlich nach: 'NW' 'NO' 'SO' und 'SW'."
                + "\nZudem hat jeder Spieler noch einen special move, welcher dem Schwarzen Spieler erlaubt seinen Spielstein nach rechts zu bewegen, "
                + "dem weißen Spieler ist es erlaubt, seinen nach links zu bewegen, der rote Spieler kann seinen nach oben und der gelbe Spieler seinen nach unten bewegen."
                + "\nDieser move wird unter 'SPECIAL' abgerufen!"
                + "\nGewinner des Spiels ist derjenige, der mehr oder gleich 42 Punkte hat!\n");
        return "";
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
