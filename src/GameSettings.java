
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class GameSettings extends JFrame implements Serializable {

    private static final long serialVersionUID = 1;
    String dateiname;
    GameBoard gameboard;

    // Start-Directory
    JFileChooser c = new JFileChooser();


    // Konstruktor
    public GameSettings() {

        this.gameboard = gameboard;
        this.dateiname = dateiname;

        c.setCurrentDirectory(new File(".\\saveFiles"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.lcm", "lcm");
        c.setFileFilter(filter);
    }

    // ### Object zu GameBoard geändert, damit direkt der richtige Klassentyp da ist
    public void loadGame(GameBoard o) {
        int rVal = c.showOpenDialog(GameSettings.this);                  // Öffne-Dialog öffnen
        if (rVal == JFileChooser.APPROVE_OPTION) {                             // falls bestätigt:
            dateiname = c.getSelectedFile().getAbsolutePath();
            try {
                FileInputStream fileIn = new FileInputStream(dateiname);
                ObjectInputStream stream = new ObjectInputStream(fileIn);
                o = (GameBoard) stream.readObject();
                System.out.println(o.toString());

                // Lässt das Fenster verschwinden
              //  o.dispose(); -> liegt evtl. am JFrame (GameBoard = hat kein JFrame mehr

                // Startet ein neues Spiel
                new GameBoard(o);
                stream.close();
                System.out.println("\n---Game loaded ---\n");
            } catch (Exception e) {
                System.out.println("Serialization Error! Can't load data.\n" + e.getClass() + " : " + e.getMessage() + "\n");
            }
        }
    }

    public void saveGame(GameBoard o) throws IOException {
        // ### Formatierung / Syntax? Error behoben
        int rVal = c.showOpenDialog(GameSettings.this);                  // Öffne-Dialog öffnen
        if (rVal == JFileChooser.APPROVE_OPTION) {                             // falls bestätigt:
            dateiname = c.getSelectedFile().getName();
            try {
                FileOutputStream fileOut = new FileOutputStream(c.getSelectedFile().getAbsolutePath() + ".lcm");
                ObjectOutputStream stream = new ObjectOutputStream(fileOut);

                c.setDialogType(JFileChooser.SAVE_DIALOG);
                stream.writeObject(o);  // es geht nicht, da ich nicht die Datei an sich speicher!! -> was für ein object muss ich übergeben??? -> Datei per UIClass ID finden?
                stream.flush();
                stream.close();
                System.out.println("Game saved!");
            } catch (Exception e) {
                System.out.println("Serialization Error! Can't save data.\n" + e.getClass() + " : " + e.getMessage() + "\n");
            }
        }
    }
}


