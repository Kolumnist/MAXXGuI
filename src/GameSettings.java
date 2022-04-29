import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameSettings extends JFrame implements Serializable {

    String dateiname;

    // Textfelder für
    JTextField filename = new JTextField();   // Dateinamen
    JTextField dir = new JTextField();        // Directory
    JTextField exits = new JTextField();      // Existenz-Abfrage
    JTextField isdir = new JTextField();      // Directory-Abfrage


    // Knöpfe für Öffnen und Speichern
    JButton open = new JButton("Öffnen");
    JButton save = new JButton("Speichern");

    // Start-Directory
    JFileChooser c = new JFileChooser(new File("C://Users//laraj//OneDrive//Dokumente//MAXXGuI//neu"));

    // Konstruktor
    public GameSettings() {
        setTitle("Save Game");               // Fenster Titel
        Container cp = getContentPane();     // Fenster-Container
        open.addActionListener(new OpenL()); // AL registrieren
        cp.add(open, BorderLayout.NORTH);    //    und einfügen
        save.addActionListener(new SaveL());
        cp.add(save, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        exits.setEditable(false);
        isdir.setEditable(false);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1, 2, 2)); // Gridlayout für Textfelder-Panel
        p.add(filename);                                             // Feld filename hinzufügen
        p.add(dir);                                                  // Feld dir hinzufügen
        p.add(exits);                                                // Feld exits hinzufügen
        p.add(isdir);                                                // Feld isdir hinzufügen
        cp.add(p, BorderLayout.CENTER);
        c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  // Sel.-Modus
        setVisible(true);
        setSize(500, 500);
    }


    class OpenL implements ActionListener {                                         // AL. für Öfnnen Knopf

        public void actionPerformed(ActionEvent e) {
            int rVal = c.showOpenDialog(GameSettings.this);                  // Öffne-Dialog öffnen
            if (rVal == JFileChooser.APPROVE_OPTION) {// falls bestätigt:
                dateiname = c.getSelectedFile().getName();
                filename.setText("Filename: " + dateiname);
                dir.setText("Akt.Directory: " + c.getCurrentDirectory());
                exits.setText("Existiert? " + c.getSelectedFile().exists());
                isdir.setText("Ist Directory? " + c.getSelectedFile().isDirectory());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {                              // falls abgebrochen:
                filename.setText("Es wurde Abbrechen gedrückt!");
                dir.setText("");
                exits.setText("");                                                 // Texte löschen
                isdir.setText("");
            }
            loadGame(new Object());
        }

        public void loadGame(Object o) {
            try {
                FileInputStream fileIn = new FileInputStream(dateiname);
                ObjectInputStream stream = new ObjectInputStream(fileIn);
                o = stream.readObject();
                stream.close();
                System.out.println("\n---Game loaded ---\n");
            } catch (Exception e) {
                System.out.println("Serialization Error! Can't load data.\n" + e.getClass() + " : " + e.getMessage() + "\n");
            }
        }
    }

    class SaveL implements ActionListener {                                        // AL für Speichern-Knopf

        public void actionPerformed(ActionEvent e) {
            int rVal = c.showSaveDialog(GameSettings.this);                     // Speichern-Dialog öffnen
            if (rVal == JFileChooser.APPROVE_OPTION) {// falls bestätigt:
                dateiname = c.getSelectedFile().getName();
                filename.setText("Filename: " + dateiname);
                dir.setText("Akt. Dictory: " + c.getCurrentDirectory());
                exits.setText("Existiert? " + c.getSelectedFile().exists());
                isdir.setText("Ist Directory? " + c.getSelectedFile().isDirectory());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {                              // falls abgebrochen:
                filename.setText("Es wurde Abbrechen gedrückt!");
                dir.setText("");
                exits.setText("");
                isdir.setText("");
            }
            try {
                saveGame(new Object());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        public void saveGame(Object o) throws IOException {
            try {
                FileOutputStream fileOut = new FileOutputStream(dateiname);
                ObjectOutputStream stream = new ObjectOutputStream(fileOut);
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